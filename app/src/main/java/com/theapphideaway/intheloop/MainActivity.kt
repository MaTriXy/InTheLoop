package com.theapphideaway.intheloop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.theapphideaway.intheloop.Fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        tab_layout.setupWithViewPager(view_pager)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        view_pager.adapter = mSectionsPagerAdapter
    }

    class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FeaturedPage()
                1 -> BusinessPage()
                2 -> TechPage()
                3 -> EntertainmentPage()
                4-> SportsPage()
                5 -> SciencePage()



                else -> {
                    return HealthPage()
                }
            }
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 7
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Featured"
                1 -> "Business"
                2 -> "Tech"
                3 -> "Entertainment"
                4 -> "Sports"
                5 -> "Science"

                else -> {
                    return "Health"
                }
            }
        }
    }
}
