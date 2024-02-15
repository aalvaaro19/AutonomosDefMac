package com.example.autonomosdef.modelo

class Proyecto {
     var idProyecto: Int
     var titulo: String
     var terminado: Int
     var id: Int
     val tareas: List<Tarea>

    constructor(idProyecto: Int, titulo: String, terminado: Int, id: Int, tareas: List<Tarea>) {
        this.idProyecto = idProyecto
        this.titulo = titulo
        this.terminado = terminado
        this.id = id
        this.tareas = tareas
    }
}