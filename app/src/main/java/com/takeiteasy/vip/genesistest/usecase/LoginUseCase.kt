package com.takeiteasy.vip.genesistest.usecase

import android.content.Intent
import com.takeiteasy.vip.genesistest.data.repository.contract.LoginRepository
import io.reactivex.Completable
import io.reactivex.Single

class LoginUseCase(
        private val repository: LoginRepository
) {
    fun handleResult(requestCode: Int, resultCode: Int, data: Intent) {
        repository.handleResult(requestCode, resultCode, data)
    }

    fun isLoggedIn(): Single<Boolean>
            = repository.isLoggedIn()

    fun registerCallback(): Completable
            = repository.registerProfileCallback()
}