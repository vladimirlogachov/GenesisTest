package com.takeiteasy.vip.genesistest.presentation.ui.login

import com.takeiteasy.vip.genesistest.presentation.mvp.IPresenter
import com.takeiteasy.vip.genesistest.presentation.mvp.IView

interface LoginContract {
    interface LoginIView : IView {
        fun onSuccess()
        fun onCancel()
    }

    interface LoginPresenter {
        fun isLoggedIn(): Boolean
        fun registerLoginCallback()
    }
}