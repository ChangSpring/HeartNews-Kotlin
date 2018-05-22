package com.alfred.heartnews.data.remote

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.alfred.heartnews.network.HttpUtils
import com.alfred.heartnews.network.ProgressSubscriber
import com.alfred.heartnews.network.SubscriberOnNextListener
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by alfred on 2018/5/21.
 */
class HomeRepository(var mContext: Context) {


    fun getFirstData(): MutableLiveData<List<HomeBean.IssueListBean>> {
        val data = MutableLiveData<List<HomeBean.IssueListBean>>()
        HttpUtils.getInstance().getHomeList(ProgressSubscriber(mContext, SubscriberOnNextListener<HomeBean> { (nextPageUrl, nextPublishTime, newestIssueType, dialog, issueList) ->
            data.value = issueList
        }))

        return data
    }

    fun getOtherData(page: Int): MutableLiveData<List<HomeBean.IssueListBean>> {
        val data = MutableLiveData<List<HomeBean.IssueListBean>>()
        HttpUtils.getInstance().getHomeMoreList(ProgressSubscriber(mContext, SubscriberOnNextListener<HomeBean> { (nextPageUrl, nextPublishTime, newestIssueType, dialog, issueList) ->
            data.value = issueList
        }), false, page)

        return data
    }
}