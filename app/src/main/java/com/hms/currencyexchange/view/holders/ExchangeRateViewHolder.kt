package com.hms.currencyexchange.view.holders

import android.view.View
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import com.hms.currencyexchange.data.vos.RateVO
import kotlinx.android.synthetic.main.view_item_exchange_rate.view.*

class ExchangeRateViewHolder(itemView: View): BaseViewHolder<RateVO>(itemView) {

    override fun setData(data: RateVO) {
        itemView.tvCurrencyCode.text = data.currencyCode
        itemView.tvSellRate.text = data.mmk

    }

    override fun onClick(v: View?) {

    }
}