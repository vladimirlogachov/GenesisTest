package com.takeiteasy.vip.genesistest.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.presentation.common.BaseActivity
import com.takeiteasy.vip.genesistest.presentation.router.ActivityRouter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.LoginView {

    @Inject
    lateinit var presenter: LoginContract.LoginPresenter
    @Inject
    lateinit var router: ActivityRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attachView(this)

        presenter.checkLoginState()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let { presenter.handleResult(requestCode, resultCode, it) }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun loginSuccess() {
        router.startMoviesActivity(this)
        finish()
    }

    override fun loginFailure() {
        presenter.registerLoginCallback()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    override fun showError(error: String) {
        displayMessageToast(error)
    }
}
