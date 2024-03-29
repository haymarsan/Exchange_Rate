package com.hms.currencyexchange.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hms.currencyexchange.R
import com.hms.currencyexchange.adapters.ExchangeRateAdapter
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl
import com.tsongkha.spinnerdatepicker.DatePicker
import com.tsongkha.spinnerdatepicker.DatePickerDialog
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.activity_previous_day_rate.*
import kotlinx.android.synthetic.main.fragment_all_currency.view.*
import java.text.SimpleDateFormat
import java.util.*

class PreviousDayRateActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var mViewModel: ExchangeRateViewModel
    lateinit var mAdapter: ExchangeRateAdapter

    private var date: String = ""

    companion object{
        fun newInstance(context: Context): Intent {
            return Intent(context, PreviousDayRateActivity::class.java)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_day_rate)

        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)

        mAdapter = ExchangeRateAdapter(this)

        recyclerExchange.layoutManager = LinearLayoutManager(this)
        recyclerExchange.adapter = mAdapter

//        btnPreviousRate.setOnClickListener {
//            //date = edDate.text.toString()
//            mViewModel.getPreviousExchangeRate(date).observe(this, Observer {
//                val data = it
//
//                Log.d("Previous DataSet", it.description)
//
//            })
//
//        }

        btnPreviousRate.setOnClickListener {
            showDate(2019, 1, 1, R.style.DatePickerSpinner )

        }




    }

    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar = GregorianCalendar(year, monthOfYear, dayOfMonth)
        date = SimpleDateFormat("dd-MM-yyy").format(calendar.time)
        btnPreviousRate.setText(date)

        progress.visibility = View.VISIBLE
        mViewModel.getPreviousExchangeRate(date).observe(this, Observer {

            val data = it

            progress.visibility = View.GONE
            Log.d("Data Set", data.description)

            var currencyList = ArrayList<RateVO>()

            for ((key, value) in it.rates) {
                currencyList.add(RateVO(key, value))

            }

            mAdapter.setNewData(currencyList as List<RateVO>)


        })

    }

    private fun showDate(year:Int, monthOfYear: Int, dayOfMonth: Int, spinnerTheme: Int){
        SpinnerDatePickerDialogBuilder()
            .context(this)
            .callback(this)
            .spinnerTheme(spinnerTheme)
            .defaultDate(year, monthOfYear, dayOfMonth)
            .build()
            .show()
    }

}
