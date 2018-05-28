package com.alfred.heartnews.ui.home

import android.content.Context
import com.alfred.heartnews.ui.base.BaseListAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by alfred on 2018/5/23.
 */
class HomeAdapter(context: Context?,layoutResId : Int) : BaseListAdapter<HomeBean.IssueListBean.ItemListBean>(context,layoutResId) {
    override fun convert(helper: BaseViewHolder?, item: HomeBean.IssueListBean.ItemListBean?) {
        var bean = item
        var title = bean?.data?.title
        var category = bean?.data?.category
        var minute = bean?.data?.duration?.div(60)
        var second = bean?.data?.duration?.minus((minute?.times(60)) as Long )
        var realMinute : String
        var realSecond : String
        if(minute!!<10){
            realMinute = "0"+minute
        }else{
            realMinute = minute.toString()
        }
        if(second!!<10){
            realSecond = "0"+second
        }else{
            realSecond = second.toString()
        }
        var playUrl = bean?.data?.playUrl
        var photo = bean?.data?.cover?.feed
        var author = bean?.data?.author
//        ImageLoadUtils.display(context!!,holder?.iv_photo, photo as String)
//        holder?.tv_title?.text = title
//        holder?.tv_detail?.text = "发布于 $category / $realMinute:$realSecond"
//        if(author!=null){
//            ImageLoadUtils.display(context!!,holder?.iv_user,author.icon as String)
//        }else{
//            holder?.iv_user?.visibility = View.GONE
//        }
    }

}