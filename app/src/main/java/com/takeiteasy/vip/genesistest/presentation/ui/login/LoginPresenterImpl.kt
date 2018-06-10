package com.takeiteasy.vip.genesistest.presentation.ui.login

import android.content.Intent
import com.takeiteasy.vip.genesistest.domain.usecase.LoginUseCase
import com.takeiteasy.vip.genesistest.presentation.mvp.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class LoginPresenterImpl (
        private val loginUseCase: LoginUseCase
) : Presenter<LoginContract.LoginView>(), LoginContract.LoginPresenter {

    override fun handleResult(requestCode: Int, resultCode: Int, data: Intent) {
        loginUseCase.handleResult(requestCode, resultCode, data)
    }

    override fun checkLoginState() {
        disposable.add(
                loginUseCase.isLoggedIn()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {b: Boolean ->
                            if (b) {
                                getView()?.loginSuccess()
                            } else {
                                getView()?.loginFailure()
                            }
                        }
        )
    }

    override fun registerLoginCallback() {
        disposable.add(
                loginUseCase.registerCallback()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { getView()?.showProgress(true) }
                        .doFinally { getView()?.showProgress(false) }
                        .subscribeWith(object : DisposableCompletableObserver() {
                            override fun onComplete() {
                                getView()?.loginSuccess()
                            }


                            override fun onError(e: Throwable) {
                                getView()?.showError(e.localizedMessage)
                                getView()?.loginFailure()
                            }

                        })
        )
    }
}