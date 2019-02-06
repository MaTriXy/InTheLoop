package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.intheloop.Adapters.EntertainmentAdapter
import com.theapphideaway.intheloop.Adapters.HeadlineAdapter
import com.theapphideaway.intheloop.Adapters.SportsAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_entertainment_page.view.*
import kotlinx.android.synthetic.main.fragment_sports_page.view.*
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
 * [SportsPage.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SportsPage.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SportsPage : Fragment() {
    val headlineService = HeadlineService()
    private var sportsAdapter: SportsAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_sports_page, container, false)

        GlobalScope.launch(Dispatchers.Main) {
            var headline = headlineService.getHeadlineApi().getSpecificHeadlines(
                "us",
                "sports",
                100,
                "5b4577ccf70249d3822d400479cd2f44"
            ).await()

            layoutManager = LinearLayoutManager(rootView.context)
            sportsAdapter = SportsAdapter(headline, rootView.context)

            rootView.sports_recycler_view.adapter = sportsAdapter
            rootView.sports_recycler_view.layoutManager = layoutManager
        }
        return rootView
    }
}
