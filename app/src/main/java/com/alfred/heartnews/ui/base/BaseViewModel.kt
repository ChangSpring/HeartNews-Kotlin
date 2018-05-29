package com.alfred.heartnews.ui.base

import android.arch.lifecycle.ViewModel
import com.alfred.heartnews.ui.viewmodel.OnRequestListener

/**
 * Created by alfred on 2018/5/28.
 */
open class BaseViewModel<T> : ViewModel() {
    var mListener: OnRequestListener<MutableList<T>>? = null

    fun setOnRequestListener(listener: OnRequestListener<MutableList<T>>) {
        //TODO mListener is null
        this.mListener = listener
    }
}