package com.example.autonomosdef.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.autonomosdef.R

@Composable
fun portadaAplicacion(navController: NavController) {
    val logo = painterResource(R.drawable.image__3_)
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
    Image(
        painter = logo,
        contentDescription = null,
        modifier = Modifier
            .size(500.dp, 250.dp)
    )
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Clickable("INICIAR SESIÓN") {
                navController.navigate("login")
            }
            Spacer(modifier = Modifier.width(25.dp))
            Clickable("REGISTRARSE") {
                navController.navigate("registro")
            }
        }
    }
}

@Composable
private fun Clickable(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .clickable { onClick.invoke() }
    )
}
