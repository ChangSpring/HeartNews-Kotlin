package com.alfred.heartnews.ui.base

/**
 * Created by alfred on 2018/5/21.
 */
abstract class BaseListViewModel<T> : BaseViewModel<T>() {

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

    fun isFirstPage(): Boolean {
        return mPageNumber == PAGE_NUMBER_FIRST
    }


}