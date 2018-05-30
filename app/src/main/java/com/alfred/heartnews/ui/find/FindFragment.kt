package com.alfred.heartnews.ui.find

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.alfred.heartnews.R
import com.alfred.heartnews.data.module.FindBean
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.alfred.heartnews.ui.base.BaseListFragment
import com.alfred.heartnews.ui.base.BaseListViewModel
import com.alfred.heartnews.ui.viewmodel.FindViewModel

/**
 * Created by alfred on 2018/3/23.
 */
class FindFragment : BaseListFragment<FindBean, FindViewModel>() {

    companion object {
        fun newInstance(): FindFragment {
            return FindFragment()
        }
    }


    override fun getAdapter(): BaseListAdapter<FindBean> {
        return FindAdapter(mContext!!, R.layout.item_find)
    }

    override fun getViewModel(): BaseListViewModel<FindBean> {
        return FindViewModel(mContext)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager? {
        return GridLayoutManager(mContext,2)
    }
}
