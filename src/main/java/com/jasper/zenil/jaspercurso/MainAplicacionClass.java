package com.jasper.zenil.jaspercurso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.jasper.zenil.jaspercurso.controller.DataBeanList;
import com.jasper.zenil.jaspercurso.model.DataBean;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

@SpringBootApplication
public class MainAplicacionClass {

	public static final Logger logger = Logger.getLogger(MainAplicacionClass.class);

	public static void main(String[] args) throws Exception {

		System.out.print(Constants.MENU);

		Scanner entradaEscaner = new Scanner(System.in);
		String entradaTeclado = entradaEscaner.nextLine();

		switch (entradaTeclado.toUpperCase()) {
		case "A":
			viewReportDesign();
			break;
		case "B":
			compilarUnJRXML(Constants.SOURCE_FILE_NAME_JRXML);
			break;
		case "C":
			fillings();
			break;
		case "D":
			viewing();
			break;
		case "E":
			printing();
			break;
		case "F":
			exporting();
			break;
		case "G":
			params();
			break;
		case "H":
			sorting();
			break;
		case "I":
			expressions();
			break;
		case "J":
			variables();
			break;
		case "K":
			sections();
			break;
		case "L":
			groups();
			break;
		default:
			break;
		}
		entradaEscaner.close();

	}

	/**
	 * Visualiza el diseño de un .jrxml
	 * 
	 * @throws Exception
	 */
	static void viewReportDesign() throws Exception {
		JasperDesignViewer.viewReportDesign(Constants.SOURCE_FILE_NAME_JRXML, true);
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

		String source_file_name_jasper = compilarUnJRXML(Constants.SOURCE_FILE_NAME_JRXML);

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<com.jasper.zenil.jaspercurso.model.DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> params = new HashMap<String, Object>();

		try {
			JasperFillManager.fillReportToFile(source_file_name_jasper, params, beanColDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Visualizar un .jrprint
	 */
	static void viewing() {

		String source_file_name_jasper = compilarUnJRXML(Constants.SOURCE_FILE_NAME_JRXML);

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

	/**
	 * Ejecuta la funcion de imprimir a partir de un .jrprint
	 */
	static void printing() {

		String source_file_name_jasper = compilarUnJRXML(Constants.SOURCE_FILE_NAME_JRXML);

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

	/**
	 * Exporta a partir de un .jrprint a: PDF, HTML y XLS
	 */
	static void exporting() {

		String source_file_name_jasper = compilarUnJRXML(Constants.SOURCE_FILE_NAME_JRXML);

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
				JasperExportManager.exportReportToPdfFile(printFileName, Constants.EXPORT_PATH + "ExportingPDF.pdf");

				/**
				 * 2.- Export to HTML file
				 */
				JasperExportManager.exportReportToHtmlFile(printFileName, Constants.EXPORT_PATH + "ExportingHTML.html");

				/**
				 * 3.- Export to Excel sheet
				 */
				generaXLSX(printFileName, Constants.EXPORT_PATH + "ExportingExcel.xlsx");
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void generaXLSX(String printFileName, String destFileName) throws JRException {

		JRXlsxExporter xlsExporter = new JRXlsxExporter();
		xlsExporter.setExporterInput(new SimpleExporterInput(printFileName));
		xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(destFileName)));

		SimpleXlsxReportConfiguration xlsxConfig = new SimpleXlsxReportConfiguration();
		xlsxConfig.setDetectCellType(true);
		xlsxConfig.setCollapseRowSpan(false);

		xlsExporter.setConfiguration(xlsxConfig);
		xlsExporter.exportReport();
	}

	/**
	 * Ejemplo que demuestra como pasar parametros para llenear un Reporte
	 */
	static void params() {

		Map<String, Object> params = new HashMap<String, Object>();

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		params.put("ReportTitle", "Lista de contactos");
		params.put("Author", "Creado por Zennil");

		try {
			JasperReport jasperReport = JasperCompileManager
					.compileReport(Constants.SOURCE_FILE_NAME_JRXML_WITH_PARAMS);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ejemplo de implementacion del <sortField> en un template
	 */
	static void sorting() {

		Map<String, Object> params = new HashMap<String, Object>();

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		params.put("TituloReporte", "Este es un reporte ordenado con sort");
		params.put("Autor", "By: Zennil");

		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(Constants.SOURCE_FILE_NAME_JRXML_WITH_SORT);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ejemplo del uso de expression en el template
	 */
	static void expressions() {

		Map<String, Object> params = new HashMap<String, Object>();

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		dataList.add(dataBeanList.createDataBean("Barry", ""));

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		params.put("ReportTitle", "Expresiones");
		params.put("Author", "Creado por Zennil");

		try {
			JasperReport jasperReport = JasperCompileManager
					.compileReport(Constants.SOURCE_FILE_NAME_JRXML_FOR_EXPRESSIONS);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void variables() {

		Map<String, Object> params = new HashMap<String, Object>();

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		params.put("ReportTitle", "Variables");
		params.put("Author", "Created by: Zennil");

		try {
			JasperReport jasperReport = JasperCompileManager
					.compileReport(Constants.SOURCE_FILE_NAME_JRXML_FOR_VARIABLES);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void sections() {
		try {
			JasperReport jasperReport = JasperCompileManager
					.compileReport(Constants.SOURCE_FILE_NAME_JRXML_FOR_SECTIONS);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	static void groups() {

		Map<String, Object> params = new HashMap<String, Object>();

		DataBeanList dataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = dataBeanList.getDataBeanList();
		dataList.add(dataBeanList.createDataBean("Bukousky", "USA"));
		dataList.add(dataBeanList.createDataBean("Cloud", "London"));
		dataList.add(dataBeanList.createDataBean("Tifa", "London"));
		dataList.add(dataBeanList.createDataBean("Barret", "Tokyo"));
		dataList.add(dataBeanList.createDataBean("Watson", "London"));
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

		params.put("ReportTitle", "Groups");
		params.put("Author", "By Zennil");

		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(Constants.SOURCE_FILE_NAME_JRXML_FOR_GROUPS);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			JasperViewer viewer = new JasperViewer(jasperPrint);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
