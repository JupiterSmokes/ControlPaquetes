/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensajeria;

import Entidades.Almacen;
import Entidades.Envio;
import Entidades.PuntoControl;
import DBManager.connectionManager;
import Funciones.funciones;
import java.sql.ResultSet;
/**
 *
 * @author DANIEL
 */
public class boceto {
    
    public void movimietno(){
        ResultSet almacenes = connectionManager.select(Almacen.class.getSimpleName(), "*", null),
                puntosDeControl,
                
                ;
        
        Almacen enMovimiento = new Almacen(0, 0, 0, "", 0);
        Envio envio;
        PuntoControl actual = new PuntoControl(0,0,"",0,0,""),
                siguiente =new PuntoControl(0,0,"",0,0,"");
        int cola;
        try {
            while (almacenes.next()) {                
                enMovimiento = (Almacen) funciones.recuperar(almacenes, enMovimiento.getClass().getSimpleName());
//                        new Almacen(
//                        almacenes.getInt("CodEnvio"),
//                        almacenes.getInt("CodPc"),
//                        almacenes.getInt("CodRuta"),
//                        almacenes.getString("Destino"),
//                        almacenes.getInt("Tiempo"),
//                        almacenes.getDouble("TarifaA"),
//                        almacenes.getDouble("Costo")
//                );
                puntosDeControl = connectionManager.select(actual, funciones.fields("*"), funciones.fields("codRuta, Destino"));
                puntosDeControl.first();
                if (!puntosDeControl.wasNull()) {
                    actual = (PuntoControl) funciones.recuperar(puntosDeControl, actual.getClass().getSimpleName());
                    if(!puntosDeControl.isLast()){
                        puntosDeControl.next();
                        siguiente = (PuntoControl) funciones.recuperar(puntosDeControl, siguiente.getClass().getSimpleName());
                    }
                }
                cola = connectionManager.query("SELECT COUNT FROM Almacen WHERE " + actual.where(funciones.fields("codpc,codruta,destino"))).getInt(1);
                 
           }
        } catch (Exception e) {
        }
    }
    
}
