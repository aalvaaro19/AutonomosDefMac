package com.example.autonomosdef.modelo

import android.util.Log
import androidx.lifecycle.ViewModel

class IniciarSesionViewModel: ViewModel(){
    suspend fun iniciarSesion(nombreUser: String, password: String): Boolean {
        try {
            // Crear una instancia del servicio Retrofit para realizar la solicitud HTTP
            val retrofitService = RetrofitService.instance.iniciarSesion(nombreUser, password)
            if (retrofitService.isSuccessful) {
                return true
            } else {
                Log.e("InicioSesion", "Error en la respuesta: ${retrofitService.code()}")
                return false
            }
        } catch (e: Exception) {
            // Ocurrió una excepción durante la llamada a la API
            Log.e("InicioSesion", "Excepción durante la llamada a la API: $e")
            return false
        }
        return true
    }
}