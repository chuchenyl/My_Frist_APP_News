package com.novices.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.makeramen.roundedimageview.RoundedImageView
import com.novices.news.Util.showToast
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val fragmentList = listOf(HomeFragment(),TestFragment("第一个"),TestFragment("第二个"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val VP_content = findViewById<ViewPager>(R.id.VP_Content)

        VP_content.offscreenPageLimit = fragmentList.size
        VP_content.adapter=MyAdapter(supportFragmentManager)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> VP_content.currentItem = 0
                R.id.menu_image -> VP_content.currentItem = 1
                R.id.menu_personal -> VP_content.currentItem = 2

            }


            false
        }

        VP_content.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                bottomNav.menu.getItem(position).isChecked = true

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })




    }


    inner class MyAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

    }


}
















