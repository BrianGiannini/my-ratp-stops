package io.myratpstop.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.myratpstop.R
import io.myratpstop.model.SchedulesReponse
import io.myratpstop.network.ApiInterface
import io.myratpstop.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RetrofitInstance.getClient().create(ApiInterface::class.java)
        val call: Call<SchedulesReponse>? = api.getSchedules()

        call?.enqueue(object : retrofit2.Callback<SchedulesReponse> {
            override fun onFailure(call: Call<SchedulesReponse>, t: Throwable) {
                Log.e("debugman", t.toString())
            }

            override fun onResponse(call: Call<SchedulesReponse>, response: Response<SchedulesReponse>) {
                if(!response.isSuccessful) {
                    Log.i("debugman", "code: ${response.code()}")
                    return
                }

                val schedules = response.body()?.getSchedules()
                Log.e("debugman", "Schedules get: ${schedules?.size}")

            }


        })

    }
}
