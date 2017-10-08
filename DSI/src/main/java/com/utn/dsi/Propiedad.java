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
    
    
    public double buscarPromedioNormalizado(Categoria cat, Date desde, Date hasta)
    {
        double suma = 0;
        int count = 0;
        for(Servicio s : servicios){
            if(s.esDeCategoria(cat) && s.esPeriodoValido(desde, hasta)){
                suma += s.buscarPromedioNormalizado(desde, hasta);
                count++;
            }
        }
        // TODO: Calcular promedio normalizado.
        double promedioNormalizado = -1;
        return promedioNormalizado;
    }
    
}
