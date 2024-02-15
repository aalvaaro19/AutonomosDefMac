package com.example.autonomosdef.modelo

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //ClienteController
    @GET("/listarClientes/{id}/")
    suspend fun listarClientes(@Path("id") id: Int): List<Cliente>

    @POST("/guardarCliente/{id}/")
    suspend fun guardarClientesId(@Path("id") id:Int, @Body cliente: Cliente): Response<Cliente>

    @POST("/editarCliente/{idCliente}/{id}/")
    suspend fun editarClientesId(@Path("idCliente") idCliente: Int, @Path("id") id: Int, @Body cliente: Cliente): Response<Cliente>

    @POST("/eliminarCliente/{idCliente}/")
    suspend fun borrarClientesId(@Path("idCliente") id:Int): Response<Cliente>

    @GET("/getCliente/{id}/{idCliente}/")
    suspend fun getCliente(@Path("id") id: Int, @Path("idCliente") idCliente: Int): Response<Cliente>
    //Fin ClienteController

    //ProveedorController
    @GET("/listarProveedores/{id}/")
    suspend fun listarProveedoresId(@Path("id") id: Int): List<Proveedor>

    @POST("/guardarProveedor/{id}/")
    suspend fun guardarProveedoresId(@Path("id") id:Int, @Body proveedor: Proveedor): Response<Proveedor>

    @POST("/editarProveedor/{idProveedor}/{id}/")
    suspend fun editarProveedoresId(@Path("idProveedor") idProveedor: Int, @Path("id") id: Int, @Body proveedor: Proveedor): Response<Proveedor>

    @POST("/eliminarProveedor/{idProveedor}/")
    suspend fun borrarProveedoresId(@Path("idProveedor") id:Int): Response<Proveedor>

    @GET("/getProveedor/{id}/{idProveedor}/")
    suspend fun getProveedor(@Path("id") id: Int, @Path("idProveedor") idProveedor: Int): Response<Proveedor>
    //Fin ProveedorController

    //ProyectoController
    @GET("listarProyectos/{id}/")
    suspend fun listarProyectos(@Path("id") id:Int): List<Proyecto>

    @POST("guardarProyecto/{id}/")
    suspend fun guardarProyectosId(@Path("id") id: Int, @Body proyecto: Proyecto): Response<Proyecto>

    @POST("/editarProyecto/{idProyecto}/{id}/")
    suspend fun editarProyectosId(@Path("idProyecto") idProyecto: Int, @Path("id") id: Int, @Body proyecto: Proyecto): Response<Proyecto>

    @POST("/eliminarProyecto/{idProyecto}/")
    suspend fun borrarProyectosId(@Path("idProyecto") id:Int): Response<Proyecto>

    @POST("/agregarTareas/{idProyecto}/{id}/")
    suspend fun agregarTareasId(@Path("idProyecto") idProyecto: Int, @Path("id") id: Int, @Body tarea: Tarea): Response<Proyecto>

    @POST("/editarTarea/{idProyecto}/{id}/{titulo}")
    suspend fun editarTareasId(@Path("idProyecto") idProyecto: Int, @Path("id") id: Int, @Path("titulo") titulo: String, @Body tarea: Tarea): Response<Proyecto>

    @POST("/eliminarTarea/{idProyecto}/{id}/")
    suspend fun borrarTareasId(@Path("idProyecto") idProyecto: Int, @Path("id") id: Int, @Body tarea: Tarea): Response<Proyecto>

    @GET("/eliminarTarea/{idProyecto}/{id}/")
    suspend fun getProyecto(@Path("id") id: Int, @Path("idProyecto") idProyecto: Int): Response<Proyecto>
    //Fin ProyectoController

    @GET("/iniciarSesion/{nombreUser}/{pass}")
    suspend fun iniciarSesion(@Path("nombreUser") nombreUser: String, @Path("pass") pass: String): Response<String>
}
