package com.hms.currencyexchange.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hms.currencyexchange.R
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl
import kotlinx.android.synthetic.main.activity_previous_day_rate.*

class PreviousDayRateActivity : AppCompatActivity() {

    lateinit var mViewModel: ExchangeRateViewModel

    private var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_day_rate)

        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)



        btnPreviousRate.setOnClickListener {
            date = edDate.text.toString()
            mViewModel.getPreviousExchangeRate(date).observe(this, Observer {
                val data = it

                Log.d("Previous DataSet", it.description)

            })

        }

    }
}
