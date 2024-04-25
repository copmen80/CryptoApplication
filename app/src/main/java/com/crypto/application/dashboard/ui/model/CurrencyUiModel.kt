package com.crypto.application.dashboard.ui.model


data class CurrencyUiModel(
    val time: Time,
    val disclaimer: String,
    val chartName: String,
    val bpi: Bpi
)

data class Time(
    val updated: String,
    val updatedISO: String,
    val updateduk: String
)

data class Bpi(
    val USD: Currency,
    val GBP: Currency,
    val EUR: Currency
)

data class Currency(
    val code: String,
    val symbol: String,
    val rate: String,
    val description: String,
    val rateFloat: Double
)