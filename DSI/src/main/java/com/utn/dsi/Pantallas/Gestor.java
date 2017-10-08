/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Pantallas;

import com.utn.dsi.Categoria;
import com.utn.dsi.Estrategias.IEstrategiaEstadistica;
import com.utn.dsi.Estrategias.MediaConDE;
import com.utn.dsi.Estrategias.PromedioNormalizado;
import com.utn.dsi.Estrategias.Sumatoria;
import com.utn.dsi.Zona;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.LinkedList;

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
    private final List<IEstrategiaEstadistica> estrategias;
    private IEstrategiaEstadistica estrategiaSeleccionada;
  
    
    // VER TIPO DE DATOS DE RETORNO DE CADA METODO
    
    Gestor(Pantalla p){
        pantalla = p;
        estrategias = new LinkedList<>();
        estrategias.add(new Sumatoria());
        estrategias.add(new PromedioNormalizado());
        //metodos.add(new MediaConDE());
    }
    
    public void estadisticaConsumo()
    {
        pantalla.solicitarPeriodo();
    }
    
    public void tomarPeriodo(Date desde, Date hasta)
    {
        fechaActual = obtenerFechaActual();
        //System.out.println(desde.toString() + " - " + hasta.toString());
        if (validarPeriodo(desde, hasta)){
            this.desde = desde;
            this.hasta = hasta; 
            
            
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
        return desde.before(hasta) && desde.before(fechaActual);
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
        System.out.println("Gestor::tomarSeleccionCategorias() invocado.");
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
        pantalla.solicitarSeleccionMetodoEstadistico(estrategias);
    }
    
    public void tomarSeleccionMetodoEstadistico(Object estrategia)
    {
        estrategiaSeleccionada = (IEstrategiaEstadistica) estrategia;
        pantalla.solicitarConfirmacion();
    }
    
    public void tomarConfirmacion()
    {
        generarReporte(calcularEstadistica());
        pantalla.solicitarDecisionImpresion();
    }
    
    public List calcularEstadistica()
    {
        return estrategiaSeleccionada.calcularEstadistica(seleccionZonas, 
                seleccionCategorias, desde, hasta);
    }
    
    public void generarReporte(List estadisticas)
    {
        //TODO: Implementar.
        pantalla.mostrarReporte();
    }
    
    public void tomarDecisionImpresion(boolean print)
    {
        
    }
    
}
