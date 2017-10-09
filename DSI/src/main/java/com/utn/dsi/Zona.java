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

    public Zona(String nombre, List<Propiedad> propiedades) 
    {
        this.nombre = nombre;
        this.propiedades = propiedades;
    }

    public String getNombre() 
    {
        return nombre;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    
    public Object[] buscarPromedioNormalizado(List<Categoria> cats, Date desde, Date hasta)
    {
        Map<Categoria, Double> sumatoria_por_categoria = new HashMap<>();
        Map<Categoria, Integer> count_por_categoria = new HashMap<>();      
        
        Object[] estadisticas_propiedad;
        Map<Categoria, Double> sumatoria_propiedad;
        Map<Categoria, Integer> count_propiedad;      
        
        for(Propiedad p : propiedades) {
            estadisticas_propiedad = p.buscarPromedioNormalizado(cats, desde, hasta);
            sumatoria_propiedad = (Map<Categoria, Double>) estadisticas_propiedad[0];
            count_propiedad = (Map<Categoria, Integer>) estadisticas_propiedad[1];
            // Une la suma y count de la propiedad a los de la zona.
            for(Entry<Categoria, Double> e : sumatoria_propiedad.entrySet()){    
                sumatoria_por_categoria.merge(e.getKey(), e.getValue(), Double::sum);                
                count_por_categoria.merge(
                        e.getKey(), 
                        count_propiedad.get(e.getKey()),
                        Integer::sum);
            }
        }
        return new Object[] {sumatoria_por_categoria, count_por_categoria};
    }
    
    public Map buscarSumatoria(List<Categoria> cats, Date desde, Date hasta){
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
