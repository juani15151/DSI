/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Gaston
 */
public class Zona 
{
    private String nombre;
    private List<Propiedad> propiedades;

    public Zona(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getNombre() 
    {
        return nombre;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    
    public double buscarPromedioNormailizado(Categoria cat, Date desde, Date hasta)
    {
        double suma = 0;
        int count = 0;
        for(Propiedad p : propiedades){
            suma += p.buscarPromedioNormalizado(cat, desde, hasta);
            count++;
            
        }
        // TODO: Calcular promedio normalizado.
        double promedioNormalizado = -1;
        return promedioNormalizado;        
    }

}
