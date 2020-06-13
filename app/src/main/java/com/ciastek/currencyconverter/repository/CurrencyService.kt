package com.ciastek.currencyconverter.repository

import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyService {

    @GET("api/android/latest?base=EUR")
    fun getCurrentExchangeRates(): Observable<ExchangeRates>
}
