package com.alfred.heartnews.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.content.Context
import com.alfred.heartnews.data.remote.HomeRepository
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by alfred on 2018/3/26.
 */

class HomeViewModel() : BaseListViewModel<List<HomeBean.IssueListBean>>() {

    var mHomeRepo: HomeRepository? = null

    constructor(context: Context, callback: BaseListCallback) : this() {
        mHomeRepo = HomeRepository(context)
    }

    override fun requestFirstData(): LiveData<List<HomeBean.IssueListBean>> {
        return mHomeRepo.getFirstData()
    }

    override fun requestOtherData() : LiveData<List<HomeBean.IssueListBean>> {
        return mHomeRepo.getOtherData()
    }




}