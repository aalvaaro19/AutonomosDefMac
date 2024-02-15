package com.example.autonomosdef.modelo

class Proveedor {
    val idProveedor: Int
    val nombre: String
    val dni: String
    val fecha: String
    val empresa: String
    val tipoEmpresa: String
    val direccion: String
    val cp : Int
    val localidad: String?
    val pais: String?
    val idUsuario: Int

    constructor(
        idProveedor: Int,
        nombre: String,
        dni: String,
        fecha: String,
        empresa: String,
        tipoEmpresa: String,
        direccion: String,
        cp: Int,
        localidad: String?,
        pais: String?,
        idUsuario: Int,
    ) {
        this.idProveedor = idProveedor
        this.nombre = nombre
        this.dni = dni
        this.fecha = fecha
        this.empresa = empresa
        this.tipoEmpresa = tipoEmpresa
        this.direccion = direccion
        this.cp = cp
        this.localidad = localidad
        this.pais = pais
        this.idUsuario = idUsuario
    }
}