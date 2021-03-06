package com.alfred.heartnews.ui.home

import android.view.View
import com.alfred.heartnews.R
import com.alfred.heartnews.data.module.HomeBean
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.alfred.heartnews.ui.base.BaseListFragment
import com.alfred.heartnews.ui.base.BaseListViewModel
import com.alfred.heartnews.ui.viewmodel.HomeViewModel
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * Created by alfred on 2018/3/23.
 */
class HomeFragment : BaseListFragment<HomeBean.IssueListBean.ItemListBean, HomeViewModel>() {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        (mViewModel as HomeViewModel).setOnRequestListener(object : OnRequestListener<MutableList<HomeBean.IssueListBean.ItemListBean>> {
//            override fun onFailure(message: String?) {
//                refreshFailed()
//            }
//
//            override fun onSuccess(t: MutableList<HomeBean.IssueListBean.ItemListBean>?) {
//                updateAdapter(t)
//            }
//        })
//        super.onActivityCreated(savedInstanceState)
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//    }

    override fun getAdapter(): BaseListAdapter<HomeBean.IssueListBean.ItemListBean> {
        return HomeAdapter(mContext, R.layout.item_home)
    }

    override fun onRecyclerViewItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }

    override fun getViewModel(): BaseListViewModel<HomeBean.IssueListBean.ItemListBean> {
        return HomeViewModel(mContext)
    }

}