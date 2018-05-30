package com.alfred.heartnews.ui.hot

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by alfred on 2018/5/29.
 */
class HotAdapter(fm: FragmentManager, var list: ArrayList<Fragment>, var titles: MutableList<String>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}