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
public class Almacen implements Insertable{
    private int codEnvio;
    private int codPC, codRuta;
    private String destino;
    private int tiempo;
    private double tarifaA;
    private double costo;

    public Almacen(int codEnvio, int codPC, int codRuta, String destino, double tarifaA) {
        this.codEnvio = codEnvio;
        this.codPC = codPC;
        this.codRuta = codRuta;
        this.destino = destino;
        this.tiempo = 0;
        this.tarifaA = tarifaA;
        this.costo = 0;
    }

    public Almacen(int codEnvio, int codPC, int codRuta, String destino, int tiempo, double tarifaA, double costo) {
        this.codEnvio = codEnvio;
        this.codPC = codPC;
        this.codRuta = codRuta;
        this.destino = destino;
        this.tiempo = tiempo;
        this.tarifaA = tarifaA;
        this.costo = costo;
    }

  public String primaryKey(){
        return String.valueOf(this.getCodEnvio());
    }    

       public String insert(){
        return String.format("%d, %d, %d, '%s', %d, %f, %f", this.codEnvio,
                this.codPC, this.codRuta, this.destino,this.tiempo,
                this.tarifaA, this.costo);
    }
        public String update(String[] fields){
        String update = "";
        for (String field : fields) {
            switch (field.toLowerCase()) {
               case "codenvio": 
                   update += ", CodEnvio = " + this.codEnvio;
                   break;
               case "codpc": 
                   update += ", CodPc = " + this.codPC;
                   break;
               case "codruta": 
                   update += ", CodRuta = " + this.codRuta;
                   break;
               case "coddestino": 
                   update += ", Destino = '" + this.destino + "'";
                   break;
               case "tiempo": 
                   update += ", Tiempo = " + this.tiempo;
                   break;
               case "tarifa": 
                   update += ", TarifaA = " + this.tarifaA;
                   break;
               case "costo": 
                   update += ", Costo = " + this.costo;
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
               case "codenvio": 
                   select += ", CodEnvio";
                   break;
               case "codpc": 
                   select += ", CodPc";
                   break;
               case "codruta": 
                   select += ", CodRuta";
                   break;
               case "coddestino": 
                   select += ", Destino";
                   break;
               case "tiempo": 
                   select += ", Tiempo";
                   break;
               case "tarifa": 
                   select += ", TarifaA";
                   break;
               case "costo": 
                   select += ", Costo";
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
               case "codenvio": 
                   condition += " AND CodEnvio = " + this.codEnvio;
                   break;
               case "codpc": 
                   condition += " AND CodPc = " + this.codPC;
                   break;
               case "codruta": 
                   condition += " AND CodRuta = " + this.codRuta;
                   break;
               case "coddestino": 
                   condition += " AND Destino = '" + this.destino + "'";
                   break;
               case "Tiempo": 
                   condition += " AND Tiempo = " + this.tiempo;
                   break;
               case "tarifa": 
                   condition += " AND TarifaA = " + this.tarifaA;
                   break;
               case "costo": 
                    condition += " AND Costo = " + this.costo;
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

    
    public int getCodEnvio() {
        return codEnvio;
    }
    public void setCodEnvio(int codEnvio) {
        this.codEnvio = codEnvio;
    }

    public int getCodPC() {
        return codPC;
    }
    public void setCodPC(int codPC) {
        this.codPC = codPC;
    }

    public int getTiempo() {
        return tiempo;
    }
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public double getTarifaA() {
        return tarifaA;
    }
    public void setTarifaA(int tarifaA) {
        this.tarifaA = tarifaA;
    }

    public double getCosto() {
        if (this.tiempo != 0 && this.tarifaA != 0) {
            this.costo = this.tiempo * this.tarifaA;
        }
        return this.costo;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(int codRuta) {
        this.codRuta = codRuta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

   
    
    
}
