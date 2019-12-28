package com.jasper.zenil.jaspercurso;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import com.jasper.zenil.jaspercurso.bean.SimpleReportOneBean;

import org.apache.log4j.Logger;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ReflectiveReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import ar.com.fdvs.dj.util.SortUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class MyDynamicReportMod extends MyBaseReport {

    private static final Logger logger = Logger.getLogger(MyDynamicReportMod.class);

    @Override
    public DynamicReport construirReporte() throws Exception {
        logger.debug("Inicia la modificacion del reporte");

        final String[] columns = { "name", "address", "zipcode", "country" };

        getParams().put("REPORT_TITLE", "Informe en base a mis polainas");
        // getParams().put("REPORT_LOGO", new File("autentialogo.png").getPath());

        final DynamicReport dynamicReport = new ReflectiveReportBuilder(getDataCollection(), columns)
                .setTemplateFile("dynamicJasperReportTemplate.jrxml").build();

        Style styleColumns = new Style();
        styleColumns.setHorizontalAlign(HorizontalAlign.CENTER);
        styleColumns.setTextColor(Color.PINK);
        styleColumns.setBorder(Border.PEN_1_POINT());

        for (int i = 0; i < columns.length; i++) {
            String name = columns[i];
            final PropertyColumn propertyColumn = (PropertyColumn) dynamicReport.getColumns().get(i);
            propertyColumn.setTitle(name);
            propertyColumn.setBlankWhenNull(true);
            propertyColumn.setWidth(25);
            propertyColumn.setStyle(styleColumns);
        }

        return dynamicReport;
    }

    @Override
    public JRDataSource getDataSource() {
        Collection<SimpleReportOneBean> dataCollectionRep = getDataCollection();
        dataCollectionRep = SortUtils.sortCollection(dataCollectionRep, dynamicReport.getColumns());
        JRDataSource dataSource = new JRBeanCollectionDataSource(dataCollectionRep);
        return dataSource;
    }

    private Collection<SimpleReportOneBean> getDataCollection() {
        Collection<SimpleReportOneBean> dataCollection = new ArrayList<SimpleReportOneBean>();
        for (int i = 0; i < 10; i++) {
            SimpleReportOneBean bean = new SimpleReportOneBean();
            bean.setName("name" + i);
            bean.setAddress("address" + i);
            bean.setZipcode(i);
            bean.setCountry("country" + i);
            dataCollection.add(bean);
        }

        return dataCollection;
    }

}