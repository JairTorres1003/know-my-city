package com.example.know_my_city.data.network

import com.example.know_my_city.data.model.RespuestaClima
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast") // Endpoint de la API
    suspend fun obtenerClima( // Función para obtener el clima
        @Query("latitude") latitud: Double,
        @Query("longitude") longitud: Double,
        @Query("current_weather") incluirClimaActual: Boolean = true
    ): RespuestaClima
}