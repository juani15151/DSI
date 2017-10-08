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
public class Factura 
{
    private float estimada;
    private Date fechaHoraLectura;
    private float valorCorregido;
    private float valorLectura;
    private List<DetalleConceptoFacturado> detalles;
    private PeriodoFacturacion periodo;

    public boolean esLecturaDePeriodo(Date desde, Date hasta){
        return desde.before(fechaHoraLectura) && hasta.after(fechaHoraLectura);
    }
    
    public boolean esDePeriodo(Date desde, Date hasta)
    {
        return periodo.incluidoEnPeriodo(desde, hasta);
    }

    public double calcularPromedioNormalizado()
    {
        
        double total = calcularTotal();
        // TODO: Calcular promedio normalizado (como era?)
        double promedioNormalizado = -1;
        
        return promedioNormalizado;
    }

    public double calcularTotal()
    {
        double suma = 0;
        for(DetalleConceptoFacturado detalle : detalles){
            suma += detalle.getSubTotal();
        }
        return suma;        
    }
 
}
