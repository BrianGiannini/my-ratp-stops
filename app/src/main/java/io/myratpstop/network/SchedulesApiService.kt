package io.myratpstop.network

import io.myratpstop.model.ScheduleModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface SchedulesApiService {

    // @Path("type") val type: String, @Path("code") val code: String, @Path("station") val station: String,
    // @Path("war") val war: String
    @GET("buses/126/hotel+de+ville+de+boulogne+billancourt/R")
    fun getSchedules(): Observable<ScheduleModel.AllResults>

    companion object RetrofitInstance {

        // https://api-ratp.pierre-grimaud.fr/v4/schedules/buses/126/hotel+de+ville+de+boulogne+billancourt/R
        private const val BASE_URL = "https://api-ratp.pierre-grimaud.fr/v4/schedules/"

        fun createService(): SchedulesApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(SchedulesApiService::class.java)
        }
    }
}
