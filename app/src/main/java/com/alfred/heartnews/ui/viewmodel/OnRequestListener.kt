package com.alfred.heartnews.ui.viewmodel

/**
 * Created by alfred on 2018/5/23.
 */
interface OnRequestListener<T> {

    fun onSuccess(t: T?)

    fun onFailure(message : String?)
}