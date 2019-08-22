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
public class Usuario implements Insertable{
    private String nombre;
    private String usuario;
    private String password;
    private int tipo;

    public Usuario(String nombre, String usuario, String password, int tipo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    public String primaryKey(){
        return this.getUsuario();
    }
    public String insert(){
        return String.format("'%s','%s','%s',%d", this.usuario, this.password, this.nombre, this.tipo);
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
               case "password": 
                   update += ", Password = '" + this.password + "'";
                   break;
               case "usuario": 
                   update += ", Usuario = '" + this.usuario + "'";
                   break;
               case "tipo": 
                   update += ", Tipo = " + this.tipo;
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
               case "password": 
                   select += ", Password";
                   break;
               case "usuario": 
                   select += ", Usuario";
                   break;
               case "tipo": 
                   select += ", Tipo";
                   break;
//               case "nombre": 
//                   select += ", Nombre";
//                   break;
//               case "nombre": 
//                   select += ", Nombre";
//                   break;             
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
               case "password": 
                   condition += "AND Password = '" + this.password + "' ";
                   break;
               case "usuario": 
                   condition += "AND Usuario = '" + this.usuario + "' ";
                   break;
               case "tipo": 
                   condition += "AND Tipo = " + this.tipo + " ";
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
    
    
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

   
    
    
}
