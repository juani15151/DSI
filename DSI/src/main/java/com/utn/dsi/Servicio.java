/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author juani
 */
public class Servicio 
{
    private Date fechaAlta;
    private Date fechaDeSolicitud;
    private List<Factura> facturas;
    private Categoria categoria;

    public Servicio(Date fechaAlta, List<Factura> facturas, Categoria categoria) {
        this.fechaAlta = fechaAlta;
        this.facturas = facturas;
        this.categoria = categoria;
    }
    
    
    
    public boolean esDeCategoria(Categoria cat)            
    {
        return this.categoria.equals(cat);
    }
    
    public boolean esDeCategoria(List<Categoria> cats){
        for(Categoria cat : cats){
            if(esDeCategoria(cat)) return true;
        }
        return false;
    }
    
    public Categoria getCategoria(){
        return categoria;
    }
    
    public boolean esPeriodoValido(Date desde, Date hasta)
    {
        return fechaAlta.after(desde) && fechaAlta.before(hasta);
    }
    
    public Double[] buscarPromedioNormalizado(Date desde, Date hasta)
    {
        Double[] estadistica = new Double[]{0.0, 0.0};

        for( Factura factura : facturas){
            if (factura.esLecturaDePeriodo(desde, hasta)){
                estadistica[0] += factura.calcularConsumoNormalizado(); //suma
                estadistica[1]++; //count
            }
        }
        return estadistica;
    }
    
    public Double calcularSumatoria(Date desde, Date hasta){
        double suma = 0.0;
                
        for(Factura factura : facturas){
            if (factura.esLecturaDePeriodo(desde, hasta)){
                suma += factura.getM3consumidos();
            }
        }
        return suma;
    }
    
    
    
}
