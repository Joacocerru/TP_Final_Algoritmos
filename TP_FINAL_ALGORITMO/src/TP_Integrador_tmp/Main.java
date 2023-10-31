package TP_Integrador_tmp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        
    DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba1.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba4.csv", ",", "N");
    DataFrame df3 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\Salida_Csv_prueba.csv", ",", "N");
    
    
    //CsvPrinter.imprimirPorFilas(df);
    CsvPrinter.imprimirPorFilas(df);

    String[] x = {"0","1"};
    df.orderPorColumnas(x);

    CsvPrinter.imprimirPorFilas(df);


    String[] etiFilas = {"1","2"};
    String[] etiColumnas = {"ColumnaPruebas","ColumnaNum"};

    List <Fila> newFila = df.getFilasColumnasListaEtiquetas (etiFilas, etiColumnas) ;    

    //DataFrame df =  new DataFrame("C:\\Users\\Hernan\\Desktop\\UNSAM\\6-Algoritmos I\\TP_INTEGRADOR2\\prueba2.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Hernan\\Desktop\\UNSAM\\6-Algoritmos I\\TP_INTEGRADOR2\\prueba2.csv", ",", "N");
    
    // VALEN //
    //DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");
    


    //Object[][] matriz = { {"Marta","Luis","Nacho",1},{1,"Anabel","Julio",true},{"Maria","David",null,0} };

    // Imprime: Cantidad de Filas, Cantidad de Columnas, Etiqueta a las Filas 
    //          y columnas y muestra el tipo de datos de las columnas. 

    String xx = df.getHeaderColumn(2);

    //Dato[] Fila1 = df2.getFila(2);

    Dato dato = df.getValorPosicion(2,2);
    Dato dato2 = df.getValorPosicion(0,2);  

    Columna Col1 = df2.getColumna(2);
    Columna Col2 = df2.getColumnaPorEtiqueta("2");
 
    String[] Listita = {"1","4"};
    List<Columna> lista2 = df2.getColumnasListaEtiquetas( Listita);
    
    CsvPrinter.info(df2);

    // Imprimir por filas utilizando CsvPrinter
    CsvPrinter.imprimirPorFilas(df2);
    CsvPrinter.imprimirPorFilas(df);

    df.setValorPorEtiqueta("1", "ColumnaPruebas", "Cambio1");
    df.setValorPorEtiqueta("3", "ColumnaNum", 9);
    df.setValorPorEtiqueta("1", "ColumnaBoolean", "false");
    df.setValorPorEtiqueta("4", "ColumnaBoolean", 1);

    df.setValorPorEtiqueta("0", "ColumnaPruebas", "NEW PRUEBA");
    df.setValorPorEtiqueta("2", "ColumnaBoolean", "true");


    CsvPrinter.imprimirPorFilas(df);
    CsvExport.exportarComoCSV(df, "C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\Salida_Csv_   prueba.csv");

    

    // Imprimir columnar utilizando CsvPrinter
    CsvPrinter.imprimirColumnar(df2);
        
    // Acceder a un valor específico por Etiqueta de fila y columna ----------------------------------------------------
    System.out.println("--------------------------------------------------------------------------------------"); 
    System.out.println("# Información de la Celda obtenida por etiqueta de Fila y Columna"); 
    System.out.println(" "); 

    String fila = "3"; // 
    String columna = "4"; // 

    Dato valor = df2.getValor(fila, columna);
        
    if (valor != null) {
    System.out.println("Valor en la fila " + (fila) + " y columna " + (columna) + ": " + valor.getDato());
    } else {
        System.out.println("Índices fuera de rango.");
    } 
    // Imprime Info del DataFrame 2 -----------------------------------------------------------------------------------------------------------------
    System.out.println("#--------------------------------------------------------------------------");  
    CsvPrinter.info(df2);
    System.out.println("#--------------------------------------------------------------------------");

    System.out.println("#--------------------------------------------------------------------------");  
    CsvPrinter.head(df2, 4);
    System.out.println("#--------------------------------------------------------------------------");

    System.out.println("#--------------------------------------------------------------------------");  
    CsvPrinter.tail(df2, 4);
    System.out.println("#--------------------------------------------------------------------------");

    System.out.println("#--------------------------------------------------------------------------");  
    CsvPrinter.info(df);
    System.out.println("#--------------------------------------------------------------------------");

    // Pruebo se setear un nuevo dat



    // Acceder a las columnas utilizando las etiquetas del encabezado -------------------------------
    System.out.println("#--------------------------------------------------------------------------");
    System.out.println("# Información de las Columnas"); 
    System.out.println(" "); 

    Columna columnaNombre = df2.getColumnaPorEtiqueta("3");
    
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

    df2.imprimirEtiquetasFilas();

    //  Acceder a las Filas utilizando las etiquetas del encabezado --------------------------------
    
    String etiquetaFila = "2";
    
    Fila FilaNombre = df2.getFilaPorEtiqueta(etiquetaFila);

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

    /*
   // EXPORTAR DF -----------------------------------------------------------------------------------
    try {

        String rutaArchivo = "C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO";
        String nombreArchivo = "archivoPrueba2";
        
        CsvExport.exportarComoCSV(df2, rutaArchivo + "\\" + nombreArchivo + ".csv");
        System.out.println("El archivo '" + nombreArchivo + "' se guardó correctamente en la siguiente ruta: " + rutaArchivo);
    } catch (Exception e) {
        System.out.println("Ocurrió un error al exportar el archivo CSV: " + e.getMessage());
    }
     */

    //----------------------------------------------------------------------------------------------------------------------------
}
}
    