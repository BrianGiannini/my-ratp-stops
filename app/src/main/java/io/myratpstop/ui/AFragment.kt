package io.myratpstop.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import io.myratpstop.R
import io.myratpstop.system.Constants.currentStop
import io.myratpstop.system.Constants.currentWay
import io.myratpstop.system.Tools
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.schedules_view.*

class AFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_refresh.setOnClickListener {
            Tools.beginSearch(currentStop, currentWay)
        }

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            Tools.beginSearch(currentStop, currentWay)
        }

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.ar_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_side.adapter = adapter
            }
        }

        spinner_side.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

        when (pos) {
            0 ->  {
                currentStop = "hotel+de+ville+de+boulogne+billancourt"
                currentWay = "R"
            }
            1 -> {
                currentStop = "victor+hugo"
                currentWay = "A"
            }
        }
        Tools.beginSearch(currentStop, currentWay)
    }

}
