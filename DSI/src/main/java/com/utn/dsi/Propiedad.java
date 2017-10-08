/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author juani
 */
public class Propiedad 
{
    private String calle;
    private String departamento;
    private int nroIdentificacionCatastral;
    private int numero;
    private int piso;
    private List<Servicio> servicios;
    
    
    public Map<Categoria, Double> buscarPromedioNormalizado(List<Categoria> cats, Date desde, Date hasta)
    {
        throw new NotImplementedException();
        // TODO: Hacer.
        Map<Categoria, Double> estadistica_por_categoria = new Map<>();

        for(Servicio s : servicios){
            if(s.esDeCategoria(cats) && s.esPeriodoValido(desde, hasta)){
                
                suma += s.buscarPromedioNormalizado(desde, hasta);
                count++;
            }
        }
        // TODO: Calcular promedio normalizado.
        double promedioNormalizado = -1;
        return promedioNormalizado;
    }
    
    public Map<Categoria, Double> buscarSumatoria(List<Categoria> cats, Date desde, Date hasta){
        Map<Categoria, Double> estadistica_por_categoria = new HashMap<>();
        
        Double suma;
        for(Servicio s : servicios){
            if(s.esDeCategoria(cats) && s.esPeriodoValido(desde, hasta)){
                // Suma el total de consumo del servicio con el resto
                // de la misma categoria.
                suma = estadistica_por_categoria.getOrDefault(s.getCategoria(), 0.0);
                suma += s.calcularSumatoria(desde, hasta);
                estadistica_por_categoria.put(s.getCategoria(), suma);
            }
        }
        return estadistica_por_categoria;
    }
    
}
