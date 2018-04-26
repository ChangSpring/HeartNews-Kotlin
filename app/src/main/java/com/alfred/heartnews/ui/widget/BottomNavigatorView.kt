package com.alfred.heartnews.ui.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alfred.heartnews.R

/**
 * Created by alfred on 2018/3/23.
 */
class BottomNavigatorView : LinearLayoutCompat {

//    private var mBinding: LayoutBottomNavigatorBinding? = null

    private var mIvIconHome: ImageView? = null
    private var mIvIconFind: ImageView? = null
    private var mIvIconHot: ImageView? = null
    private var mIvIconUserCenter: ImageView? = null

    var mOnBottomNavigatorViewItemClickListener: OnBottomNavigatorViewItemClickListener? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = HORIZONTAL

//        mBinding = LayoutBottomNavigatorBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_bottom_navigator,this))
        val view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_navigator, this)
        mIvIconHome = view.findViewById(R.id.ivIconHome)
        mIvIconFind = view.findViewById(R.id.ivIconFind)
        mIvIconHot = view.findViewById(R.id.ivIconHot)
        mIvIconUserCenter = view.findViewById(R.id.ivIconUserCenter)

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
                        mIvIconHome?.setImageResource(R.drawable.home_selected)
                    }
                    R.id.ivIconFind -> {
                        mIvIconFind?.setImageResource(R.drawable.find_selected)
                    }
                    R.id.ivIconHot -> {
                        mIvIconHot?.setImageResource(R.drawable.hot_selected)
                    }
                    R.id.ivIconUserCenter -> {
                        mIvIconUserCenter?.setImageResource(R.drawable.mine_selected)
                    }
                }


            }

        }
    }

    private fun resetTabIcon() {
        mIvIconHome?.setImageResource(R.drawable.home_normal)
        mIvIconFind?.setImageResource(R.drawable.find_normal)
        mIvIconHot?.setImageResource(R.drawable.hot_normal)
        mIvIconUserCenter?.setImageResource(R.drawable.mine_normal)
    }

    fun setOnBottomNavigatorViewItemClickListener(onBottomNavigatorViewItemClickListener: OnBottomNavigatorViewItemClickListener) {
        this@BottomNavigatorView.mOnBottomNavigatorViewItemClickListener = onBottomNavigatorViewItemClickListener
    }

    interface OnBottomNavigatorViewItemClickListener {
        fun onBottomNavigatorViewItemClick(position: Int, view: View)
    }

}