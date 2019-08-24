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
public class Paquete implements Insertable {
    private int codigo; //Codigo de identificacion (Llave primaria)
    private double peso; //Peso del paquete
    private String nit; //Llave foranea de Cliente
    private boolean priorizado; //El paquete esta priorizado?
    private int prior;

    public Paquete(int codigo, double peso, String nit, boolean priorizado) {
        this.codigo = codigo;
        this.peso = peso;
        this.nit = nit;
        this.priorizado = priorizado;
        this.prior = priorizado? 1:0;
        
    }

    public Paquete(int codigo, double peso, String nit, int prior) {
        this.codigo = codigo;
        this.peso = peso;
        this.nit = nit;
        this.prior = prior;
        switch(this.prior){
            case 1: priorizado = true; break;
            case 0: priorizado = false; break;
        }
    }
    
      public String primaryKey(){
        return String.valueOf(this.getCodigo());
    }

        public String insert(){
            return String.format("%d, %f, '%s', %d", this.codigo, this.peso, this.nit, this.prior);
        }
        
                public String update(String[] fields){
        String update = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
               case "codigo": 
                   update += ", Codigo = " + this.codigo;
                   break;
               case "peso": 
                   update += ", Peso = " + this.peso;
                   break;
               case "nit": 
                   update += ", NIT = '" + this.nit + "'";
                   break;
               case "priorizado": 
                   update += ", Priorizado = " + this.prior;
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
               case "peso": 
                   select += ", Peso";
                   break;
               case "nit": 
                   select += ", NIT";
                   break;
               case "priorizado": 
                   select += ", Priorizado";
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
                              case "codigo": 
                   condition += "AND Codigo = " + this.codigo;
                   break;
               case "peso": 
                   condition += "AND Peso = " + this.peso;
                   break;
               case "nit": 
                   condition += "AND NIT = '" + this.nit + "'";
                   break;
               case "priorizado": 
                   condition += "AND Priorizado = " + this.prior;
                   break;
            }
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
