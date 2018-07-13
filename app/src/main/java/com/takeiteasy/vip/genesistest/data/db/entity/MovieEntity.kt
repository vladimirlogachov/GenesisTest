package com.takeiteasy.vip.genesistest.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "overview")
        val overview: String,
        @ColumnInfo(name = "release_date")
        val releaseDate: String,
        @ColumnInfo(name = "poste_path")
        val posterPath: String?,
        @ColumnInfo(name = "favorite")
        val isFavorite: Boolean
)