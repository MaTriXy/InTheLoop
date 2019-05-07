package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.theapphideaway.intheloop.Adapters.BaseAdapter
import com.theapphideaway.intheloop.Models.HeadlineResponse

import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_search_results.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class SearchResults : Fragment() {

    val headlineService = HeadlineService()
    private var baseAdapter: BaseAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search_results, container, false)

        val progressbar = rootView.findViewById<ProgressBar>(R.id.progress_bar)

        var mToolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = getString(R.string.app_name)
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

        val bundle = arguments


            val headlineResponse = bundle!!.getSerializable("results") as HeadlineResponse
        if(headlineResponse.totalResults > 0) {
            layoutManager = LinearLayoutManager(rootView.context)
            baseAdapter = BaseAdapter(headlineResponse, rootView.context)

            rootView.results_recycler_view.adapter = baseAdapter
            rootView.results_recycler_view.layoutManager = layoutManager
            progressbar.visibility = View.GONE
        }else {
            rootView.empty_image.visibility = View.VISIBLE
            rootView.no_results_text_view.visibility = View.VISIBLE
            progressbar.visibility = View.GONE
        }

        var text = bundle.getString("SearchText")

        rootView.toolbar_url_search_edit_text.setText(text)

        mToolbar.setNavigationOnClickListener {
            switchBack()
        }

        rootView.toolbar_url_search_edit_text.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                GlobalScope.launch(Dispatchers.Main) {
                    var headline = headlineService.getHeadlineApi().getSearchedHeadlines(
                        rootView.toolbar_url_search_edit_text.text.toString(),
                        100,
                        "3032d7f983094e72b4c01be17235b206"
                    ).await()


                    if(headline.body()!!.totalResults > 0) {

                        rootView.empty_image.visibility = View.GONE
                        rootView.no_results_text_view.visibility = View.GONE

                        layoutManager = LinearLayoutManager(rootView.context)
                        baseAdapter = BaseAdapter(headline.body()!!, rootView.context)

                        rootView.results_recycler_view.adapter = baseAdapter
                        rootView.results_recycler_view.layoutManager = layoutManager
                        progressbar.visibility = View.GONE
                    }
                    else{
                        rootView.results_recycler_view.adapter = null
                        rootView.results_recycler_view.layoutManager = null

                        rootView.empty_image.visibility = View.VISIBLE
                        rootView.no_results_text_view.visibility = View.VISIBLE
                        progressbar.visibility = View.GONE
                    }

                }
            }
            false
        }
        return rootView
    }

    private fun switchBack() {
        val manager = activity!!.supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        var transaction = manager.beginTransaction()

        transaction.attach(SearchResults())
        transaction.replace(R.id.fragment, ExploreFragment()).commit()

    }


}
