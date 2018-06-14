package com.takeiteasy.vip.genesistest.presentation.router

import android.content.Context
import android.content.Intent
import com.takeiteasy.vip.genesistest.presentation.ui.movie.MoviesActivity

class ActivityRouter {
    companion object {
        const val PLAIN_TEXT = "text/plain"
    }

    fun startMoviesActivity(context: Context) {
        val intent = Intent(context, MoviesActivity::class.java)
        context.startActivity(intent)
    }

    fun requestSharing(context: Context?, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.type = PLAIN_TEXT
        context?.startActivity(intent)
    }
}