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

class SharedViewModelCliente : ViewModel() {
    private val _idCliente = MutableStateFlow<Int?>(null)
    val idCliente:StateFlow<Int?> = _idCliente

    fun actualizarIdCliente(id:Int) {
        _idCliente.value = id
    }
}

class ClienteViewModel : ViewModel() {
    var clientes by mutableStateOf<List<Cliente>>(emptyList())
    var _clienteGuardado = MutableLiveData<Cliente>()
    fun guardarClienteEnBaseDeDatos(cliente: Cliente) {
        viewModelScope.launch {
            try {
                val response = RetrofitService.instance.guardarClientesId(0, cliente)
                if (response.isSuccessful) {
                    _clienteGuardado.value = response.body()
                    Log.d("ClienteGuardado", "Éxito: ${response.body()}")
                } else {
                    Log.e("ClienteGuardado", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ClienteGuardado", "Error general: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    suspend fun getCliente(idCliente: Int): Response<Cliente>? {
        try {
            val cliente : Response<Cliente> = RetrofitService.instance.getCliente(0,idCliente)
            return cliente
        } catch (e: Exception) {
            Log.e("ClienteViewModel", "Error al obtener el ID del cliente: $e")
            return null
        }
    }


    suspend fun editarClientes(idCliente: Int, id: Int, cliente: Cliente): Cliente?{
        try {
            val response = RetrofitService.instance.editarClientesId(idCliente, id, cliente)
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

    suspend fun borrarCliente(idCliente: Int): Boolean {
        try {
            val response = RetrofitService.instance.borrarClientesId(idCliente)
            if (response.isSuccessful) {
                // Verifica si el cliente existe antes de actualizar la lista local
                val clienteExistente = clientes.find { it.idCliente == idCliente }
                if (clienteExistente != null) {
                    // Actualiza la lista local excluyendo el cliente borrado
                    clientes = clientes.filter { it.idCliente != idCliente }
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
}