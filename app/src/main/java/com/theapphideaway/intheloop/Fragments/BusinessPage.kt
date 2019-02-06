package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.intheloop.Adapters.BusinessAdapter
import com.theapphideaway.intheloop.Adapters.FeaturedAdapter
import com.theapphideaway.intheloop.Adapters.HeadlineAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_business_page.view.*
import kotlinx.android.synthetic.main.fragment_featured_page.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BusinessPage : Fragment() {
    val headlineService = HeadlineService()
    private var businessAdapter: BusinessAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_business_page, container, false)

        GlobalScope.launch(Dispatchers.Main) {
            var headline = headlineService.getHeadlineApi().getSpecificHeadlines(
                "us",
                "business",
                100,
                "5b4577ccf70249d3822d400479cd2f44"
            ).await()

            layoutManager = LinearLayoutManager(rootView.context)
            businessAdapter = BusinessAdapter(headline, rootView.context)

            rootView.business_recycler_view.adapter = businessAdapter
            rootView.business_recycler_view.layoutManager = layoutManager
        }


        return rootView
    }
}
