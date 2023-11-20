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
     
    //DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    
    CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 2: COPIA PROFUNDA DE LOS ELEMENTOS DE LA ESTRUCTURA TABULAR ////////////////////////////

    DataFrame copiadf = null;
    copiadf = df.clone();
    CsvPrinter.imprimirColumnar(copiadf);
    
    ////  USER STORY 3: CONCATENACIÓN DE DOS ESTRUCTURAS EXISTENTES   ////////////////////////////

    DataFrame resultado = df.concatenar(df); 

    CsvPrinter.imprimirColumnar(resultado);
    CsvPrinter.imprimirPorFilas(resultado);
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ******************************* CONSULTAS DE ESTADOS DE LA TABLA ***********************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ******************************** METODOS DE ACCESO ****************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////  USER STORY 8: GENERAR UNA VISTA REDUCIDA (SLICING)  ////////////////////////////
    
    // impresión por pantalla de una vista reducida

    List<String> etiquetasFilas5 = Arrays.asList("1", "2", "3");
    List<String> etiquetasColumnas5 = Arrays.asList("Columna2","Columna3");
    CsvPrinter.imprimirVistaReducida(df, etiquetasFilas5, etiquetasColumnas5);

    //-------------------------------------------
    
    // vista reducida del DataFrame original
    
    DataFrame.vistaReducida(df, new ArrayList<>(Arrays.asList("Columna1", "Columna4")), new ArrayList<>((Arrays.asList("1", "3", "5")))); 
    
    // vistas especiales------------------------

    CsvPrinter.head(df, 2);
    CsvPrinter.tail(df, 2);
    
    // Random Sciling ---------------------------

    DataFrame.muestreoAleatorio(df, 0.8);  

    ////  USER STORY 9: FILTRO APLICADO A LOS VALORES DE LAS CELDAS (QUERY)  ////////////////////////////
 
    DataFrame filtro = df.FiltroPorColumna("Columna2", 0, 5);  // -1 Menor que , 0 igual que, 1 Mayor que  // ####
    DataFrame filtro2 = df.FiltroPorColumna("Columna2", -1, 3);
    //DataFrame filtro = df.FiltroPorColumna("ColumnaNum", 1, 3);
    //DataFrame filtro = df.FiltroPorColumna("ColumnaBoolean", 0, "True");
    //DataFrame filtro = df.FiltroPorColumna("ColumnaPruebas", -1, "Prueba4");
    
    CsvPrinter.imprimirColumnar(filtro);
    CsvPrinter.imprimirPorFilas(filtro);
    
    CsvPrinter.imprimirColumnar(filtro2);
    ////  USER STORY 10: EXPORTAR TABLA COMO CSV ////////////////////////////
    
    CsvExport.exportarComoCSV(df, "C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\archivoExportado.csv");


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ********************************************************  METODOS DE VISUALIZACION  ******************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////  USER STORY 11: IMPRIMIR LOS DATOS EN FORMA DE TABLA  ////////////////////////////
    
    CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 12: ORDENAR DATOS SEGÚN UNA COLUMNA  ////////////////////////////

    String[] indices = {"Columna1","Columna2","Columna3"};
    df.orderPorColumnas(indices); // ####
    //CsvPrinter.imprimirColumnar(df);
    CsvPrinter.imprimirPorFilas(df);

    ////  USER STORY 13: BUSCAR Y FILTRAR DATOS EN LA TABLA  ////////////////////////////
    
    Dato_Numerico valorABuscarNumerico = new Dato_Numerico(5);  // ####
    String resultadoBusquedaNumerica = df.buscarValor(valorABuscarNumerico);
    System.out.println(resultadoBusquedaNumerica);

    Dato_Boolean valorABuscarBoolean = new Dato_Boolean(true);
    String resultadoBusquedaBoolean = df.buscarValor(valorABuscarBoolean);
    System.out.println(resultadoBusquedaBoolean);

    Dato_String valorABuscarString = new Dato_String("Prueba2"); 
    String resultadoBusquedaString = df.buscarValor(valorABuscarString);
    System.out.println(resultadoBusquedaString);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // **********************************************************  GESTION DE DATOS  **********************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////  USER STORY 14: ACCEDER A CELDA Y SETEAR NUEVO VALOR ////////////////////////////
    
    df.setValorDataFrame("3", "Columna2", 69);
    CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 15: INSERTAR UNA NUEVA COLUMNA A PARTIR DE UNA COLUMNA EXISTENTE ////////////////////////////
    
    df.clonarYAgregarColumna("Columna2", "ColumnaNueva");
    CsvPrinter.imprimirColumnar(df);

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
 
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // *******************************************  GESTION DEL DATASET  *******************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //// USER STORY 18: INSERTAR UNA NUEVA COLUMNA UTILIZANDO SECUENCIA LINEAL NATIVA  ////////////////////////////
    
    String[] datosNuevaColumna = {"Nueva1", "Nueva2","Nueva3", "Nueva4", "Nueva5", "Nueva6"};
    df.agregarColumnaSecuencia(df, datosNuevaColumna,"Columna5", "S"); // "S" = String 
    CsvPrinter.imprimirColumnar(df);

    //// USER STORY 19: ELIMINAR COLUMNA Y/O FILA  ////////////////////////////  
    // que pasa si quiero eliminar un rango?

    df.eliminarColumna("Columna2");
    CsvPrinter.imprimirColumnar(df);
    df.eliminarFila("2");
    CsvPrinter.imprimirColumnar(df);

    //// USER STORY 20: AGRUPAMIENTO O GROUP BY - METODO A IMPLEMENTAR   //////////////////////////// 

    ////  USER STORY 21: MANEJAR EXEPCIONES  - METODO A IMPLEMENTAR ////////////////////////////


}
}