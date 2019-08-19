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
public class PuntoControl {
    private int codigo; //Codigo de identificacion del punto de control (Llave primaria)
    private int codRuta;  //Codigo de la ruta (Llave primaria y foranea)
    private int codDestino; //Codigo del destino (Llave primaria ruta y foranea)
    private double tarifa; //Tarifa de operacion del PC
    private double tarifaG; //Tarifa global de PCs
    private int limite; //Limite de paquetes en el PC
    //private cola (Tal vez implementada personalmente)
    private String ubicacion; //Ubicacion del PC

    public PuntoControl(int codigo, int codRuta, int codDestino, double tarifa, int limite, String ubicacion) {
        this.codigo = codigo;
        this.codRuta = codRuta;
        this.codDestino = codDestino;
        this.tarifa = tarifa;
        this.limite = limite;
        this.ubicacion = ubicacion;
        //crear cola con limite = limite
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodRuta() {
        return codRuta;
    }
    public void setCodRuta(int codRuta) {
        this.codRuta = codRuta;
    }

    public double getTarifa() {
        return tarifa;
    }
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public int getLimite() {
        return limite;
    }
    public void setLimite(int limite) {
        this.limite = limite;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCodDestino() {
        return codDestino;
    }
    public void setCodDestino(int codDestino) {
        this.codDestino = codDestino;
    }

    public double getTarifaG() {
        return tarifaG;
    }
    public void setTarifaG(double tarifaG) {
        this.tarifaG = tarifaG;
    }
    
    
}
