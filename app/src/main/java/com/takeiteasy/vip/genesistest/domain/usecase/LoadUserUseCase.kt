package com.takeiteasy.vip.genesistest.domain.usecase

import com.facebook.Profile
import com.takeiteasy.vip.genesistest.domain.repository.UserRepository
import io.reactivex.Single

class LoadUserUseCase(
        private val userRepository: UserRepository
) {
    fun loadUser(): Single<Profile> = userRepository.loadUserProfile()
}