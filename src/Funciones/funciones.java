/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import DBManager.Insertable;
import Entidades.Almacen;
import Entidades.Cliente;
import Entidades.Destino;
import Entidades.Envio;
import Entidades.Paquete;
import Entidades.PuntoControl;
import Entidades.Ruta;
import Entidades.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DANIEL
 */
public class funciones {
    
    private static DefaultTableModel dm;
    
    public static String[] fields(String fields){
        return fields.split(",");
    }
    
       public static Insertable recuperar(ResultSet recuperado, String table){
         Insertable buscado = null;
        try {
            switch (table.toLowerCase()){
             case "almacen":
                    buscado = new Almacen(recuperado.getInt("CodEnvio"),
                    recuperado.getInt("CodPc"),
                            recuperado.getInt("CodRuta"),
                            recuperado.getString("Destino"),
                            recuperado.getInt("Tiempo"),
                            recuperado.getDouble("TarifaA"),
                            recuperado.getDouble("Costo")
                    );
                    break;
             case "cliente":
                 buscado = new Cliente(recuperado.getString("Nombre")
                        , recuperado.getString("Nit"));
                 break;
             case "destino":
                    buscado = new Destino(recuperado.getString("Direccion"),
                            recuperado.getDouble("Cuota")
                    );
                    break;
             case "envio":
                    buscado = new Envio(recuperado.getInt("Codigo"),
                            recuperado.getInt("CodPaquete"),
                            recuperado.getInt("CodRuta"),
                            recuperado.getString("Destino"),
                            recuperado.getDouble("CostoT"),
                            recuperado.getInt("TiempoT"),
                            recuperado.getInt("Recibido")
                    );
                    break;
             case "paquete":
                    buscado = new Paquete(recuperado.getInt("Codigo"),
                    recuperado.getDouble("Peso"),
                            recuperado.getString("NIT"),
                            recuperado.getInt("Priorizado")
                    );
                    break;
             case "puntocontrol":
                    buscado = new PuntoControl(recuperado.getInt("Codigo"),
                    recuperado.getInt("CodRuta"),
                            recuperado.getString("Destino"),
                            recuperado.getDouble("Tarifa"),
                            recuperado.getDouble("TarifaG"),
                            recuperado.getInt("Limite"),
                            recuperado.getString("Ubicacion")
                    );
                    break;
             case "ruta":
                    buscado = new Ruta(recuperado.getInt("Codigo"),
                    recuperado.getString("Destino"),
                            recuperado.getInt("Estado")
                    );
                    break;
             case "usuario":
                    buscado = new Usuario(recuperado.getString("Nombre"),
                    recuperado.getString("Usuario"),
                            recuperado.getString("Password"),
                            recuperado.getInt("Tipo")
                    );
                    break;
             default:
                 buscado = null;
            }
        } catch (Exception e) {
        }
        return buscado;
    }

    public static  ArrayList<Insertable> recuperar(Insertable obj, ResultSet result ){
        Insertable recuperado;
        ArrayList<Insertable> recuperados = new ArrayList<>();
                try {
            while (result.next()) {                
                recuperado = recuperar(result, obj.getClass().getSimpleName());
                recuperados.add(recuperado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
                return recuperados;
    }

    public static void fillTable(JTable tbl, Object[] cols,Object[][] data){
        cleanTable(tbl);
        dm = new DefaultTableModel(data, cols);
        tbl.setModel(dm);
    }
    public static void addRow(JTable tbl, Object[] data){
        dm = (DefaultTableModel) tbl.getModel();
        dm.addRow(data);
        tbl.setModel(dm);
    }
    
    public static void cleanTable(JTable tbl){
        dm = new DefaultTableModel();
        tbl.setModel(dm);
    }
}
