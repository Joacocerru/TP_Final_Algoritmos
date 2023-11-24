package TP_Integrador_tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presentacion {
    public static void main(String[] args) {
    
   
    // USER STORY 1: CARGAR DATOS DESDE ARCHIVO .CSV   /////////////////////////////////////////////////////////////////////////////////////
    
    //DataFrame df =  new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_FINAL_ALGORITMO\\TP_FINAL_ALGORITMO\\jugadores.csv", ",", "S");
    //DataFrame df1 =  new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_FINAL_ALGORITMO\\TP_FINAL_ALGORITMO\\jugadores2.csv", ",", "S");
    DataFrame df =  new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\jugadores.csv", ",", "S");
    DataFrame df1 =  new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\jugadores2.csv", ",", "S");
    //DataFrame df2 =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\jugadores2.csv", ",", "S");
    
    ////  USER STORY 11: IMPRIMIR LOS DATOS EN FORMA DE TABLA  /////////////////////////////////////////////////////////////////////////////////////
    
    CsvPrinter.imprimirColumnar(df);

    CsvPrinter.imprimirColumnar(df1);

    ////  USER STORY 4: CANTIDAD DE FILAS Y COLUMNAS /////////////////////////////////////////////////////////////////////////////////////
    // Info general del dataframe

    CsvPrinter.info(df);   
    
    CsvPrinter.info(df1); 
    
    ////  USER STORY 5: OBTENER ETIQUETAS DE FILAS Y COLUMNAS /////////////////////////////////////////////////////////////////////////////////////
    
    // Etiquetas de las columnas
    df.imprimirEtiquetasColumnas();
    
    // Etiquetas de las filas
    df.imprimirEtiquetasFilas(); 

    ////  USER STORY 7: ACCEDER A LOS DATOS A TRAVES DE FILA Y COLUMNA /////////////////////////////////////////////////////////////////////////////////////
    
    CsvPrinter.infoColumna(df1, "Nacionalidad"); // con header
    //CsvPrinter.infoColumna(df, "3"); // sin header

    //CsvPrinter.infoFila(df, "Columna2"); // con header
    CsvPrinter.infoFila(df1, "2"); // sin header

    ////  USER STORY 8: GENERAR UNA VISTA REDUCIDA (SLICING)  /////////////////////////////////////////////////////////////////////////////////////
    
    // impresión por pantalla de una vista reducida

    List<String> etiquetasFilasVista = Arrays.asList("1", "2", "3");
    List<String> etiquetasColumnasVista = Arrays.asList("Jugador","Goles"); // con header
    //List<String> etiquetasColumnas5 = Arrays.asList("2","3"); // sin header

    CsvPrinter.imprimirVistaReducida(df, etiquetasFilasVista, etiquetasColumnasVista);

    //-------------------------------------------
    
    // vista reducida del DataFrame original

    List<String> etiquetasColumnas = Arrays.asList("Jugador", "Nacionalidad");

    List<String> etiquetasFilas = Arrays.asList("1", "3", "5"); 
    
    DataFrame.vistaReducida(df, etiquetasColumnas, etiquetasFilas); // con header
    //DataFrame.vistaReducida(df, new ArrayList<>(Arrays.asList("1", "3")), new ArrayList<>((Arrays.asList("1", "3", "5")))); // sin header
    
    // vistas especiales------------------------

    CsvPrinter.head(df, 2);

    CsvPrinter.tail(df, 2);
    
    // Random Sciling ---------------------------

    DataFrame.muestreoAleatorio(df, 0.5);  

    ////--------------------------------------------------------------------------------------------------
    ////  USER STORY 9: FILTRO APLICADO A LOS VALORES DE LAS CELDAS (QUERY)  /////////////////////////////////////////////////////////////////////////////////////
 
    DataFrame filtro = df.FiltroPorColumna("Goles", 0, 23);  // -1 Menor que , 0 igual que, 1 Mayor que  // con header   ####
    CsvPrinter.imprimirColumnar(filtro);
    
    DataFrame filtro1 = df1.FiltroPorColumna("Asistencias", -1, 15);  // -1 Menor que , 0 igual que, 1 Mayor que  // sin header
    CsvPrinter.imprimirColumnar(filtro1);

    ////  USER STORY 13: BUSCAR Y FILTRAR DATOS EN LA TABLA  /////////////////////////////////////////////////////////////////////////////////////
    
    CsvPrinter.imprimirColumnar(df);
    
    Object valorABuscar = "Brasil";

    df.valorBuscado(valorABuscar);

    Object valorABuscar2 = 41;

    df.valorBuscado(valorABuscar2);
    
    Object valorABuscar3 = 88;
                        
    df.valorBuscado(valorABuscar3);
    
    ////  USER STORY 2: COPIA PROFUNDA DE LOS ELEMENTOS DE LA ESTRUCTURA TABULAR /////////////////////////////////////////////////////////////////////////////////////

    DataFrame copiadf = null;

    copiadf = df.clone();

    CsvPrinter.imprimirColumnar(copiadf);


    ////  USER STORY 14: ACCEDER A CELDA Y SETEAR NUEVO VALOR /////////////////////////////////////////////////////////////////////////////////////
    
    df.setValorDataFrame("0", "Jugador", "Diego Maradona"); // con header
    //df.setValorDataFrame("3", "2", 69); // sin header

    CsvPrinter.imprimirColumnar(df);

    CsvPrinter.imprimirColumnar(copiadf);

    ////  USER STORY 3: CONCATENACIÓN DE DOS ESTRUCTURAS EXISTENTES   /////////////////////////////////////////////////////////////////////////////////////

    DataFrame resultado = df.concatenar(copiadf); 

    CsvPrinter.imprimirColumnar(resultado);

    ////  USER STORY 6: TIPOS DE DATOS DE COLUMNAS /////////////////////////////////////////////////////////////////////////////////////
    // implementado más arriba

    ////  USER STORY 16: CELDAS CON DATOS FALTANTES SEAN IDENTIFICADAS CON NA  /////////////////////////////////////////////////////////////////////////////////////

    DataFrame df4 = resultado.clone();
    
    //CsvPrinter.imprimirColumnar(df4);

    CsvPrinter.info(df4);
    
    // Valores -> -9 numericos, nullStr para Strings, false para los boolean
    df4.sacarNAs(-9, "nullStr", false);
    
    CsvPrinter.info(df4);
    
    CsvPrinter.imprimirColumnar(df4);
    
    ////  USER STORY 10: EXPORTAR TABLA COMO CSV /////////////////////////////////////////////////////////////////////////////////////
    
    CsvExport.exportarComoCSV(df4,"archivoExportado.csv","C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\");
    //CsvExport.exportarComoCSV(df, "C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_FINAL_ALGORITMO\\TP_FINAL_ALGORITMO\\archivoExportado.csv");

    ////  USER STORY 12: ORDENAR DATOS SEGÚN UNA COLUMNA  /////////////////////////////////////////////////////////////////////////////////////
 
    String[] indices = {"Goles","Jugador"}; // con header
    //String[] indices = {"2","1","3"}; // sin header

    df.orderPorColumnas(indices); 

    CsvPrinter.imprimirColumnar(df);

    ////  USER STORY 15: INSERTAR UNA NUEVA COLUMNA A PARTIR DE UNA COLUMNA EXISTENTE /////////////////////////////////////////////////////////////////////////////////////
    
    df.clonarYAgregarColumna("Jugador", "ColumnaNueva"); // con header
    //df.clonarYAgregarColumna("1", "ColumnaNueva"); // sin header
    CsvPrinter.imprimirColumnar(df);

    //// USER STORY 19: ELIMINAR COLUMNA Y/O FILA  /////////////////////////////////////////////////////////////////////////////////////  

    // elimina las columnas por etiquetas
    df.imprimirEliminarColumna("Asistencias");
    CsvPrinter.imprimirColumnar(df);


    //// USER STORY 18: INSERTAR UNA NUEVA COLUMNA UTILIZANDO SECUENCIA LINEAL NATIVA  /////////////////////////////////////////////////////////////////////////////////////
     
    String[] datosNuevaColumna = { "11", "24","38", "56", "65", "89", "12", "4","9", "10"};
    
    df.agregarColumnaSecuencia(df, datosNuevaColumna,"Partidos", "S"); // "S" = String // con header
    //df.agregarColumnaSecuencia(df, datosNuevaColumna,"NuevaColumna", "S"); // "S" = String // sin header

    //// USER STORY 19: ELIMINAR COLUMNA Y/O FILA  //////////
    
    // elimina las filas por etiquetas
    df.imprimirEliminarFila("3");
    CsvPrinter.imprimirColumnar(df);

}
//-----------------------------------------------------------------------------------------------------------
}
//-----------------------------------------------------------------------------------------------------------