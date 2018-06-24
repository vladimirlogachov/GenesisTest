package com.takeiteasy.vip.genesistest.usecase

import com.facebook.Profile
import com.takeiteasy.vip.genesistest.data.repository.contract.ProfileRepository
import io.reactivex.Single

class ProfileUseCase(
        private val profileRepository: ProfileRepository
) {
    fun loadUser(): Single<Profile>
            = profileRepository.loadUserProfile()
}