package com.takeiteasy.vip.genesistest.data.repository.mapper

import java.text.SimpleDateFormat
import java.util.*

class DateMapper : Mapper<Date, String> {

    companion object {
        @JvmField
        val DATE_FORMAT: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }

    override fun toModel(entity: Date): String {
        return DATE_FORMAT.format(entity)
    }

    override fun toEntity(model: String): Date {
        return DATE_FORMAT.parse(model)
    }
}