package com.theapphideaway.intheloop.Fragments

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.theapphideaway.intheloop.Adapters.BaseAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_featured_page.view.*
import kotlinx.android.synthetic.main.fragment_search_results.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.net.NetworkInfo
import kotlinx.android.synthetic.main.fragment_featured_page.*


class FeaturedPage : Fragment() {

    val headlineService = HeadlineService()
    private var baseAdapter: BaseAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_featured_page, container, false)

        val progressbar = rootView.findViewById<ProgressBar>(R.id.progress_bar)


        layoutManager = LinearLayoutManager(rootView.context)

        try {

            val connMgr = activity!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connMgr.activeNetworkInfo

            if (networkInfo != null && networkInfo.isConnected) {
                // fetch data

            GlobalScope.launch(Dispatchers.Main) {
                    var headline = headlineService.getHeadlineApi().getSpecificHeadlines(
                        "us",
                        "general",
                        100,
                        "3032d7f983094e72b4c01be17235b206"
                    ).await()

                    if (headline.isSuccessful) {
                        baseAdapter = BaseAdapter(headline.body()!!, rootView.context)

                        featured_recycler_view.adapter = baseAdapter
                        featured_recycler_view.layoutManager = layoutManager
                    } else {
                        featured_recycler_view.adapter = null
                        featured_recycler_view.layoutManager = null
                    }
                    progressbar.visibility = View.GONE
                }


                featured_recycler_view.adapter = baseAdapter
                featured_recycler_view.layoutManager = layoutManager

            } else {
                rootView.featured_recycler_view.adapter = baseAdapter
                rootView.featured_recycler_view.layoutManager = layoutManager
                progressbar.visibility = View.GONE
                AlertDialog.Builder(requireContext()).setTitle("No Internet Connection")
                    .setMessage("Please check your internet connection and try again")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
            }
        } catch (ex: Exception){
            println("Hello world")
        }


        return rootView
    }

}
