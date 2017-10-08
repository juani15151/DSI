/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Estrategias;

import com.utn.dsi.Categoria;
import com.utn.dsi.Zona;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gaston
 */
public class MediaConDE implements IEstrategiaEstadistica {

    @Override
    public List calcularEstadistica(List<Zona> zonas, List<Categoria> categorias, Date desde, Date hasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        return "Media con Desviación Estándar";
    }
    
}
