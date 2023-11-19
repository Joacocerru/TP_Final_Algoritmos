package TP_Integrador_tmp;

//import java.util.Arrays;
//import java.util.List;

public class Presentacion {
    public static void main(String[] args) {
    

    // USER STORY 1: CARGAR DATOS DESDE ARCHIVO .CSV   ////////////////////////////
    DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame iris = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\iris.csv", ",", "S");

    CsvPrinter.imprimirColumnar(df);


    ////  USER STORY 2: COPIA PROFUNDA DE LOS ELEMENTOS DE LA ESTRUCTURA TABULAR ////////////////////////////

    ////  USER STORY 3: CONCATENACIÓN DE DOS ESTRUCTURAS EXISTENTES  - CODIGO A REVISAR ////////////////////////////

    ////  USER STORY 4: CANTIDAD DE FILAS Y COLUMNAS ////////////////////////////
    
    // Info general del dataframe
    CsvPrinter.info(df);                   
    //CsvPrinter.imprimirColumnar(df);

    // Info de columna elegida
    CsvPrinter.infoColumna(df, "Columna3"); // busca por etiqueta            
    //CsvPrinter.imprimirColumnar(df);

    // Info de fila elegida
    CsvPrinter.infoFila(df, "2"); // busca por etiqueta            
    //CsvPrinter.imprimirColumnar(df);
    

    ////  USER STORY 5: OBTENER ETIQUETAS DE FILAS Y COLUMNAS ////////////////////////////

    // Etiquetas de las columnas
    df.imprimirEtiquetasColumnas();
    
    // Etiquetas de las filas
    df.imprimirEtiquetasFilas(); 



    ////  USER STORY 6: TIPOS DE DATOS DE COLUMNAS ////////////////////////////
    // esta más arriba
    // si se da un parámetro busca el tipo de dato de esa columna, si no imprime los tipos de
    
    ////  USER STORY 7: ACCEDER A LOS DATOS A TRAVES DE FILA Y COLUMNA ////////////////////////////
    CsvPrinter.infoColumna(df, "Columna3"); 
    CsvPrinter.infoFila(df, "2"); 

    ////  USER STORY 8: GENERAR UNA VISTA REDUCIDA (SLICING)  ////////////////////////////
    // Slicing Random

    ////  USER STORY 9: FILTRO APLICADO A LOS VALORES DE LAS CELDAS (QUERY)  ////////////////////////////
    
    ////  USER STORY 10: EXPORTAR TABLA COMO CSV ////////////////////////////
    CsvExport.exportarComoCSV(df, "C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\archivoExportado.csv");

    ////  USER STORY 11: IMPRIMIR LOS DATOS EN FORMA DE TABLA  ////////////////////////////
    CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 12: ORDENAR DATOS SEGÚN UNA COLUMNA  ////////////////////////////

    ////  USER STORY 13: BUSCAR Y FILTRAR DATOS EN LA TABLA  ////////////////////////////

    ////  USER STORY 14: ACCEDER A CELDA Y SETEAR NUEVO VALOR ////////////////////////////
    df.setValorDataFrame("3", "Columna2", 69);
    //CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 15: INSERTAR UNA NUEVA COLUMNA A PARTIR DE UNA COLUMNA EXISTENTE ////////////////////////////
    df.clonarYAgregarColumna("Columna2", "ColumnaNueva");
    //CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 16: CELDAS CON DATOS FALTANTES SEAN IDENTIFICADAS CON NA  ////////////////////////////

    //// USER STORY 17: REALIZAR OPERACIONES SIN ERRORES EN CASO DE TENER NA  ////////////////////////////

    //// USER STORY 18: INSERTAR UNA NUEVA COLUMNA UTILIZANDO SECUENCIA LINEAL NATIVA  ////////////////////////////
    //String[] datosNuevaColumna = {"Nueva1", "Nueva2","Nueva3", "Nueva4", "Nueva5", "Nueva6"};
    //df.agregarColumnaSecuencia(datosNuevaColumna,"Columna5", "S"); // "S" = String 


    //// USER STORY 19: ELIMINAR COLUMNA Y/O FILA  ////////////////////////////  
    // que pasa si quiero eliminar un rango? 
    df.eliminarColumna("Columna2");
    df.eliminarFila("2");
    //CsvPrinter.imprimirColumnar(df);



    //// USER STORY 20: AGRUPAMIENTO O GROUP BY - METODO A IMPLEMENTAR   //////////////////////////// 
    ////  USER STORY 21: MANEJAR EXEPCIONES  - METODO A IMPLEMENTAR ////////////////////////////




}
}