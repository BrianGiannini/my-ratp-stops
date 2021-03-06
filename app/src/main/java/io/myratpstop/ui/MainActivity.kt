package io.myratpstop.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import io.myratpstop.R
import io.myratpstop.model.ScheduleModel
import io.myratpstop.network.SchedulesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.schedules_view.*
import timber.log.Timber


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var currentStop = "hotel+de+ville+de+boulogne+billancourt"
    var currentWay = "R"

    private val schedulesApiService by lazy {
        SchedulesApiService.createService()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ArrayAdapter.createFromResource(
            this,
            R.array.ar_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_side.adapter = adapter
        }

        spinner_side.onItemSelectedListener = this

        fab_refresh.setOnClickListener {
            beginSearch(currentStop, currentWay)
        }

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            beginSearch(currentStop, currentWay)
        }
    }

    override fun onResume() {
        super.onResume()

        beginSearch(currentStop, currentWay)
    }

    override fun onPause() {
        super.onPause()

        disposable?.dispose()
    }

    private fun beginSearch(type: String, way: String) {
        disposable =
            schedulesApiService.getSchedules(type, way)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> displaySchedules(result.result.schedules) },
                    { error -> Timber.e(error) }
                )
    }

    private fun displaySchedules(schedules: List<ScheduleModel.Schedule>) {
        time1.text = schedules[0].message
        time2.text = schedules[1].message
        stop1.text = schedules[0].destination
        stop2.text = schedules[1].destination
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {

        when (pos) {
            0 ->  {
                currentStop = "rue+du+point+du+jour"
                currentWay = "R"
            }
            1 -> {
                currentStop = "victor+hugo"
                currentWay = "A"
            }
        }
        beginSearch(currentStop, currentWay)
    }

}
