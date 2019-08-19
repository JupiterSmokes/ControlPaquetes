/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author DANIEL
 */
public class Ruta {
    private int codigo; //Codigo de ruta (Llave primaria)
    private int codDestino; //Codigo del destino al que lleva (Llave primaria y foranea)
    private boolean estado; //Esta activa?

    public Ruta(int codigo, int codDestino) {
        this.codigo = codigo;
        this.codDestino = codDestino;
        this.estado = true;
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
