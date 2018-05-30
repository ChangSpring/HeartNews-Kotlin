package com.alfred.heartnews.ui.hot

import com.alfred.heartnews.R
import com.alfred.heartnews.data.module.HotBean
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.alfred.heartnews.ui.base.BaseListFragment
import com.alfred.heartnews.ui.base.BaseListViewModel
import com.alfred.heartnews.ui.viewmodel.RankViewModel

/**
 * Created by alfred on 2018/5/29.
 */
class RankFragment : BaseListFragment<HotBean, RankViewModel>() {

    companion object {
        fun newInstance(): RankFragment {
            return RankFragment()
        }
    }

    override fun getAdapter(): BaseListAdapter<HotBean> {
        return RankAdapter(mContext, R.layout.item_rank)
    }

    override fun getViewModel(): BaseListViewModel<HotBean> {
        return RankViewModel()
    }

}