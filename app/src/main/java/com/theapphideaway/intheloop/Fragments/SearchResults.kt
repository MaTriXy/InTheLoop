package com.theapphideaway.intheloop.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.intheloop.Adapters.FeaturedAdapter
import com.theapphideaway.intheloop.Adapters.ScienceAdapter
import com.theapphideaway.intheloop.Adapters.SearchResultsAdapter
import com.theapphideaway.intheloop.Models.HeadlineResponse

import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_explore.view.*
import kotlinx.android.synthetic.main.fragment_science_page.view.*
import kotlinx.android.synthetic.main.fragment_search_results.*
import kotlinx.android.synthetic.main.fragment_search_results.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchResults.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchResults.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SearchResults : Fragment() {

    val headlineService = HeadlineService()
    private var resultsAdapter: SearchResultsAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search_results, container, false)

        var mToolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = getString(R.string.app_name)
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

        val bundle = arguments


            val headlineResponse = bundle!!.getSerializable("results") as HeadlineResponse
        if(headlineResponse.totalResults > 0) {
            layoutManager = LinearLayoutManager(rootView.context)
            resultsAdapter = SearchResultsAdapter(headlineResponse, rootView.context)

            rootView.results_recycler_view.adapter = resultsAdapter
            rootView.results_recycler_view.layoutManager = layoutManager
        }else {
            rootView.empty_image.visibility = View.VISIBLE
            rootView.no_results_text_view.visibility = View.VISIBLE
        }

        var text = bundle!!.getString("SearchText")

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
                        "5b4577ccf70249d3822d400479cd2f44"
                    ).await()

                    if(headline.totalResults > 0) {

                        rootView.empty_image.visibility = View.GONE
                        rootView.no_results_text_view.visibility = View.GONE

                        layoutManager = LinearLayoutManager(rootView.context)
                        resultsAdapter = SearchResultsAdapter(headline, rootView.context)

                        rootView.results_recycler_view.adapter = resultsAdapter
                        rootView.results_recycler_view.layoutManager = layoutManager
                    }
                    else{
                        rootView.results_recycler_view.adapter = null
                        rootView.results_recycler_view.layoutManager = null

                        rootView.empty_image.visibility = View.VISIBLE
                        rootView.no_results_text_view.visibility = View.VISIBLE
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