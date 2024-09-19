/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backen.anuncios;

/**
 *
 * @author xavi
 */
public class AnuncioPrecios {
    
     private String tipoAnuncio;
        private double precioUnDia;
        private double precioTresDias;
        private double precioUnaSemana;
        private double precioDosSemanas;

        public AnuncioPrecios() {

        }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public double getPrecioUnDia() {
        return precioUnDia;
    }

    public void setPrecioUnDia(double precioUnDia) {
        this.precioUnDia = precioUnDia;
    }

    public double getPrecioTresDias() {
        return precioTresDias;
    }

    public void setPrecioTresDias(double precioTresDias) {
        this.precioTresDias = precioTresDias;
    }

    public double getPrecioUnaSemana() {
        return precioUnaSemana;
    }

    public void setPrecioUnaSemana(double precioUnaSemana) {
        this.precioUnaSemana = precioUnaSemana;
    }

    public double getPrecioDosSemanas() {
        return precioDosSemanas;
    }

    public void setPrecioDosSemanas(double precioDosSemanas) {
        this.precioDosSemanas = precioDosSemanas;
    }
        
}
