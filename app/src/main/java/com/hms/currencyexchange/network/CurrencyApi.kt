package com.hms.currencyexchange.network

import com.hms.currencyexchange.data.vos.ExchangeRateVO
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {


    @GET("latest")
    fun getExchangeRate(): Call<ExchangeRateVO>
}