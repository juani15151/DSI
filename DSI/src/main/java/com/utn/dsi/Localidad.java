/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gaston
 */
public class Localidad 
{
    private String nombre;
    private List<Zona> zonas;

    public Localidad(String nombre, List<Zona> zonas) {
        this.nombre = nombre;
        this.zonas = zonas;
    }  

    public List<Zona> getZonas() {
        return new LinkedList<>(zonas);
    }

    
    // ver tipo de dato de retorno
    public void buscarZonasDeLocalidad()
    {
    }
    
    // ver tipo de dato de retorno
    public void buscarPromedioNormalizado()
    {
    }
    
}
