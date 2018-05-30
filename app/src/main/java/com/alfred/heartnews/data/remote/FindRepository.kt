package com.alfred.heartnews.data.remote

import android.content.Context
import com.alfred.heartnews.data.module.FindBean
import com.alfred.heartnews.network.HttpUtils
import com.alfred.heartnews.ui.viewmodel.OnRequestListener
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by alfred on 2018/5/29.
 */
class FindRepository(var context: Context?) {
    fun getFindData(listener: OnRequestListener<MutableList<FindBean>>) {
        HttpUtils.getInstance().getFindList(object : Observer<MutableList<FindBean>> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MutableList<FindBean>) {
                listener.onSuccess(t)
            }

            override fun onError(e: Throwable) {
                listener.onFailure(e.message)
            }
        })
    }
}