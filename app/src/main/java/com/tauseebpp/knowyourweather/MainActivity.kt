package com.tauseebpp.knowyourweather

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jsonParsing()
    }

    @SuppressLint("SetTextI18n", "NewApi", "SimpleDateFormat")
    private fun jsonParsing() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?q=Pilibhit&units=metric&appid=f6af6aebf0fbc173057378fa35988016"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->

                    val obj = response.getJSONObject("main")
                    val arr = response.getJSONArray("weather")
                    pressure.text = obj.getString("pressure")
                    val tempmin1 = obj.getString("temp_min")
                    temp_min.text = "Min Temp: $tempmin1\u2103"
                    val tempmax1 = obj.getString("temp_max")
                    temp_max.text = "Max Temp: $tempmax1\u2103"
                    humidity.text = obj.getString("humidity")
                    val temp1 = obj.getString("temp")
                    temp.text = "$temp1\u2103"
                    address.text = response.getString("name")
                    val arrobject = arr.getJSONObject(0)
                    status.text = arrobject.getString("description")
                    val air = response.getJSONObject("wind")
                    wind.text = air.getString("speed")
//              val sun=java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(1605834463))
                    val sdf = SimpleDateFormat("dd-M-yyyy hh:mm:ss")
                    val currentdate = sdf.format(Date())
                    updated_at.text = currentdate

                }, { error ->
            Toast.makeText(this, "Check Your Connectivity", Toast.LENGTH_SHORT).show()

        })

        requestQueue.add(jsonObjectRequest)
    }
}
