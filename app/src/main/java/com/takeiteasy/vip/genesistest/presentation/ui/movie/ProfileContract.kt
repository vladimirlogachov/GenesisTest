package com.takeiteasy.vip.genesistest.presentation.ui.movie

import com.facebook.Profile
import com.takeiteasy.vip.genesistest.presentation.mvp.IPresenter
import com.takeiteasy.vip.genesistest.presentation.mvp.IView

interface ProfileContract {
    interface ProfileView : IView {
        fun showProfile(profile: Profile)
    }

    interface ProfilePresenter : IPresenter<ProfileView> {
        fun loadProfile()
    }
}