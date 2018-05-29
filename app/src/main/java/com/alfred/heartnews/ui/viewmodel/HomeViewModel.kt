package com.alfred.heartnews.ui.viewmodel

import android.content.Context
import com.alfred.heartnews.data.module.HomeBean
import com.alfred.heartnews.data.remote.HomeRepository
import com.alfred.heartnews.ui.base.BaseListViewModel
import java.util.regex.Pattern

/**
 * Created by alfred on 2018/3/26.
 */

class HomeViewModel() : BaseListViewModel<HomeBean.IssueListBean.ItemListBean>() {

    var mHomeRepo: HomeRepository? = null

    var mData: String? = null

    constructor(context: Context?) : this() {
        mHomeRepo = HomeRepository(context)
    }

    override fun requestData() {
        mHomeRepo?.getHomeData(isFirstPage(), if (isFirstPage()) "0" else mData!!, object : OnRequestListener<HomeBean> {
            override fun onFailure(message: String?) {
                mListener?.onFailure(message)
            }

            override fun onSuccess(t: HomeBean?) {
                setDataParams(t)
                var list: MutableList<HomeBean.IssueListBean.ItemListBean> = mutableListOf()
                t?.issueList!!
                        .flatMap { it.itemList!! }
                        .filter { it.type.equals("video") }
                        .forEach { list.add(it) }
                mListener?.onSuccess(list)
            }
        })
    }

    fun setDataParams(homeBean: HomeBean?) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(homeBean?.nextPageUrl)
        mData = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
    }


}