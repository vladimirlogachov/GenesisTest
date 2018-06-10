package com.takeiteasy.vip.genesistest.presentation.ui.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.takeiteasy.vip.genesistest.R


class FavoriteMoviesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FavoriteMoviesFragment()
    }
}