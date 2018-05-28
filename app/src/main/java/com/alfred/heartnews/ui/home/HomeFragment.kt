package com.alfred.heartnews.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfred.heartnews.R
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.alfred.heartnews.ui.base.BaseListFragment
import com.alfred.heartnews.ui.viewmodel.BaseListViewModel
import com.alfred.heartnews.ui.viewmodel.HomeViewModel
import com.alfred.heartnews.ui.viewmodel.OnRequestListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by alfred on 2018/3/23.
 */
class HomeFragment : BaseListFragment<HomeBean.IssueListBean.ItemListBean>() {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        (mViewModel as HomeViewModel).setOnRequestListener(object : OnRequestListener<MutableList<HomeBean.IssueListBean.ItemListBean>> {
            override fun onFailure(message: String?) {

            }

            override fun onSuccess(t: MutableList<HomeBean.IssueListBean.ItemListBean>?) {
                updateAdapter(t)
            }
        })
    }

    override fun getAdapter(): BaseListAdapter<HomeBean.IssueListBean.ItemListBean> {
       return HomeAdapter(mContext, R.layout.item_home)
    }

    override fun onRecyclerViewItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }

    override fun getViewModel(): BaseListViewModel<HomeBean.IssueListBean.ItemListBean> {
       return HomeViewModel(mContext)
    }

}