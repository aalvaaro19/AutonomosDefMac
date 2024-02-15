package com.example.autonomosdef.modelo

class Usuario {
    val id: Int
    val nombreUser: String?
    val email: String?
    val password: String?
    val nombreCompleto: String?
    val dni: String?
    val direccion: String?
    val cp : Int
    val localidad: String?
    val pais: String?

    constructor(
        id: Int,
        nombreUser: String?,
        email: String?,
        password: String?,
        nombreCompleto: String?,
        dni: String?,
        direccion: String?,
        cp: Int,
        localidad: String?,
        pais: String?,
    ) {
        this.id = id
        this.nombreUser = nombreUser
        this.email = email
        this.password = password
        this.nombreCompleto = nombreCompleto
        this.dni = dni
        this.direccion = direccion
        this.cp = cp
        this.localidad = localidad
        this.pais = pais
    }
}