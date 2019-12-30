package com.jasper.zenil.jaspercurso;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportGenerador {

    public static final String REPORTE_SIMPLE_PELICULAS = "ReporteSimplePeliculas.jasper";
    public static final String REPORTE_BY_CLASIFICACION = "ReportePeliculasByRating.jasper";
    public static final String REPORTE_CON_PARAMETRO = "ReporteRatingConParametro.jasper";

    public static JasperPrint generaReporteSimplePeliculas() {
        try {
            JasperPrint reporteLleno = JasperFillManager.fillReport(REPORTE_SIMPLE_PELICULAS, new HashMap<>(),
                    Conexion.getMySQLConnection());
            return reporteLleno;
        } catch (JRException ex) {
            Logger.getLogger(ReportGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JasperPrint generaReportePeliculasByRating() {
        try {
            JasperPrint reproteLleno = JasperFillManager.fillReport(REPORTE_BY_CLASIFICACION, new HashMap<>(),
                    Conexion.getMySQLConnection());
            return reproteLleno;
        } catch (JRException ex) {
            Logger.getLogger(ReportGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JasperPrint generaReporteRatingConParametro(String rating) {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("rating_reporte", rating);

        try {
            JasperPrint reporteLleno = JasperFillManager.fillReport(REPORTE_CON_PARAMETRO, parametros,
                    Conexion.getMySQLConnection());
            return reporteLleno;
        } catch (JRException ex) {
            Logger.getLogger(ReportGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JasperPrint generaReporteDynamicJasper() throws Exception {
        DynamicReport dynamRep = crearDynamicReport();
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        JasperReport jaspReport = DynamicJasperHelper.generateJasperReport(dynamRep, getLayouManager(), parametros);
        try {
            JasperPrint reporteLleno = JasperFillManager.fillReport(jaspReport, parametros);
            return reporteLleno;
        } catch (JRException ex) {
            Logger.getLogger(ReportGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Metodo que crea el DynamicReport
    public static DynamicReport crearDynamicReport() throws Exception {
        FastReportBuilder drb = new FastReportBuilder();

        drb.addColumn("Nombre", "name", String.class.getName(), 30)
                .addColumn("Direccion", "address", String.class.getName(), 30)
                .addColumn("Codigo ZIP", "zipcode", Integer.class.getName(), 10)
                .addColumn("Pais", "country", String.class.getName(), 50).setTitle("Este es el titulo del reporte")
                .setSubtitle("Reporte creado con Dynamic Jasper").setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true);
        return drb.build();
    }

    // Metodo que crea el Layout que se usara para generar un JasperReport
    static protected LayoutManager getLayouManager() {
        return new ClassicLayoutManager();
    };
}