package com.takeiteasy.vip.genesistest.data.repository

import com.facebook.Profile
import com.takeiteasy.vip.genesistest.domain.repository.ProfileRepository
import io.reactivex.Single

class ProfileRepositoryImpl : ProfileRepository {
    override fun loadUserProfile(): Single<Profile> {
        return Single.just(Profile.getCurrentProfile())
    }

}