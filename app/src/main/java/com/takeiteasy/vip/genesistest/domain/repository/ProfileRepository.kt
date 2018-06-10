package com.takeiteasy.vip.genesistest.domain.repository

import com.facebook.Profile
import io.reactivex.Single

interface ProfileRepository {
    fun loadUserProfile(): Single<Profile>
}