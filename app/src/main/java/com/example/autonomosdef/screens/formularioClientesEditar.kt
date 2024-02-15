package com.example.autonomosdef.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.autonomosdef.modelo.Cliente
import com.example.autonomosdef.modelo.ClienteViewModel
import com.example.autonomosdef.modelo.SharedViewModelCliente
import kotlinx.coroutines.launch
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formularioClientesEditar(navController: NavController, sharedViewModelCliente: SharedViewModelCliente) {
    val viewModel: ClienteViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var empresa by remember { mutableStateOf("") }
    var tipoEmpresa by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var cp by remember { mutableStateOf("") }
    var localidad by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var cliente : Response<Cliente>? by remember { mutableStateOf(null) }
    val id: Int? by sharedViewModelCliente.idCliente.collectAsState()

    LaunchedEffect(Unit) {
        cliente = id?.let { viewModel.getCliente(it) }
        Log.d("getCliente", "Cliente: ${cliente?.body()?.idCliente}")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(59, 64, 72, 255),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Editar Cliente", fontSize = 22.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("clientes") }) {
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
                value = nombre,
                onValueChange = { newValue -> nombre = newValue },
                label = { Text("${cliente?.body()?.nombre}") },
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
                value = dni,
                onValueChange = { newValue -> dni = newValue },
                label = { Text("${cliente?.body()?.dni}") },
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
                value = fecha,
                onValueChange = { newValue -> fecha = newValue },
                label = { Text("${cliente?.body()?.fecha}") },
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
                value = empresa,
                onValueChange = { newValue -> empresa = newValue },
                label = { Text("${cliente?.body()?.empresa}") },
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
                value = tipoEmpresa,
                onValueChange = { newValue -> tipoEmpresa = newValue },
                label = { Text("${cliente?.body()?.tipoEmpresa}") },
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
                value = direccion,
                onValueChange = { newValue -> direccion = newValue },
                label = { Text("${cliente?.body()?.direccion}") },
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
                value = cp,
                onValueChange = { newValue -> cp = newValue },
                label = { Text("${cliente?.body()?.cp}") },
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
                value = localidad,
                onValueChange = { newValue -> localidad = newValue },
                label = { Text("${cliente?.body()?.localidad}") },
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
                value = pais,
                onValueChange = { newValue -> pais = newValue },
                label = { Text("${cliente?.body()?.pais}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 0.dp),
            )
            Button(
                onClick = {
                    if(nombre.isEmpty()){
                        nombre = cliente?.body()?.nombre ?: ""
                    } else {
                        nombre = nombre
                    }
                    if(dni.isEmpty()){
                        dni = cliente?.body()?.dni ?: ""
                    } else {
                        dni = dni
                    }
                    if(empresa.isEmpty()){
                        empresa = cliente?.body()?.empresa ?: ""
                    } else {
                        empresa = empresa
                    }
                    if(tipoEmpresa.isEmpty()){
                        tipoEmpresa = cliente?.body()?.tipoEmpresa ?: ""
                    } else {
                        tipoEmpresa = tipoEmpresa
                    }
                    if(fecha.isEmpty()){
                        fecha = cliente?.body()?.fecha ?: ""
                    } else {
                        fecha = fecha
                    }
                    if(direccion.isEmpty()){
                        direccion = cliente?.body()?.direccion ?: ""
                    } else {
                        direccion = direccion
                    }
                    if(cp.isEmpty()){
                        cp = cliente?.body()?.cp.toString() ?: ""
                    } else {
                        cp = cp
                    }
                    if(localidad.isEmpty()){
                        localidad = cliente?.body()?.localidad ?: ""
                    } else {
                        localidad = localidad
                    }
                    if(pais.isEmpty()){
                        pais = cliente?.body()?.pais ?: ""
                    } else {
                        pais = pais
                    }

                    // Crea un objeto Cliente con los datos del formulario
                    coroutineScope.launch {
                        val clienteEditado = Cliente(
                            idCliente = 0,
                            nombre = nombre,
                            dni = dni,
                            empresa = empresa,
                            tipoEmpresa = tipoEmpresa,
                            fecha = fecha,
                            direccion = direccion,
                            cp = cp.toInt(),
                            localidad = localidad,
                            pais = pais,
                            idUsuario = 0
                        )
                        id?.let { viewModel.editarClientes(it, 0, clienteEditado) }
                        navController.navigate("clientes")
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