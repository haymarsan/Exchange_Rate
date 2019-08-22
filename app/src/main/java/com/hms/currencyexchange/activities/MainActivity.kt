package com.hms.currencyexchange.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
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


    companion object {

        fun newInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_24dp)

        mPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = mPagerAdapter
        tabLayout.setupWithViewPager(viewPager)


        navigationView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_home ->{

                    val intent = MainActivity.newInstance(applicationContext)
                    startActivity(intent)
                }
                R.id.nav_history ->{
                    Snackbar.make(navigationView, "Tapped Previous Day Currency Rate", Snackbar.LENGTH_LONG).show()
                }
                R.id.nav_rate ->{
                    openMarket(getString(R.string.app_url), getString(R.string.app_package))
                }
                R.id.nav_invite ->{
                    inviteFriend()
                }
                R.id.nav_update ->{
                    openMarket(getString(R.string.app_url), getString(R.string.app_package))
                }
                R.id.nav_about->{
                    val intent = AboutActivity.newInstance(applicationContext)
                    startActivity(intent)
                }


            }
//            for(menuItemIndex in 0 until navigationView.menu.size()) {
//                navigationView.menu.getItem(menuItemIndex).isChecked = false
//            }
//            it.isChecked = true
//            drawerLayout.closeDrawer(GravityCompat.END)

            return@setNavigationItemSelectedListener (true)
        }



    }

    private fun inviteFriend(){
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,"Invite Friend Text Here")
        intent.setType("text/plain")
        startActivity(intent)
    }

    private fun openMarket(url: String, appPackage: String) {
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackage))
        intent.data = Uri.parse(url)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(this, intent, null)
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
