package com.ciastek.currencyconverter.view

interface CurrencyConverterMVP {

    interface Presenter {

        fun attach(view: View)

        fun detach()
    }

    interface View {

        fun showCurrencies(currencies: List<Currency>)
    }
}
