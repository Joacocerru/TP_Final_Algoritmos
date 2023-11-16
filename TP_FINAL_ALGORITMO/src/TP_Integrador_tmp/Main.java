package TP_Integrador_tmp;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) 
{
        
    DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba9.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba2.csv", ",", "N");
    
    ////  USER STORIE 1: Lectura CSV   \\\\

    // Pre: Dataframe(ruta_archivo, delimitado, Header o no)
    // Pos: Solo se carga el dataset. 

    //DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba9.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba2.csv", ",", "N");
    
    //DataFrame df =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\prueba1.csv", ",", "S");
    //DataFrame df1 =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\prueba1.csv", ",", "N");
    
    //DataFrame df3 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_3 (BuscarValor - anda)\\prueba2.csv", ",", "S");
    //DataFrame df4 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_3 (BuscarValor - anda)\\prueba2.csv", ",", "N");
    
    // VALEN //
    //DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");
    

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    ////  USER STORIE 11: Imprimir datos en formato tabla  \\\\

    DataFrame copiadf = null;

    copiadf = df.clone();

    CsvPrinter.imprimirColumnar(copiadf);
    CsvPrinter.imprimirColumnar(df);

    df.eliminarFila("4");

    CsvPrinter.imprimirColumnar(copiadf);

    CsvPrinter.imprimirColumnar(df);

    df.eliminarColumna("ColumnaPruebas");

    CsvPrinter.imprimirColumnar(copiadf);

    CsvPrinter.imprimirColumnar(df);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    ////  USER STORIE 7:  Buscar dato por etiqueta de Fila Y Columna \\\\
    
    System.out.println("--------------------------------------------------------------------------------------"); 
    System.out.println("# Información de la Celda obtenida por Etiqueta de Fila y Columna"); 
    System.out.println(" "); 

    String Fila = "3"; //  
    String Columna = "Columna4"; 
    //String Columna = "0";


    try {
        //Dato valor = df2.getValor(fila, columna);
        Dato valor = df.getValor(Fila, Columna);
            

        if (valor != null) 
            System.out.println("Valor en la fila " + (Fila) + " y columna " + (Columna) + ": " + valor.getDato());
        else 
            System.out.println("Índices fuera de rango.");
        }
    catch (NullPointerException e) {
        System.out.println("Fila/Columna Inexistente"); 
    }
    

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    DataFrame copiadf1 = null;

    copiadf1 = df.clone();

    System.out.println("Copia profunda del DataFrame sin header.");
    
    CsvPrinter.imprimirColumnar(copiadf1);
    CsvPrinter.imprimirColumnar(df);

    df.eliminarFila("4");
    //copiadf.eliminarFila("2");

    CsvPrinter.imprimirColumnar(copiadf1);
    CsvPrinter.imprimirColumnar(df);
    



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////  USER STORIE 15:  Agregar una Columna del Dataframe \\\\
    

    // METODO PARA AGREGAR UNA COLUMNA DEL DATAFRAME   

    System.out.println("Agregar una columna ya existente del DataFrame");

    CsvPrinter.imprimirPorFilas(df);

    // Supongamos que tienes una columna llamada "ColumnaExistente" en tu DataFrame
    String etiquetaColumnaExistente = "ColumnaBoolean";
    String etiquetaNuevaColumna = "Columna_Nueva";
    
    //DataFrame df = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_8\\prueba1.csv", ",", "S");
    
    df.clonarYAgregarColumna(etiquetaColumnaExistente, etiquetaNuevaColumna, df);
    
    // Imprimir el DataFrame
    CsvPrinter.imprimirColumnar(df);
    
    // Supongamos que tienes una columna llamada "ColumnaExistente" en tu DataFrame
    String etiquetaColumnaExistente2 = "ColumnaNum";
    String etiquetaNuevaColumna2 = "Columna_Nueva_2";
    
    df.clonarYAgregarColumna(etiquetaColumnaExistente2, etiquetaNuevaColumna2, df);
    
    // Imprimir el DataFrame
    CsvPrinter.imprimirColumnar(df);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    CsvPrinter.imprimirPorFilas(df);

    // METODO PARA ELIMINAR UNA FILA DEL DATAFRAME
    System.out.println("eliminar una Fila del DataFrame");

    //df.eliminarColumna("ColumnaNum");

    df.eliminarFila("4");

    CsvPrinter.imprimirPorFilas(df);


    CsvPrinter.imprimirPorFilas(df);
    //CsvPrinter.imprimirColumnar(df);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


   	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Imprime: Cantidad de Filas, Cantidad de Columnas, Etiqueta a las Filas 
    // y columnas y muestra el tipo de datos de las columnas. 
 
    String xx = df.getHeaderColumn(1);

    Dato dato = df.getValorPosicion(2,2);
    Dato dato2 = df.getValorPosicion(0,2);  

    Columna Col1 = df2.getColumna(2);
    Columna Col2 = df2.getColumnaPorEtiqueta("3");
 
    String[] Listita = {"1","4"};
    List<Columna> lista2 = df2.getColumnaListaEtiquetas( Listita);

    String fila = "3"; // 
    String columna = "Columna4"; //

    try {
        //Dato valor = df2.getValor(fila, columna);
        Dato valor = df.getValor(fila, columna);
      
        if (valor != null) 
        System.out.println("Valor en la fila " + (fila) + " y columna " + (columna) + ": " + valor.getDato());
        else 
        System.out.println("Índices fuera de rango.");
    }
    catch (NullPointerException e) {
        System.out.println("Fila/Columna Inexistente"); 
    }

	Columna columnaNombre = df.getColumnaPorEtiqueta("Columna3");
    
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//  Acceder a las Filas utilizando las etiquetas del encabezado --------------------------------

    
	df.imprimirEtiquetasFilas();
    //df2.imprimirEtiquetasFilas();
    
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


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////  USER STORIE 14: ACCEDER A UNA CELDA Y SETEAR NUEVO VALOR  \\\\
    // Seteo un valor en el DataFrame definiendo las etiquetas Fila y Columna y asignando un nuevo valor

    String etiquetaFila2 = "3"; // posición en Fila
    String etiquetaColumna2 = "Columna2"; // posición en Columna
    //String etiquetaColumna2 = "4";

    // Accede a la celda y establece el nuevo valor
    df.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 00);
    //df2.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 00);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    ////  USER STORIE 8: GENERAR UNA VISTA REDUCIDA (SLICING)  \\\\

    System.out.println("Impresión de una vista reducida del DataFrame (slicing)");
    System.out.println("---------------------------------------------------------------------");
    
    // Lista de etiquetas de filas y columnas a incluir en la vista reducida
    List<String> etiquetasFilas = Arrays.asList("1", "2", "3");
    List<String> etiquetasColumnas = Arrays.asList("Columna2","Columna3");

    CsvPrinter.imprimirVistaReducida(df, etiquetasFilas, etiquetasColumnas);

    // CASOS ESPECIALES DE VISTA REDUCIDA DEL DF - 
    // METODO PARA IMPRIMIR LAS PRIMERAS Y ULTIMAS FILAS

    System.out.println("Informacion de las primeras filas del DataFrame (head)");
    CsvPrinter.head(df, 2);

    System.out.println("Informacion de las ultimas filas del DataFrame (tail)");
    CsvPrinter.tail(df, 2);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////  USER STORIE 19: Eliminar una Fila del DF  \\\\

    System.out.println("eliminar una Fila del DataFrame");
    df2.eliminarFila("3");
    CsvPrinter.imprimirPorFilas(df2);
    

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////  USER STORIE 19: Eliminar una columna del DF  \\\\ 

    System.out.println("eliminar una columna del DataFrame");
    df2.eliminarColumna("Columna2");
    CsvPrinter.imprimirColumnar(df2);

    // Segundo ejemplo
	System.out.println("eliminar una columna del DataFrame");
	CsvPrinter.imprimirColumnar(df);
	df.eliminarColumna("ColumnaPruebas");
	CsvPrinter.imprimirColumnar(df);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // COPIA PROFUNDA DE LA ESTRUCTURA COLUMNAR

	DataFrame copiadf2 = null;
	copiadf2 = df.clone();
 	System.out.println("Copia profunda del DataFrame sin header.");
	CsvPrinter.imprimirColumnar(copiadf2);
    System.out.println("Copia profunda del DataFrame sin header.");
    CsvPrinter.imprimirPorFilas(copiadf2);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // IMPRIMO EL DF ORIGINAL CON VALOR SETEADO Y LA COPIA PROFUNDA

    System.out.println("Impresión del DataFrame con valor seteado");

    //CsvPrinter.imprimirColumnar(df2);
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Impresión de la copia profunda del DataFrame");

    CsvPrinter.imprimirColumnar(copiadf1);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // METODO PARA ELIMINAR UNA COLUMNA DEL DATAFRAME DE LA COPIA COLUMNA

	System.out.println("eliminar una columna del DataFrame");
	
	CsvPrinter.imprimirColumnar(df);
	
	df.eliminarColumna("ColumnaPruebas");
	
	CsvPrinter.imprimirColumnar(df);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // IMPRIMO EL DF ORIGINAL Y LA COPIA PROFUNDA

    System.out.println("Las etiquetas de las columnas son:" + df.getAllHeaderColumn());
    
    System.out.println("-----------------------------------------------------------");
    System.out.println("Impresión del DataFrame sin header.");
    
    //CsvPrinter.imprimirColumnar(df2);
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Impresión de la Copia profunda del DataFrame sin header.");

    CsvPrinter.imprimirColumnar(copiadf);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////  USER STORIE 13: Buscar datos en la tabla  \\\\ 
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


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////  USER STORIE 10: EXPORTAR DF COMO CSV  \\\\ 
    try {
        CsvExport.exportarComoCSV(df, "C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO_5\\archivo.csv");
        System.out.println("Tu nuevo DataFrame se guardó correctamente en la ruta de archivo seleccionada");

    } catch (Exception e) {
        System.out.println("Ocurrió un error al exportar el archivo CSV: " + e.getMessage());
        }
    }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------------------------------------------------------------------------------------------------
    // METODO PARA AGREGAR UNA COLUMNA DEL DATAFRAME
    /*
    System.out.println("Agragar una columna del DataFrame");

    // Supongamos que tienes una columna llamada "ColumnaExistente" en tu DataFrame
    String etiquetaNuevaColumna = "Columna5";
    String etiquetaColumnaExistente = "Columna2";

    // Verificar si la columna existente realmente existe
    if (df3.getColumnaPorEtiqueta(etiquetaColumnaExistente) != null) {
        // Obtener la columna existente
        Columna columnaExistente = df3.getColumnaPorEtiqueta(etiquetaColumnaExistente);

        // Crear la nueva columna y copiar los datos de la columna existente
        Columna nuevaColumna = new Columna();
        nuevaColumna.setEtiqueta(etiquetaNuevaColumna);

        // Supongamos que la clase Columna tiene un método para obtener los datos internos
        // Reemplaza esto con el método real que obtenga los datos internos de la columna
        Dato[] datosColumnaExistente = columnaExistente.obtenerDatosInternos();

        // Configurar los datos en la nueva columna
        nuevaColumna.configurarDatosInternos(datosColumnaExistente);

        // Agregar la nueva columna al DataFrame
        df3.dataColumnar.add(nuevaColumna);
        df3.ColumnArray.add(etiquetaNuevaColumna);
        df3.columnMap.put(etiquetaNuevaColumna, nuevaColumna);
    } else {
        System.out.println("Error: La columna '" + etiquetaColumnaExistente + "' no existe en el DataFrame.");
    }

    CsvPrinter.imprimirColumnar(df3); */
    //-----------------------------------------------------------------------------------------------
    /* 
    //CsvPrinter.imprimirColumnar(df3);
    //CsvPrinter.imprimirColumnar(df4);

    System.out.println("Concatenación de dos DataFrames");
    System.out.println("---------------------------------------------------------------------");

    try {
        // Verificar que ambos DataFrames tengan las mismas columnas
        if (df3.getAllHeaderColumn().equals(df4.getAllHeaderColumn())) {
        // Concatenar los DataFrames verticalmente
        DataFrame resultado = df3.concatenar(df4);
        // Imprimir el DataFrame resultante
        CsvPrinter.imprimirColumnar(resultado);
        } else {
            System.out.println("Error: Los DataFrames tienen columnas diferentes y no se pueden concatenar.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al concatenar: " + e.getMessage());
        } */
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------