package com.example.autonomosdef.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autonomosdef.R
import com.example.autonomosdef.modelo.ClienteViewModel
import com.example.autonomosdef.modelo.IniciarSesionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login (navController: NavController) {
    val viewModel: IniciarSesionViewModel = viewModel()
    val image1 = painterResource(R.drawable.visibility)
    val image2 = painterResource(R.drawable.visibilityoff)
    val google = painterResource(R.drawable.gogle_removebg_preview__1_)
    val facebook = painterResource(R.drawable.facebook_removebg_preview__1_)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(59, 64, 72, 255),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text("Login")
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
        )  {
            Text(text = "Inicia sesión con tu cuenta", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text ="Bienvenido de nuevo", fontSize = 20.sp, color = Color.Gray)
            Row {
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.White),
                ) {
                    Image(
                        google,
                        contentDescription = "Google",
                        modifier = Modifier.size(100.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.White),
                    ) {
                    Image(
                        facebook,
                        contentDescription = "Facebook",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Text("o continua con tu email", color = Color.Gray)
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
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
            Spacer(modifier = Modifier.size(10.dp))

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
            Spacer(modifier = Modifier
                .size(10.dp)
                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                )
            RememberMeCheckbox()
            Button(
                onClick = {
                /*    val response = viewModel.*/

                    navController.navigate("clientes")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(66.dp, 10.dp, 66.dp, 0.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
            ) {
                Text(
                    text = "LOG IN",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            ClickableLogin("¿No tienes cuenta?", "Regístrate") {
                navController.navigate("registro")
            }
        }
    }
}

@Composable
private fun ClickableLogin(text1: String, text2: String, onClick: () -> Unit) {
    Row{
        Text(
            text = text1,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text2,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(4, 104, 249, 255),
            modifier = Modifier
                .clickable { onClick.invoke() }
        )
    }
}

@Composable
fun RememberMeCheckbox(){
    var rememberMeState by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.toggleable(
            value = rememberMeState,
            onValueChange = { isChecked -> rememberMeState = isChecked }
        ),
    ) {
        Checkbox(
            checked = rememberMeState,
            onCheckedChange = null, // Dejamos esto como nulo ya que ya manejamos el cambio en la fila
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Gray,
            )
        )
        Text(
            text = "Recuérdame",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black, // Color del texto
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}