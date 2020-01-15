package io.myratpstop.network

import io.myratpstop.model.ScheduleModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface SchedulesApiService {

    @GET("buses/126/{stop}/{way}")
    fun getSchedules(@Path("stop") type: String, @Path("way") way: String): Observable<ScheduleModel.AllResults>

    companion object RetrofitInstance {

        // https://api-ratp.pierre-grimaud.fr/v4/schedules/buses/126/hotel+de+ville+de+boulogne+billancourt/R
        // https://api-ratp.pierre-grimaud.fr/v4/schedules/buses/126/victor+hugo/A
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
