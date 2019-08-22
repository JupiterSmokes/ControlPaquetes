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
public class Envio implements Insertable{
    private int codigo;
    private int codPaquete;
    private int codRuta;
    private int codDestino;
    private double costoT;
    private int tiempoT;
    private boolean recibido;
    private int rec;

    public Envio(int codigo, int codPaquete, int codRuta, int codDestino) {
        this.codigo = codigo;
        this.codPaquete = codPaquete;
        this.codRuta = codRuta;
        this.codDestino = codDestino;
        this.costoT = 0;
        this.tiempoT = 0;
        this.recibido = false;
        this.rec = this.recibido? 1:0;
    }

    public Envio(int codigo, int codPaquete, int codRuta, int codDestino, double costoT, int tiempoT, int rec) {
        this.codigo = codigo;
        this.codPaquete = codPaquete;
        this.codRuta = codRuta;
        this.codDestino = codDestino;
        this.costoT = costoT;
        this.tiempoT = tiempoT;
        this.rec = rec;
        switch (this.rec) {
            case 1: this.recibido = true; break;
            case 0: this.recibido = false;break;
        }
    }

      public String primaryKey(){
        return String.valueOf(this.getCodigo());
    }
    
        public String insert(){
            return String.format("%d, %d, %d, %d, %f, %f, %d", this.codigo, this.codPaquete, this.codRuta, this.codDestino, this.costoT, this.tiempoT, this.rec);
        }
        
        public String update(String[] fields){
            String update = "";
            for (String field : fields) {
                switch (field.toLowerCase()) {
                   case "codigo": 
                       update += ", Codigo = " + this.codigo;
                       break;
                   case "codpaquete": 
                       update += ", CodPaquete = " + this.codPaquete;
                       break;
                   case "codruta": 
                       update += ", CodRuta = " + this.codRuta;
                       break;
                   case "coddestino": 
                       update += ", CodDestino = " + this.codDestino;
                       break;
                   case "tiempot": 
                       update += ", TiempoT = " + this.tiempoT;
                       break;
                   case "costot": 
                       update += ", CostoT = " + this.costoT;
                       break;
                   case "recibido": 
                       update += ", Recibido = " + this.rec;
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
                   case "codpaquete": 
                       select += ", CodPaquete";
                       break;
                   case "codruta": 
                       select += ", CodRuta";
                       break;
                   case "coddestino": 
                       select += ", CodDestino";
                       break;
                   case "tiempot": 
                       select += ", TiempoT";
                       break;
                   case "costot": 
                       select += ", CostoT";
                       break;
                   case "recibido": 
                       select += ", Recibido";
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
                   case "codpaquete": 
                       condition += "AND CodPaquete = " + this.codPaquete;
                       break;
                   case "codruta": 
                       condition += "AND CodRuta = " + this.codRuta;
                       break;
                   case "coddestino": 
                       condition += "AND CodDestino = " + this.codDestino;
                       break;
                   case "tiempot": 
                       condition += "AND TiempoT = " + this.tiempoT;
                       break;
                   case "costot": 
                       condition += "AND CostoT = " + this.costoT;
                       break;
                   case "recibido": 
                       condition += "AND Recibido = " + this.rec;
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

    public int getCodPaquete() {
        return codPaquete;
    }
    public void setCodPaquete(int codPaquete) {
        this.codPaquete = codPaquete;
    }

    public int getCodRuta() {
        return codRuta;
    }
    public void setCodRuta(int codRuta) {
        this.codRuta = codRuta;
    }

    public int getCodDestino() {
        return codDestino;
    }
    public void setCodDestino(int codDestino) {
        this.codDestino = codDestino;
    }

    public double getCostoT() {
        return costoT;
    }
    public void setCostoT(double costoT) {
        this.costoT = costoT;
    }

    public int getTiempoT() {
        return tiempoT;
    }
    public void setTiempoT(int tiempoT) {
        this.tiempoT = tiempoT;
    }

    public boolean isRecibido() {
        return recibido;
    }
    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }
     
    
}
