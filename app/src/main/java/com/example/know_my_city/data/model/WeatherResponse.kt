package com.example.know_my_city.data.model

data class RespuestaClima(
    val current_weather: ClimaActual // objeto que contiene la información del clima actual
)

data class ClimaActual(
    val temperature: Double, // temperatura en grados Celsius
    val windspeed: Double, // velocidad del viento en kilómetros por hora
    val weathercode: Int// código de clima
)