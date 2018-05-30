package com.alfred.heartnews.ui.base

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfred.heartnews.R
import com.alfred.heartnews.databinding.FragmentBaseListBinding
import com.alfred.heartnews.ui.viewmodel.OnRequestListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

/**
 * Created by alfred on 2018/5/21.
 */
abstract class BaseListFragment<T, U : BaseViewModel<T>> : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    var mBinding: FragmentBaseListBinding? = null
    var mAdapter: BaseListAdapter<T>? = null
    var mViewModel: BaseListViewModel<T>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(mActivity?.layoutInflater, R.layout.fragment_base_list, container, false)
        return mBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = getViewModel()

        mBinding?.let {
            it.swipeRefreshLayout.setOnRefreshListener(this)
            it.swipeRefreshLayout.setColorSchemeColors(Color.RED)

            it.recyclerView.layoutManager = if (getLayoutManager() == null) LinearLayoutManager(mContext) else getLayoutManager()

            mAdapter = getAdapter()
            mAdapter?.setOnLoadMoreListener(this, it.recyclerView)
            it.recyclerView.adapter = mAdapter

            it.recyclerView.addOnItemTouchListener(object : OnItemClickListener() {
                override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    onRecyclerViewItemClick(adapter, view, position)
                }
            })

            it.swipeRefreshLayout.isRefreshing = true
        }

        setRequestListener()
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
            } else {
                mAdapter?.loadMoreComplete()
                mAdapter?.addData(data!!)
            }
        }
    }

    fun refreshFailed() {
        if (mViewModel?.isFirstPage()!!) {
            mBinding?.swipeRefreshLayout?.isRefreshing = false
        } else {
            mAdapter?.loadMoreFail()
        }
    }

    private fun setRequestListener() {
        (mViewModel as U).setOnRequestListener(object : OnRequestListener<MutableList<T>> {
            override fun onFailure(message: String?) {
                refreshFailed()
            }

            override fun onSuccess(t: MutableList<T>?) {
                updateAdapter(t)
            }
        })
    }

    open fun getLayoutManager(): RecyclerView.LayoutManager? {
        return null
    }

    protected abstract fun getAdapter(): BaseListAdapter<T>

    protected abstract fun getViewModel(): BaseListViewModel<T>

    open fun onRecyclerViewItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }


}