package com.alfred.heartnews.ui.viewmodel

import android.arch.lifecycle.ViewModel

/**
 * Created by alfred on 2018/5/21.
 */
abstract class BaseListViewModel<T> : ViewModel() {

    var mPageNumber = PAGE_NUMBER_FIRST

    companion object {
        val PAGE_NUMBER_FIRST = 1
        val PAGE_SIZE = 10
    }

    abstract fun requestData()

    fun onRefreshRequest() {
        mPageNumber = PAGE_NUMBER_FIRST
    }

    fun onLoadMoreRequest() {
        mPageNumber++
    }



}