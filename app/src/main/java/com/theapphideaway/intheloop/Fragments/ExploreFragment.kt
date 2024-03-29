package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.theapphideaway.intheloop.Models.HeadlineResponse

import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_explore.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class ExploreFragment : Fragment() {

    val headlineService = HeadlineService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_explore, container, false)

        rootView.url_search_edit_text.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                GlobalScope.launch(Dispatchers.Main) {
                    var headline = headlineService.getHeadlineApi().getSearchedHeadlines(
                        rootView.url_search_edit_text.text.toString(),
                        100,
                        "3032d7f983094e72b4c01be17235b206"
                    ).await()

                    switchToResults(headline.body()!!, rootView.url_search_edit_text.text.toString())
                }
            }
            false
        })


        return rootView
    }

    private fun switchToResults(headline: HeadlineResponse, searchText: String) {
        val manager = activity!!.supportFragmentManager
        manager.findFragmentById(R.id.fragment)
        var transaction = manager.beginTransaction()
        var searchResults = SearchResults()

        val bundle = Bundle()
        bundle.putSerializable("results", headline)
        bundle.putString("SearchText", searchText)
        searchResults.arguments = bundle

        transaction.attach(SearchResults())
        transaction.replace(R.id.fragment, searchResults).commit()

    }
}
