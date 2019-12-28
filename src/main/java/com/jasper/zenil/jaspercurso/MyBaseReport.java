package com.jasper.zenil.jaspercurso;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public abstract class MyBaseReport {

    private static final Logger logger = Logger.getLogger(MyBaseReport.class);

    protected JasperPrint jasperPrint;
    protected JasperReport jasperReport;
    protected Map<String, Object> params = new HashMap<>();
    protected DynamicReport dynamicReport;

    public abstract DynamicReport construirReporte() throws Exception;

    public abstract JRDataSource getDataSource();

    public void generarReporte() throws Exception {

        dynamicReport = construirReporte();

        JRDataSource dataSource = getDataSource();

        jasperReport = DynamicJasperHelper.generateJasperReport(dynamicReport, getLayoutManager(), params);

        logger.debug("Llenado el reporte !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (dataSource != null) {
            jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        } else {
            jasperPrint = JasperFillManager.fillReport(jasperReport, params);
        }
        logger.debug("LLenado terminado ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }

    protected LayoutManager getLayoutManager() {
        return new ClassicLayoutManager();
    }

    protected void exportToJRXML() throws Exception {
        if (jasperReport != null)
            DynamicJasperHelper.generateJRXML(jasperReport, "UTF-8", "PreReporteMamalon.jrxml");
        else
            DynamicJasperHelper.generateJRXML(dynamicReport, getLayoutManager(), this.params, "UTF-8",
                    "PreReporteMamalon.jrxml");
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public JasperReport getJasperReport() {
        return jasperReport;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}