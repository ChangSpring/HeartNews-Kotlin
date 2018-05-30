package com.alfred.heartnews.ui.find

import android.content.Context
import com.alfred.heartnews.R
import com.alfred.heartnews.data.module.FindBean
import com.alfred.heartnews.global.image.ImageLoaderUtil
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by alfred on 2018/5/29.
 */
class FindAdapter(var context: Context?, layoutResId: Int) : BaseListAdapter<FindBean>(context, layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: FindBean?) {
        ImageLoaderUtil.display(context,helper?.getView(R.id.iv_photo) , item?.bgPicture!!)
        helper?.setText(R.id.tv_title,item.name)
    }

}