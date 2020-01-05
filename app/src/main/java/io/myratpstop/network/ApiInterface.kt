package io.myratpstop.network

import io.myratpstop.model.SchedulesReponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    // @Path("type") val type: String, @Path("code") val code: String, @Path("station") val station: String,
    // @Path("war") val war: String
    @GET("buses/126/hotel+de+ville+de+boulogne+billancourt/R")
    fun getSchedules(): Call<SchedulesReponse>
}