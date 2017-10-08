/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Pantallas;

import com.utn.dsi.Categoria;
import com.utn.dsi.Zona;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Gaston
 */
public class Gestor 
{
    private final Pantalla pantalla;
    private Date desde;
    private Date hasta;
    private Date fechaActual;
    private List<Categoria> categorias;
    private List<Categoria> seleccionCategorias;
    private List<Zona> zonas;
    private List<Zona> seleccionZonas;
    private float promedioNormalizado;
  
    
    // VER TIPO DE DATOS DE RETORNO DE CADA METODO
    
    Gestor(Pantalla p){
        pantalla = p;
    }
    
    public void estadisticaConsumo()
    {
        pantalla.solicitarPeriodo();
    }
    
    public void tomarPeriodo(Date desde, Date hasta)
    {
        System.out.println(desde.toString() + " - " + hasta.toString());
        if (validarPeriodo(desde, hasta)){
            this.desde = desde;
            this.hasta = hasta; 
            
            fechaActual = obtenerFechaActual();
            categorias = buscarCategorias();
            
            pantalla.solicitarSeleccionCategorias(categorias);
            
        } else {
           // TODO: Como manejar este caso? 
        }
            
    }
    
    public Date obtenerFechaActual()
    {
        return new Date(); // Por defecto es la fecha actual. Verificar.
    }
    
    public boolean validarPeriodo(Date desde, Date hasta)
    {
        return !desde.after(hasta) && !desde.after(fechaActual);
    }
    
    public List<Categoria> buscarCategorias()
    {
        ArrayList<Categoria> list = new ArrayList<>();
        // TODO: Buscar categorias.        
        list.add(new Categoria("test 1"));
        list.add(new Categoria("test 2"));
        list.add(new Categoria("test 3"));
        
        return list;       
    }
    
    public void tomarSeleccionCategorias(Object[] categorias)
    {
        for(Object categoria : categorias){
            this.categorias.add((Categoria) categoria);
        }
        zonas = buscarZonas();
        pantalla.solicitarSeleccionZonas(zonas);
    }
    
    public List<Zona> buscarZonas()
    {
        ArrayList<Zona> list = new ArrayList<>();
        // TODO: Buscar categorias.
        list.add(new Zona("test zona 1"));
        list.add(new Zona("test zona 2"));
        list.add(new Zona("test zona 3"));
        
        return list;       
    }
    
    public void tomarSeleccionZonas(Object[] zonas)
    {
        for(Object zona : zonas){
            this.zonas.add((Zona) zona);
        }
    }
    
    public void tomarSeleccionMetodoEstadistico(Object metodo)
    {
        
    }
    
    public void tomarConfirmacion()
    {
    }
    
    public void calcularPromedioNormalizado()
    {
    }
    
    public void generarReporte()
    {
    }
    
    public void tomarDecisionImpresion()
    {
    }
    
}
