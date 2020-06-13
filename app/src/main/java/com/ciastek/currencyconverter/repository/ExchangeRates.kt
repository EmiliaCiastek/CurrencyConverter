package com.ciastek.currencyconverter.repository

import java.math.BigDecimal

data class ExchangeRates(val baseCurrency: String, val rates: Map<String, BigDecimal>)