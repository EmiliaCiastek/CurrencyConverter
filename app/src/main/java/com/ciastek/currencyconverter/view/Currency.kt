package com.ciastek.currencyconverter.view

import androidx.annotation.DrawableRes

data class Currency(val shortcut: String, val fullName: String, @DrawableRes val flagThumbnail: Int, val value: Double)
