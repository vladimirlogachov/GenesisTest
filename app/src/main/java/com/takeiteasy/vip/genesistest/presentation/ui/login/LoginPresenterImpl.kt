package com.takeiteasy.vip.genesistest.presentation.ui.login

import android.content.Intent
import com.takeiteasy.vip.genesistest.usecase.LoginUseCase
import com.takeiteasy.vip.genesistest.presentation.mvp.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class LoginPresenterImpl (
        private val useCase: LoginUseCase
) : Presenter<LoginContract.LoginView>(), LoginContract.LoginPresenter {

    override fun handleResult(requestCode: Int, resultCode: Int, data: Intent) {
        useCase.handleResult(requestCode, resultCode, data)
    }

    override fun checkLoginState() {
        disposable.add(
                useCase.isLoggedIn()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { getView()?.showProgress(true) }
                        .doFinally { getView()?.showProgress(false) }
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
                useCase.registerCallback()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableCompletableObserver() {
                            override fun onComplete() {
                                getView()?.loginSuccess()
                            }


                            override fun onError(e: Throwable) {
                                getView()?.showError(e.localizedMessage)
                            }

                        })
        )
    }
}