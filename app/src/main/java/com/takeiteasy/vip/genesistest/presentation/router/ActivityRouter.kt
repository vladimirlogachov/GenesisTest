package com.takeiteasy.vip.genesistest.presentation.router

import android.content.Context
import android.content.Intent
import com.takeiteasy.vip.genesistest.presentation.ui.movie.MoviesActivity

class ActivityRouter {
    fun startMoviesActivity(context: Context) {
        val intent = Intent(context, MoviesActivity::class.java)
        context.startActivity(intent)
    }
}