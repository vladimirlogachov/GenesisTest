package com.takeiteasy.vip.genesistest.data

import android.content.Intent
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.takeiteasy.vip.genesistest.domain.repository.LoginRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleEmitter

class LoginRepositoryImpl(
        private val callbackManager: CallbackManager,
        private val loginManager: LoginManager
) : LoginRepository {

    override fun handleResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun isLoggedIn(): Single<Boolean> {
        return Single.create{ emitter: SingleEmitter<Boolean>
            -> emitter.onSuccess(Profile.getCurrentProfile() != null)
        }
    }

    override fun registerProfileCallback(): Completable {
        return Completable.create { emitter ->
            if (!emitter.isDisposed) {
                loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        emitter.onComplete()
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        if (!emitter.isDisposed) {
                            error?.let { emitter.onError(it) }
                        }
                    }

                })
            }
        }
    }
}