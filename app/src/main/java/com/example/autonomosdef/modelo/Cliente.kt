package com.example.autonomosdef.modelo


class Cliente{
    val idCliente: Int
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

    //Constructor vacío

    constructor(
        idCliente: Int,
        nombre: String,
        dni: String,
        empresa: String,
        tipoEmpresa: String,
        fecha: String,
        direccion: String,
        cp : Int,
        localidad: String?,
        pais: String?,
        idUsuario: Int,
    ) {
        this.idCliente = idCliente
        this.nombre = nombre
        this.dni = dni
        this.empresa = empresa
        this.tipoEmpresa = tipoEmpresa
        this.fecha = fecha
        this.direccion = direccion
        this.cp = cp
        this.localidad = localidad
        this.pais = pais
        this.idUsuario = idUsuario
    }

    // Otro constructor
    constructor(
        nombre: String,
        dni: String,
        empresa: String,
        tipoEmpresa: String,
        fecha: String,
        direccion: String,
        cp: Int,
        localidad: String?,
        pais: String?,
        idUsuario: Int
    ) : this(0, nombre, dni, empresa, tipoEmpresa, fecha, direccion, cp, localidad, pais, idUsuario)


    fun copy(
        nombre: String = this.nombre,
        dni: String = this.dni,
        fecha: String = this.fecha,
        empresa: String = this.empresa,
        tipoEmpresa: String = this.tipoEmpresa,
        direccion: String = this.direccion,
        cp: Int = this.cp,
        localidad: String? = this.localidad,
        pais: String? = this.pais,
        idUsuario: Int = this.idUsuario
    ) = Cliente(nombre, dni, empresa, tipoEmpresa, fecha, direccion, cp, localidad, pais, idUsuario)
}