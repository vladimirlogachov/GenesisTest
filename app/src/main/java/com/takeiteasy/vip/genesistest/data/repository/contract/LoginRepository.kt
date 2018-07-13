package com.takeiteasy.vip.genesistest.data.repository.contract

import android.content.Intent
import io.reactivex.Completable
import io.reactivex.Single

interface LoginRepository {
    fun handleResult(requestCode: Int, resultCode: Int, data: Intent)
    fun isLoggedIn(): Single<Boolean>
    fun registerProfileCallback(): Completable
}