package com.ciastek.currencyconverter.di

import com.ciastek.currencyconverter.view.CurrencyConverterMVP
import com.ciastek.currencyconverter.view.CurrencyPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class CurrenciesModule {

    @Binds
    abstract fun bindPresenter(presenter: CurrencyPresenter): CurrencyConverterMVP.Presenter
}