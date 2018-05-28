package com.alfred.heartnews.ui.base

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by alfred on 2018/5/23.
 */
abstract class BaseListAdapter<T>(protected var mContext: Context?, layoutResId : Int) : BaseQuickAdapter<T, BaseViewHolder>(layoutResId)