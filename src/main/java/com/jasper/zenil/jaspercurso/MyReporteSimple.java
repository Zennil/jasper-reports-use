package com.jasper.zenil.jaspercurso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jasper.zenil.jaspercurso.bean.Persona;

import org.apache.log4j.Logger;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class MyReporteSimple extends MyBaseReport {

    private static final Logger logger = Logger.getLogger(MyReporteSimple.class);

    @Override
    public DynamicReport construirReporte() throws Exception {
        logger.debug("Construyendo el reporte");
        FastReportBuilder builder = new FastReportBuilder();

        builder.setMargins(20, 40, 30, 30);
        builder.setDetailHeight(10);
        builder.setTitle("Mi reporte con DynamicJasper");
        builder.setSubtitle("Se genero en: " + new Date());
        builder.addColumn("Nombre", "nombre", String.class.getName(), 30);
        builder.addColumn("Apellido Paterno", "apellidoPaterno", String.class.getName(), 45);
        builder.addColumn("Apellido Materno", "apellidoMaterno", String.class.getName(), 45);
        builder.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT);
        builder.setPrintBackgroundOnOddRows(true);
        builder.setUseFullPageWidth(true);
        
        // GroupBuilder gb1 = new GroupBuilder();
        // DJGroup group1 = gb1.setCriteriaColumn((PropertyColumn) builder.getColumn(0))
        //         .setGroupLayout(GroupLayout.VALUE_IN_HEADER).build();
        // builder.addGroup(group1);

        getParams().put("paramAlgo", "lalalalal");

        builder.setTemplateFile("ReportePlantilla.jrxml");

        return builder.build();
    }

    @Override
    public JRDataSource getDataSource() {
        List<Persona> lista = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Persona persona = new Persona();
            persona.setNombre("Sir");
            persona.setApellidoPaterno("Arthur Conan" + i);
            persona.setApellidoMaterno("Doyle" + i);
            lista.add(persona);
        }
        JRDataSource dataSource = new JRBeanCollectionDataSource(lista);
        return dataSource;
    }

    
}