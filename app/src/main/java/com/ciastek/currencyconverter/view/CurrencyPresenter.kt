package com.ciastek.currencyconverter.view

import com.ciastek.currencyconverter.di.RxModule.BackgroundScheduler
import com.ciastek.currencyconverter.di.RxModule.UiScheduler
import com.ciastek.currencyconverter.repository.CurrencyService
import com.ciastek.currencyconverter.repository.ExchangeRates
import com.ciastek.currencyconverter.view.CurrencyConverterMVP.View
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.math.BigDecimal
import javax.inject.Inject

class CurrencyPresenter @Inject constructor(
    private val currencyService: CurrencyService,
    @BackgroundScheduler private val backgroundScheduler: Scheduler,
    @UiScheduler private val uiScheduler: Scheduler
) : CurrencyConverterMVP.Presenter {

    private var view: View? = null
    private val disposable = CompositeDisposable()

    override fun attach(view: View) {
        this.view = view

        currencyService.getCurrentExchangeRates()
            .subscribeOn(backgroundScheduler)
            .observeOn(uiScheduler)
            .subscribe( {
                view?.showCurrencies(it.mapToCurrencyView())
            }, {
                view?.handleError(it)
            } )
            .apply {
                disposable.add(this)
            }
    }

    override fun detach() {
        disposable.clear()
        view = null
    }

    private fun ExchangeRates.mapToCurrencyView(): List<CurrencyView> {
        val currencies = mutableListOf(
            CurrencyView(
                shortcut = baseCurrency,
                fullName = currenciesNames[baseCurrency] ?: baseCurrency,
                value = BigDecimal.ONE
            )
        )

        rates.map {
            currencies.add(
                CurrencyView(
                    shortcut = it.key,
                    fullName = currenciesNames[it.key] ?: it.key,
                    value = it.value
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

