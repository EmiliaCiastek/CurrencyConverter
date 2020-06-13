package com.ciastek.currencyconverter.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Qualifier

@Module
class RxModule {

    @Provides
    @UiScheduler
    fun provideUiScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @BackgroundScheduler
    fun provideBackgroundScheduler() = Schedulers.io()

    @Qualifier
    annotation class BackgroundScheduler

    @Qualifier
    annotation class UiScheduler
}