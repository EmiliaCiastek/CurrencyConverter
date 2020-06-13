package com.ciastek.currencyconverter.view

import com.ciastek.currencyconverter.view.CurrencyConverterMVP.View
import javax.inject.Inject

class CurrencyPresenter @Inject constructor() : CurrencyConverterMVP.Presenter {

    private var view: View? = null

    override fun attach(view: View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
}
