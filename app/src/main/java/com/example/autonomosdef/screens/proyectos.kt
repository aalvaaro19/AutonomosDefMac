@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.autonomosdef.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autonomosdef.R
import com.example.autonomosdef.modelo.Proyecto
import com.example.autonomosdef.modelo.ProyectoViewModel
import com.example.autonomosdef.modelo.RetrofitService
import com.example.autonomosdef.modelo.SharedViewModelProyecto
import com.example.autonomosdef.modelo.Tarea
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun MyApiProyecto(onSuccess: (List<Proyecto>) -> Unit, onError: (String) -> Unit) {
    var proyectosLista by remember { mutableStateOf<List<Proyecto>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitService.instance.listarProyectos(0)
            proyectosLista = response
            onSuccess(response)
        } catch (e: IOException) {
            error = "Error de conexión: ${e.message}"
            e.printStackTrace()
            onError(error!!)
        } catch (e: HttpException) {
            error = "Error HTTP: ${e.message}"
            e.printStackTrace()
            onError(error!!)
        } catch (e: Exception) {
            error = "Error desconocido: ${e.message}"
            e.printStackTrace()
            onError(error!!)
        }
    }
}

@Composable
fun ProyectoDetails(proyecto: Proyecto, navController: NavController, sharedViewModelProyecto: SharedViewModelProyecto) {
    val viewModel: ProyectoViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(39.dp, 15.dp, 39.dp, 0.dp)
            .border(1.dp, Color.Black)
    ) {
        Text(
            text = ("${proyecto.titulo}"),
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 5.dp),
            fontWeight = FontWeight.Bold
        )
        proyecto.tareas.forEachIndexed { index, tarea ->
            TareaCheckbox("${tarea.titulo}: ${tarea.tiempo}", proyecto, tarea, navController, sharedViewModelProyecto)
        }
        Row {
            IconButton(
                onClick = {
                    viewModel.viewModelScope.launch {
                        try {
                            sharedViewModelProyecto.actualizarId(proyecto.idProyecto)
                            navController.navigate("formularioProyectosEditar")
                            Log.d("EditarProyecto", "ID del cliente: ${proyecto.titulo}")
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
                    val isDeleted = viewModel.borrarProyecto(proyecto.idProyecto)
                    navController.navigate("proyectos")
                    if (!isDeleted) {
                        Log.e("Cliente", "No se ha podido borrar: ${proyecto.idProyecto} ")
                    } else {
                        Log.d("Cliente", "Borrado con éxito: ${proyecto.idProyecto}")
                    }
                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Borrar cliente")
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {navController.navigate("formularioTareas")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(49.dp, 0.dp, 49.dp, 8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(
                    59,
                    64,
                    72,
                    255
                )
            ),
        ) {
            Text(
                text = "Añadir tarea",
                color = Color.White
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun proyectos (navController: NavController, sharedViewModelProyecto: SharedViewModelProyecto){
    var proyectosLista by remember { mutableStateOf<List<Proyecto>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }
    var searchText by remember { mutableStateOf("") }
    val facturas = painterResource(R.drawable.image__4_)
    val logout = painterResource(R.drawable.image__5_)

        MyApiProyecto(
            onSuccess = { proyectos ->
                proyectosLista = proyectos
            },
            onError = { errorMsg ->
                error = errorMsg
            }
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
                        ClickableTextButt("Clientes") {
                            navController.navigate("clientes")
                        }
                        ClickableTextButt("Proveedores") {
                            navController.navigate("proveedores")
                        }
                        ClickableProyecto("Proyectos") {
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
                                IconButton(onClick = {}) {
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
            onClick = { navController.navigate("formularioProyectos") },
            modifier = Modifier
                .padding(39.dp, 75.dp, 39.dp, 0.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
        ) {
            Text(
                text = "Añadir Proyecto",
                color = Color.White
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 120.dp, 0.dp, 0.dp)
        )  {
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
            Log.d("Proyectos", proyectosLista.size.toString())
            proyectosLista?.let {
                items(it) { proyecto ->
                    ProyectoDetails(proyecto, navController, sharedViewModelProyecto)
                }
            }
        }
    }
}

@Composable
fun ClickableTextButt(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(8.dp),
        color = Color.White,
    )
}
@Composable
fun ClickableProyecto(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick.invoke() }
            .padding(8.dp),
        color = Color.White,
        textDecoration = TextDecoration.Underline,
    )
}

@Composable
fun TareaCheckbox(text: String, proyecto: Proyecto, tarea: Tarea, navController: NavController, sharedViewModelProyecto: SharedViewModelProyecto) {
    var isChecked by remember { mutableStateOf(false) }
    val viewModel: ProyectoViewModel = viewModel()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = if (isChecked) Color.Green else Color.Black, modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 5.dp),
        )
        Row {
            IconButton(
                onClick = {
                    viewModel.viewModelScope.launch {
                        try {
                            sharedViewModelProyecto.actualizarId(proyecto.idProyecto)
                            navController.navigate("formularioEdicionTareas")
                            Log.d("Tarea", "Titulo de la tarea: ${tarea.titulo}")
                        } catch (e: Exception) {
                            Log.e("IconButton", "Error al obtener el ID del cliente: $e")
                        }
                    }
                }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar tarea")
            }
            IconButton(onClick = {
                viewModel.viewModelScope.launch {
                    val isDeleted = viewModel.eliminarTarea(proyecto.idProyecto, 0, tarea)
                    navController.navigate("proyectos")
                    Log.d("ProyectoID", "ID del proyecto: ${proyecto.idProyecto}")
                    if (!isDeleted) {
                        Log.e("TareaB", "No se ha podido borrar: ${tarea.titulo} ")
                        Log.e("TareaB", "ID del proyecto: ${proyecto.idProyecto}")
                        Log.e("TareaB", "Mensaje de error ${isDeleted}")
                    } else {
                        Log.d("TareaB", "Tarea borrada con éxito: ${tarea.titulo}")
                    }
                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Borrar tarea")
            }
        }
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}