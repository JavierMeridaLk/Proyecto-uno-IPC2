/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backen.anuncios;

import java.time.LocalDate;

/**
 *
 * @author xavi
 */
public class Anuncio {
    private String anuncio;
    private String fechaPago;
    private String estado;
    private String fechaLimite;
    
    public Anuncio(String anuncio, String fechaPago, String estado, String fechaLimite) {
         this.anuncio = anuncio;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
        
    }

    // Getters y Setters
  public String getAnuncio() {
        return anuncio;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }
    
    
}