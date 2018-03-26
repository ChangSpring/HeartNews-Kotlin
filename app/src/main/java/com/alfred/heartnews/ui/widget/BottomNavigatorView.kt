package com.alfred.heartnews.ui.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alfred.heartnews.R
import com.alfred.heartnews.databinding.LayoutBottomNavigatorBinding

/**
 * Created by alfred on 2018/3/23.
 */
class BottomNavigatorView : LinearLayoutCompat {

    private var mBinding: LayoutBottomNavigatorBinding? = null

    var mOnBottomNavigatorViewItemClickListener: OnBottomNavigatorViewItemClickListener? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = HORIZONTAL
        mBinding = LayoutBottomNavigatorBinding.bind(View.inflate(context, R.layout.layout_bottom_navigator, this))

        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.setOnClickListener {
                mOnBottomNavigatorViewItemClickListener?.onBottomNavigatorViewItemClick(i, view)
            }
        }

    }

    fun select(position: Int) {
        resetTabIcon()
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (i == position) {
                selectedChild(childView, true)
            }
        }
    }

    private fun selectedChild(childView: View, select: Boolean) {
        if (childView is ViewGroup) {
            childView.isSelected = select
            for (i in 0 until childView.childCount) {
                selectedChild(childView.getChildAt(i), select)
            }
        } else {
            if (childView is ImageView) {
                when (childView.id) {
                    R.id.ivIconHome -> {
                        mBinding?.ivIconHome?.setImageResource(R.drawable.home_selected)
                    }
                    R.id.ivIconFind -> {
                        mBinding?.ivIconFind?.setImageResource(R.drawable.find_normal)
                    }
                    R.id.ivIconHot -> {
                        mBinding?.ivIconHot?.setImageResource(R.drawable.hot_normal)
                    }
                    R.id.ivIconUserCenter -> {
                        mBinding?.ivIconUserCenter?.setImageResource(R.drawable.mine_normal)
                    }
                }


            }

        }
    }

    private fun resetTabIcon() {
        mBinding?.ivIconHome?.setImageResource(R.drawable.home_normal)
        mBinding?.ivIconFind?.setImageResource(R.drawable.find_normal)
        mBinding?.ivIconHot?.setImageResource(R.drawable.hot_normal)
        mBinding?.ivIconUserCenter?.setImageResource(R.drawable.mine_normal)
    }

    fun setOnBottomNavigatorViewItemClickListener(onBottomNavigatorViewItemClickListener: OnBottomNavigatorViewItemClickListener) {
        this@BottomNavigatorView.mOnBottomNavigatorViewItemClickListener = onBottomNavigatorViewItemClickListener
    }

    interface OnBottomNavigatorViewItemClickListener {
        fun onBottomNavigatorViewItemClick(position: Int, view: View)
    }

}