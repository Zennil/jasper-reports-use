package com.jasper.zenil.jaspercurso.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class CajaAndEtiquetaBean {

    String cve_producto;
    String nom_producto;
    BigDecimal venta_promedio;
    BigDecimal existencias;
    BigDecimal dias_inventario;
    Date fecha_inicio_produccion;
    String Mes;
    BigDecimal ultimo_costo;
    BigDecimal cantidad_programada;
    BigDecimal costo_total;
    String estatus;
    BigDecimal num_orden;
    String estapa;
    String lote;
    BigDecimal pp;
    BigDecimal st;
    BigDecimal ac;
    BigDecimal pt;
    BigInteger cve_estructura;

    public String getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(String cve_producto) {
        this.cve_producto = cve_producto;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public BigDecimal getVenta_promedio() {
        return venta_promedio;
    }

    public void setVenta_promedio(BigDecimal venta_promedio) {
        this.venta_promedio = venta_promedio;
    }

    public BigDecimal getExistencias() {
        return existencias;
    }

    public void setExistencias(BigDecimal existencias) {
        this.existencias = existencias;
    }

    public BigDecimal getDias_inventario() {
        return dias_inventario;
    }

    public void setDias_inventario(BigDecimal dias_inventario) {
        this.dias_inventario = dias_inventario;
    }

    public Date getFecha_inicio_produccion() {
        return fecha_inicio_produccion;
    }

    public void setFecha_inicio_produccion(Date fecha_inicio_produccion) {
        this.fecha_inicio_produccion = fecha_inicio_produccion;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }

    public BigDecimal getUltimo_costo() {
        return ultimo_costo;
    }

    public void setUltimo_costo(BigDecimal ultimo_costo) {
        this.ultimo_costo = ultimo_costo;
    }

    public BigDecimal getCantidad_programada() {
        return cantidad_programada;
    }

    public void setCantidad_programada(BigDecimal cantidad_programada) {
        this.cantidad_programada = cantidad_programada;
    }

    public BigDecimal getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(BigDecimal costo_total) {
        this.costo_total = costo_total;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getNum_orden() {
        return num_orden;
    }

    public void setNum_orden(BigDecimal num_orden) {
        this.num_orden = num_orden;
    }

    public String getEstapa() {
        return estapa;
    }

    public void setEstapa(String estapa) {
        this.estapa = estapa;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigDecimal getPp() {
        return pp;
    }

    public void setPp(BigDecimal pp) {
        this.pp = pp;
    }

    public BigDecimal getSt() {
        return st;
    }

    public void setSt(BigDecimal st) {
        this.st = st;
    }

    public BigDecimal getAc() {
        return ac;
    }

    public void setAc(BigDecimal ac) {
        this.ac = ac;
    }

    public BigDecimal getPt() {
        return pt;
    }

    public void setPt(BigDecimal pt) {
        this.pt = pt;
    }

    public BigInteger getCve_estructura() {
        return cve_estructura;
    }

    public void setCve_estructura(BigInteger cve_estructura) {
        this.cve_estructura = cve_estructura;
    }

    
}