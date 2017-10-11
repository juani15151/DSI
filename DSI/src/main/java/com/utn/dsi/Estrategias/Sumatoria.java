/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Estrategias;

import com.utn.dsi.Categoria;
import com.utn.dsi.Zona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gaston
 */
public class Sumatoria implements IEstrategiaEstadistica {

    @Override
    public Collection<Map> calcularEstadistica(List<Zona> zonas, List<Categoria> categorias, Date desde, Date hasta) {
        HashMap<Zona, Double> estadisticas_zonas = new HashMap<>();
        HashMap<Categoria, Double> estadisticas_categorias = new HashMap<>();
        
        Map<Categoria, Double> estadisticas_por_categoria;
        Double e_zona = 0.0;
        
        for(Zona zona : zonas){ 
            if (zona == null) continue; // Nose porque se da este caso.
            e_zona = 0.0;
            estadisticas_por_categoria = zona.buscarSumatoria(categorias, desde, hasta);
            
            for(Map.Entry<Categoria, Double> e : estadisticas_por_categoria.entrySet()){
                // Une ambos mapas. Si la clave ya existe, suma sus valores.
                estadisticas_categorias.merge(e.getKey(), e.getValue(), Double::sum);                
                e_zona += e.getValue();
            }
            
            estadisticas_zonas.put(zona, e_zona);
            
        }
        
        List estadisticas = new ArrayList();
        estadisticas.add(estadisticas_zonas);
        estadisticas.add(estadisticas_categorias);        
        return estadisticas;
    }
    
    @Override
    public String toString(){
        return "Sumatoria";
    }
}
