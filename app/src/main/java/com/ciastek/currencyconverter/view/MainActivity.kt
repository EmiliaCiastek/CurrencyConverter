package com.ciastek.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciastek.currencyconverter.R
import com.ciastek.currencyconverter.di.DaggerMainActivityComponent
import kotlinx.android.synthetic.main.activity_main.currencies_list as currenciesList
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CurrencyConverterMVP.View {

    companion object {

        private const val TAG = "MainActivity"
    }

    @Inject
    lateinit var currenciesPresenter: CurrencyConverterMVP.Presenter

    private lateinit var currenciesAdapter: CurrenciesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        currenciesAdapter = CurrenciesAdapter{currenciesPresenter.updateCurrentActiveCurrency(it)}
        currenciesList.apply {
            adapter = currenciesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onStart() {
        super.onStart()

        currenciesPresenter.attach(this)
    }

    override fun onStop() {
        currenciesPresenter.detach()

        super.onStop()
    }

    override fun showCurrencies(currencies: List<CurrencyView>) {
        currenciesAdapter.setCurrencies(currencies)
    }

    override fun handleError(it: Throwable) {
        Toast.makeText(
            this,
            it.message ?: getString(R.string.default_error_message),
            Toast.LENGTH_LONG
        )
            .show()

        Log.e(TAG, it.message)
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.create().inject(this)
    }
}
