package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.intheloop.Adapters.FeaturedAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_featured_page.view.*
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
 * [FeaturedPage.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FeaturedPage.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FeaturedPage : Fragment() {

    val headlineService = HeadlineService()
    private var featuredAdapter: FeaturedAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_featured_page, container, false)

        GlobalScope.launch(Dispatchers.Main) {
            var headline = headlineService.getHeadlineApi().getSpecificHeadlines(
                "us",
                "general",
                100,
                "5b4577ccf70249d3822d400479cd2f44"
            ).await()
//            rootView.text_view_test.text = headline.articles[0].title

            layoutManager = LinearLayoutManager(rootView.context)
            featuredAdapter = FeaturedAdapter(headline, rootView.context)

            rootView.featured_recycler_view.adapter = featuredAdapter
            rootView.featured_recycler_view.layoutManager = layoutManager
        }

        rootView.featured_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {

                }
            }
        })


        return rootView
    }

}
