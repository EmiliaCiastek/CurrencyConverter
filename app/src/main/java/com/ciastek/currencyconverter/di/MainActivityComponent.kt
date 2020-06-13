package com.ciastek.currencyconverter.di

import com.ciastek.currencyconverter.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CurrenciesModule::class, RxModule::class, NetworkModule::class])
interface MainActivityComponent {

    fun inject(activity: MainActivity)
}