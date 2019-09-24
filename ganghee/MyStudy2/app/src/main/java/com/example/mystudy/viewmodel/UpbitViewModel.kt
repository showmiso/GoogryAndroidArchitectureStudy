package com.example.mystudy.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.example.mystudy.base.BaseViewModel
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.data.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpbitViewModel(
    private val repository: UpbitRepository
) : BaseViewModel() {

    val tickerList = ObservableField<List<FormatTickers>>()

    fun getTicker(firstMarket: String?) {
        repository.getMarket()
            .observeOn(Schedulers.newThread())
            .subscribe { it ->
                repository.getTicker(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.filter { TickerResponse ->
                            TickerResponse.market.split("-")[0] == firstMarket
                        }
                    }
                    .subscribe({
                        tickerList.set(it.map { it.toTicker() })
                    }, {
                        showFailedUpbitTickerList()
                    })
            }.also { compositeDisposable.add(it) }
    }

    private fun showFailedUpbitTickerList() {
        Log.d("TickerFail", "Ticker is not show")
    }

}