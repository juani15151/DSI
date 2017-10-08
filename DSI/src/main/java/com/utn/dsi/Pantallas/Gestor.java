/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Pantallas;

import com.utn.dsi.Categoria;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Gaston
 */
public class Gestor 
{
    private Pantalla pantalla;
    private Date periodo;
    private Date fechaActual;
    private String categorias;
    private String seleccionCategorias;
    private String zonas;
    private String seleccionZonas;
    private float promedioNormalizado;
  
    
    // VER TIPO DE DATOS DE RETORNO DE CADA METODO
    
    public Gestor(Pantalla p){
        pantalla = p;
    }
    
    public void estadisticaConsumo()
    {
        pantalla.solicitarPeriodo();
    }
    
    public void tomarPeriodo(Date desde, Date hasta)
    {
        if (validarPeriodo(desde, hasta)){
            // TODO: Guardar periodo.
            List<Categoria> cats = buscarCategorias();
            pantalla.solicitarSeleccionCategorias(cats);
        }
    }
    
    public Date obtenerFechaActual()
    {
        return new Date(); // Por defecto es la fecha actual. Verificar.
    }
    
    public boolean validarPeriodo(Date desde, Date hasta)
    {
        // TODO: Implementar
        return true;
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
        // TODO: Castear a categoria
    }
    
    public void buscarZonas()
    {
    }
    
    public void tomarSeleccionZonas(Object[] zonas)
    {
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