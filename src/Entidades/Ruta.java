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
public class Ruta implements Insertable{
    private int codigo; //Codigo de ruta (Llave primaria)
    private int codDestino; //Codigo del destino al que lleva (Llave primaria y foranea)
    private boolean estado; //Esta activa?
    private int est;

    public Ruta(int codigo, int codDestino) {
        this.codigo = codigo;
        this.codDestino = codDestino;
        this.estado = true;
        est = this.estado? 1:0;
    }

    public Ruta(int codigo, int codDestino, int est) {
        this.codigo = codigo;
        this.codDestino = codDestino;
        this.est = est;
        switch (this.est){
            case 1: this.estado = true; break;
            case 0: this.estado = false; break;
            
        }
    }
  public String primaryKey(){
        return String.valueOf(this.getCodigo());
    }
        public String insert(){
            return String.format("%d, %d, %d", this.codigo, this.codDestino, this.est);
        }
        
            public String update(String[] fields){
        String update = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
               case "codigo": 
                   update += ", Codigo = " + this.codigo;
                   break;
               case "coddestino": 
                   update += ", CodDestino = " + this.codDestino;
                   break;
               case "estado": 
                   update += ", Estado = " + this.estado;
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
               case "codigo": 
                   select += ", Codigo";
                   break;
               case "coddestino": 
                   select += ", CodDestino";
                   break;
               case "estado": 
                   select += ", Estado";
                   break;               default:
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
               case "codigo": 
                   condition += "AND Codigo = " + this.codigo;
                   break;
               case "coddestino": 
                   condition += "AND CodDestino = " + this.codDestino;
                   break;
               case "estado": 
                   condition += "AND Estado = " + this.estado;
                   break;            }
        }
         condition = condition.replaceFirst("AND", "");
        return condition;
    }
        
         @Override
        public String table() {
            return this.getClass().getSimpleName();
        }
    
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodDestino() {
        return codDestino;
    }
    public void setCodDestino(int codDestino) {
        this.codDestino = codDestino;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
