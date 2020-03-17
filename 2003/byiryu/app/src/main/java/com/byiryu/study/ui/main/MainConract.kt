package com.byiryu.study.ui.main

import android.content.Context
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.base.BaseContract

interface MainConract {

    interface View : BaseContract.View {
        fun setPrevQuery(query : String)

        fun setResult(items : List<MovieItem>)
    }

    interface Presenter<V : View> : BaseContract.Presenter<V>{

        fun onViewPrepared()

        fun search(query : String)
    }
}