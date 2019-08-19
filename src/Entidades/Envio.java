/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author DANIEL
 */
public class Envio {
    private int codigo;
    private int codPaquete;
    private int codRuta;
    private int codDestino;
    private double costoT;
    private int tiempoT;
    private boolean recibido;

    public Envio(int codigo, int codPaquete, int codRuta, int codDestino) {
        this.codigo = codigo;
        this.codPaquete = codPaquete;
        this.codRuta = codRuta;
        this.codDestino = codDestino;
        this.costoT = 0;
        this.tiempoT = 0;
        this.recibido = false;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodPaquete() {
        return codPaquete;
    }
    public void setCodPaquete(int codPaquete) {
        this.codPaquete = codPaquete;
    }

    public int getCodRuta() {
        return codRuta;
    }
    public void setCodRuta(int codRuta) {
        this.codRuta = codRuta;
    }

    public int getCodDestino() {
        return codDestino;
    }
    public void setCodDestino(int codDestino) {
        this.codDestino = codDestino;
    }

    public double getCostoT() {
        return costoT;
    }
    public void setCostoT(double costoT) {
        this.costoT = costoT;
    }

    public int getTiempoT() {
        return tiempoT;
    }
    public void setTiempoT(int tiempoT) {
        this.tiempoT = tiempoT;
    }

    public boolean isRecibido() {
        return recibido;
    }
    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }
     
    
}
