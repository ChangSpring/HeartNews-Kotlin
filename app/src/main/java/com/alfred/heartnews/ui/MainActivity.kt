package com.alfred.heartnews.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.alfred.heartnews.R
import com.alfred.heartnews.databinding.ActivityMainBinding
import com.alfred.heartnews.ui.base.BaseActivity
import com.alfred.heartnews.ui.widget.BottomNavigatorView
import com.aspsine.fragmentnavigator.FragmentNavigator

class MainActivity : BaseActivity(), BottomNavigatorView.OnBottomNavigatorViewItemClickListener {

    private var mNavigator: FragmentNavigator? = null

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mBottomNavigatorView : BottomNavigatorView

    companion object {
        const val DEFAULT_POSITION = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mBottomNavigatorView = mBinding.bottomNavigatorView
        mBottomNavigatorView.setOnBottomNavigatorViewItemClickListener(this)

        mNavigator = FragmentNavigator(supportFragmentManager, FragmentAdapter(), R.id.fl_content_main)
        mNavigator?.setDefaultPosition(DEFAULT_POSITION)
        mNavigator?.onCreate(savedInstanceState)

        mBottomNavigatorView.mOnBottomNavigatorViewItemClickListener = this

        setCurrentTab(mNavigator?.currentPosition!!)
    }

    private fun setCurrentTab(position: Int) {
        mNavigator?.showFragment(position)
        mBottomNavigatorView.select(position)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mNavigator?.onSaveInstanceState(outState)
    }

    override fun onBottomNavigatorViewItemClick(position: Int, view: View) {
        setCurrentTab(position)
    }

}