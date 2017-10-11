/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Estrategias;

import com.utn.dsi.Categoria;
import com.utn.dsi.Pantallas.Gestor;
import com.utn.dsi.Zona;
import java.util.ArrayList;
import java.util.Collection;
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
    public Collection<Map<Object, Double>> calcularEstadistica(List<Zona> zonas, List<Categoria> cats, Date desde, Date hasta) {
        Map<Zona, Double> estadisticas_zonas = new HashMap<>();
        Map<Categoria, Double[]> valores_por_categoria = new HashMap<>();
        Map<Categoria, Double> estadisticas_por_categoria = new HashMap<>();
        
        for(Zona zona : zonas){  
            if (zona == null) continue;           
            double suma_zona = 0.0;
            double count_zona = 0.0;

            for(Map.Entry<Categoria, Double[]> eZona : 
                    zona.buscarPromedioNormalizado(cats, desde, hasta).entrySet()) {
                        valores_por_categoria.merge(
                        eZona.getKey(),
                        eZona.getValue(),
                        Gestor::sumEach);
                    suma_zona += eZona.getValue()[0];
                    count_zona += eZona.getValue()[1];
            }  
            estadisticas_zonas.put(zona, suma_zona / count_zona);
        }
        
        for(Map.Entry<Categoria, Double[]> vCat : valores_por_categoria.entrySet()){
            Double promedio = vCat.getValue()[0] / vCat.getValue()[1];
            estadisticas_por_categoria.put(vCat.getKey(), promedio);
        }
        
        List estadisticas = new ArrayList();
        estadisticas.add(estadisticas_zonas);
        estadisticas.add(estadisticas_por_categoria);       
        return estadisticas;
    }   
    
    
    
    @Override
    public String toString(){
        return "Promedio Normalizado";
    }
    
}

