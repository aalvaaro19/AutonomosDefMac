package com.example.autonomosdef.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.autonomosdef.modelo.Tarea
import com.example.autonomosdef.modelo.SharedViewModelProyecto
import kotlinx.coroutines.launch
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formularioProyectosEditar(navController: NavController, sharedViewModelProyecto: SharedViewModelProyecto) {
    val viewModelP: ProyectoViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var titulo by remember { mutableStateOf("") }
    var tiempo by remember { mutableStateOf("") }
    var tareas by remember { mutableStateOf<List<String>>(emptyList()) }
    var proyecto : Response<Proyecto>? by remember { mutableStateOf(null) }
    val id: Int? by sharedViewModelProyecto.idProyecto.collectAsState()

    LaunchedEffect(Unit) {
        proyecto = id?.let { viewModelP.getProyecto(it) }
        Log.d("getProyecto", "Proyecto: ${proyecto?.body()?.idProyecto}")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(59, 64, 72, 255),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Editar proyecto", fontSize = 22.sp, color = Color.White)
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
                value = titulo,
                onValueChange = { newValue -> titulo = newValue },
                label = { Text("${proyecto?.body()?.titulo}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 0.dp),
            )
            LazyColumn(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                items(proyecto?.body()?.tareas ?: emptyList()) { tarea ->
                    var tiempo by remember { mutableStateOf(tarea.tiempo) }
                    var titulo by remember { mutableStateOf(tarea.titulo) }
                    Log.d("getTiempo", "Tiempo: ${tarea.tiempo}")
                        OutlinedTextField(
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(4, 104, 249, 255),
                                unfocusedBorderColor = Color.Gray,
                                focusedLabelColor = Color(4, 104, 249, 255),
                                unfocusedLabelColor = Color.Gray
                            ),
                            value = titulo,
                            onValueChange = { newValue -> titulo = newValue },
                            label = { Text("${tarea.titulo}") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                        )

                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(4, 104, 249, 255),
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color(4, 104, 249, 255),
                            unfocusedLabelColor = Color.Gray
                        ),
                        value = tiempo,
                        onValueChange = { newValue -> tiempo =
                            (newValue.toIntOrNull() ?: 0) as String
                        },
                        label = { Text("${tarea.tiempo}") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    )
                }
            }
            Button(
                onClick = {
                    Log.d("Tareas", "tiempo ${proyecto?.body()?.tareas?.map { "${it.titulo} - ${it.tiempo}" } ?: emptyList()}")
                    if(titulo.isEmpty()){
                        titulo = proyecto?.body()?.titulo ?: ""
                    } else {
                        titulo = titulo
                    }

                    coroutineScope.launch {
                        id?.let {
                            viewModelP.editarTarea(
                                it,
                                0,
                                "puta",
                                Tarea(titulo, tiempo, 0)
                            )
                        }
                    }

                    // Crea un objeto Proyecto con los datos del formulario
                    coroutineScope.launch {
                        val proyectoEditado = Proyecto(
                            idProyecto = 0,
                            titulo = titulo,
                            terminado = 0,
                            id = 0,
                            tareas = tareas.map { Tarea(it, it  , 0) }
                        )
                        proyectoEditado?.let {
                            id?.let { it1 -> viewModelP.editarProyecto(it1,0,proyectoEditado) }
                            it.tareas.forEachIndexed { index, tarea ->
                                id?.let { it1 -> viewModelP.editarTarea(it1, index, tarea.titulo, tarea) }
                            }
                        }
                        navController.navigate("proyectos")
                    }
                },
                modifier = Modifier
                    .size(200.dp, 75.dp)
                    .padding(top = 20.dp, start = 0.8.dp, end = 20.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255))
            ) {
                Text("Guardar", fontSize = 20.sp)
            }
        }
    }
}