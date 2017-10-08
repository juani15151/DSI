/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

/**
 *
 * @author juani
 */
public class DetalleConceptoFacturado 
{
    private String concepto;
    private double subtotal;

    public DetalleConceptoFacturado(String concepto, double subtotal) {
        this.concepto = concepto;
        this.subtotal = subtotal;
    }
            
    public double getSubTotal()
    {
        return subtotal;
    }

    public String getConcepto() {
        return concepto;
    }

    @Override
    public String toString() {
        return "-- " + concepto + " ( $" + subtotal + ')';
    }
    
    
    
}
