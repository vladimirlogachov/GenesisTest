package com.takeiteasy.vip.genesistest.domain.repository

interface Mapper<E, M> {
    fun toModel(entity: E): M
    fun toEntity(model: M): E
}