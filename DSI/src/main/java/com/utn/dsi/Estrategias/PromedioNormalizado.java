/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Estrategias;

import com.utn.dsi.Categoria;
import com.utn.dsi.Zona;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Calcula el promedio de los consumos normalizados de las facturas por zona y categoria.
 * @author Gaston
 */
public class PromedioNormalizado implements IEstrategiaEstadistica
{

    @Override
    public List calcularEstadistica(List<Zona> zonas, List<Categoria> categorias, Date desde, Date hasta) {
        HashMap<Zona, Double> estadisticas_zonas = new HashMap<>();
        HashMap<Categoria, Double> sumatoria_por_categorias = new HashMap<>();
        HashMap<Categoria, Integer> count_por_categorias = new HashMap<>();
        
        Object[] estadistica_from_zona;
        Map<Categoria, Double> sumatoria_from_zona;
        Map<Categoria, Integer> count_from_zona;
             
        
        Double sum_zona;
        Integer count_zona;
        
        for(Zona zona : zonas){  
            if (zona == null) continue;
            sum_zona = 0.0;
            count_zona = 0;
            
            estadistica_from_zona = zona.buscarPromedioNormalizado(categorias, desde, hasta);
            sumatoria_from_zona = (Map<Categoria, Double>) estadistica_from_zona[0];
            count_from_zona = (Map<Categoria, Integer>) estadistica_from_zona[1];
            
            for(Map.Entry<Categoria, Double> e : sumatoria_from_zona.entrySet()){
                // Une ambos mapas. Si la clave ya existe, suma sus valores.
                sumatoria_por_categorias.merge(e.getKey(), e.getValue(), Double::sum);                
                count_por_categorias.merge(e.getKey(), count_from_zona.get(e.getKey()), Integer::sum);
                sum_zona += e.getValue();
                count_zona++;
            }
            
            estadisticas_zonas.put(zona, sum_zona / count_zona);
            
        }
        
        for(Map.Entry<Categoria, Double> e : sumatoria_por_categorias.entrySet()){
            Double promedio_categoria = e.getValue() / count_por_categorias.get(e.getKey());
            e.setValue(promedio_categoria);           
            // Ahora sumatoria_por_categorias contiene los promedio_por_categoria.
        }
        
        List estadisticas = new ArrayList();
        estadisticas.add(estadisticas_zonas);
        estadisticas.add(sumatoria_por_categorias);       
        return estadisticas;
    }   
    
    @Override
    public String toString(){
        return "Promedio Normalizado";
    }
    
}

