package com.example.autonomosdef.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autonomosdef.modelo.Proyecto
import com.example.autonomosdef.modelo.ProyectoViewModel
import com.example.autonomosdef.modelo.SharedViewModelProyecto
import com.example.autonomosdef.modelo.Tarea
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formularioEdicionTareas(navController: NavController, sharedViewModelProyecto: SharedViewModelProyecto) {
    var viewModel: ProyectoViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var tareas = mutableStateOf<List<Tarea>>(emptyList())
    var tarea by rememberSaveable { mutableStateOf("") }
    var updatedTarea = rememberUpdatedState(tarea)
    var tiempoEsperado by remember { mutableStateOf("") }
    val updatedTiempoEsperado = rememberUpdatedState(tiempoEsperado)
    val id : Int? by sharedViewModelProyecto.idProyecto.collectAsState()

    LaunchedEffect(Unit) {
        tarea = id?.let { viewModel.getProyecto(it)?.body()?.tareas?.last()?.titulo } ?: ""
        Log.d("getTareas", "Tarea: ${tarea?.let { it }}")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(59, 64, 72, 255),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Formulario de tareas", fontSize = 22.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("proyectos") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp, 5.dp, 0.dp),
                value = updatedTarea.value,
                onValueChange = { tarea = it },
                singleLine = true,
                label = {
                    Row {
                        Icon(
                            imageVector = if (tarea.isNotEmpty()) Icons.Default.List else Icons.Default.List,
                            contentDescription = "Person Icon",
                            tint = if (tarea.isNotEmpty()) Color.Black else Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("${tareas.value.last().titulo}")
                    }
                }
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp, 5.dp, 0.dp),
                value = updatedTiempoEsperado.value,
                onValueChange = { tiempoEsperado = it },
                singleLine = true,
                label = {
                    Row {
                        if (tiempoEsperado.isEmpty()) {
                            Icon(
                                imageVector = if (tiempoEsperado.isNotEmpty()) Icons.Default.Person else Icons.Default.Person,
                                contentDescription = "Person Icon",
                                tint = if (tiempoEsperado.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("${tareas.value.last().tiempo}")
                    }
                }
            )
            Button(
                onClick = {
                    val nuevaTarea = Tarea(tarea, tiempoEsperado, 0)
                    val nuevasTareas = tareas.value.toMutableList()  // Convertir a lista mutable

                    // Encontrar la tarea antigua y reemplazarla con la nueva tarea
                    val indiceTareaAntigua = nuevasTareas.indexOfFirst { it.titulo == tarea }
                    if (indiceTareaAntigua != -1) {
                        nuevasTareas[indiceTareaAntigua] = nuevaTarea
                    } else {
                        // Si no se encuentra la tarea antigua, agregar la nueva tarea al final
                        nuevasTareas.add(nuevaTarea)
                    }

                    tareas.value = nuevasTareas.toList()  // Actualizar la lista de tareas

                    // Limpiar los campos de tarea y tiempo
                    tarea = ""
                    tiempoEsperado = ""

                    // Log para verificar las tareas guardadas
                    tareas.value.forEach { tarea ->
                        Log.d("TareaGuardada", "Tarea: ${tarea.titulo} Tiempo: ${tarea.tiempo}")
                    }

                    // Crear el objeto Proyecto con las tareas actualizadas
                    val proyecto = Proyecto(0, tarea, 0, 0, tareas.value)

                    // Actualizar el proyecto en el ViewModel
                    coroutineScope.launch {
                        proyecto.tareas.forEach { tarea ->
                            viewModel.editarTarea(proyecto.idProyecto, 0, tarea.titulo, tarea)
                        }
                    }

                    navController.navigate("proyectos")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 5.dp, end = 5.dp, bottom = 0.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255))
            ) {
                Text("Guardar", fontSize = 20.sp)
            }
        }
    }
}