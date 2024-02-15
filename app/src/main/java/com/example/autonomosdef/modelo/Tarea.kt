package com.example.autonomosdef.modelo

class Tarea {
     var titulo: String
     var tiempo: String
     val terminada: Int

    constructor(
        titulo: String,
        tiempo: String,
        terminada: Int,
    ) {
        this.titulo = titulo
        this.tiempo = tiempo
        this.terminada = terminada
    }
}