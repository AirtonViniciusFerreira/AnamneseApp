package com.example.anamnesedrapp.geral.entity

import androidx.room.TypeConverter

enum class TipoPessoaEnum(value: Int) {
    FISICO(0),
    JURIDICO(1)
}

class TipoPessoaConverter {
    @TypeConverter
    fun toTipoPessoaEnum(value: Int): TipoPessoaEnum =
        enumValues<TipoPessoaEnum>()[value]

    @TypeConverter
    fun fromTipoPessoaEnum(value: TipoPessoaEnum): Int = value.ordinal
}