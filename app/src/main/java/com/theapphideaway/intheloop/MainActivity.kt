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
                1 -> UsPage()
                2 -> WorldPage()
                3 -> BusinessPage()
                4 -> TechPage()
                5 -> EntertainmentPage()
                6 -> SportsPage()
                7 -> SciencePage()



                else -> {
                    return HealthPage()
                }
            }
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 9
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Featured"
                1 -> "US"
                2 -> "World"
                3 -> "Business"
                4 -> "Tech"
                5 -> "Entertainment"
                6 -> "Sports"
                7 -> "Science"

                else -> {
                    return "Health"
                }
            }
        }
    }
}
