package com.takeiteasy.vip.genesistest.domain.usecase

import com.facebook.Profile
import com.takeiteasy.vip.genesistest.domain.repository.ProfileRepository
import io.reactivex.Single

class ProfileUseCase(
        private val profileRepository: ProfileRepository
) {
    fun loadUser(): Single<Profile> = profileRepository.loadUserProfile()
}