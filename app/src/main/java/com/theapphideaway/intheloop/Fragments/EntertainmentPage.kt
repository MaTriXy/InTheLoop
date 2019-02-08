package com.theapphideaway.intheloop.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theapphideaway.intheloop.Adapters.EntertainmentAdapter
import com.theapphideaway.intheloop.R
import com.theapphideaway.intheloop.Services.HeadlineService
import kotlinx.android.synthetic.main.fragment_entertainment_page.view.*
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
 * [EntertainmentPage.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EntertainmentPage.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EntertainmentPage : Fragment() {

    val headlineService = HeadlineService()
    private var entertainmentAdapter: EntertainmentAdapter? = null
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
                "5b4577ccf70249d3822d400479cd2f44"
            ).await()

            layoutManager = LinearLayoutManager(rootView.context)
            entertainmentAdapter = EntertainmentAdapter(headline, rootView.context)

            rootView.entertainment_recycler_view.adapter = entertainmentAdapter
            rootView.entertainment_recycler_view.layoutManager = layoutManager
        }
        return rootView
    }
}
