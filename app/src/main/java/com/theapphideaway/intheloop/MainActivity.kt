package com.theapphideaway.intheloop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatDelegate
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.theapphideaway.intheloop.Fragments.*
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : FragmentActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchToHeadlines()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchToExplore()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun switchToHeadlines() {
        val manager = supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        manager.beginTransaction()
            .attach(MainFragment())
            .replace(R.id.fragment, MainFragment()).commit()

    }
    fun switchToExplore() {
        val manager = supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        manager.beginTransaction()
            .attach(ExploreFragment())
            .replace(R.id.fragment, ExploreFragment()).commit()
    }
}
