package com.example.autonomosdef.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autonomosdef.modelo.Proveedor
import com.example.autonomosdef.modelo.ProveedorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formularioProveedores(navController: NavController){
    val viewModel: ProveedorViewModel = viewModel()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(59, 64, 72, 255),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Insertar Proveedor", fontSize = 22.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("proveedores") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
            )
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var nombre by remember { mutableStateOf("") }
            val updatedNombre = rememberUpdatedState(nombre)

            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedNombre.value,
                    onValueChange = { nombre = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (nombre.isEmpty()) {
                                Icon(
                                    imageVector = if (nombre.isNotEmpty()) Icons.Default.Person else Icons.Default.Person,
                                    contentDescription = "Person Icon",
                                    tint = if (nombre.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Nombre del proveedor")
                        }
                    },
                )
            }
            var NIF by remember { mutableStateOf("") }
            val updatedNIF = rememberUpdatedState(NIF)

            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedNIF.value,
                    onValueChange = { NIF = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (NIF.isEmpty()) {
                                Icon(
                                    imageVector = if (NIF.isNotEmpty()) Icons.Default.MailOutline else Icons.Default.MailOutline,
                                    contentDescription = "Person Icon",
                                    tint = if (NIF.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("DNI")
                        }
                    },
                )
            }
            var NombreEmpresa by remember { mutableStateOf("") }
            val updatedNombreEmpresa = rememberUpdatedState(NombreEmpresa)

            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedNombreEmpresa.value,
                    onValueChange = { NombreEmpresa = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (NombreEmpresa.isEmpty()) {
                                Icon(
                                    imageVector = if (NombreEmpresa.isNotEmpty()) Icons.Default.Info else Icons.Default.Info,
                                    contentDescription = "Person Icon",
                                    tint = if (NombreEmpresa.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Nombre de la empresa")
                        }
                    },
                )
            }
            var TipoEmpresa by remember { mutableStateOf("") }
            val updatedTipoEmpresa = rememberUpdatedState(TipoEmpresa)

            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedTipoEmpresa.value,
                    onValueChange = { TipoEmpresa = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (TipoEmpresa.isEmpty()) {
                                Icon(
                                    imageVector = if (TipoEmpresa.isNotEmpty()) Icons.Default.Info else Icons.Default.Info,
                                    contentDescription = "Person Icon",
                                    tint = if (TipoEmpresa.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Tipo de empresa")
                        }
                    },
                )
            }
            var Fecha by remember { mutableStateOf("") }
            val updatedFecha = rememberUpdatedState(Fecha)

            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedFecha.value,
                    onValueChange = { Fecha = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (Fecha.isEmpty()) {
                                Icon(
                                    imageVector = if (Fecha.isNotEmpty()) Icons.Default.DateRange else Icons.Default.DateRange,
                                    contentDescription = "Person Icon",
                                    tint = if (Fecha.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Fecha de creacion de la empresa")
                        }
                    },
                )
            }
            var Direccion by remember { mutableStateOf("") }
            val updatedDireccion = rememberUpdatedState(Direccion)

            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedDireccion.value,
                    onValueChange = { Direccion = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (Direccion.isEmpty()) {
                                Icon(
                                    imageVector = if (Direccion.isNotEmpty()) Icons.Default.LocationOn else Icons.Default.LocationOn,
                                    contentDescription = "Person Icon",
                                    tint = if (Direccion.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Localizacion de la empresa")
                        }
                    },
                )
            }
            var cp by remember { mutableStateOf("") }
            val updatedCp = rememberUpdatedState(cp)
            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedCp.value,
                    onValueChange = { cp = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (cp.isEmpty()) {
                                Icon(
                                    imageVector = if (cp.isNotEmpty()) Icons.Default.Place else Icons.Default.Place,
                                    contentDescription = "Person Icon",
                                    tint = if (cp.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Codigo postal")
                        }
                    },
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            var Localidad by remember { mutableStateOf("") }
            val updatedLocalidad = rememberUpdatedState(Localidad)
            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedLocalidad.value,
                    onValueChange = { Localidad = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (Localidad.isEmpty()) {
                                Icon(
                                    imageVector = if (Localidad.isNotEmpty()) Icons.Default.Place else Icons.Default.Place,
                                    contentDescription = "Person Icon",
                                    tint = if (Localidad.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Localidad")
                        }
                    },
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            var Pais by remember { mutableStateOf("") }
            val updatedPais = rememberUpdatedState(Pais)
            Row {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(4, 104, 249, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(4, 104, 249, 255),
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    value = updatedPais.value,
                    onValueChange = { Pais = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (Pais.isEmpty()) {
                                Icon(
                                    imageVector = if (Pais.isNotEmpty()) Icons.Default.Place else Icons.Default.Place,
                                    contentDescription = "Person Icon",
                                    tint = if (Pais.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Pais")
                        }
                    },
                )
            }
            Button(
                onClick = {
                    // Crea un objeto Cliente con los datos del formulario
                    val proveedor = Proveedor(1, nombre, NIF, NombreEmpresa, TipoEmpresa, Fecha, Direccion, cp.toInt(), Localidad, Pais, 0)
                    // Llama a la función del ViewModel para guardar el cliente
                    viewModel.guardarProveedoresId(proveedor)
                    navController.navigate("proveedores")
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