package com.alfred.heartnews.data.remote

import android.content.Context
import com.alfred.heartnews.data.module.HomeBean
import com.alfred.heartnews.network.HttpUtils
import com.alfred.heartnews.ui.viewmodel.OnRequestListener
import io.reactivex.disposables.Disposable

/**
 * Created by alfred on 2018/5/21.
 */
class HomeRepository(var context: Context?) {

    fun getHomeData(isFirst: Boolean, data: String, listener: OnRequestListener<HomeBean>) {
        HttpUtils.getInstance().getHomeList(object : io.reactivex.Observer<HomeBean> {
            override fun onError(e: Throwable) {
                listener.onFailure(e.message)
            }

            override fun onNext(t: HomeBean) {
                listener.onSuccess(t)
            }

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

        }, isFirst, data)

    }
}