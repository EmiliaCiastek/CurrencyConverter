package com.ciastek.currencyconverter.view

interface CurrencyConverterMVP {

    interface Presenter {

        fun attach(view: View)

        fun detach()

        fun updateCurrentActiveCurrency(currencyShortcut: String)
    }

    interface View {

        fun showCurrencies(currencies: List<CurrencyView>)

        fun handleError(it: Throwable)
    }
}
