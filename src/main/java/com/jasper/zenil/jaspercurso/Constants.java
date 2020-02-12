package com.jasper.zenil.jaspercurso;

public class Constants {

    private static final String SRC_PATH = "src/main/resources/jasper-resources/templates/";

    public static final String EXPORT_PATH = "src/main/resources/jasper-resources/exports/";

    public static final String SOURCE_FILE_NAME_JRXML = SRC_PATH + "template.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_WITH_PARAMS = SRC_PATH + "template_with_params.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_WITH_SORT = SRC_PATH + "template_with_sort.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_EXPRESSIONS = SRC_PATH + "template_for_expressions.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_VARIABLES = SRC_PATH + "template_for_variables.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_SECTIONS = SRC_PATH + "template_for_sections.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_GROUPS = SRC_PATH + "template_for_groups.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_FONTS = SRC_PATH + "template_for_fonts.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_UNICODE = SRC_PATH + "template_for_unicode.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_STYLES = SRC_PATH + "template_for_styles.jrxml";
    public static final String SOURCE_FILE_NAME_JRXML_FOR_SCRIPTLES = SRC_PATH + "template_for_scriptlets.jrxml";

    public static final String MENU = "\n\n Elije una opcion: " + "\n\n (A): Ver reporte basado en un diseño (.jrxml)"
            + "\n (B): Generar un .jasper basado en un .jrxml"
            + "\n (C): Genera un .jrprint a partir de un .jasper, params y un dataSource"
            + "\n (D): Visualizar un .jrprint" + "\n (E): Ejecuta la funcion de imprimir a partir de un .jrprint"
            + "\n (F): Exporta a partir de un .jrprint a: PDF, HTML y XLS"
            + "\n (G): Ejemplo que demuestra como pasar parametros para llenear un Reporte"
            + "\n (H): Ejemplo de implementacion del <sortField> en un template" + "\n (I): Uso de expresiones"
            + "\n (J): Uso de Variables" + "\n (K): Ejemplo de secciones" + "\n (L): Ejemplo del uso de Group"
            + "\n (M): Ejemplo de uso de fuentes"
            + "\n (N): Ejemplo del uso de Unicode"
            + "\n (O): Ejemplo del uso de styles"
            + "\n (P): Ejemplo del uso de Scriptlet\n\n";
}