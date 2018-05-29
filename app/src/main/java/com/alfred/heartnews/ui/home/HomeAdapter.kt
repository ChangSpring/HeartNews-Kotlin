package com.alfred.heartnews.ui.home

import android.content.Context
import com.alfred.heartnews.R
import com.alfred.heartnews.data.module.HomeBean
import com.alfred.heartnews.global.image.ImageLoaderUtil
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by alfred on 2018/5/23.
 */
class HomeAdapter(var context: Context?, layoutResId: Int) : BaseListAdapter<HomeBean.IssueListBean.ItemListBean>(context, layoutResId) {
    override fun convert(helper: BaseViewHolder?, item: HomeBean.IssueListBean.ItemListBean?) {
        var bean = item
        var title = bean?.data?.title
        var category = bean?.data?.category
        var minute = bean?.data?.duration?.div(60)
        var second = bean?.data?.duration?.minus((minute?.times(60)) as Long)
        var realMinute: String
        var realSecond: String
        if (minute!! < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second!! < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        var playUrl = bean?.data?.playUrl
        var photo = bean?.data?.cover?.feed
        var author = bean?.data?.author
        helper?.let {
            ImageLoaderUtil.display(context, helper.getView(R.id.iv_photo), photo as String)
            helper.setText(R.id.tv_title, title)
            helper.setText(R.id.tv_detail, "发布于 $category / $realMinute:$realSecond")
            if (author != null) {
                ImageLoaderUtil.display(context!!, helper.getView(R.id.iv_user), author.icon)
            } else {
                helper.setGone(R.id.iv_user, true)
            }
        }

    }

}