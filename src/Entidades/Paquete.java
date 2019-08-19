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
public class Paquete {
    private int codigo; //Codigo de identificacion (Llave primaria)
    private double peso; //Peso del paquete
    private String nit; //Llave foranea de Cliente
    private boolean priorizado; //El paquete esta priorizado?

    public Paquete(int codigo, int peso, String nit, boolean priorizado) {
        this.codigo = codigo;
        this.peso = peso;
        this.nit = nit;
        this.priorizado = priorizado;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }

    public boolean isPriorizado() {
        return priorizado;
    }
    public void setPriorizado(boolean priorizado) {
        this.priorizado = priorizado;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    
}
