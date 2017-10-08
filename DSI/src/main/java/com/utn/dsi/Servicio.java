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
public class Servicio 
{
    private Date fechaAlta;
    private Date fechaDeSolicitud;
    private List<Factura> facturas;
    private Categoria categoria;
    
    // ver tipo de dato de retorno en cada metodo
    
    public boolean esDeCategoria(Categoria cat)            
    {
        return this.categoria.equals(cat);
    }
    
    public boolean esPeriodoValido(Date desde, Date hasta)
    {
        return true;
    }
    
    public double buscarPromedioNormalizado(Date desde, Date hasta)
    {
        double suma = 0;
        int count = 0;
        for( Factura factura : facturas){
            if (factura.esLecturaDePeriodo(desde, hasta)){
                // Nota: No estoy seguro que esta lógica funcione
                // estariamos promediando promedios...
                suma += factura.calcularPromedioNormalizado();
                count++;
            }
        }
        // TODO: Calcular promedio normalizado
        double promedioNormalizado = -1;
        return promedioNormalizado;
    }
    
}
