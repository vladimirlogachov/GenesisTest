package com.takeiteasy.vip.genesistest.data.repository.contract

import com.facebook.Profile
import io.reactivex.Single

interface ProfileRepository {
    fun loadUserProfile(): Single<Profile>
}