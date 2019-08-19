package com.hms.currencyexchange.activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hms.currencyexchange.R
import com.hms.currencyexchange.adapters.ExchangeRateAdapter
import com.hms.currencyexchange.adapters.ViewPagerAdapter
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_all_currency.*

class MainActivity : AppCompatActivity() {



    lateinit var mPagerAdapter: ViewPagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = mPagerAdapter
        tabLayout.setupWithViewPager(viewPager)


//        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)
//        mAdapter = ExchangeRateAdapter(applicationContext)
//
//        recyclerExchange.layoutManager = LinearLayoutManager(this)
//        recyclerExchange.adapter = mAdapter
//
//
//        progress.visibility = View.VISIBLE
//        mViewModel.getExchangeRate().observe(this, Observer {
//            val data = it
//
//            progress.visibility = View.GONE
//            Log.d("Data Set", data.description)
//
//            var currencyList = ArrayList<RateVO>()
//
//            for ((key, value)in it.rates){
//                currencyList.add(RateVO(key, value))
//
//            }
//
//            mAdapter.setNewData(currencyList as List<RateVO>)
//        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
