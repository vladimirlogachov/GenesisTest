package com.takeiteasy.vip.genesistest.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.takeiteasy.vip.genesistest.data.db.dao.MovieDaoImpl
import com.takeiteasy.vip.genesistest.data.db.entity.MovieEntity

fun createMovieDao(context: Context): MovieDaoImpl {
    return Room.databaseBuilder(context, NoteDatabase::class.java, "movies_db")
            .fallbackToDestructiveMigration()
            .build().movieDao()
}

@Database(entities = [(MovieEntity::class)], version = 2, exportSchema = false)
internal abstract class NoteDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDaoImpl
}