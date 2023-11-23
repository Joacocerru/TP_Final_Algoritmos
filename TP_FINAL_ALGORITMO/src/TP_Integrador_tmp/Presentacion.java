package TP_Integrador_tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presentacion {
    public static void main(String[] args) {
    
        
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ************************************* CONSTRUCTORES *************************************************************************************

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // USER STORY 1: CARGAR DATOS DESDE ARCHIVO .CSV   ////////////////////////////
     
    DataFrame dfx = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "N");
    DataFrame df =  new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba8.csv", ",", "S");
    DataFrame df1 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba8.csv", ",", "S");
    //DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    

    ////  USER STORY 2: COPIA PROFUNDA DE LOS ELEMENTOS DE LA ESTRUCTURA TABULAR ////////////////////////////

    DataFrame copiadf = null;
    copiadf = df.clone();
    CsvPrinter.imprimirColumnar(copiadf);
    
    ////  USER STORY 3: CONCATENACIÓN DE DOS ESTRUCTURAS EXISTENTES   ////////////////////////////

    DataFrame resultado = df.concatenar(df); 

    CsvPrinter.imprimirColumnar(resultado);
    CsvPrinter.imprimirPorFilas(resultado);
      
    ////  USER STORY 4: CANTIDAD DE FILAS Y COLUMNAS ////////////////////////////
    
    // Info general del dataframe
    CsvPrinter.info(df);                               
    
    ////  USER STORY 5: OBTENER ETIQUETAS DE FILAS Y COLUMNAS ////////////////////////////
    // Etiquetas de las columnas
    df.imprimirEtiquetasColumnas();
    
    // Etiquetas de las filas
    df.imprimirEtiquetasFilas(); 

    ////  USER STORY 6: TIPOS DE DATOS DE COLUMNAS ////////////////////////////
    // implementado más arriba
    
    ////  USER STORY 7: ACCEDER A LOS DATOS A TRAVES DE FILA Y COLUMNA ////////////////////////////
    
    CsvPrinter.infoColumna(df, "Columna3"); 
    CsvPrinter.infoFila(df, "2"); 
 
    ////  USER STORY 8: GENERAR UNA VISTA REDUCIDA (SLICING)  ////////////////////////////
    
    // impresión por pantalla de una vista reducida

    List<String> etiquetasFilas5 = Arrays.asList("1", "2", "3");
    List<String> etiquetasColumnas5 = Arrays.asList("Columna2","Columna3");
    CsvPrinter.imprimirVistaReducida(df, etiquetasFilas5, etiquetasColumnas5);

    //-------------------------------------------
    
    // vista reducida del DataFrame original
 
    List<String> etiquetasColumnas = Arrays.asList("Columna2", "Columna3");
    List<String> etiquetasFilas = Arrays.asList("1", "3", "5"); 
    DataFrame.vistaReducida(df, etiquetasColumnas, etiquetasFilas); // con header
    
    // vistas especiales------------------------

    CsvPrinter.head(df, 2);
    CsvPrinter.tail(df, 2);
    
    // Random Sciling ---------------------------

    DataFrame.muestreoAleatorio(df, 0.8);  

    ////  USER STORY 9: FILTRO APLICADO A LOS VALORES DE LAS CELDAS (QUERY)  ////////////////////////////
 
    DataFrame filtro = df1.FiltroPorColumna("Columna2", 0, 3);  // -1 Menor que , 0 igual que, 1 Mayor que  // con header
    //DataFrame filtro = df1.FiltroPorColumna("1", 0, 3);  // -1 Menor que , 0 igual que, 1 Mayor que  // sin header
    
    //DataFrame filtro = df1.FiltroPorColumna("Columna2", -1, 3); // con header
    //DataFrame filtro = df1.FiltroPorColumna("1", -1, 3); // sin header
    
    //DataFrame filtro = df1.FiltroPorColumna("Columna2", 1, 3); // con header
    //DataFrame filtro = df1.FiltroPorColumna("1", 1, 3); // sin header

    //DataFrame filtro = df1.FiltroPorColumna("Columna1", 0, "True"); // con header
    //DataFrame filtro = df1.FiltroPorColumna("0", 0, "True"); // sin header

    //DataFrame filtro = df1.FiltroPorColumna("Columna3", -1, "Prueba4"); // con header
    //DataFrame filtro = df1.FiltroPorColumna("2", -1, "Prueba4"); // sin header
    
    CsvPrinter.imprimirColumnar(filtro);

    ////  USER STORY 10: EXPORTAR TABLA COMO CSV ////////////////////////////
    
    CsvExport.exportarComoCSV(df, "C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\archivoExportado.csv");

    ////  USER STORY 11: IMPRIMIR LOS DATOS EN FORMA DE TABLA  ////////////////////////////
    
    CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 12: ORDENAR DATOS SEGÚN UNA COLUMNA  ////////////////////////////

    String[] indices = {"Columna2","Columna3","Columna4"}; // con header
    //String[] indices = {"2","1","3"}; // sin header

    df.orderPorColumnas(indices); 

    CsvPrinter.imprimirColumnar(df);

    
    ////  USER STORY 13: BUSCAR Y FILTRAR DATOS EN LA TABLA  ////////////////////////////
    
    //    Object valorABuscar = 5;
    Object valorABuscar = true;
    //    Object valorABuscar = "Prueba2";
    //    Object valorABuscar = 88;         
    df.valorBuscado(valorABuscar);

    ////  USER STORY 14: ACCEDER A CELDA Y SETEAR NUEVO VALOR ////////////////////////////
    
    df.setValorDataFrame("3", "Columna2", 69); // con header
    //df.setValorDataFrame("3", "2", 69); // sin header

    ////  USER STORY 15: INSERTAR UNA NUEVA COLUMNA A PARTIR DE UNA COLUMNA EXISTENTE ////////////////////////////
    
    df.clonarYAgregarColumna("Columna2", "ColumnaNueva"); // con header
    //df.clonarYAgregarColumna("1", "ColumnaNueva"); // sin header

    ////  USER STORY 16: CELDAS CON DATOS FALTANTES SEAN IDENTIFICADAS CON NA  ////////////////////////////

    DataFrame df4 = df.clone();
    CsvPrinter.info(df4);
    CsvPrinter.imprimirColumnar(df4);
    
    // Valores -> -9 numericos, nullStr para Strings, false para los boolean
    df4.sacarNAs(-9, "nullStr", false);
    CsvPrinter.info(df4);
    CsvPrinter.imprimirColumnar(df4);

    ////  USER STORY 17: REALIZAR OPERACIONES SIN ERRORES EN CASO DE TENER NA  ////////////////////////////

    // xxxxxxx

    //// USER STORY 18: INSERTAR UNA NUEVA COLUMNA UTILIZANDO SECUENCIA LINEAL NATIVA  ////////////////////////////
    
    String[] datosNuevaColumna = {  "Nueva1", "Nueva2","Nueva3", "Nueva4", "Nueva5", "Nueva6",
                                    "Nueva7", "Nueva8","Nueva9", "Nueva10", "Nueva11", "Nueva12",
                                    "Nueva13", "Nueva14","Nueva15", "Nueva16", "Nueva17", "Nueva18",
                                    "Nueva19", "Nueva20","Nueva21", "Nueva22", "Nueva23", "Nueva24"};
    
    //String[] datosNuevaColumna = {  "Nueva1", "Nueva2","Nueva3", "Nueva4", "Nueva5", "Nueva6"};
    
    df.agregarColumnaSecuencia(df, datosNuevaColumna,"NuevaColumna", "S"); // "S" = String // con header
    //df.agregarColumnaSecuencia(df, datosNuevaColumna,"NuevaColumna", "S"); // "S" = String // sin header
    
    //// USER STORY 19: ELIMINAR COLUMNA Y/O FILA  ////////////////////////////  

    System.out.println(" ");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   ELIMINA COLUMNA ");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");
    
    //String etiquetaColumna = "Columna2"; // con header
    String etiquetaColumna = "Columna2"; // sin header
    
    df.eliminarColumna(etiquetaColumna);

    System.out.println("Eliminamos la columna '" + etiquetaColumna + "' del dataframe.");

    CsvPrinter.imprimirColumnar(df);

    System.out.println(" ");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   ELIMINA FILA ");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    String etiquetaFila = "2";
    
    df.eliminarFila(etiquetaFila);

    System.out.println("Eliminamos la fila '" + etiquetaFila + "' del dataframe.");

    CsvPrinter.imprimirColumnar(df);
    
    //// USER STORY 20: AGRUPAMIENTO O GROUP BY - METODO A IMPLEMENTAR   //////////////////////////// 

    ////  USER STORY 21: MANEJAR EXEPCIONES  - METODO A IMPLEMENTAR ////////////////////////////
}
//-----------------------------------------------------------------------------------------------------------
}