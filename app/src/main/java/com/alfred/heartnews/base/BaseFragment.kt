package com.alfred.heartnews.base

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by alfred on 2018/3/21.
 */

abstract class BaseFragment : Fragment() {

    protected var mContext: Context? = null
    protected var mActivity: Activity? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }
}
