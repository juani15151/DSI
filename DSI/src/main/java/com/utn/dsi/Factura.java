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
public class Factura implements Comparable<Factura>
{
    private int numeroFactura;
    private int diasDeLecturaFacturados;
    private Date fechaFacturacion;
    private Double m3consumidos;
    private Date fechaHoraLectura; // Este atributo no esta en el diagrama de la profe.
    private float estimada; // No esta en el diagrama de la profe.
    private PeriodoFacturacion periodo;
    private List<DetalleConceptoFacturado> detalles;
    private float valorCorregido; // No esta en el diagrama de la profe.
    private float valorLectura; // No esta en el diagrama de la profe.   

    
    public Double getM3consumidos() {
        return m3consumidos;
    }
    
    // No esta en el diagrama de clases de la profe.
    public boolean esLecturaDePeriodo(Date desde, Date hasta){
        return desde.before(fechaHoraLectura) && hasta.after(fechaHoraLectura);
    }
    
    public boolean esDePeriodo(Date desde, Date hasta)
    {
        return periodo.incluidoEnPeriodo(desde, hasta);
    }

    public double calcularPromedioNormalizado()
    {
        return (m3consumidos / diasDeLecturaFacturados) * 30 ;
    }

    @Override
    public int compareTo(Factura f) {
        return fechaHoraLectura.compareTo(f.fechaHoraLectura);        
    }
 
}
