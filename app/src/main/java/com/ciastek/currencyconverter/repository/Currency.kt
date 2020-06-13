package com.ciastek.currencyconverter.repository

import java.math.BigDecimal

data class Currency(val shortcut: String, val fullName: String, val rate: BigDecimal)