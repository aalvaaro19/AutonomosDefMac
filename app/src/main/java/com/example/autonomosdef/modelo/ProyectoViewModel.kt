package com.example.autonomosdef.modelo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class SharedViewModelProyecto : ViewModel(){
    private val _idProyecto = MutableStateFlow<Int?>(null)
    val idProyecto: StateFlow<Int?> = _idProyecto

    fun actualizarId(id:Int) {
        _idProyecto.value = id
    }

}
class ProyectoViewModel : ViewModel(){
    var proyectos by mutableStateOf<List<Proyecto>>(emptyList())
    private val _proyectoGuardado = MutableLiveData<Proyecto>()
    fun guardarProyecto(proyecto: Proyecto) {
        viewModelScope.launch {
            try {
                val response = RetrofitService.instance.guardarProyectosId(0, proyecto)
                if (response.isSuccessful) {
                    _proyectoGuardado.value = response.body()
                    Log.d("ProyectoGuardado", "Éxito: ${response.body()}")
                } else {
                    Log.e("ProyectoGuardado", "Error en la respuesta: ${response.code()}")
                    Log.e("ProyectoGuardado", "Cuerpo de la respuesta en caso de error: ${response.errorBody()?.string()}")

                }
            } catch (e: Exception) {
                Log.e("ProyectoGuardado", "Error general: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun agregarTareas(idProyecto: Proyecto, tarea: Tarea) {
        viewModelScope.launch {
            try {
                val response = RetrofitService.instance.agregarTareasId(idProyecto.id, 0, tarea)
                if (response.isSuccessful) {
                    _proyectoGuardado.value = response.body()
                    Log.d("TareaGuardada", "Éxito: ${response.body()}")
                } else {
                    Log.e("TareaGuardada", "Error en la respuesta: ${response.code()}")
                    Log.e("TareaGuardada", "Cuerpo de la respuesta en caso de error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("TareaGuardada", "Error general: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    suspend fun getProyecto(idProyecto: Int): Response<Proyecto>? {
        try {
            val proyecto : Response<Proyecto> = RetrofitService.instance.getProyecto(0,idProyecto)
            return proyecto
        } catch (e: Exception) {
            Log.e("ClienteViewModel", "Error al obtener el ID del cliente: $e")
            return null
        }
    }

    suspend fun editarProyecto(idProyecto: Int, id: Int, proyecto: Proyecto): Proyecto?{
        try {
            val response = RetrofitService.instance.editarProyectosId(idProyecto, id, proyecto)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e("ClienteViewModel", "Error en la respuesta: ${response.code()}")
                return null
            }
        } catch (e: IOException) {
            Log.e("ClienteViewModel", "Excepción de E/S durante la llamada a la API: $e")
        } catch (e: HttpException) {
            Log.e("ClienteViewModel", "Error HTTP durante la llamada a la API: ${e.code()}")
        } catch (e: Exception) {
            Log.e("ClienteViewModel", "Excepción durante la llamada a la API: $e")
        }
        return null
    }

    suspend fun borrarProyecto(idProyecto: Int): Boolean {
        try {
            val response = RetrofitService.instance.borrarProyectosId(idProyecto)
            if (response.isSuccessful) {
                val proyectoExistente = proyectos.find { it.idProyecto == idProyecto }
                if (proyectoExistente != null) {
                    proyectos = proyectos.filter { it.idProyecto != idProyecto }
                } else {
                    Log.e("ClienteViewModel", "Intento de borrar un cliente que no existe")
                }
            } else {
                Log.e("ClienteViewModel", "Error en la respuesta: ${response.code()}")
            }
            return response.isSuccessful
        } catch (e: Exception) {
            Log.e("ClienteViewModel", "Excepción durante la llamada a la API: $e")
            return false
        }
    }

    suspend fun editarTarea(idProyecto: Int, id: Int, titulo: String, tarea: Tarea): Proyecto? {
        try {
            val response = RetrofitService.instance.editarTareasId(idProyecto, id, titulo, tarea)
            if (response.isSuccessful) {
                return response.body()
            } else {
                Log.e("ClienteViewModel", "Error en la respuesta: ${response.code()}")
                return null
            }
        } catch (e: IOException) {
            Log.e("ClienteViewModel", "Excepción de E/S durante la llamada a la API: $e")
        } catch (e: HttpException) {
            Log.e("ClienteViewModel", "Error HTTP durante la llamada a la API: ${e.code()}")
        } catch (e: Exception) {
            Log.e("ClienteViewModel", "Excepción durante la llamada a la API: $e")
        }
        return null
    }

    suspend fun eliminarTarea(idProyecto: Int, id: Int, tarea: Tarea): Boolean {
        try {
            val response = RetrofitService.instance.borrarTareasId(idProyecto, id, tarea)
            if (response.isSuccessful) {
                val proyectoExistente = proyectos.find { it.idProyecto == idProyecto }
                if (proyectoExistente != null) {
                    proyectos = proyectos.filter { it.idProyecto != idProyecto }
                } else {
                    Log.e("TareaB", "Intento de borrar un cliente que no existe")
                }
            } else {
                Log.e("TareaB", "Error en la respuesta: ${response.code()}")
            }
            return response.isSuccessful
        } catch (e: Exception) {
            Log.e("TareaB", "Excepción durante la llamada a la API: $e")
            return false
        }
    }
}