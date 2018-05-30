package com.alfred.heartnews.ui.hot

import android.content.Context
import com.alfred.heartnews.data.module.HotBean
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by alfred on 2018/5/29.
 */
class RankAdapter(var context: Context,var layoutResId : Int) : BaseListAdapter<HotBean> (context,layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: HotBean?) {
    }

}