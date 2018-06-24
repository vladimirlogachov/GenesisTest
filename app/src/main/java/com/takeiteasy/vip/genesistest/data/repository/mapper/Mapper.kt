package com.takeiteasy.vip.genesistest.data.repository.mapper

interface Mapper<E, M> {
    fun toModel(entity: E): M
    fun toEntity(model: M): E
}