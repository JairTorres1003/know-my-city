package com.example.know_my_city.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.know_my_city.data.model.RespuestaClima
import com.example.know_my_city.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//Clase ViewModel para obtener el clima
class ClimaViewModel : ViewModel() {

    private val _clima = MutableStateFlow<RespuestaClima?>(null) // estado mutable para el clima
    val clima: StateFlow<RespuestaClima?> = _clima // estado inmutable para el clima (externo)

    init {
        cargarClima(4.61, -74.07) // cargar el clima por defecto (Bogotá)
    }

    //funcion para cargar el clima desde el repositorio de la API
    fun cargarClima(latitud: Double, longitud: Double) {
        viewModelScope.launch { // lanza una corrutina para obtener el clima
            try {
                _clima.value = WeatherRepository.obtenerClima(latitud, longitud) //_clima: estado mutable para el clima
            } catch (e: Exception) {
                _clima.value = null // si hay un error, _clima: estado mutable para el clima
                e.printStackTrace() // imprime el error en la consola
            }
        }
    }
}