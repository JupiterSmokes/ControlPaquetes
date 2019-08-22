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
public class Destino implements Insertable{
    private int codigo; //Codigo del destino (Llave primaria)
    private String direccion; //Ubicacion del destino
    private double cuota; //Precio de envio hacia el destino

    public Destino(int codigo, String direccion, double cuota) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.cuota = cuota;
    }

        public String insert(){
            return String.format("%d,'%s',%f", this.codigo, this.direccion, this.cuota);
        }
         @Override
        public String table() {
            return this.getClass().getSimpleName();
        }
    
        public String update(String[] fields){
        String update = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
               case "direccion": 
                   update += ", Direccion = '" + this.direccion + "'";
                   break;
               case "codigo": 
                   update += ", Codigo = " + this.codigo;
                   break;
               case "cuota": 
                   update += ", Cuota = " + this.cuota;
                   break;
            }
        }
        if (update.charAt(0) == ',') update = update.replaceFirst(",", "");
        return update;
    }
    
    public String select(String[] fields){
        String select = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
               case "direccion": 
                   select += ", Direccion";
                   break;
               case "codigo": 
                   select += ", Codigo";
                   break;
               case "cuota": 
                   select += ", Cuota";
                   break;             
               default:
                   select += "*";
            }
        }
        if (select.charAt(0) == ',') select = select.replaceFirst(",", "");
        return select;
    }

    public String where(String[] fields){
        String condition = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
                case "direccion": 
                   condition += "AND Direccion = '" + this.direccion + "'";
                   break;
               case "codigo": 
                   condition += "AND Codigo = " + this.codigo;
                   break;
               case "cuota": 
                   condition += "AND Cuota = " + this.cuota;
                   break;
//               case "nombre": 
//                   select += ", Nombre";
//                   break;
//               case "nombre": 
//                   select += ", Nombre";
//                   break;             
            }
        }
         condition = condition.replaceFirst("AND", "");
        return condition;
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
