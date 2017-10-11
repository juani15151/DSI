/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi.Estrategias;

import com.utn.dsi.Categoria;
import com.utn.dsi.Categoria;
import com.utn.dsi.Zona;
import com.utn.dsi.Zona;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gaston
 */
public interface IEstrategiaEstadistica 
{
    // La lista debe contener 2 mapas, uno con zonas y otro con categorias.
    Collection<Map> calcularEstadistica(List<Zona> zonas, List<Categoria> categorias, Date desde, Date hasta);
}
