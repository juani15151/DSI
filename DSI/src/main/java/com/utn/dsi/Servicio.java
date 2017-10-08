/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

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
            if(!esDeCategoria(cat)) return false;
        }
        return true;
    }
    
    public Categoria getCategoria(){
        return categoria;
    }
    
    public boolean esPeriodoValido(Date desde, Date hasta)
    {
        return fechaAlta.after(desde) && fechaAlta.before(hasta);
    }
    
    public Double buscarPromedioNormalizado(Date desde, Date hasta)
    {
        double suma = 0.0;
        int count = 0;
        for( Factura factura : facturas){
            if (factura.esLecturaDePeriodo(desde, hasta)){
                suma += factura.calcularPromedioNormalizado();
                count++;
            }
        }
        // Nota: No estoy seguro que esta lógica funcione
        // estariamos promediando promedios...
        return suma / count;
    }
    
    public Double calcularSumatoria(Date desde, Date hasta){
        double suma = 0.0;
                
        for( Factura factura : facturas){
            if (factura.esLecturaDePeriodo(desde, hasta)){
                suma += factura.getM3consumidos();
            }
        }
        return suma;
    }
    
    
    
}
