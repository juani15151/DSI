/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

/**
 *
 * @author Gaston
 */
public class Categoria 
{
    private Float m3BasicosDesde;
    private Float M3BasicosHasta;
    private Float montoBasico;
    private String nombre;
    
    Categoria(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String toString(){
        return nombre;
    }    
    
}
