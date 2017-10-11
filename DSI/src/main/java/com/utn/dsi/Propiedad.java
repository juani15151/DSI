/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

import com.utn.dsi.Estrategias.PromedioNormalizado;
import com.utn.dsi.Pantallas.Gestor;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Propiedad(int nroIdentificacionCatastral, List<Servicio> servicios) {
        this.nroIdentificacionCatastral = nroIdentificacionCatastral;
        this.servicios = servicios;
    }
    
    public Map<Categoria, Double[]> buscarPromedioNormalizado(List<Categoria> cats, Date desde, Date hasta)
    {      
        Map<Categoria, Double[]> estadistica_por_categoria = new HashMap<>();  
        Double[] estadistica_servicio;
        
        for(Servicio s : servicios){
            if(s.esDeCategoria(cats) && s.esPeriodoValido(desde, hasta)){
                // Suma el total de consumo del servicio con el resto
                // de la misma categoria.
                estadistica_servicio = s.buscarPromedioNormalizado(desde, hasta);
                estadistica_por_categoria.merge(
                        s.getCategoria(),
                        estadistica_servicio,
                        Gestor::sumEach);
            }
        }
        
        return estadistica_por_categoria;
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
