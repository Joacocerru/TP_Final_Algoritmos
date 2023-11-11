package TP_Integrador_tmp;

//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashMap;
import java.util.List;
//import TP_Integrador_tmp.Dato_Numerico;

public class Main {
    public static void main(String[] args) {
        
    //DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba2.csv", ",", "N");
    
    DataFrame df =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\prueba1.csv", ",", "S");
    //DataFrame df1 =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\prueba1.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\prueba1.csv", ",", "N");
    
    //DataFrame df3 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_3 (BuscarValor - anda)\\prueba2.csv", ",", "S");
    //DataFrame df4 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_3 (BuscarValor - anda)\\prueba2.csv", ",", "N");
    
    // VALEN //
    //DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");
    


    //Object[][] matriz = { {"Marta","Luis","Nacho",1},{1,"Anabel","Julio",true},{"Maria","David",null,0} };

// Imprime: Cantidad de Filas, Cantidad de Columnas, Etiqueta a las Filas 
//          y columnas y muestra el tipo de datos de las columnas. 
/* 
    String xx = df.getHeaderColumn(1);

    //Dato[] Fila1 = df2.getFila(2);

    Dato dato = df.getValorPosicion(2,2);
    Dato dato2 = df.getValorPosicion(0,2);  

    Columna Col1 = df2.getColumna(2);
    Columna Col2 = df2.getColumnaPorEtiqueta("C");
 
    String[] Listita = {"1","4"};
    List<Columna> lista2 = df2.getColumnaListaEtiquetas( Listita);
*/
//    CsvPrinter.info(df2);
//----------------------------------------------------------------------
// Imprimir por filas utilizando CsvPrinter
    //CsvPrinter.imprimirPorFilas(df2);

// Imprimir columnar utilizando CsvPrinter
//    CsvPrinter.imprimirColumnar(df2);
//--------------------------------------------------------------------------        
// Acceder a un valor específico por Etiqueta de fila y columna 
        System.out.println("--------------------------------------------------------------------------------------"); 
        System.out.println("# Información de la Celda obtenida por etiqueta de Fila y Columna"); 
        System.out.println(" "); 

        String fila = "3"; // 
        //String columna = "4"; // 
        String columna = "Columna4"; //

        //Dato valor = df2.getValor(fila, columna);
        Dato valor = df.getValor(fila, columna);
        
        if (valor != null) {
        System.out.println("Valor en la fila " + (fila) + " y columna " + (columna) + ": " + valor.getDato());
    } else {
        System.out.println("Índices fuera de rango.");
    } 
// Imprime Info del DataFrame 2 -----------------------------------------------------------------------------------------------------------------
//System.out.println("#--------------------------------------------------------------------------");  
//CsvPrinter.info(df2);
//System.out.println("#--------------------------------------------------------------------------");

// Acceder a las columnas utilizando las etiquetas del encabezado -------------------------------
System.out.println("#--------------------------------------------------------------------------");
System.out.println("# Información de las Columnas"); 
System.out.println(" "); 

//Columna columnaNombre = df2.getColumnaPorEtiqueta("Columna3");
Columna columnaNombre = df.getColumnaPorEtiqueta("Columna3");
    
// Imprimo la columna segun la etiqueta elegida --------------------------------------------------

    if (columnaNombre != null) {

        String nombreColumna = columnaNombre.getEtiqueta(); // obtengo etiqueta de la columna 2
        int cantidadDatos = columnaNombre.getCantDatos(); // obtengo la cantidad de datos de la columna 2 
        String tipoDato = columnaNombre.getTipoDato(); // obtengo el tipo de dato de la columna 2

        System.out.println("Nombre de la Columna: " + nombreColumna);
        System.out.println("Tipo de Dato de la Columna " + nombreColumna + ": " + tipoDato);
        System.out.println("Cantidad de Datos en la Columna " + nombreColumna + ": " + cantidadDatos);
    
        System.out.println("Datos de la Columna "+ nombreColumna + ":");
        for (int i = 0; i < cantidadDatos; i++) {
            Dato dato3 = columnaNombre.getDato(i);
            System.out.println(dato3.getDato());
        }
    } else {
        String nombreColumna = df.getHeaderColumn(1);
        System.out.println("La columna "+ nombreColumna + " no existe en el DataFrame.");
    }

//  Imprimo Etiquetas de las Filas -------------------------------------
System.out.println("#--------------------------------------------------------------------------");
System.out.println("# Información de las Filas");    
System.out.println(" ");

//df2.imprimirEtiquetasFilas();
df.imprimirEtiquetasFilas();

//  Acceder a las Filas utilizando las etiquetas del encabezado --------------------------------
    
    String etiquetaFila = "2";
    
    //Fila FilaNombre = df2.getFilaPorEtiqueta(etiquetaFila);
    Fila FilaNombre = df.getFilaPorEtiqueta(etiquetaFila);

// imprimo la columna segun la etiqueta elegida ------------------------------------------------

    if (FilaNombre != null) {

        int cantidadDatos = FilaNombre.getCantDatos(); // obtengo la cantidad de datos de la columna 2 

        System.out.println("Etiqueta de la Fila seleccionada: " + etiquetaFila);
        System.out.println("Cantidad de Datos en la Fila " + etiquetaFila + ": " + cantidadDatos);
        System.out.println("Datos de la Fila "+ etiquetaFila + ":");

        for (int i = 0; i < df2.getNroColumnas(); i++) {
            Object dato4 = FilaNombre.getDato(i);
            System.out.print(dato4+ " ");
        }
    } else {
        System.out.println("La fila con etiqueta " + etiquetaFila + " no existe en el DataFrame.");
    }
    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");

   // EXPORTAR DF COMO CSV ---------------------------------------------------------------------------------------------------
    try {
        CsvExport.exportarComoCSV(df, "C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\archivo.csv");
        System.out.println("Tu nuevo DataFrame se guardó correctamente en la ruta de archivo seleccionada");
    } catch (Exception e) {
        System.out.println("Ocurrió un error al exportar el archivo CSV: " + e.getMessage());
    }
   //----------------------------------------------------------------------------------------------------------------------------

  /*   //-----------------------------------------------------------------------------------------
   // COPIA PROFUNDA DE LA ESTRUCTURA COLUMNAR
   
   DataFrame copiaDataFrame1 = null;

   try {
       // Llama al método clone
       copiaDataFrame1 = df.clone();
   } catch (CloneNotSupportedException e) {
       e.printStackTrace(); // Maneja la excepción de clonación si es necesario
   }
//----------------------------------------------------------------------

    System.out.println("Copia profunda del DataFrame sin header.");
    
    CsvPrinter.imprimirColumnar(copiaDataFrame1);

    System.out.println("Copia profunda del DataFrame sin header.");

    CsvPrinter.imprimirPorFilas(copiaDataFrame1);
*/
//---------------------------------------------------------------------
// COPIA PROFUNDA DE LA ESTRUCTURA COLUMNAR

    DataFrame copiadf = null;

   try {
       //copiaDataFrame2 = df2.clone();
       copiadf = df.clone();
   } catch (CloneNotSupportedException e) {
       e.printStackTrace(); // Maneja la excepción de clonación 
   }
//-----------------------------------------------------------
// IMPRIMO EL DF ORIGINAL Y LA COPIA PROFUNDA

    System.out.println("Las etiquetas de las columnas son:" + df.getAllHeaderColumn());
    
    System.out.println("-----------------------------------------------------------");
    System.out.println("Impresión del DataFrame sin header.");
    
    //CsvPrinter.imprimirColumnar(df2);
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Impresión de la Copia profunda del DataFrame sin header.");

    CsvPrinter.imprimirColumnar(copiadf);

//---------------------------------------------------------------
// Seteo un valor en el DataFrame definiendo las etiquetas Fila y Columna
// y asignando un nuevo valor

    String etiquetaFila2 = "3";
    
  //String etiquetaColumna2 = "4";
    String etiquetaColumna2 = "Columna2";

// Accede a la celda y establece el nuevo valor

    //df2.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 00);
    df.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 00);

//---------------------------------------------------------------------
// IMPRIMO EL DF ORIGINAL CON VALOR SETEADO Y LA COPIA PROFUNDA

    System.out.println("Impresión del DataFrame con valor seteado");

    //CsvPrinter.imprimirColumnar(df2);
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Impresión de la copia profunda del DataFrame");
    
    CsvPrinter.imprimirColumnar(copiadf);
//--------------------------------------------------------------------------------
// Llamo al método buscarValor y le paso el valor a buscar
   System.out.println("Busqueda de un elemento dentro del DataFrame");
   System.out.println("---------------------------------------------------------------------");

   Dato_Numerico valorABuscar = new Dato_Numerico(88); 
   String resultadoBusqueda = df.buscarValor(valorABuscar);
   System.out.println(resultadoBusqueda);
   System.out.println("#--------------------------------------------------------------------------");

   Dato_Numerico valorABuscarNumerico = new Dato_Numerico(5); 
   String resultadoBusquedaNumerica = df.buscarValor(valorABuscarNumerico);
   System.out.println(resultadoBusquedaNumerica);
   System.out.println("#--------------------------------------------------------------------------");

   Dato_Boolean valorABuscarBoolean = new Dato_Boolean(true); 
   String resultadoBusquedaBoolean = df.buscarValor(valorABuscarBoolean);
   System.out.println(resultadoBusquedaBoolean);
   System.out.println("#--------------------------------------------------------------------------");
   
   Dato_String valorABuscarString = new Dato_String("Prueba2"); 
   String resultadoBusquedaString = df.buscarValor(valorABuscarString);
   System.out.println(resultadoBusquedaString);
   System.out.println("#--------------------------------------------------------------------------");
//-----------------------------------------------------------------
// GENERAR UNA VISTA REDUCIDA (SLICING)
//-----------------------------------------------------------------
    System.out.println("Impresión de una vista reducida del DataFrame (slicing)");
    System.out.println("---------------------------------------------------------------------");
    
    // Lista de etiquetas de filas y columnas a incluir en la vista reducida
    List<String> etiquetasFilas = Arrays.asList("1", "2", "3");
    
    List<String> etiquetasColumnas = Arrays.asList("Columna2","Columna3");

    CsvPrinter.imprimirVistaReducida(df, etiquetasFilas, etiquetasColumnas);
//--------------------------------------------------------------------------------
    
//--------------------------------------------------------------------------------    
}    
//--------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------