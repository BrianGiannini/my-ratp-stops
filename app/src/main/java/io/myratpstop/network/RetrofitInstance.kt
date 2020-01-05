package io.myratpstop.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // https://api-ratp.pierre-grimaud.fr/v4/schedules/buses/126/hotel+de+ville+de+boulogne+billancourt/R
    private const val BASE_URL = "https://api-ratp.pierre-grimaud.fr/v4/schedules/"

//    private val okHttp = OkHttpClient.Builder()

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttp.build())
            .build()
    }
}