@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.autonomosdef.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autonomosdef.R
import com.example.autonomosdef.modelo.Proveedor
import com.example.autonomosdef.modelo.ProveedorViewModel
import com.example.autonomosdef.modelo.RetrofitService
import com.example.autonomosdef.modelo.ShareViewModelProveedor
import com.example.autonomosdef.modelo.SharedViewModelCliente
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun MyApiProveedores(onSuccess: (List<Proveedor>) -> Unit, onError: (String) -> Unit) {
    var proveedoresLista by remember { mutableStateOf<List<Proveedor>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitService.instance.listarProveedoresId(0)
                proveedoresLista = response // Actualiza el estado con la respuesta
                onSuccess(response) // Llama a la función onSuccess
            } catch (e: IOException) {
                // Manejo del error
                error = "Error de conexión: ${e.message}"
                e.printStackTrace()
                onError(error!!)
            } catch (e: HttpException) {
                // Manejo del error HTTP
                error = "Error HTTP: ${e.message}"
                e.printStackTrace()
                onError(error!!)
            } catch (e: Exception) {
                // Manejo de otros errores
                error = "Error desconocido: ${e.message}"
                e.printStackTrace()
                onError(error!!)
            }
        }
    }
}

@Composable
fun ProveedoresDetails(proveedor: Proveedor, navController: NavController, sharedViewModelProveedor: ShareViewModelProveedor) {
    val viewModel: ProveedorViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(39.dp, 15.dp, 39.dp, 0.dp)
            .border(1.dp, Color.Black)
    ) {
        Text(
            text = "${proveedor.nombre} ${proveedor.dni}",
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 5.dp)
        )
        Text(
            text = "${proveedor.empresa}",
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 5.dp)
        )
        Text(
            text = "${proveedor.direccion}",
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 5.dp)
        )
        Row {
            IconButton(
                onClick = {
                    viewModel.viewModelScope.launch {
                        try {
                            sharedViewModelProveedor.actualizarIdProveedor(proveedor.idProveedor)
                            navController.navigate("formularioProveedoresEditar")
                            Log.d("ProveedorE", "ID del proveedor: ${proveedor.idProveedor}")
                        } catch (e: Exception) {
                            Log.e("ProveedorE", "Error al obtener el ID del cliente: $e")
                        }
                    }
                }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar cliente")
            }
            IconButton(onClick = {
                viewModel.viewModelScope.launch {
                    val isDeleted = viewModel.borrarProveedor(proveedor.idProveedor)
                    navController.navigate("proveedores")
                    if (!isDeleted) {
                        Log.e("ProveedorB", "No se ha podido borrar: ${proveedor.idProveedor} ")
                    } else {
                        Log.d("ProveedorB", "Borrado con éxito: ${proveedor.idProveedor}")
                    }
                }
            }){
                Icon(Icons.Default.Delete, contentDescription = "Borrar cliente")
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = { navController.navigate("formularioFacturasP") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(49.dp, 0.dp, 49.dp, 8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(59, 64, 72, 255)
            ),
        ) {
            Text(
                text = "Añadir Factura",
                color = Color.White
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun proveedores (navController: NavController, sharedViewModelProveedor: ShareViewModelProveedor) {
    var proveedoresLista by remember { mutableStateOf<List<Proveedor>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var searchText by remember { mutableStateOf("") }
    val facturas = painterResource(R.drawable.image__4_)
    val logout = painterResource(R.drawable.image__5_)
    MyApiProveedores(
        onSuccess = { proveedoresLista = it },
        onError = { error = it }
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.White,
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        ClickableText("Clientes") {
                            navController.navigate("clientes")
                        }
                        ClickableProveedor("Proveedores") {
                            navController.navigate("proveedores")
                        }
                        ClickableText("Proyectos") {
                            navController.navigate("proyectos")
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        // Espaciado inicial
                        Spacer(modifier = Modifier.weight(1f))

                        // Icono Home
                        BottomNavigationItem(
                            selected = false,
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            icon = {
                                IconButton(onClick = {navController.navigate("portadaAplicacion")}) {
                                    Image(
                                        logout,
                                        contentDescription = "Logout",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                        )

                        // Espaciado entre iconos
                        Spacer(modifier = Modifier.weight(2.5f))

                        // Icono Search
                        BottomNavigationItem(
                            selected = false,
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            icon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Image(
                                        facturas,
                                        contentDescription = "Facturas",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                        )

                        // Espaciado entre iconos
                        Spacer(modifier = Modifier.weight(2.5f))

                        // Icono Notifications
                        BottomNavigationItem(
                            selected = false,
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    tint = Color.White,
                                    contentDescription = "Mensajes"
                                )
                            },
                        )

                        // Espaciado entre iconos
                        Spacer(modifier = Modifier.weight(2.5f))

                        // Icono Person
                        BottomNavigationItem(
                            selected = false,
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    tint = Color.White,
                                    contentDescription = "Profile"
                                )
                            },
                        )

                        // Espaciado final
                        Spacer(modifier = Modifier.weight(1f))
                    }
                },
                backgroundColor = Color.DarkGray,
            )
        }
    ) {
        Button(
        onClick = { navController.navigate("formularioProveedores") },
        modifier = Modifier
            .padding(39.dp, 75.dp, 39.dp, 0.dp)
            .fillMaxWidth(),
        colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
    ) {
        Text(
            text = "Añadir Proveedor",
            color = Color.White
        )
    }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 120.dp, 0.dp, 0.dp)

        ) {
            // Barra de búsqueda
            item {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(39.dp, 10.dp, 39.dp, 0.dp),
                    placeholder = { Text("Buscar proveedores...") },
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    // Lógica para limpiar la búsqueda si es necesario
                                    searchText = ""
                                }
                            ) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }else{
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            }
            Log.d("Proveedor", proveedoresLista?.size.toString())
            proveedoresLista?.let {
                items(it) {
                        proveedor ->
                    ProveedoresDetails(proveedor = proveedor, navController = navController, sharedViewModelProveedor)
                }
            }
        }
    }
}

@Composable
fun ClickableText(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(8.dp),
        color = Color.White,
    )
}
@Composable
fun ClickableProveedor(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(8.dp),
        color = Color.White,
        textDecoration = TextDecoration.Underline,
    )
}