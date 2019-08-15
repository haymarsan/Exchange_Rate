package com.hms.currencyexchange.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import com.hms.currencyexchange.repository.RepositoryImpl

class ExchangeRateViewModelImpl: ViewModel(), ExchangeRateViewModel {

    private lateinit var exchangeRateList: MutableLiveData<ExchangeRateVO>

    override fun getExchangeRate(): LiveData<ExchangeRateVO> {
        exchangeRateList = RepositoryImpl().getLatestRate()

        return exchangeRateList

    }


}