package com.jasper.zenil.jaspercurso;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class MyScriptlet extends JRDefaultScriptlet {

    public void afterReportInit() throws JRScriptletException {
        System.out.println("call afterReportInit()");
        // this.setVariableValue("AllCountries", sbuffer.toString());
        this.setVariableValue("someVar", new String("El valor de esta variable fue modificada por Scriptlet."));
    }

    public String hello() throws JRScriptletException {
        return "Hola! Soy el objeto scriptlet del reporte.";
    }
}