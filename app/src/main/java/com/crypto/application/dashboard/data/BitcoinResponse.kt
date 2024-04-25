package com.crypto.application.dashboard.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BitcoinResponse(
    @SerialName("time")
    val time: TimeResponse,
    @SerialName("disclaimer")
    val disclaimer: String,
    @SerialName("chartName")
    val chartName: String,
    @SerialName("bpi")
    val bpi: BpiResponse
)

@Serializable
data class TimeResponse(
    @SerialName("updated")
    val updated: String,
    @SerialName("updatedISO")
    val updatedISO: String,
    @SerialName("updateduk")
    val updateduk: String
)

@Serializable
data class BpiResponse(
    @SerialName("USD")
    val USD: CurrencyResponse,
    @SerialName("GBP")
    val GBP: CurrencyResponse,
    @SerialName("EUR")
    val EUR: CurrencyResponse
)

@Serializable
data class CurrencyResponse(
    @SerialName("code")
    val code: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("rate")
    val rate: String,
    @SerialName("description")
    val description: String,
    @SerialName("rateFloat")
    val rateFloat: Double? = null
)