package com.takeiteasy.vip.genesistest.presentation.ui.login

import android.content.Intent
import com.takeiteasy.vip.genesistest.presentation.mvp.IPresenter
import com.takeiteasy.vip.genesistest.presentation.mvp.IView

interface LoginContract {
    interface LoginView : IView {
        fun loginSuccess()
        fun loginFailure()
    }

    interface LoginPresenter : IPresenter<LoginView> {
        fun handleResult(requestCode: Int, resultCode:Int, data: Intent)
        fun checkLoginState()
        fun registerLoginCallback()
    }
}