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

/**
 *
 * @author Gaston
 */
public class PromedioNormalizado implements IEstrategiaEstadistica
{

    @Override
    public List calcularEstadistica(List<Zona> zonas, List<Categoria> categorias, Date desde, Date hasta) {
        HashMap<Zona, Double> estadisticas_zonas = new HashMap<>();
        HashMap<Categoria, Double> sumatoria_por_categorias = new HashMap<>();
        HashMap<Categoria, Integer> count_por_categorias = new HashMap<>();
        
        Map<Categoria, Double> estadisticas_por_categoria;
        
        Double sum_zona;
        Integer count_zona;
        
        for(Zona zona : zonas){                       
            sum_zona = 0.0;
            count_zona = 0;
            
            estadisticas_por_categoria = zona.buscarPromedioNormalizado(categorias, desde, hasta);
            
            for(Map.Entry<Categoria, Double> e : estadisticas_por_categoria.entrySet()){
                // Une ambos mapas. Si la clave ya existe, suma sus valores.
                sumatoria_por_categorias.merge(e.getKey(), e.getValue(), Double::sum);                
                count_por_categorias.merge(e.getKey(),1, Integer::sum);
                sum_zona += e.getValue();
                count_zona++;
            }
            
            estadisticas_zonas.put(zona, sum_zona / count_zona);
            
        }
        
        List estadisticas = new ArrayList();
        estadisticas.add(estadisticas_zonas);
        estadisticas.add(sumatoria_por_categorias);        
        return estadisticas;
    }   
    
}

