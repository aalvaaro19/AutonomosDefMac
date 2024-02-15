@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.autonomosdef.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.autonomosdef.R
import retrofit2.HttpException
import java.io.IOException
import android.util.Log
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.autonomosdef.modelo.Cliente
import com.example.autonomosdef.modelo.ClienteViewModel
import com.example.autonomosdef.modelo.RetrofitService
import com.example.autonomosdef.modelo.SharedViewModelCliente
import kotlinx.coroutines.launch



@Composable
fun MyApiCliente(onSuccess: (List<Cliente>) -> Unit, onError: (String) -> Unit) {
    var clientesLista by remember { mutableStateOf<List<Cliente>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitService.instance.listarClientes(0)
                clientesLista = response // Actualiza el estado con la respuesta
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
fun ClientesDetails(cliente: Cliente, navController: NavController, sharedViewModelCliente: SharedViewModelCliente) {
    val viewModel: ClienteViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(39.dp, 15.dp, 39.dp, 0.dp)
            .border(1.dp, Color.Black)
    ) {
        Text(
            text = "${cliente.nombre} ${cliente.dni}",
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 5.dp)
        )
        Text(
            text = "${cliente.empresa}",
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 5.dp)
        )
        Text(
            text = "${cliente.direccion}",
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 5.dp)
        )
        Row {
            IconButton(
                onClick = {
                    viewModel.viewModelScope.launch {
                        try {
                            sharedViewModelCliente.actualizarIdCliente(cliente.idCliente)
                            navController.navigate("formularioClientesEditar")
                            Log.d("IconButton", "ID del cliente: ${cliente.idCliente}")
                        } catch (e: Exception) {
                            Log.e("IconButton", "Error al obtener el ID del cliente: $e")
                        }
                    }
                }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar cliente")
            }
            IconButton(onClick = {
                viewModel.viewModelScope.launch {
                    val isDeleted = viewModel.borrarCliente(cliente.idCliente)
                    navController.navigate("clientes")
                    if (!isDeleted) {
                        Log.e("Cliente", "No se ha podido borrar: ${cliente.idCliente} ")
                    } else {
                        Log.d("Cliente", "Borrado con éxito: ${cliente.idCliente}")
                    }
                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Borrar cliente")
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {navController.navigate("formularioFacturas")},
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
fun clientes (navController: NavController, sharedViewModelCliente: SharedViewModelCliente) {
    var clientesLista by remember { mutableStateOf<List<Cliente>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var searchText by remember { mutableStateOf("") }
    val factura = painterResource(R.drawable.image__4_)
    val logout = painterResource(R.drawable.image__5_)
    MyApiCliente(
        onSuccess = { clientesLista = it },
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
                        ClickableCliente("Clientes") {
                            navController.navigate("clientes")
                        }
                        ClickableTextButton("Proveedores") {
                            navController.navigate("proveedores")
                        }
                        ClickableTextButton("Proyectos") {
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
                                        factura,
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
                                IconButton(onClick = {
                                    println("El ícono ha sido presionado")
                                    navController.navigate("pruebaAPI")
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        tint = Color.White,
                                        contentDescription = "Profile"
                                    )
                                }
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
            onClick = { navController.navigate("formularioClientes") },
            modifier = Modifier
                .padding(39.dp, 75.dp, 39.dp, 0.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
        ) {
            Text(
                text = "Añadir Cliente",
                color = Color.White
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 120.dp, 0.dp, 0.dp)
            ) {
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
                    placeholder = { Text("Buscar cliente") },
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
            Log.d("Cliente", clientesLista?.size.toString())
            clientesLista?.let {
                items(it) {
                    cliente ->
                    ClientesDetails(cliente = cliente, navController = navController, sharedViewModelCliente)
                }
            }
        }
    }
}
@Composable
fun ClickableTextButton(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(8.dp),
        color = Color.White,
    )
}
@Composable
fun ClickableCliente(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(8.dp),
        color = Color.White,
        textDecoration = TextDecoration.Underline,
    )
}

private fun recargar(navController: NavController){
    navController.navigate("clientes")
}

