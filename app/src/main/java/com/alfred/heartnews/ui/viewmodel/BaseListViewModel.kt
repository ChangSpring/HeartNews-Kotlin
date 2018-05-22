package com.alfred.heartnews.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by alfred on 2018/5/21.
 */
abstract class BaseListViewModel<T>() : ViewModel() {

    var mPageNumber = PAGE_NUMBER_FIRST

    companion object {
        val PAGE_NUMBER_FIRST = 1
        val PAGE_SIZE = 10
    }
    var mCallback : BaseListCallback? = null

    constructor(callback: BaseListCallback) : this() {
        this.mCallback = callback
    }

    fun requestData() {
        if (mPageNumber == PAGE_NUMBER_FIRST) {
            requestFirstData()
        }else {
            requestOtherData()
        }
    }

    fun refreshFirstPageData() {
        mPageNumber = PAGE_NUMBER_FIRST
    }

    fun refreshOtherPageData() {
        mPageNumber ++

    }

    abstract fun requestFirstData() : LiveData<T>

    abstract fun requestOtherData() : LiveData<T>

}