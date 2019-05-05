package com.theapphideaway.intheloop.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.theapphideaway.intheloop.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {


    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val tabLayout: TabLayout = rootView.findViewById(R.id.tab_layout)

        val viewPager =rootView.findViewById<ViewPager>(R.id.view_pager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        tabLayout.setupWithViewPager(viewPager)


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager!!)

        // Set up the ViewPager with the sections adapter.
        viewPager.adapter = mSectionsPagerAdapter

        return rootView
    }

    class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FeaturedPage()
                1 -> BusinessPage()
                2 -> TechPage()
                3 -> EntertainmentPage()
                4 -> SportsPage()
                5 -> SciencePage()

                else -> {
                    return HealthPage()
                }
            }
        }

        override fun getCount(): Int {
            return 7
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Featured"
                1 -> "Business"
                2 -> "Technology"
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
