package com.example.autonomosdef.screens

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.autonomosdef.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioFacturasP(navController: NavController) {
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
                    Text("FACTURAS")
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
            var numeroF by remember { mutableStateOf("") }
            val updatedNumeroF = rememberUpdatedState(numeroF)

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
                    value = updatedNumeroF.value,
                    onValueChange = { numeroF = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (numeroF.isEmpty()) {
                                Icon(
                                    imageVector = if (numeroF.isNotEmpty()) Icons.Default.Info else Icons.Default.Info,
                                    contentDescription = "Person Icon",
                                    tint = if (numeroF.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Número de factura")
                        }
                    },
                )
            }
            var TipoPago by rememberSaveable { mutableStateOf("") }
            val updateTipoPago = rememberUpdatedState(TipoPago)
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
                value = updateTipoPago.value,
                onValueChange = { TipoPago = it },
                singleLine = true,
                label = {
                    Row {
                        if (TipoPago.isEmpty()) {
                            Icon(
                                imageVector = if (TipoPago.isNotEmpty()) Icons.Default.Email else Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = if (TipoPago.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Tipo de pago")
                    }
                },
            )
            var NB by remember { mutableStateOf("") }
            val updatedNB = rememberUpdatedState(NB)

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
                    value = updatedNB.value,
                    onValueChange = { NB = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (NB.isEmpty()) {
                                Icon(
                                    imageVector = if (NB.isNotEmpty()) Icons.Default.ShoppingCart else Icons.Default.ShoppingCart,
                                    contentDescription = "Person Icon",
                                    tint = if (NB.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Nombre de la entidad bancaria")
                        }
                    },
                )
            }

            var Iban by remember { mutableStateOf("") }
            val updatedIban = rememberUpdatedState(Iban)

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
                    value = updatedIban.value,
                    onValueChange = { Iban = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (Iban.isEmpty()) {
                                Icon(
                                    imageVector = if (Iban.isNotEmpty()) Icons.Default.MailOutline else Icons.Default.MailOutline,
                                    contentDescription = "Person Icon",
                                    tint = if (Iban.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Iban de la entidad bancaria")
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
                            Text("Fecha de creación")
                        }
                    },
                )
            }
            var Vencimiento by remember { mutableStateOf("") }
            val updatedVencimiento = rememberUpdatedState(Vencimiento)

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
                    value = updatedVencimiento.value,
                    onValueChange = { Vencimiento = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (Vencimiento.isEmpty()) {
                                Icon(
                                    imageVector = if (Vencimiento.isNotEmpty()) Icons.Default.DateRange else Icons.Default.DateRange,
                                    contentDescription = "Person Icon",
                                    tint = if (Vencimiento.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Fecha de vencimiento")
                        }
                    },
                )
            }

            var TotalBI by remember { mutableStateOf("") }
            val updatedTotalBI = rememberUpdatedState(TotalBI)

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
                    value = updatedTotalBI.value,
                    onValueChange = { TotalBI = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (TotalBI.isEmpty()) {
                                Icon(
                                    imageVector = if (TotalBI.isNotEmpty()) Icons.Default.Check else Icons.Default.Check,
                                    contentDescription = "Person Icon",
                                    tint = if (TotalBI.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Total B.I.")
                        }
                    },
                )
            }
            Button(
                onClick = {
                    navController.navigate("proveedores")
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