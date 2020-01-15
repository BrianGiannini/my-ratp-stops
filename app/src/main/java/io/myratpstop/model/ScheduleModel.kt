package io.myratpstop.model

object ScheduleModel {
    data class AllResults(val result: Result)
    data class Result(val schedules: List<Schedule>)
    data class Schedule(val message: String)
}