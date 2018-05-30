package com.alfred.heartnews.ui.viewmodel

import android.content.Context
import com.alfred.heartnews.data.module.FindBean
import com.alfred.heartnews.data.remote.FindRepository
import com.alfred.heartnews.ui.base.BaseListViewModel

/**
 * Created by alfred on 2018/5/29.
 */
class FindViewModel() : BaseListViewModel<FindBean>() {

    var mFindRepo: FindRepository? = null

    constructor(context: Context?) : this() {
        mFindRepo = FindRepository(context)
    }

    override fun requestData() {
        mFindRepo?.getFindData(object : OnRequestListener<MutableList<FindBean>> {
            override fun onFailure(message: String?) {
                mListener?.onFailure(message)
            }

            override fun onSuccess(t: MutableList<FindBean>?) {
                mListener?.onSuccess(t)
            }
        })
    }

}