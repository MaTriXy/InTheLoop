package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.intheloop.Adapters.ScienceAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_science_page.view.*
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
 * [SciencePage.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SciencePage.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SciencePage : Fragment() {
    val headlineService = HeadlineService()
    private var scienceAdapter: ScienceAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_science_page, container, false)

        GlobalScope.launch(Dispatchers.Main) {
            var headline = headlineService.getHeadlineApi().getSpecificHeadlines(
                "us",
                "science",
                100,
                "5b4577ccf70249d3822d400479cd2f44"
            ).await()

            layoutManager = LinearLayoutManager(rootView.context)
            scienceAdapter = ScienceAdapter(headline, rootView.context)

            rootView.science_recycler_view.adapter = scienceAdapter
            rootView.science_recycler_view.layoutManager = layoutManager
        }
        return rootView
    }
}
