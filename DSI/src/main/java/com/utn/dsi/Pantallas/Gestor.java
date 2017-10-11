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
import com.utn.dsi.Localidad;
import com.utn.dsi.Zona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import persistencia.Persistencia;

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
    private List<Categoria> seleccionCategorias = new LinkedList<>();
    private List<Zona> zonas;
    private List<Zona> seleccionZonas = new LinkedList<>();
    private final List<IEstrategiaEstadistica> estrategias;
    private IEstrategiaEstadistica estrategiaSeleccionada;
  
    
    // VER TIPO DE DATOS DE RETORNO DE CADA METODO
    
    Gestor(Pantalla p){
        pantalla = p;
        estrategias = new LinkedList<>();
        estrategias.add(new Sumatoria());
        estrategias.add(new PromedioNormalizado());
        estrategias.add(new MediaConDE());
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
        return Persistencia.getCategorias();       
    }
    
    public void tomarSeleccionCategorias(Object[] categorias)
    {
        seleccionCategorias.clear();
        for(Object categoria : categorias){
            this.seleccionCategorias.add((Categoria) categoria);
        }
        zonas = buscarZonas();
        pantalla.solicitarSeleccionZonas(zonas);
    }
    
    public List<Zona> buscarZonas()
    {
        List<Localidad> localidades = Persistencia.getLocalidades();
        List<Zona> zonas = new LinkedList();
        for(Localidad l : localidades){
            zonas.addAll(l.getZonas());
        }        
        return zonas;       
    }
    
    public void tomarSeleccionZonas(Object[] zonas)
    {
        seleccionZonas.clear();
        for(Object zona : zonas){
            this.seleccionZonas.add((Zona) zona);
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
    
    public Collection<Map<Object, Double>> calcularEstadistica()
    {
        return estrategiaSeleccionada.calcularEstadistica(seleccionZonas, 
                seleccionCategorias, desde, hasta);
    }
    
    public void generarReporte(Collection<Map<Object, Double>> estadisticas)
    {
        generarReporteZonas((Map<Zona, Double>) estadisticas.toArray()[0]);
        generarReporteCategorias((Map<Categoria, Double>) estadisticas.toArray()[1]);
    }
    
    private void generarReporteZonas(Map<Zona, Double> eZonas){
        StringBuilder reporte_zonas = new StringBuilder();
        for(Entry<Zona, Double> e : eZonas.entrySet()){
            reporte_zonas.append(e.getKey());
            reporte_zonas.append(" -> ");
            reporte_zonas.append(e.getValue());
            reporte_zonas.append("\n");
        }        
        pantalla.mostrarReporteZonas(reporte_zonas.toString());
    }
    
    private void generarReporteCategorias(Map<Categoria, Double> eCats){
        StringBuilder reporte_categorias = new StringBuilder();
        for(Entry<Categoria, Double> e : eCats.entrySet()){
            reporte_categorias.append(e.getKey());
            reporte_categorias.append(" -> ");
            reporte_categorias.append(e.getValue());
            reporte_categorias.append("\n");
        }        
        pantalla.mostrarReporteCategorias(reporte_categorias.toString());
    }
    
    public void tomarDecisionImpresion(boolean print)
    {
        if(print) System.out.println("Imprimiendo reporte...");        
    }
    
    public static Double[] sumEach(Double[] a, Double[] b){        
        Double[] c = new Double[a.length];
        for(int i = 0; i < a.length; i++){
            c[i] = a[i] + b[i];
        }
        return c;
    }
}
