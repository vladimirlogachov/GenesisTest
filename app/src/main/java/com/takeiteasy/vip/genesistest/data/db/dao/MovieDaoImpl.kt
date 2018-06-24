package com.takeiteasy.vip.genesistest.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.takeiteasy.vip.genesistest.data.db.entity.MovieEntity
import io.reactivex.Single

@Dao
interface MovieDaoImpl : MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrUpdate(movies: List<MovieEntity>)

    @Query("UPDATE movies SET favorite = 1 WHERE id = :id")
    fun addToFavorite(id: Int)

    @Query("UPDATE movies SET favorite = 0 WHERE id = :id")
    fun removeFromFavorite(id: Int)

    @Query("SELECT * FROM movies WHERE favorite = 1")
    fun findFavoriteMovies(): Single<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun getAllMovies(): Single<List<MovieEntity>>
}