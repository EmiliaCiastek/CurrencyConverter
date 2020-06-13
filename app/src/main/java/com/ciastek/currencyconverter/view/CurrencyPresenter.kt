package com.ciastek.currencyconverter.view

import com.ciastek.currencyconverter.di.RxModule.BackgroundScheduler
import com.ciastek.currencyconverter.di.RxModule.UiScheduler
import com.ciastek.currencyconverter.repository.CurrenciesInteractor
import com.ciastek.currencyconverter.repository.Currency
import com.ciastek.currencyconverter.view.CurrencyConverterMVP.View
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CurrencyPresenter @Inject constructor(
    private val currencyInteractor: CurrenciesInteractor,
    @BackgroundScheduler private val backgroundScheduler: Scheduler,
    @UiScheduler private val uiScheduler: Scheduler
) : CurrencyConverterMVP.Presenter {

    private var view: View? = null
    private val disposable = CompositeDisposable()

    override fun attach(view: View) {
        this.view = view

        currencyInteractor.getExchangeRates()
            .subscribeOn(backgroundScheduler)
            .observeOn(uiScheduler)
            .map { it.map { currency -> currency.mapToCurrencyView() } }
            .subscribe({
                view?.showCurrencies(it)
            }, {
                view?.handleError(it)
            })
            .apply {
                disposable.add(this)
            }
    }

    override fun detach() {
        disposable.clear()
        view = null
    }

    private fun Currency.mapToCurrencyView(): CurrencyView =
        CurrencyView(shortcut = shortcut, fullName = fullName, value = rate)
}

