/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.utn.dsi.Categoria;
import com.utn.dsi.Factura;
import com.utn.dsi.Localidad;
import com.utn.dsi.PeriodoFacturacion;
import com.utn.dsi.Propiedad;
import com.utn.dsi.Servicio;
import com.utn.dsi.Zona;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author juani
 */
public class Persistencia {
    private static int GEN_LOCALIDADES = 4;
    private static int GEN_ZONAS = 2;
    private static int MAX_GEN_PROPIEDADES = 5;
    private static int MAX_GEN_SERVICIOS = 4;
    private static int MAX_GEN_FACTURAS = 8;
    private static int GEN_CATEGORIAS = 3;
    private static Date DATE_MAX = new Date(); // Hoy.
    private static Date DATE_MIN = MIN_DATE();

    private static List<Categoria> categorias;
    private static List<PeriodoFacturacion> periodos;
    
    public static List<Localidad> getLocalidades(){
        return generateLocalidades();
    }
    
    public static List<Categoria> getCategorias(){
        if (categorias == null){
            categorias = generateCategorias();
        }
        return categorias;
    }
    
    private static Categoria getRandomCategoria(){
        return getCategorias().get((int) (Math.random() * categorias.size()));
    }
    
    private static List<Localidad> generateLocalidades(){
        String[] pre_nombres = { "", "Río ", "Villa ", "San ", "Santa "};
        String[] nombres = {"Córdoba", "Primero", "Segundo", "Tercero", "Cuarto", "Quinto",
        "Agustin", "Maria", "Manolo"};
        
        List<Localidad> localidades = new LinkedList<>();
        for(int i = 0; i < GEN_LOCALIDADES; i++){
            String nombre = pre_nombres[(int) (Math.random() * pre_nombres.length)] +
                    nombres[(int) (Math.random() * nombres.length)];
            localidades.add(new Localidad(nombre, generateZonas()));
        }
        return localidades;
    }
    
    private static List<Zona> generateZonas(){
        String[] pre_nombres = { "", "Bº ", "Barrio ", "Villa ", "Nueva ", "Ciudad ", "General "};
        String[] nombres = {"de los cuartetos", "Paz", "Córdoba", "Yapeyu", "Pueyrredon", "Centro"};
        
        List<Zona> zonas = new LinkedList<>();
        for(int i = 0; i < GEN_ZONAS; i++){
            String nombre = pre_nombres[(int) (Math.random() * pre_nombres.length)] +
                    nombres[(int) (Math.random() * nombres.length)];
            zonas.add(new Zona(nombre, generatePropiedades()));
        }
        return zonas;
    }
    
    private static List<Propiedad> generatePropiedades(){
        List<Propiedad> propiedades = new LinkedList<>();
        int gen = (int) (Math.random() * MAX_GEN_PROPIEDADES);
        for(int i = 0; i < gen; i++){
            propiedades.add(new Propiedad((int) (Math.random() * 10000),
                            generateServicios()));
        }
        
        return propiedades;
    }
    
    private static List<Servicio> generateServicios(){
        List<Servicio> servicios = new LinkedList<>();        
        int gen = (int) (Math.random() * MAX_GEN_SERVICIOS);
        
        for(int i = 0; i < gen; i++){
            servicios.add(new Servicio(generateRandomDate(),
                generateFacturas(), getRandomCategoria()));
        }
        
        return servicios;
    }
    
    private static Date generateRandomDate(){
        return generateRandomDate(DATE_MIN, DATE_MAX);
    }
    
    private static Date generateRandomDate(Date desde, Date hasta){
        
        long MILLIS_PER_DAY = 1000*60*60*24;
        GregorianCalendar s = new GregorianCalendar();
        s.setTimeInMillis(desde.getTime());
        GregorianCalendar e = new GregorianCalendar();
        e.setTimeInMillis(hasta.getTime());
       
        // Get difference in milliseconds
        long endL   =  e.getTimeInMillis() +  e.getTimeZone().getOffset(e.getTimeInMillis());
        long startL = s.getTimeInMillis() + s.getTimeZone().getOffset(s.getTimeInMillis());
        long dayDiff = (endL - startL) / MILLIS_PER_DAY;
       
        Calendar cal = Calendar.getInstance();
        cal.setTime(desde);
        cal.add(Calendar.DATE, new Random().nextInt((int)dayDiff));          
        return cal.getTime();
    }
    
    private static Date MIN_DATE(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date DATE_MIN = new Date();
        try{
            DATE_MIN = df.parse("01/01/2010");
        } catch (Exception e){
            // Silence
        } 
        return DATE_MIN;
    }
    
    private static List<Factura> generateFacturas(){
        List<Factura> facturas = new LinkedList<>(); 
        int gen = (int) (Math.random() * MAX_GEN_FACTURAS);
        for(int i = 0; i < gen; i++){
            PeriodoFacturacion periodo = getRandomPeriodo();
            Date fechaFacturacion = generateRandomDate(periodo.getDesde(), periodo.getHasta());
            Date fechaHoraLectura = generateRandomDate(periodo.getDesde(), fechaFacturacion);
            facturas.add(new Factura(
                    (int) (Math.random() * 10000),
                    (int) (Math.random() * 5 + 25),
                    fechaFacturacion,
                    (Math.random() * 250),
                    fechaHoraLectura,
                    periodo));
        }
        
        return facturas;
    }
    
    private static PeriodoFacturacion getRandomPeriodo(){
        if (periodos == null){
            periodos = generatePeriodos();
        }
        return periodos.get((int) (Math.random() * periodos.size()));
    }
    
    private static List<PeriodoFacturacion> generatePeriodos(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(DATE_MIN);
        cal.add(Calendar.MONTH, 1);
        Date before = DATE_MIN;
        Date hoy = new Date();
        
        List<PeriodoFacturacion> periodos = new LinkedList<>();
        for(; cal.getTime().before(hoy); cal.add(Calendar.MONTH, 1)){
            periodos.add(new PeriodoFacturacion(before, cal.getTime()));
        }
        return periodos;
    }
    
    private static List<Categoria> generateCategorias(){
        String[] nombres = {"Primera", "Segunda", "Tercera", "Alto", "Medio", "Bajo"};
        
        List<Categoria> categorias = new LinkedList<>();
        for(int i = 0; i < GEN_CATEGORIAS; i++){
            String nombre = nombres[(int) (Math.random() * nombres.length)];
            categorias.add(new Categoria(nombre));
        }
        return categorias;
    }
    
    
}
