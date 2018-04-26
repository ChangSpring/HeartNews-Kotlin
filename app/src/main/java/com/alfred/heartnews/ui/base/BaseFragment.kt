package com.alfred.heartnews.ui.base

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by alfred on 2018/3/23.
 */
open class BaseFragment : Fragment() {

    private var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun getContext(): Context? {
        return mContext
    }

}