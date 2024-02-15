package com.example.autonomosdef.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autonomosdef.modelo.ShareViewModelProveedor
import com.example.autonomosdef.modelo.SharedViewModelCliente
import com.example.autonomosdef.modelo.SharedViewModelProyecto
import com.example.autonomosdef.screens.FormularioFacturas
import com.example.autonomosdef.screens.FormularioFacturasP
import com.example.autonomosdef.screens.formularioClientes
import com.example.autonomosdef.screens.formularioProveedores
import com.example.autonomosdef.screens.formularioProyectos
import com.example.autonomosdef.screens.formularioTareas
import com.example.autonomosdef.screens.login
import com.example.autonomosdef.screens.portadaAplicacion
import com.example.autonomosdef.screens.proveedores
import com.example.autonomosdef.screens.proyectos
import com.example.autonomosdef.screens.registro
import com.example.autonomosdef.screens.clientes
import com.example.autonomosdef.screens.formularioClientesEditar
import com.example.autonomosdef.screens.formularioEdicionTareas
import com.example.autonomosdef.screens.formularioProveedoresEditar
import com.example.autonomosdef.screens.formularioProyectosEditar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation () {
    val navigationController = rememberNavController()
    val sharedViewModelCliente = SharedViewModelCliente()
    val shareViewModelProveedor = ShareViewModelProveedor()
    val sharedViewModelProyecto = SharedViewModelProyecto()
    NavHost(navController = navigationController, startDestination = AppScreens.portadaAplicacion.ruta){
        composable(AppScreens.portadaAplicacion.ruta) { portadaAplicacion(navigationController)}
        composable(AppScreens.login.ruta) { login(navigationController) }
        composable(AppScreens.registro.ruta) { registro(navigationController) }
        composable(AppScreens.proyectos.ruta) { proyectos(navigationController,sharedViewModelProyecto) }
        composable(AppScreens.clientes.ruta) { clientes(navigationController, sharedViewModelCliente) }
        composable(AppScreens.proveedores.ruta) { proveedores(navigationController, shareViewModelProveedor) }
        composable(AppScreens.formularioProyectos.ruta){ formularioProyectos(navigationController)}
        composable(AppScreens.formularioClientes.ruta){ formularioClientes(navigationController) }
        composable(AppScreens.formularioProveedores.ruta){ formularioProveedores(navigationController) }
        composable(AppScreens.formularioFacturas.ruta){ FormularioFacturas(navigationController) }
        composable(AppScreens.formularioFacturasP.ruta){ FormularioFacturasP(navigationController) }
        composable(AppScreens.formularioTareas.ruta){ formularioTareas(navigationController) }
        composable(AppScreens.formularioClientesEditar.ruta){ formularioClientesEditar(navigationController, sharedViewModelCliente) }
        composable(AppScreens.formularioProveedoresEditar.ruta){ formularioProveedoresEditar(navigationController, shareViewModelProveedor) }
        composable(AppScreens.formularioProyectosEditar.ruta){ formularioProyectosEditar(navigationController, sharedViewModelProyecto) }
        composable(AppScreens.formularioEdicionTareas.ruta){ formularioEdicionTareas(navigationController, sharedViewModelProyecto) }
    }
}


