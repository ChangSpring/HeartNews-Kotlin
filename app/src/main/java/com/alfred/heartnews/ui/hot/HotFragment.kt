package com.alfred.heartnews.ui.hot

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alfred.heartnews.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Created by alfred on 2018/3/23.
 */
class HotFragment : BaseFragment() {

//    var mBinding: FragmentHotBinding? = null
    var mTabs = listOf("周排行", "月排行", "总排行").toMutableList()
    lateinit var mFragments: ArrayList<Fragment>
    val STRATEGY = arrayOf("weekly", "monthly", "historical")

    companion object {
        fun newInstance(): HotFragment {
            return HotFragment()
        }

        val STRATEGY_EXTRA = "strategy"
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hot, container, false)
//        return mBinding?.root
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var weekFragment = RankFragment()
        var weekBundle = Bundle()
        weekBundle.putString(STRATEGY_EXTRA, STRATEGY[0])
        weekFragment.arguments = weekBundle
        var monthFragment = RankFragment()
        var monthBundle = Bundle()
        monthBundle.putString(STRATEGY_EXTRA, STRATEGY[1])
        monthFragment.arguments = monthBundle
        var allFragment = RankFragment()
        var allBundle = Bundle()
        allBundle.putString(STRATEGY_EXTRA, STRATEGY[2])
        allFragment.arguments = allBundle
        mFragments = ArrayList()
        mFragments.add(weekFragment as Fragment)
        mFragments.add(monthFragment as Fragment)
        mFragments.add(allFragment as Fragment)

        vp_content.adapter = HotAdapter(childFragmentManager,mFragments,mTabs)
        tabs.setupWithViewPager(vp_content)
    }
}
