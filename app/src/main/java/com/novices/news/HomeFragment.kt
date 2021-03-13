package com.novices.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    //支持类型,top(推荐,默认),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育),junshi(军事),keji(科技),caijing(财经),shishang(时尚),youxi(游戏),qiche(汽车),jiankang(健康)
    val newsTypleList = listOf(
        "top",
        "guonei",
        "guoji",
        "yule",
        "tiyu",
        "junshi",
        "keji",
        "caijing",
        "shishang",
        "youxi",
        "qiche",
        "jiankang"
    )
    val titleList = listOf("推荐", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚", "游戏", "汽车", "健康")
    val fragmentList = ArrayList<NewsFragment>()
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var toolbar: androidx.appcompat.widget.Toolbar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = view.findViewById(R.id.TL_News)
        viewPager = view.findViewById(R.id.VP_News)
        toolbar = view.findViewById(R.id.tool_bar_home)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.inflateMenu(R.menu.home_tool_bar_menu)

        for (newsType in newsTypleList) {
            fragmentList.add(NewsFragment(newsType))
        }
        viewPager.offscreenPageLimit = titleList.size
        viewPager.adapter = activity?.supportFragmentManager?.let { MyAdapter(it) }
        tabLayout.setupWithViewPager(viewPager)


    }

    inner class MyAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }

}