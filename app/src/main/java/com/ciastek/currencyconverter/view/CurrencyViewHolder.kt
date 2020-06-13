package com.ciastek.currencyconverter.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_item_layout.view.*

class CurrencyViewHolder(itemView: View, private val onItemClicked: (String) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(currency: CurrencyView) {
        itemView.currency_shortcut.text = currency.shortcut
        itemView.currency_name.text = currency.fullName
        itemView.currency_value.setText(currency.value.toString())
        itemView.currency_value.isEnabled = currency.isActive
        itemView.setOnClickListener { onItemClicked(currency.shortcut) }
        itemView.country_flag_thumbnail.setImageDrawable(itemView.context.getDrawable(currency.flagThumbnail))
    }
}
