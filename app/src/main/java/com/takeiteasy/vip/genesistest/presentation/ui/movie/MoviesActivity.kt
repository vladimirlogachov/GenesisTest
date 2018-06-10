package com.takeiteasy.vip.genesistest.presentation.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.Profile
import com.takeiteasy.vip.genesistest.R
import com.takeiteasy.vip.genesistest.presentation.common.BaseActivity
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : BaseActivity(), ProfileContract.ProfileView {

    @Inject
    lateinit var presenter: ProfileContract.ProfilePresenter

    private var pictureSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        setSupportActionBar(toolbar)
        title = null

        pictureSize = resources.getDimensionPixelSize(R.dimen.pictureSize)

        pager.adapter = NavigationAdapter(supportFragmentManager, resources.getStringArray(R.array.PageTitles))
        navigation.setupWithViewPager(pager)

        presenter.attachView(this)
        presenter.loadProfile()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showProfile(profile: Profile) {
        title = profile.name
        Glide.with(this)
                .load(profile.getProfilePictureUri(pictureSize, pictureSize))
                .apply(RequestOptions.circleCropTransform())
                .into(image)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    override fun showError(error: String) {
        displayMessageToast(error)
    }
}
