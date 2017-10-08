/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
    
    
    public double buscarPromedioNormalizado(Categoria cat, Date desde, Date hasta)
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
    
    public Map buscarSumatoria(List<Categoria> cats, Date desde, Date hasta){
        Double estadistica_zona = 0.0;
        Map<Categoria, Double> estadistica_por_categorias = new HashMap<>();
        
        for(Propiedad p : propiedades){
            // Suma los valores de las categorias en la propiedad al mapa de todo.
            // Nota: Podria ser mas eficiente si pasamos el mapa hacia abajo y
            // que las otras clases vayan sumando en lugar de hacer el merge.
            for(Entry<Categoria, Double> e : p.buscarSumatoria(cats, desde, hasta).entrySet()){    
                estadistica_por_categorias.merge(e.getKey(), e.getValue(), Double::sum);
            }
            
        }
        return estadistica_por_categorias;        
    }
}
