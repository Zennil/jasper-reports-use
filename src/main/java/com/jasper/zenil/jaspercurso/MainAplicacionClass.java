package com.jasper.zenil.jaspercurso;

import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperDesignViewer;

@SpringBootApplication
public class MainAplicacionClass {

	public static final Logger logger = Logger.getLogger(MainAplicacionClass.class);
	URL urlRepTempl = getClass().getClassLoader().getResource("jasper_report_template.jrxml");

	public static void main(String[] args) throws Exception {

		// viewReportDesign();

		compilarUnJRXML();

		// FIN ---------------------------------------------------------

		// newReporteSimplePelicula();

		// newReporteByClasificacion();

		// newReporteConParametro();

		// newReporteDynamic();

		// newMyDynamicReport();

		// newMyDynamicReportMod();

		// newMyReportCyEPruebaUno();

		// Codigo para mostrar un reporte ya creado
		// JasperViewer viewer = new JasperViewer(reporteSimplePeliculas);
		// viewer.setVisible(true);

	}

	static void newReporteSimplePelicula() throws Exception {
		JasperPrint reporteSimplePeliculas = ReportGenerador.generaReporteSimplePeliculas();
		JasperExportManager.exportReportToPdfFile(reporteSimplePeliculas, "ReporteMamalonSimplePeliculas.pdf");
	}

	static void newReporteByClasificacion() throws Exception {
		JasperPrint reporteByClasificacion = ReportGenerador.generaReportePeliculasByRating();
		JasperExportManager.exportReportToPdfFile(reporteByClasificacion, "ReporteMamalonByClasificacion.pdf");
	}

	static void newReporteConParametro() throws Exception {
		JasperPrint reporteConParametro = ReportGenerador.generaReporteRatingConParametro("NC-17");
		JasperExportManager.exportReportToPdfFile(reporteConParametro, "ReporteMamalonConParametro.pdf");
	}

	static void newReporteDynamic() throws Exception {
		JasperPrint reporteDynamic = ReportGenerador.generaReporteDynamicJasper();
		JasperExportManager.exportReportToPdfFile(reporteDynamic, "DynamicReport.pdf");
	}

	static void newMyDynamicReport() throws Exception {
		// logger.info("Iniciando newMyDynamicReport -- Mi ejemplo");
		MyReporteSimple reporte = new MyReporteSimple();
		reporte.generarReporte();
		JasperExportManager.exportReportToPdfFile(reporte.getJasperPrint(), "MyReporteDynamic.pdf");
		JasperDesignViewer.viewReportDesign(reporte.jasperReport);
	}

	static void newMyDynamicReportMod() throws Exception {
		// logger.debug("Inicia modificacion");
		MyDynamicReportMod reporte = new MyDynamicReportMod();
		reporte.generarReporte();
		JasperExportManager.exportReportToPdfFile(reporte.getJasperPrint(), "MyReporteDynamicModificado.pdf");
	}

	static void newMyReportCyEPruebaUno() throws Exception {
		// logger.debug("Inicia modificacion");
		MyReportCyEPruebaUno reporte = new MyReportCyEPruebaUno();
		reporte.generarReporte();
		JasperExportManager.exportReportToPdfFile(reporte.getJasperPrint(), "MyReporteCajaYEtiqueta.pdf");
	}

	static void viewReportDesign() throws Exception {
		JasperReport jr = JasperCompileManager.compileReport(
				"C:/Workspace/ZenilProjects/CursoJasper/jasper-curso/src/main/resources/jasper_report_template.jrxml");
		JasperDesignViewer.viewReportDesign(jr);
	}

	static void compilarUnJRXML() {
		String sourceFileName = "C:/Workspace/ZenilProjects/CursoJasper/jasper-curso/src/main/resources/jasper_report_template.jrxml";

		try {
			JasperCompileManager.compileReport(sourceFileName);
		} catch (JRException e) {
			e.printStackTrace();
		}
		System.out.println("Termina Compilacion!.");
	}

}
