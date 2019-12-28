package com.jasper.zenil.jaspercurso;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.jasper.zenil.jaspercurso.bean.CajaAndEtiquetaBean;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ReflectiveReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import ar.com.fdvs.dj.util.SortUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class MyReportCyEPruebaUno extends MyBaseReport {

    // Logger logger = Logger.getLogger(MyReportCyEPruebaUno.class);

    @Override
    public DynamicReport construirReporte() throws Exception {
        // logger.debug("Inicia la modificacion del reporte");

        final String[] columns = { "cve_producto", "nom_producto" };

        getParams().put("REPORT_TITLE", "Informe en base a mis polainas");
        // getParams().put("REPORT_LOGO", new File("autentialogo.png").getPath());

        final DynamicReport dynamicReport = new ReflectiveReportBuilder(getDataCollection(), columns)
                .setTemplateFile("prd_CataEtiquetaRep.jrxml").build();

        Style styleColumns = new Style();
        styleColumns.setHorizontalAlign(HorizontalAlign.CENTER);
        styleColumns.setTextColor(Color.BLUE);
        styleColumns.setBorder(Border.PEN_1_POINT());

        System.out.println("\n Inicia FOR");
        for (int i = 0; i < columns.length; i++) {
            String name = columns[i];
            final PropertyColumn propertyColumn = (PropertyColumn) dynamicReport.getColumns().get(i);
            propertyColumn.setTitle(name);
            propertyColumn.setBlankWhenNull(true);
            propertyColumn.setWidth(10);
            propertyColumn.setStyle(styleColumns);
        }

        return dynamicReport;
    }

    @Override
    public JRDataSource getDataSource() {
        Collection<CajaAndEtiquetaBean> dataCollectionRep = getDataCollection();
        dataCollectionRep = SortUtils.sortCollection(dataCollectionRep, dynamicReport.getColumns());
        JRDataSource dataSource = new JRBeanCollectionDataSource(dataCollectionRep);
        return dataSource;
    }

    private Collection<CajaAndEtiquetaBean> getDataCollection() {
        Collection<CajaAndEtiquetaBean> dataCollection = new ArrayList<CajaAndEtiquetaBean>();
        for (int i = 0; i < 3; i++) {
            CajaAndEtiquetaBean bean = new CajaAndEtiquetaBean();
            bean.setCve_producto("6202");
            bean.setNom_producto("4 plex lalal");
            bean.setVenta_promedio(BigDecimal.valueOf(4.85));
            bean.setExistencias(BigDecimal.valueOf(42.85));
            bean.setDias_inventario(BigDecimal.valueOf(46.85));
            bean.setFecha_inicio_produccion(new Date());
            bean.setMes("Noviembre");
            bean.setUltimo_costo(BigDecimal.valueOf(44.85));
            bean.setCantidad_programada(BigDecimal.valueOf(84.85));
            bean.setCosto_total(BigDecimal.valueOf(64.85));
            bean.setEstatus("P");
            bean.setNum_orden(BigDecimal.valueOf(94.85));
            bean.setEstapa("FAB");
            bean.setLote("JPE83K20");
            bean.setPp(BigDecimal.valueOf(24.85));
            bean.setSt(BigDecimal.valueOf(44.85));
            bean.setAc(BigDecimal.valueOf(74.85));
            bean.setPt(BigDecimal.valueOf(84.85));
            bean.setCve_estructura(BigInteger.valueOf(2));
            dataCollection.add(bean);
        }

        return dataCollection;
    }

}