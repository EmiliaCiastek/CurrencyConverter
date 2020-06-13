package com.ciastek.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciastek.currencyconverter.R
import kotlinx.android.synthetic.main.activity_main.currencies_list as currenciesList
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CurrencyConverterMVP.View {

    @Inject
    lateinit var currenciesPresenter: CurrencyConverterMVP.Presenter

    private lateinit var currenciesAdapter: CurrenciesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        currenciesAdapter = CurrenciesAdapter()
        currenciesList.apply {
            adapter = currenciesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun showCurrencies(currencies: List<Currency>) {
        currenciesAdapter.setCurrencies(currencies)
    }

    private fun injectDependencies() {

    }
}
