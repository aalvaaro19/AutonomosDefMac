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


class ShareViewModelProveedor : ViewModel(){
    private val _idProveedor = MutableStateFlow<Int?>(null)
    val idProveedor: StateFlow<Int?> = _idProveedor
    fun actualizarIdProveedor(id:Int) {
        _idProveedor.value = id
    }

}
class ProveedorViewModel : ViewModel(){
    private val _proveedorGuardado = MutableLiveData<Proveedor>()
    var proveedores by mutableStateOf<List<Proveedor>>(emptyList())
    fun guardarProveedoresId(proveedor: Proveedor) {
        viewModelScope.launch {
            try {
                val response = RetrofitService.instance.guardarProveedoresId(0, proveedor)
                if (response.isSuccessful) {
                    _proveedorGuardado.value = response.body()
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

    suspend fun getProveedor(idProveedor: Int): Response<Proveedor>? {
        try {
            return RetrofitService.instance.getProveedor(0, idProveedor)
        } catch (e: Exception) {
            Log.e("ClienteViewModel", "Error al obtener el ID del cliente: $e")
            return null
        }
    }

    suspend fun editarProveedor(idProveedor: Int, id: Int, proveedor: Proveedor): Proveedor?{
        try {
            val response = RetrofitService.instance.editarProveedoresId(idProveedor, id, proveedor)
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

    suspend fun borrarProveedor(idProveedor: Int): Boolean {
        try {
            val response = RetrofitService.instance.borrarProveedoresId(idProveedor)
            if (response.isSuccessful) {
                val proveedorExistente = proveedores.find { it.idProveedor == idProveedor }
                if (proveedorExistente != null) {
                    proveedores = proveedores.filter { it.idProveedor != idProveedor }
                } else {
                    Log.e("ProveedorViewModel", "Intento de borrar un proveedor que no existe")
                }
            } else {
                Log.e("ProveedorViewModel", "Error en la respuesta: ${response.code()}")
            }
            return response.isSuccessful
        } catch (e: Exception) {
            Log.e("ProveedorViewModel", "Excepción durante la llamada a la API: $e")
            return false
        }
    }
}