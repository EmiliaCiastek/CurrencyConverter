package com.ciastek.currencyconverter.view

import androidx.annotation.DrawableRes
import com.ciastek.currencyconverter.R
import java.math.BigDecimal

data class CurrencyView(val shortcut: String, val fullName: String, @DrawableRes val flagThumbnail: Int = R.drawable.ic_flag, val value: BigDecimal) {

    var isActive: Boolean = false
}
