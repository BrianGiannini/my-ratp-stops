package io.myratpstop.system

import io.myratpstop.model.ScheduleModel
import io.myratpstop.network.SchedulesApiService
import io.myratpstop.ui.AFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.schedules_view.*
import timber.log.Timber

object Tools {

    var disposable: Disposable? = null
    var fragment = AFragment()

    private val schedulesApiService by lazy {
        SchedulesApiService.createService()
    }

    fun beginSearch(type: String, way: String) {
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
        fragment.time1.text = schedules[0].message
        fragment.time2.text = schedules[1].message
    }

}