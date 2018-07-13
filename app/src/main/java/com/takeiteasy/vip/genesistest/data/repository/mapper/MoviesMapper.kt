package com.takeiteasy.vip.genesistest.data.repository.mapper

import com.takeiteasy.vip.genesistest.data.db.entity.MovieEntity
import com.takeiteasy.vip.genesistest.data.network.model.Movie

class MoviesMapper : Mapper<MovieEntity, Movie> {
    override fun toModel(entity: MovieEntity): Movie {
        return Movie(entity.id, entity.title, entity.overview, entity.releaseDate, entity.posterPath, entity.isFavorite)
    }

    override fun toEntity(model: Movie): MovieEntity {
        return MovieEntity(model.id, model.title, model.overview, model.releaseDate, model.posterPath, model.isFavorite)
    }
}