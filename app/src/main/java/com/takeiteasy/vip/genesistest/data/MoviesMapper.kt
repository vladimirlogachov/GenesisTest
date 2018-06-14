package com.takeiteasy.vip.genesistest.data

import com.takeiteasy.vip.genesistest.domain.model.Movie
import com.takeiteasy.vip.genesistest.domain.repository.Mapper

class MoviesMapper : Mapper<MovieEntity, Movie> {
    override fun toModel(entity: MovieEntity): Movie {
        return Movie(entity.id, entity.title, entity.overview, entity.releaseDate, entity.posterPath, entity.isFavorite)
    }

    override fun toEntity(model: Movie): MovieEntity {
        return MovieEntity(model.id, model.title, model.overview, model.releaseDate, model.posterPath, model.isFavorite)
    }
}