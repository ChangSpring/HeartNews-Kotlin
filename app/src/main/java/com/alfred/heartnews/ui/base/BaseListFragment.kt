package com.alfred.heartnews.ui.base

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfred.heartnews.R
import com.alfred.heartnews.databinding.FragmentBaseListBinding
import com.alfred.heartnews.ui.viewmodel.BaseListViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

/**
 * Created by alfred on 2018/5/21.
 */
abstract class BaseListFragment<T> : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    var mBinding: FragmentBaseListBinding? = null
    var mAdapter: BaseListAdapter<T>? = null
    var mViewModel: BaseListViewModel<T>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(mActivity?.layoutInflater, R.layout.fragment_base_list, container, false)
        return mBinding?.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = getViewModel()

        mBinding?.swipeRefreshLayout?.setOnRefreshListener { this }
        mBinding?.swipeRefreshLayout?.setColorSchemeColors(Color.RED)
        mBinding?.recyclerView?.layoutManager = LinearLayoutManager(mContext)

        mAdapter = getAdapter()
        mAdapter?.setOnLoadMoreListener(this, mBinding?.recyclerView)
        mBinding?.recyclerView?.adapter = mAdapter

        mBinding?.recyclerView?.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                onRecyclerViewItemClick(adapter, view, position)
            }
        })

        mBinding?.swipeRefreshLayout?.isRefreshing = true

        mViewModel?.requestData()

    }


    override fun onLoadMoreRequested() {
        mViewModel?.onLoadMoreRequest()
        //TODO 数据不足一页 改如何来处理
//        if (mAdapter.getData().size() < PAGE_SIZE) {
//            //第一页 也就是最后一页
//            mAdapter.setEnableLoadMore(false)
//        } else {
//            requestData()
//            mAdapter.loadMoreComplete()
//        }
        mViewModel?.requestData()
    }

    override fun onRefresh() {
        mViewModel?.onRefreshRequest()
        mViewModel?.requestData()

    }

    fun updateAdapter(data: MutableList<T>?) {
        mViewModel?.let {
            if (it.mPageNumber == BaseListViewModel.PAGE_NUMBER_FIRST) {
                mBinding?.swipeRefreshLayout?.isRefreshing = false
                mAdapter?.setNewData(data)
            }else {
                mAdapter?.addData(data!!)
            }
        }
    }

    protected abstract fun getAdapter(): BaseListAdapter<T>

    protected abstract fun getViewModel(): BaseListViewModel<T>

    open fun onRecyclerViewItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }


}