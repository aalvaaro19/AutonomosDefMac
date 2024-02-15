package com.example.autonomosdef.navigation

open class AppScreens (val ruta: String){
    object portadaAplicacion : AppScreens("portadaAplicacion")
    object login : AppScreens("login")
    object registro : AppScreens("registro")
    object proyectos : AppScreens("proyectos")
    object clientes : AppScreens("clientes")
    object proveedores : AppScreens("proveedores")
    object formularioProyectos : AppScreens("formularioProyectos")
    object formularioClientes : AppScreens("formularioClientes")
    object formularioProveedores : AppScreens("formularioProveedores")
    object formularioFacturas : AppScreens("formularioFacturas")
    object formularioFacturasP : AppScreens("formularioFacturasP")
    object formularioTareas : AppScreens("formularioTareas")
    object formularioClientesEditar : AppScreens("formularioClientesEditar")
    object formularioProveedoresEditar: AppScreens("formularioProveedoresEditar")
    object formularioProyectosEditar: AppScreens("formularioProyectosEditar")
    object formularioEdicionTareas: AppScreens("formularioEdicionTareas")
}