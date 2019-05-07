package com.theapphideaway.intheloop.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.theapphideaway.intheloop.Adapters.BaseAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_business_page.view.*
import kotlinx.android.synthetic.main.fragment_health_page.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class HealthPage : Fragment() {
    val headlineService = HeadlineService()
    private var baseAdapter: BaseAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_health_page, container, false)
        val progressbar = rootView.findViewById<ProgressBar>(R.id.progress_bar)

        val connMgr = activity!!
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connMgr.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            // fetch data

            GlobalScope.launch(Dispatchers.Main) {
                var headline = headlineService.getHeadlineApi().getSpecificHeadlines(
                    "us",
                    "health",
                    100,
                    "3032d7f983094e72b4c01be17235b206"
                ).await()

                layoutManager = LinearLayoutManager(rootView.context)
                baseAdapter = BaseAdapter(headline.body()!!, rootView.context)

            rootView.health_recycler_view.adapter = baseAdapter
            rootView.health_recycler_view.layoutManager = layoutManager
                progressbar.visibility = View.GONE
            }
        }else {
            rootView.health_recycler_view.adapter = baseAdapter
            rootView.health_recycler_view.layoutManager = layoutManager
            progressbar.visibility = View.GONE
        }
        return rootView
    }
}
