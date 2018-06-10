package com.takeiteasy.vip.genesistest.presentation.ui.movie

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.takeiteasy.vip.genesistest.presentation.ui.movie.favorite.FavoriteMoviesFragment
import com.takeiteasy.vip.genesistest.presentation.ui.movie.ongoing.OngoingMoviesFragment

class NavigationAdapter(fm: FragmentManager, private val titles: Array<String>) : FragmentPagerAdapter(fm) {

    companion object {
        const val PAGES_COUNT: Int = 2
    }

    private val TITLES: List<String> = listOf()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> FavoriteMoviesFragment.newInstance()
            else -> {
                OngoingMoviesFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return PAGES_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}