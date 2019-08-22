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
public class PuntoControl implements Insertable{
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

    public PuntoControl(int codigo, int codRuta, int codDestino, double tarifa, double tarifaG, int limite, String ubicacion) {
        this.codigo = codigo;
        this.codRuta = codRuta;
        this.codDestino = codDestino;
        this.tarifa = tarifa;
        this.tarifaG = tarifaG;
        this.limite = limite;
        this.ubicacion = ubicacion;
    }
    

        public String insert(){
            return String.format("%d, %d, %d, %f, %f, %d, ?, '%s'", this.codigo, this.codRuta, this.codDestino, this.tarifa, this.tarifaG, this.limite, this.ubicacion);
        }
         
        public String update(String[] fields){
            String update = "";
            for (String field : fields) {
                switch (field.toLowerCase()) {
                   case "codigo": 
                       update += ", Codigo = " + this.codigo;
                       break;
                   case "tarifa": 
                       update += ", Tarifa = " + this.tarifa;
                       break;
                   case "codruta": 
                       update += ", CodRuta = " + this.codRuta;
                       break;
                   case "coddestino": 
                       update += ", CodDestino = " + this.codDestino;
                       break;
                   case "tarifag": 
                       update += ", TarifaG = " + this.tarifaG;
                       break;
                   case "limite": 
                       update += ", Limite = " + this.limite;
                       break;
                   case "ubicacion": 
                       update += ", Ubicacion = '" + this.ubicacion + "'";
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
                   case "tarifa": 
                       select += ", Tarifa";
                       break;
                   case "codruta": 
                       select += ", CodRuta";
                       break;
                   case "coddestino": 
                       select += ", CodDestino";
                       break;
                   case "tarifag": 
                       select += ", TarifaG";
                       break;
                   case "limite": 
                       select += ", Limite";
                       break;
                   case "ubicacion": 
                       select += ", Ubicacion";
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
                   case "tarifa": 
                       condition += "AND Tarifa = " + this.tarifa;
                       break;
                   case "codruta": 
                       condition += "AND CodRuta = " + this.codRuta;
                       break;
                   case "coddestino": 
                       condition += "AND CodDestino = " + this.codDestino;
                       break;
                   case "tarifag": 
                       condition += "AND TarifaG = " + this.tarifaG;
                       break;
                   case "limite": 
                       condition += "AND Limite = " + this.limite;
                       break;
                   case "ubicacion": 
                       condition += "AND Ubicacion = '" + this.ubicacion + "'";
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
