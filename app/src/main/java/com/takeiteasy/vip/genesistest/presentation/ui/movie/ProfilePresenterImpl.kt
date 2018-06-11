package com.takeiteasy.vip.genesistest.presentation.ui.movie

import com.facebook.Profile
import com.takeiteasy.vip.genesistest.domain.usecase.ProfileUseCase
import com.takeiteasy.vip.genesistest.presentation.mvp.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProfilePresenterImpl(
      private val useCase: ProfileUseCase
) : Presenter<ProfileContract.ProfileView>(), ProfileContract.ProfilePresenter {
    override fun loadProfile() {
        disposable.add(
                useCase.loadUser()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { getView()?.showProgress(true) }
                        .doFinally { getView()?.showProgress(false) }
                        .subscribeWith(object : DisposableSingleObserver<Profile>() {
                            override fun onSuccess(t: Profile) {
                                getView()?.showProfile(t)
                            }

                            override fun onError(e: Throwable) {
                                getView()?.showError(e.localizedMessage)
                            }

                        })
        )
    }
}