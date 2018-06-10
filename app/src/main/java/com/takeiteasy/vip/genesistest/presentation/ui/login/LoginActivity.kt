package com.takeiteasy.vip.genesistest.presentation.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.takeiteasy.vip.genesistest.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {

    @Inject
    lateinit var presenter: LoginContract.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attachView(this)

        presenter.registerLoginCallback()
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
        Log.d("TAG", "loginSuccess(): ")
    }

    override fun loginFailure() {
        Log.d("TAG", "loginFailure(): ")
    }

    override fun showProgress(show: Boolean) {

    }

    override fun showError(error: String) {
        Log.d("TAG", "showError(): $error")
    }
}
