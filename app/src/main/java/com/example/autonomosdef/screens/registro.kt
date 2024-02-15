package com.example.autonomosdef.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.autonomosdef.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registro (navController: NavController) {
    val image1 = painterResource(R.drawable.visibility)
    val image2 = painterResource(R.drawable.visibilityoff)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(59, 64, 72, 255),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Insertar usuario")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("portadaAplicacion") }) {
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
                .padding(
                    PaddingValues(
                    top = innerPadding.calculateTopPadding() - 40.dp,
                )
                ),
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
                        .padding(10.dp, 30.dp, 10.dp, 0.dp),
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
                            Text("Nombre de usuario")
                        }
                    },
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            var correo by rememberSaveable { mutableStateOf("") }
            val updateCorreo = rememberUpdatedState(correo)
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray
                ),
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 0.dp),
                value = updateCorreo.value,
                onValueChange = { correo = it },
                singleLine = true,
                label = {
                    Row {
                        if (correo.isEmpty()) {
                            Icon(
                                imageVector = if (correo.isNotEmpty()) Icons.Default.Email else Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = if (correo.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Email")
                    }
                },
            )
            Spacer(modifier = Modifier.size(5.dp))
            var password by rememberSaveable { mutableStateOf("") }
            var isPasswordVisible by remember { mutableStateOf(false) }
            var isPasswordFocused by remember { mutableStateOf(false) }
            val updateContraseña = rememberUpdatedState(password)
            val iconSize = 20.dp

            // Se sugiere utilizar un nombre más descriptivo para la función de cambio de contraseña
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray
                ),
                // Utiliza un nombre más descriptivo para la variable 'updateContraseña'
                value = updateContraseña.value,
                onValueChange = {
                    // Asigna el nuevo valor directamente a 'password'
                    password = it
                },
                singleLine = true,
                label = {
                    Row {
                        if (password.isEmpty()) {
                            Icon(
                                imageVector = if (password.isNotEmpty()) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = if (password.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Contraseña")
                    }
                },
                visualTransformation = if (isPasswordVisible) {
                    // Usa VisualTransformation.None para mostrar el texto de la contraseña si es visible
                    VisualTransformation.None
                } else {
                    // Usa PasswordVisualTransformation para ocultar el texto de la contraseña si no es visible
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            // Invierte el estado de isPasswordVisible al hacer clic en el icono
                            isPasswordVisible = !isPasswordVisible
                        },
                    ) {
                        Image(
                            // Utiliza operador ternario para seleccionar la imagen en función de isPasswordVisible
                            if (isPasswordVisible) image2 else image1,
                            // Utiliza operador ternario para seleccionar la descripción de la imagen
                            contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            // Utiliza el modificador size directamente en Image
                            modifier = Modifier.size(iconSize)
                        )
                    }
                },
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .onFocusChanged { isPasswordFocused = it.isFocused }
            )
            Spacer(modifier = Modifier.size(5.dp))
            var nombreCompleto by remember { mutableStateOf("") }
            val updatedNombreCompleto = rememberUpdatedState(nombreCompleto)

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
                    value = updatedNombreCompleto.value,
                    onValueChange = { nombreCompleto = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (nombreCompleto.isEmpty()) {
                                Icon(
                                    imageVector = if (nombreCompleto.isNotEmpty()) Icons.Default.Person else Icons.Default.Person,
                                    contentDescription = "Person Icon",
                                    tint = if (nombreCompleto.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Nombre completo")
                        }
                    },
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
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
            Spacer(modifier = Modifier.size(5.dp))
            var direccion by remember { mutableStateOf("") }
            val updatedDireccion = rememberUpdatedState(direccion)
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
                    value = updatedDireccion.value,
                    onValueChange = { direccion = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (direccion.isEmpty()) {
                                Icon(
                                    imageVector = if (direccion.isNotEmpty()) Icons.Default.LocationOn else Icons.Default.LocationOn,
                                    contentDescription = "Person Icon",
                                    tint = if (direccion.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Dirección")
                        }
                    },
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
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
                    navController.navigate("login")
                },

                modifier = Modifier
                    .size(200.dp, 75.dp)
                    .padding(top = 20.dp, start = 0.8.dp, end = 20.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255))
            ) {
                Text("Enviar", fontSize = 20.sp)
            }
        }
    }

}