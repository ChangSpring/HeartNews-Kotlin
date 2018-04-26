package com.alfred.heartnews.ui

import android.support.v4.app.Fragment
import com.alfred.heartnews.ui.find.FindFragment
import com.alfred.heartnews.ui.home.HomeFragment
import com.alfred.heartnews.ui.hot.HotFragment
import com.alfred.heartnews.ui.user.UserFragment
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter

/**
 * Created by alfred on 2018/3/21.
 */
class FragmentAdapter : FragmentNavigatorAdapter {
    companion object {
        val TAB_TITLE = arrayOf("首页", "发现", "热门", "我的")
    }

    override fun onCreateFragment(p0: Int): Fragment {
        return when (p0) {
            0 -> {
                HomeFragment.newInstance()
            }
            1 -> {
                FindFragment.newInstance()
            }
            2 -> {
                HotFragment.newInstance()
            }
            3 -> {
                UserFragment.newInstance()
            }
            else -> {
                HomeFragment.newInstance()
            }
        }
    }

    override fun getTag(p0: Int): String {
        return "Hello " + p0
    }

    override fun getCount(): Int {
        return TAB_TITLE.size
    }
}