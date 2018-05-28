package com.alfred.heartnews.data.remote

import android.content.Context
import com.alfred.heartnews.network.HttpUtils
import com.alfred.heartnews.ui.viewmodel.OnRequestListener
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean
import rx.Subscriber

/**
 * Created by alfred on 2018/5/21.
 */
class HomeRepository(var context: Context?) {

    fun getHomeData(isFirst: Boolean, data: String, listener: OnRequestListener<HomeBean>) {
        HttpUtils.getInstance().getHomeList(object : Subscriber<HomeBean>() {
            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                listener.onFailure(e?.message)
            }

            override fun onNext(t: HomeBean?) {
                listener.onSuccess(t)
            }
        }, isFirst, data)

    }
}