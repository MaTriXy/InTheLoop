package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.intheloop.Adapters.BaseAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_entertainment_page.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EntertainmentPage : Fragment() {

    val headlineService = HeadlineService()
    private var baseAdapter: BaseAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_entertainment_page, container, false)

        GlobalScope.launch(Dispatchers.Main) {
            var headline = headlineService.getHeadlineApi().getSpecificHeadlines(
                "us",
                "entertainment",
                100,
                "3032d7f983094e72b4c01be17235b206"
            ).await()

            layoutManager = LinearLayoutManager(rootView.context)
            baseAdapter = BaseAdapter(headline, rootView.context)

            rootView.entertainment_recycler_view.adapter = baseAdapter
            rootView.entertainment_recycler_view.layoutManager = layoutManager
        }
        return rootView
    }
}
