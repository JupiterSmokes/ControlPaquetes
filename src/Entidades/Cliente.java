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

    
      public String primaryKey(){
        return this.getNit();
    }
        public String insert(){
            return String.format("'%s','%s'", this.nit, this.nombre, this.nombre);
        }
         @Override
        public String table() {
            return this.getClass().getSimpleName();
        }

            public String update(String[] fields){
        String update = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
               case "nombre": 
                   update += ", Nombre = '" + this.nombre + "'";
                   break;
               case "nit": 
                   update += ", NIT = '" + this.nit + "'";
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
               case "nombre": 
                   select += ", Nombre";
                   break;
               case "nit": 
                   select += ", NIT";
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
               case "nombre": 
                   condition += "AND Nombre = '" + this.nombre + "' ";
                   break;
               case "nit": 
                   condition += "AND NIT = '" + this.nit + "' ";
                   break;
            }
        }
         condition = condition.replaceFirst("AND", "");
        return condition;
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
