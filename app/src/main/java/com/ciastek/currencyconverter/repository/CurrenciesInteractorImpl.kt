package com.ciastek.currencyconverter.repository

import io.reactivex.Observable
import java.math.BigDecimal
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrenciesInteractorImpl @Inject constructor(private val currencyService: CurrencyService): CurrenciesInteractor {

    companion object {

        private const val REQUEST_INTERVAL = 1L
    }

    override fun getExchangeRates(): Observable<List<Currency>> =
        Observable.interval(REQUEST_INTERVAL, TimeUnit.SECONDS)
            .switchMap { currencyService.getCurrentExchangeRates() }
            .map { it.mapToCurrency() }


    private fun ExchangeRates.mapToCurrency(): List<Currency> {
        val currencies = mutableListOf(
            Currency(
                shortcut = baseCurrency,
                fullName = currenciesNames[baseCurrency] ?: baseCurrency,
                rate = BigDecimal.ONE
            )
        )

        rates.map {
            currencies.add(
                Currency(
                    shortcut = it.key,
                    fullName = currenciesNames[it.key] ?: it.key,
                    rate = it.value
                )
            )
        }

        return currencies
    }

    private val currenciesNames: Map<String, String> = mapOf(
        "EUR" to "European euro",
        "BRL" to "Brazilian real",
        "USD" to "United States dollar"
    )
}