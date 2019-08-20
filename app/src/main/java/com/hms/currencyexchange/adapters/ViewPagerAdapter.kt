package com.hms.currencyexchange.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hms.currencyexchange.fragments.AllCurrencyFragment


class ViewPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AllCurrencyFragment.newFragment()
            1 -> FavouriteCurrencyFragment.newFragment()
            else -> CurrencyCalculatorFragment.newFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "All"
            1 -> "Favourite"
            else -> "Calculator"
        }
    }
}