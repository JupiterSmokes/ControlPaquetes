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
public class Destino {
    private int codigo; //Codigo del destino (Llave primaria)
    private String direccion; //Ubicacion del destino
    private double cuota; //Precio de envio hacia el destino

    public Destino(int codigo, String direccion, double cuota) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.cuota = cuota;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCuota() {
        return cuota;
    }
    public void setCuota(double cuota) {
        this.cuota = cuota;
    }
    
}
