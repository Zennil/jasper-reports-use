package com.jasper.zenil.jaspercurso;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jasper.zenil.jaspercurso.bean.DataBean;
import com.jasper.zenil.jaspercurso.DataBeanList;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

@SpringBootApplication
public class MainAplicacionClass {

	private static final String SOURCE_FILE_NAME_JRXML = "C:/Workspace/ZenilProjects/CursoJasper/JasperCourse/jasper-reports-use/src/main/resources/jasper-resources/jasper_report_template.jrxml";
	private static final String SOURCE_FILE_NAME_JASPER = "C:/Workspace/ZenilProjects/CursoJasper/JasperCourse/jasper-reports-use/src/main/resources/jasper-resources/jasper_report_template.jasper";

	private static final String SOURCE_FILE_NAME_JRXML_WITH_PARAMS = "C:/Workspace/ZenilProjects/CursoJasper/JasperCourse/jasper-reports-use/src/main/resources/jasper-resources/jasper_report_template_with_params.jrxml";
	private static final String SOURCE_FILE_NAME_JRXML_WITH_SORT = "C:/Workspace/ZenilProjects/CursoJasper/JasperCourse/jasper-reports-use/src/main/resources/jasper-resources/jasper_report_template_with_sort.jrxml";

	public static final Logger logger = Logger.getLogger(MainAplicacionClass.class);
	URL urlRepTempl = getClass().getClassLoader().getResource("jasper_report_template.jrxml");

	public static void main(String[] args) throws Exception {

		// viewReportDesign();

		// compilarUnJRXML();

		// fillings();

		// viewing();

		// exporting();

		// params();

		sorting();

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
		JasperReport jr = JasperCompileManager.compileReport(SOURCE_FILE_NAME_JRXML);
		JasperDesignViewer.viewReportDesign(jr);
	}

	/**
	 * Genera un .jasper a partir de un .jrxml
	 */
	static void compilarUnJRXML() {
		try {
			JasperCompileManager.compileReportToFile(SOURCE_FILE_NAME_JRXML);
		} catch (JRException e) {
			e.printStackTrace();
		}
		System.out.println("Termina Compilacion!.");
	}

	/**
	 * Se debe ejecutar antes el metodod compilarUnJRXML() 
	 * Genera un .jrprint a partir de un .jasper, params y un dataSource;
	 */
	static void fillings() {

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			JasperFillManager.fillReportToFile(SOURCE_FILE_NAME_JASPER, params, beanColDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Se debe ejecutar antes el metodo compilarUnJRXML()
	 * Visualiza un .jrprint
	 */
	static void viewing() {
		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
		Map<String, Object> params = new HashMap<String, Object>();

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(SOURCE_FILE_NAME_JASPER, params, beanColDataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void printing() {

		String printFileName = null;

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			printFileName = JasperFillManager.fillReportToFile(SOURCE_FILE_NAME_JASPER, params, dataSource);
			if (printFileName != null) {
				JasperPrintManager.printReport(printFileName, true);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void exporting() {
		String printFileName = null;

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			printFileName = JasperFillManager.fillReportToFile(SOURCE_FILE_NAME_JASPER, params, dataSource);
			if (printFileName != null) {
				/**
				 * 1.- Export to PDF file
				 */
				JasperExportManager.exportReportToPdfFile(printFileName, "Exporting.pdf");

				/**
				 * 2.- Export to HTML file
				 */
				JasperExportManager.exportReportToHtmlFile(printFileName, "Exporting.html");

				/**
				 * 3.- Export to Excel sheet
				 */
				JasperExportManager.exportReportToXmlFile(printFileName, "Exporting.xls", true);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	static void params() {

		Map<String, Object> params = new HashMap<String, Object>();
		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		params.put("ReportTitle", "Lista de contactos");
		params.put("Author", "Creado por Zenil");

		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(SOURCE_FILE_NAME_JRXML_WITH_PARAMS);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	static void sorting() {

		Map<String, Object> params = new HashMap<String, Object>();

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		params.put("TituloReporte", "Este es un reporte ordenado con sort");
		params.put("Autor", "Armando Alonso Zenil");

		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(SOURCE_FILE_NAME_JRXML_WITH_SORT);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}
