package com.alexander.korovin.currency.converter.model

data class CurrencyResponseModel(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val time_last_updated: Int
)