package com.example.know_my_city.data.repository

import com.example.know_my_city.data.model.RespuestaClima
import com.example.know_my_city.data.network.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepository {

    private val clienteHttp = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    suspend fun obtenerClima(latitud: Double, longitud: Double): RespuestaClima {
        return clienteHttp.obtenerClima(latitud, longitud)
    }
}