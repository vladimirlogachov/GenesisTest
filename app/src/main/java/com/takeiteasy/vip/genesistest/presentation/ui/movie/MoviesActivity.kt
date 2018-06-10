package com.takeiteasy.vip.genesistest.presentation.ui.movie

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.takeiteasy.vip.genesistest.R

import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        setSupportActionBar(toolbar)


        pager.adapter = NavigationAdapter(supportFragmentManager, resources.getStringArray(R.array.PageTitles))
    }

}
