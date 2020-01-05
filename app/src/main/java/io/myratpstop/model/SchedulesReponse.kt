package io.myratpstop.model

data class SchedulesReponse (
    private val schedules: MutableList<Schedule>
) {
    fun getSchedules(): MutableList<Schedule> {
        return schedules
    }
}