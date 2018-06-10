package com.takeiteasy.vip.genesistest.domain.repository

import com.facebook.Profile

interface UserRepository {
    fun saveUserProfile(profile: Profile)
    fun readUserProfile(): Profile
}