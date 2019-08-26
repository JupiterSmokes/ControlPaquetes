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
    private String destino; //Codigo del destino al que lleva (Llave primaria y foranea)
    private boolean estado; //Esta activa?
    private int est;

    public Ruta(int codigo, String destino) {
        this.codigo = codigo;
        this.destino = destino;
        this.estado = true;
        est = this.estado? 1:0;
    }

    public Ruta(int codigo, String destino, int est) {
        this.codigo = codigo;
        this.destino = destino;
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
            return String.format("%d, '%s', %d", this.codigo, this.destino, this.est);
        }
        
    public String update(String[] fields){
        String update = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
               case "codigo": 
                   update += ", Codigo = " + this.codigo;
                   break;
               case "destino": 
                   update += ", Destino = '" + this.destino + "'";
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
               case "destino": 
                   condition += "AND Destino = '" + this.destino + "'";
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

    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public Object[] toArray(){
        return new Object[] {this.codigo, this.destino, this.estado};
    }
}
