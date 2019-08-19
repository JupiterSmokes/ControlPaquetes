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
public class Almacen {
    private int codEnvio;
    private int codPC;
    private int tiempo;
    private double tarifaA;
    private double costo;

    public Almacen(int codEnvio, int codPC, double tarifaA) {
        this.codEnvio = codEnvio;
        this.codPC = codPC;
        this.tarifaA = tarifaA;
        this.tiempo = 0;
        this.costo = 0;
    }

    public int getCodEnvio() {
        return codEnvio;
    }
    public void setCodEnvio(int codEnvio) {
        this.codEnvio = codEnvio;
    }

    public int getCodPC() {
        return codPC;
    }
    public void setCodPC(int codPC) {
        this.codPC = codPC;
    }

    public int getTiempo() {
        return tiempo;
    }
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public double getTarifaA() {
        return tarifaA;
    }
    public void setTarifaA(int tarifaA) {
        this.tarifaA = tarifaA;
    }

    public double getCosto() {
        if (this.tiempo != 0 && this.tarifaA != 0) {
            this.costo = this.tiempo * this.tarifaA;
        }
        return this.costo;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    
}
