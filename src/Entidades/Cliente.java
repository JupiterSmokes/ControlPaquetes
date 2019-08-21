/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import DBManager.Insertable;

/**
 *
 * @author DANIEL
 */
public class Cliente implements Insertable{
    private String nombre; //Nombre del cliente
    private String nit; //Numero de identificacion tributaria (Llave primaria)

        public String insert(){
            return String.format("'%s','%s'", this.nit, this.nombre, this.nombre);
        }
         @Override
        public String table() {
            return this.getClass().getSimpleName();
        }

    public Cliente(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    
    
}
