package com.alexander.korovin.currency.converter.network.model

data class CurrencyDataModel(
    val date: String,
    val previousDate: String,
    val previousURL: String,
    val timestamp: String,
    val valute: Map<String, Valute>
)

data class Valute(
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Long,
    val name: String,
    val value: Double,
    val previous: Double
)