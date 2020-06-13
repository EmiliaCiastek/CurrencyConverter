package com.ciastek.currencyconverter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciastek.currencyconverter.R

class CurrenciesAdapter(private val onItemClicked: (String) -> Unit) : RecyclerView.Adapter<CurrencyViewHolder>() {

    private var currencies: List<CurrencyView> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.currency_item_layout, parent, false),
        onItemClicked)

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position])
    }

    fun setCurrencies(currencies: List<CurrencyView>) {
        this.currencies = currencies
        notifyDataSetChanged()
    }
}
