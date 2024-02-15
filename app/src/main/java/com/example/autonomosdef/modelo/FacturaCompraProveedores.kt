package com.example.autonomosdef.modelo

class FacturaCompraProveedores {
    private val numeroFactura: String?
    private val tipoPago: String?
    private val iban: String?
    private val fecha: String?
    private val vencimiento: String?
    private val nombreBanco: String?
    private val totalBI: Int
    private val totalIVA: Int
    private val total: Int
    private val id: Int
    private val idProveedor: Int

    constructor(
        numeroFactura: String?,
        tipoPago: String?,
        iban: String?,
        fecha: String?,
        vencimiento: String?,
        nombreBanco: String?,
        totalBI: Int,
        totalIVA: Int,
        total: Int,
        id: Int,
        idProveedor: Int,
    ) {
        this.numeroFactura = numeroFactura
        this.tipoPago = tipoPago
        this.iban = iban
        this.fecha = fecha
        this.vencimiento = vencimiento
        this.nombreBanco = nombreBanco
        this.totalBI = totalBI
        this.totalIVA = totalIVA
        this.total = total
        this.id = id
        this.idProveedor = idProveedor
    }
}