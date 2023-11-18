package TP_Integrador_tmp;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // **************************************************************** CONSTRUCTORES *************************************************************************************

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //// USER STORY 1: CARGAR DATOS DESDE ARCHIVO .CSV   ////

    // Como usuario, quiero poder cargar datos desde un archivo .CSV en el software para crear una estructura tabular


    DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba9.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba2.csv", ",", "N");
    
    //DataFrame df =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df1 =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    
    //DataFrame df3 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "S");
    //DataFrame df4 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "S");
    
    // VALEN //
    //DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");
    

    //  PRUEBA FINAL //

    // Busqueda binaria
    
    CsvPrinter.imprimirColumnar(df2);
    //int resultado1 = df2.buscarBinariaEnColumna("2", 53);

    // clonado + ordenar x 3 campos + eliminar 2 filas y 1 columna
    DataFrame df3 = df2.clone();
    String[] xxx = {"0","1","2"};
    df3.orderPorColumnas(xxx);

    CsvPrinter.imprimirColumnar(df3);
    
    df3.eliminarFila ("6");
    df3.eliminarColumna("2");
    df3.eliminarFila ("2");
    
    CsvPrinter.imprimirColumnar(df3);
    CsvPrinter.imprimirPorFilas(df3);

    //------------------------------
    // PRUEBA SACAR-NA con parametros x default
    DataFrame df4 = df.clone();

    CsvPrinter.imprimirColumnar(df4);
    CsvPrinter.info(df4);
    
    // Valores -> -9 numericos, nullStr para Strings, false para los boolean
    df4.sacarNAs(-9, "nullStr", false);
    
    CsvPrinter.info(df4);
    CsvPrinter.imprimirColumnar(df4);

    
    DataFrame resultado = df.concatenar(df);

    // Imprimir el DataFrame resultante
    CsvPrinter.imprimirColumnar(resultado);

    // Imprimir el DataFrame resultante
    CsvPrinter.imprimirColumnar(resultado);


/*     
    // Lista de etiquetas de columnas a seleccionar
    List<String> etiquetasColumnas1 = Arrays.asList("ColumnaNum", "ColumnaVarios");

    // Lista de etiquetas de filas a seleccionar
    List<String> etiquetasFilas1 = Arrays.asList("1", "3", "5");

    // Llamada al método para seleccionar la vista reducida
    DataFrame vistaRed1 = df.seleccionarVista(etiquetasFilas1, etiquetasColumnas1);

    // Imprimir el DataFrame resultante (vista reducida)
    CsvPrinter.imprimirPorFilas(vistaRed1);
    CsvPrinter.imprimirColumnar(vistaRed1);



    CsvPrinter.imprimirColumnar(df);
    CsvPrinter.imprimirColumnar(df2);
    //CsvPrinter.imprimirColumnar(dfprueba);

*/

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 2: COPIA PROFUNDA DE LOS ELEMENTOS DE LA ESTRUCTURA TABULAR ////

    // Como usuario, quiero poder realizar una copia profunda de los elementos de la estructura tabular para generar una nueva 
    // estructura con los mismos valores, pero independiente de la estructura original en memoria


// PRUEBAS DE FILTRADO

    CsvPrinter.imprimirColumnar(df);


    DataFrame filtro1 = df.FiltroPorColumna("ColumnaNum", 0, 5);
    DataFrame filtro2 = df.FiltroPorColumna("ColumnaNum", -1, 3);
    DataFrame filtro3 = df.FiltroPorColumna("ColumnaNum", 1, 3);
    DataFrame filtro4 = df.FiltroPorColumna("ColumnaBoolean", 0, "True");
    DataFrame filtro5 = df.FiltroPorColumna("ColumnaPruebas", -1, "Prueba4");
    
    CsvPrinter.imprimirColumnar(filtro1);
    CsvPrinter.imprimirColumnar(filtro2);
    CsvPrinter.imprimirColumnar(filtro3);
    CsvPrinter.imprimirColumnar(filtro4);
    CsvPrinter.imprimirColumnar(filtro5);

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   COPIA PROFUNDA");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    System.out.println("DataFrame: ");
    CsvPrinter.imprimirColumnar(df);

    DataFrame copiadf1 = null;

    copiadf1 = df.clone();

    System.out.println("#--------------------------------------------------------------------------");

    System.out.println("Copia profunda del DataFrame");
    
    CsvPrinter.imprimirColumnar(copiadf1);

    String etiquetaFila = "4";
    System.out.println("Se elimina la fila con etiqueta "+etiquetaFila);
    df.eliminarFila(etiquetaFila);
    copiadf1.eliminarFila("2");

    System.out.println("DataFrame");
    CsvPrinter.imprimirColumnar(df);
    
    System.out.println("Copia profunda del DataFrame");
    CsvPrinter.imprimirColumnar(copiadf1);

    /* -----------------------------------------------------
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
    */

    //---------------------------------------------------------
    /*

    DataFrame copiadf2 = null;

    copiadf2 = df.clone();

    System.out.println("Copia profunda del DataFrame sin header.");

    CsvPrinter.imprimirColumnar(copiadf2);

    System.out.println("Copia profunda del DataFrame sin header.");

    CsvPrinter.imprimirPorFilas(copiadf2);*/

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 3: CONCATENACIÓN DE DOS ESTRUCTURAS EXISTENTES  - CODIGO A REVISAR ////

    // Como usuario, quiero la capacidad de generar una nueva estructura tabular a partir de la concatenación de dos estructuras existentes, 
    // creando así una nueva combinando las filas de la primera y luego las filas de la segunda. 
    // Esta operación es válida si las columnas de ambas estructuras coinciden.
    
    /* 
    
    //CsvPrinter.imprimirColumnar(df3);
    //CsvPrinter.imprimirColumnar(df4);

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   CONCATENACIÓN DE DOS DATAFRAMES");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

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
        }*/


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ***************************************************************** CONSULTAS DE ESTADOS DE LA TABLA ***********************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 4: OBTENER CANTIDAD DE FILAS Y COLUMNAS //// 

    // Como usuario, quiero poder obtener la cantidad de filas y de columnas en el dataset 

    

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   INFORMACIÓN SOBRE DATAFRAMES");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

        
    System.out.println("Información general del Dataframe"); 
    System.out.println(" ");
    CsvPrinter.info(df);                    
    System.out.println(" ");
    CsvPrinter.info(df2); 


    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");


    // Imprimo info de la columna elegida segun la etiqueta elegida 
    String etiquetaColumna3 = "Columna3";
    Columna columnaNombre = df.getColumnaPorEtiqueta( etiquetaColumna3);

    System.out.println("Información sobre la columna elegida");
    System.out.println(" ");

    if (columnaNombre != null) {

        String nombreColumna = columnaNombre.getEtiqueta(); // obtengo etiqueta de la columna 
        int cantidadDatos = columnaNombre.getCantDatos(); // obtengo la cantidad de datos de la columna  
        String tipoDato = columnaNombre.getTipoDato(); // obtengo el tipo de dato de la columna 
         
        System.out.println("Etiqueta de la Columna selecionada: " + nombreColumna);
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


    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");

 
    // Imprimo info de la fila elegida segun la etiqueta elegida 
    String etiquetaFila3 = "2";
    Fila FilaNombre = df.getFilaPorEtiqueta(etiquetaFila3);

    System.out.println("Información sobre la fila elegida");
    System.out.println(" ");
    

    if (FilaNombre != null) {

        int cantidadDatos = FilaNombre.getCantDatos(); // obtengo la cantidad de datos de la fila

        System.out.println("Etiqueta de la Fila seleccionada: " + etiquetaFila3);
        System.out.println("Cantidad de Datos en la Fila " + etiquetaFila3 + ": " + cantidadDatos);
        System.out.println("Datos de la Fila "+ etiquetaFila3 + ":");     /// ###2  DA UN RESULTADO QUE NO DEBERIA 

        for (int i = 0; i <= df2.getNroColumnas(); i++) {

            Object dato4 = FilaNombre.getDato(i);
            System.out.print(dato4+ " ");
        }
    } else {

        System.out.println("La fila con etiqueta " + etiquetaFila3 + " no existe en el DataFrame.");
    }

    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");

     

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 5: OBTENER ETIQUETAS DE FILAS Y COLUMNAS ////

    // Como usuario, quiero poder obtener las etiquetas de las filas y columnas en el dataset.

    
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   ETIQUETAS DE FILAS Y COLUMNAS");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");


    df.imprimirEtiquetasFilas();                             

    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");
    System.out.println("Las etiquetas de las columnas son: " + df.getAllHeaderColumn());
    //System.out.println("Las etiquetas de las columnas son: " + df2.getAllHeaderColumn());
    //System.out.println("Las etiquetas de las columnas son: " + dfprueba.getAllHeaderColumn());
    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");


    //--------------------------------------------------------------------------------------
    // Adicional - acceso indexado a nivel de fila y columna

    // Acceder una fila completa si se selecciona la etiqueta de la fila
    String etiquetaFila4 = "4";
    Fila fila = df.getFilaPorEtiqueta(etiquetaFila4);

    System.out.println("Las fila indexada por la etiqueta '" + etiquetaFila4 + "' contiene los datos " + fila);
    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");



    // Acceder una columna completa si se selecciona la etiqueta de la columna
    String etiquetaColumna1 = "Columna3";
    Columna columna = df.getColumnaPorEtiqueta(etiquetaColumna1);

    System.out.println("Las columna indexada por la etiqueta '" + etiquetaColumna1 + "' contiene los datos " + columna);  
    System.out.println(" ");

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 6: obtener los tipos de datos de las columnas

    // Como usuario, quiero poder obtener los tipos de datos de las columnas en el dataset


    // YA IMPLEMENTADO EN USER STORIE 4

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 7: ACCEDER A LOS DATOS A TRAVES DE FILA Y COLUMNA //// 

    // Como usuario, quiero poder acceder a los datos de la estructura tabular a través de un índice de fila y columna.

    //-----------------------------------------------------------------------------------------------------------------
    
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   INFORMACIÓN DE LA CELDA OBTENIDA POR ETIQUETA DE FILA Y COLUMNA");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");


    String Fila = "4"; // 
    String Columna = "Columna1"; 
    //String Columna = "0";


    try {
        Dato valor = df.getValor(Fila, Columna);
        //Dato valor = df2.getValor(Fila, Columna);
            
        if (valor != null) 
            System.out.println("Valor en la fila " + (Fila) + " y columna " + (Columna) + ": " + valor.getDato());
        else 
            System.out.println("Índices fuera de rango.");
        }
    catch (NullPointerException e) {
        System.out.println("EXEPCIÓN: Fila y/o Columna Inexistente. Por favor, vuelva a corroborar los datos ingresados..."); 
    }
    System.out.println("---------------------------------------------------------------------");

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ****************************************************************** METODOS DE ACCESO ****************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      

    ////  USER STORY 8: GENERAR UNA VISTA REDUCIDA (SLICING) - CORREGIR EL CODIGO   //// 

    // Como usuario, quiero la capacidad de seleccionar una vista reducida de la estructura tabular a 
    // través de una lista de etiquetas de índice (slicing).


    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   IMPRESIÓN DE UNA VISTA REDUCIDA (SLICING)");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");
    
    
    // Lista de etiquetas de columnas a seleccionar
    List<String> etiquetasColumnas = Arrays.asList("Columna1", "Columna4");

    // Lista de etiquetas de filas a seleccionar
    List<String> etiquetasFilas = Arrays.asList("1", "3", "5");

    // Llamada al método para seleccionar la vista reducida
    DataFrame vistaRed = df.seleccionarVista(etiquetasFilas, etiquetasColumnas);

    // Imprimir el DataFrame resultante (vista reducida)
    CsvPrinter.imprimirPorFilas(vistaRed);
    CsvPrinter.imprimirColumnar(vistaRed);

    System.out.println("---------------------------------------------------------------------");
    
    //-------------------------------------------------------------------------------------------------
    // ESTO ES SOLO UNA IMPRESION POR PANTALLA DE LOS DATOS DADAS LAS ETIQUETAS

    System.out.println("Impresión por pantalla de los datos dadas las etiquetas filas y columnas");
    System.out.println("------------------------------------------------------------------------------------");
    
    // Lista de etiquetas de filas y columnas a incluir en la vista reducida
    List<String> etiquetasFilas5 = Arrays.asList("1", "2", "3");
    List<String> etiquetasColumnas5 = Arrays.asList("Columna2","Columna3");
    CsvPrinter.imprimirVistaReducida(df, etiquetasFilas5, etiquetasColumnas5);

    System.out.println("------------------------------------------------------------------------------------");

    //------------------------------------------------------------------------------------------------
    // CASOS ESPECIALES DE VISTA REDUCIDA DEL DF - METODO PARA IMPRIMIR LAS PRIMERAS Y ULTIMAS FILAS

    System.out.println("Vistas especiales: primeras y ultimas filas");
    System.out.println("Informacion de las primeras filas del DataFrame (head)");
    CsvPrinter.head(df, 2);
    System.out.println("Informacion de las ultimas filas del DataFrame (tail)");
    CsvPrinter.tail(df, 2);



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 9: FILTRO APLICADO A LOS VALORES DE LAS CELDAS (QUERY) - CODIGO A IMPLEMETAR   ////

    // Como usuario, quiero poder seleccionar una parte de la estructura tabular utilizando un 
    // filtro aplicado a los valores de las celdas (query).


    // CODIGO A IMPLEMETAR
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    ////  USER STORY 10: EXPORTAR TABLA COMO CSV ////

    // Como usuario, quiero la opción de exportar los datos de la tabla como CSV

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   EXPORTAR DATAFRAME EN CSV");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    // ruta a exportar
    String rutaArchivo = "C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\archivoExportado.csv";
    
    
    CsvExport.exportarComoCSV(df, rutaArchivo);
        
    
    // Esta opción es para traer el archivo recién exportado y ver si lo imprime bien
    //DataFrame dfExportado =  new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\archivoExportado.csv", ",", "S");
    //CsvPrinter.imprimirPorFilas(dfExportado);
    //CsvPrinter.imprimirColumnar(dfExportado);



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ********************************************************  METODOS DE VISUALIZACION  ******************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 11: IMPRIMIR LOS DATOS EN FORMA DE TABLA  //// 
    
    // Como usuario, quiero ver los datos en forma de tabla para comprender la información de manera clara y concisa.

    CsvPrinter.imprimirPorFilas(df); 

    CsvPrinter.imprimirColumnar(df);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 12: ORDENAR DATOS SEGÚN UNA COLUMNA - METODO IMPLEMENTADO ?  ////

    // Como usuario, quiero poder ordenar los datos en la tabla según una columna específica.


    /*   
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   ORDENAR DATOS SEGÚN UNA COLUMNA");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    */ 


    // METODO IMPLEMENTADO ?

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 13: BUSCAR Y FILTRAR DATOS EN LA TABLA  //// 

    // Como usuario, quiero la capacidad de buscar y filtrar datos en la tabla para encontrar información específica.

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   BUSCAR DATOS ESPECÍFICOS EN EL DATAFRAME");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    // Buscar datos en la tabla - llamo al método buscarValor y le paso el valor a buscar
    System.out.println("---------------------------------------------------------------------");
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

    // **********************************************************  GESTION DE DATOS  **********************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // ////  USER STORY 14: ACCEDER A CELDA Y SETEAR NUEVO VALOR ////

    // Como usuario, quiero poder acceder directamente a una celda y asignar un nuevo valor para actualizar los datos.

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   ACCEDER A UNA CELDA Y SETEAR NUEVO VALOR");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    // Seteo un valor en el DataFrame definiendo las etiquetas Fila y Columna y asignando un nuevo valor
    String etiquetaFila2 = "3"; // posición en Fila
    String etiquetaColumna2 = "Columna2"; // posición en Columna

    df.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 69);
    //df2.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 0);


    // IMPRIMO EL DF ORIGINAL CON VALOR SETEADO Y LA COPIA PROFUNDA

    //CsvPrinter.imprimirColumnar(df2);

    System.out.println("Impresión del DataFrame");
    CsvPrinter.imprimirColumnar(df);
    System.out.println("Impresión de la copia profunda del DataFrame");
    CsvPrinter.imprimirColumnar(copiadf1);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 15: INSERTAR UNA NUEVA COLUMNA A PARTIR DE UNA COLUMNA EXISTENTE ////

    // Como usuario, quiero poder insertar una nueva columna a partir de otra columna existente.

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   AGREGAR UNA COLUMNA AL DATAFRAME");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    
    System.out.println("Impresión del DataFrame");
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Agregar una columna ya existente del DataFrame");
 
    String etiquetaColumnaExistente = "Columna3";
    String etiquetaNuevaColumna = "ColumnaNueva";
    df.clonarYAgregarColumna(etiquetaColumnaExistente, etiquetaNuevaColumna);
    
    // Imprimir el DataFrame
    System.out.println("Impresión del DataFrame con la nueva columna agregada");
    CsvPrinter.imprimirColumnar(df);

    

    /*
    String etiquetaColumnaExistente2 = "ColumnaNum";

    String etiquetaNuevaColumna2 = "Columna_Nueva_2";
    
    df.clonarYAgregarColumna(etiquetaColumnaExistente2, etiquetaNuevaColumna2, df);
    
    // Imprimir el DataFrame
    CsvPrinter.imprimirColumnar(df);*/

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 16: CELDAS CON DATOS FALTANTES SEAN IDENTIFICADAS CON NA  -  METODO IMPLEMENTADO ////

    // Como usuario, quiero que las celdas con datos faltantes sean identificadas y marcadas con un formato "NA".

  
    // METODO IMPLEMENTADO ?

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //// USER STORY 17: REALIZAR OPERACIONES SIN ERRORES EN CASO DE TENER NA - METODO A IMPLEMENTAR ////

    // Como usuario, quiero que el sistema realice operaciones sin errores incluso cuando haya datos faltantes marcados como "NA".


    // METODO A IMPLEMENTAR

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // *******************************************  GESTION DEL DATASET  *******************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // USER STORY 18: INSERTAR UNA NUEVA COLUMNA UTILIZANDO SECUENCIA LINEAL NATIVA  - A REVISAR  ////

    // Como usuario, quiero poder insertar una nueva columna en el dataset utilizando una secuencia lineal nativa de Java.

    
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   AGREGAR UNA COLUMNA AL DATAFRAME CON SECUENCIA LINEAL");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");

    // METODO PARA AGREGAR UNA NUEVA COLUMNA DEL DATAFRAME

    System.out.println("Agregar una columna Nueva al DataFrame");
    
    String etiquetaColumnaNueva = "Columna5";
    DataFrame df5 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df5 = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    String[] datosNuevaColumna = {"Nueva1", "Nueva2","Nueva3", "Nueva4", "Nueva5", "Nueva6"};
    
    // Crear un array de Dato
    Dato[] datosArray = new Dato[datosNuevaColumna.length];

    for (int i = 0; i < datosNuevaColumna.length; i++) {
        datosArray[i] = new Dato(datosNuevaColumna[i]);
    }
    
    // Crear la nueva columna
    Columna nuevaColumna = new Columna();
    nuevaColumna.setColumna(datosArray, "S");
    df5.AgregarColumnaNueva(etiquetaColumnaNueva, nuevaColumna);
    
    // Imprimir el DataFrame
    System.out.println("Impresión del DataFrame con la nueva columna agregada");
    CsvPrinter.imprimirColumnar(df5);

    

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //// USER STORY 19: ELIMINAR COLUMNA Y/O FILA  ////

    // Como usuario, quiero la capacidad de eliminar una columna y/o fila específica del dataset.

    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   ELIMINAR FILA Y/O COLUMNA ");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" ");    
    

    
    // METODO PARA ELIMINAR UNA FILA DEL DATAFRAME
    System.out.println("Impresión del DataFrame");
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Eliminar una Fila específica del DataFrame");
    System.out.println(" ");

    df.eliminarFila("3");
    CsvPrinter.imprimirColumnar(df);


    //---------------------------------------------
    // METODO PARA ELIMINAR UNA COLUMNA DEL DATAFRAME
    System.out.println("Impresión del DataFrame");
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Eliminar una columna específica del DataFrame");
    System.out.println(" "); 

    df.eliminarColumna("Columna2");
    CsvPrinter.imprimirColumnar(df);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //// USER STORY 20: AGRUPAMIENTO O GROUP BY - METODO A IMPLEMENTAR   ////

    /*  
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println("   AGRUPAMIENTO O GROUP BY ");
    System.out.println("#-----------------------------------------------------------------------------");
    System.out.println(" "); 

    // Como usuario, quiero la capacidad de dividir las filas en diferentes grupos (agrupamiento o groupby) 
    // según una o más columnas. Luego, quiero aplicar una operación de sumarización estadística a estos grupos.

    */
    // METODO A IMPLEMENTAR

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
    ////  USER STORY 21: MANEJAR EXEPCIONES  - METODO A IMPLEMENTAR ////
    
    // Como usuario, quiero que el software maneje errores mediante excepciones, permitiendo la continuidad 
    // de la ejecución y proporcionando información sobre el tipo de error capturado.

    // METODO A IMPLEMENTAR

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

