package io.myratpstop.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.myratpstop.R
import io.myratpstop.model.ScheduleModel
import io.myratpstop.network.SchedulesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val schedulesApiService by lazy {
        SchedulesApiService.createService()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beginSearch()
    }

    override fun onPause() {
        super.onPause()

        disposable?.dispose()
    }

    private fun beginSearch() {
        disposable =
            schedulesApiService.getSchedules()
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
    }
}
