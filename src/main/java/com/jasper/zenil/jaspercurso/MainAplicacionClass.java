package com.jasper.zenil.jaspercurso;

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

	private static final String SOURCE_FILE_NAME_JRXML = "C:/Workspace/ZenilProjects/CursoJasper/JasperCourse/jasper-reports-use/src/main/resources/jasper-resources/templates/template.jrxml";
	private static final String SOURCE_FILE_NAME_JRXML_WITH_PARAMS = "C:/Workspace/ZenilProjects/CursoJasper/JasperCourse/jasper-reports-use/src/main/resources/jasper-resources/templates/template_with_params.jrxml";
	private static final String SOURCE_FILE_NAME_JRXML_WITH_SORT = "C:/Workspace/ZenilProjects/CursoJasper/JasperCourse/jasper-reports-use/src/main/resources/jasper-resources/templates/template_with_sort.jrxml";

	public static final Logger logger = Logger.getLogger(MainAplicacionClass.class);

	public static void main(String[] args) throws Exception {

		// viewReportDesign();

		// compilarUnJRXML(SOURCE_FILE_NAME_JRXML);

		// fillings();

		// viewing();

		// printing();

		exporting();

		// params();

		// sorting();

	}

	/**
	 * Visualiza el diseño de un .jrxml
	 * 
	 * @throws Exception
	 */
	static void viewReportDesign() throws Exception {
		JasperDesignViewer.viewReportDesign(SOURCE_FILE_NAME_JRXML, true);
	}

	/**
	 * Genera un .jasper a partir de un .jrxml
	 * 
	 * @param sourceFileName
	 * @return
	 */
	static String compilarUnJRXML(String sourceFileName) {
		try {
			return JasperCompileManager.compileReportToFile(sourceFileName);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Genera un .jrprint a partir de un .jasper, params y un dataSource;
	 */
	static void fillings() {

		String source_file_name_jasper = compilarUnJRXML(SOURCE_FILE_NAME_JRXML);

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			JasperFillManager.fillReportToFile(source_file_name_jasper, params, beanColDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Visualiza un .jrprint
	 */
	static void viewing() {

		String source_file_name_jasper = compilarUnJRXML(SOURCE_FILE_NAME_JRXML);

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
		Map<String, Object> params = new HashMap<String, Object>();

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(source_file_name_jasper, params, beanColDataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void printing() {

		String source_file_name_jasper = compilarUnJRXML(SOURCE_FILE_NAME_JRXML);

		String printFileName = null;

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			printFileName = JasperFillManager.fillReportToFile(source_file_name_jasper, params, dataSource);
			if (printFileName != null) {
				JasperPrintManager.printReport(printFileName, true);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void exporting() {

		String source_file_name_jasper = compilarUnJRXML(SOURCE_FILE_NAME_JRXML);

		String printFileName = null;

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			printFileName = JasperFillManager.fillReportToFile(source_file_name_jasper, params, dataSource);
			if (printFileName != null) {
				/**
				 * 1.- Export to PDF file
				 */
				JasperExportManager.exportReportToPdfFile(printFileName, "src/main/resources/jasper-resources/exports/ExportingPDF.pdf");

				/**
				 * 2.- Export to HTML file
				 */
				JasperExportManager.exportReportToHtmlFile(printFileName, "src/main/resources/jasper-resources/exports/ExportingHTML.html");

				/**
				 * 3.- Export to Excel sheet
				 */
				JasperExportManager.exportReportToXmlFile(printFileName, "src/main/resources/jasper-resources/exports/ExportingExcel.xls", true);
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
