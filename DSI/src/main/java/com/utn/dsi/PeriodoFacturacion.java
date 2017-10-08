/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.dsi;

import java.util.Date;

/**
 *
 * @author juani
 */
public class PeriodoFacturacion {
    
    private Date desde;
    private Date hasta;
    private String nombre;

    public PeriodoFacturacion(Date desde, Date hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public Date getDesde() {
        return desde;
    }

    public Date getHasta() {
        return hasta;
    }   
    
    public boolean incluidoEnPeriodo(Date desde, Date hasta){
        // TODO: Creo que no contempla iguales. Verificar.
        return this.desde.after(desde) && this.hasta.before(hasta);
    }
    
}
