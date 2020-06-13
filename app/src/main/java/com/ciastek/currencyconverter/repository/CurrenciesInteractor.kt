package com.ciastek.currencyconverter.repository

import io.reactivex.Observable

interface CurrenciesInteractor {

    fun getExchangeRates(): Observable<List<Currency>>
}
