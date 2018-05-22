package com.alfred.heartnews.base

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfred.heartnews.R
import com.alfred.heartnews.adapter.BaseListAdapter
import com.alfred.heartnews.databinding.FragmentBaseListBinding
import com.alfred.heartnews.ui.viewmodel.BaseListCallback
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

/**
 * Created by alfred on 2018/5/21.
 */
abstract class BaseListFragment<T> : BaseFragment(), SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,BaseListCallback {

    var mBinding: FragmentBaseListBinding? = null

    var mAdapter: BaseListAdapter<T>? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<FragmentBaseListBinding>(mActivity?.layoutInflater, R.layout.fragment_base_list, container, false)
        return mBinding?.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding?.swipeRefreshLayout?.setOnRefreshListener { this }
        mBinding?.swipeRefreshLayout?.setColorSchemeColors(Color.RED)
        mBinding?.recyclerView?.layoutManager = LinearLayoutManager(mContext)

        mAdapter = getAdapter()
        mAdapter?.setOnLoadMoreListener(this,mBinding?.recyclerView)
        mBinding?.recyclerView?.adapter = mAdapter

        mBinding?.recyclerView?.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                onRecyclerViewItemClick(adapter, view, position)
            }
        })

        mBinding?.swipeRefreshLayout?.isRefreshing = true

    }

    override fun onLoadMoreRequested() {

    }

    override fun onRefresh() {
    }

    protected abstract fun getAdapter(): BaseListAdapter<T>

    protected abstract fun onRecyclerViewItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int)


}