/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Revistas;

import java.time.LocalDate;

/**
 *
 * @author xavi
 */
public class Revista {

    private String nombreRevista;
    private String nombreAutor;
    private LocalDate fechaPublicacion;
    private String descripcion;
    private String categoria;
    private boolean estadoMeGustas;
    private boolean estadoComentarios;
    private boolean estadoSuscripciones;
    private Double costo;

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Revista() {
        estadoMeGustas = true;
        estadoComentarios = true;
        estadoSuscripciones = true;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nobreRevista) {
        this.nombreRevista = nobreRevista;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isEstadoMeGustas() {
        return estadoMeGustas;
    }

    public void setEstadoMeGustas(boolean estadoMeGustas) {
        this.estadoMeGustas = estadoMeGustas;
    }

    public boolean isEstadoComentarios() {
        return estadoComentarios;
    }

    public void setEstadoComentarios(boolean estadoComentarios) {
        this.estadoComentarios = estadoComentarios;
    }

    public boolean isEstadoSuscripciones() {
        return estadoSuscripciones;
    }

    public void setEstadoSuscripciones(boolean estadoSuscripciones) {
        this.estadoSuscripciones = estadoSuscripciones;
    }

}
