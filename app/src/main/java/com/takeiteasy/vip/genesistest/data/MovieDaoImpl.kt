package com.takeiteasy.vip.genesistest.data

import android.arch.persistence.room.*
import com.takeiteasy.vip.genesistest.domain.db.MovieDao
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MovieDaoImpl : MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(movies: List<MovieEntity>)

    @Query("UPDATE movies SET favorite = 1 WHERE id = :id")
    fun addToFavorite(id: Int)

    @Query("UPDATE movies SET favorite = 0 WHERE id = :id")
    fun removeFromFavorite(id: Int)

    @Query("SELECT * FROM movies WHERE favorite = 1")
    fun findFavoriteMovies(): Single<List<MovieEntity>>

    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAllMovies(): Single<List<MovieEntity>>
}