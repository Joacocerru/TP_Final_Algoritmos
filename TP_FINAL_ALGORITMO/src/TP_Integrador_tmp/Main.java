package TP_Integrador_tmp;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // **************************************************************** CONSTRUCTORES *************************************************************************************

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // USER STORY 1: cargar datos desde un archivo .CSV

    // Como usuario, quiero poder cargar datos desde un archivo .CSV en el software para crear una estructura tabular


    //DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba9.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba2.csv", ",", "N");
    
    //DataFrame df =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df1 =  new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    
    //DataFrame df3 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "S");
    //DataFrame df4 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "S");
    
    // VALEN //

    DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 2: Copia profunda de los elementos de la estructura tabular

    // Como usuario, quiero poder realizar una copia profunda de los elementos de la estructura tabular para generar una nueva 
    // estructura con los mismos valores, pero independiente de la estructura original en memoria

    System.out.println("DataFrame");
    CsvPrinter.imprimirColumnar(df);
    
    DataFrame copiadf1 = null;

    copiadf1 = df.clone();

    System.out.println("#--------------------------------------------------------------------------");

    System.out.println("Copia profunda del DataFrame");
    
    CsvPrinter.imprimirColumnar(copiadf1);

    String etiquetaFila = "4";
    System.out.println("Se elimina la fila con etiqueta "+etiquetaFila);
    df.eliminarFila(etiquetaFila);
    //copiadf.eliminarFila("2");

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

    ////  USER STORY 3: concatenación de dos estructuras existentes    // CODIGO A REVISAR

    // Como usuario, quiero la capacidad de generar una nueva estructura tabular a partir de la concatenación de dos estructuras existentes, 
    // creando así una nueva combinando las filas de la primera y luego las filas de la segunda. 
    // Esta operación es válida si las columnas de ambas estructuras coinciden.
    /* 
    CsvPrinter.imprimirColumnar(df3);

    CsvPrinter.imprimirColumnar(df4);

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
        }*/


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ***************************************************************** CONSULTAS DE ESTADOS DE LA TABLA ***********************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 4: obtener la cantidad de filas y de columnas en el dataset 

    // Como usuario, quiero poder obtener la cantidad de filas y de columnas en el dataset 

    // Imprimo la columna segun la etiqueta elegida --------------------------------------------------

    System.out.println("-------------------------------------------------------------------------");
    System.out.println("Obtener la información de filas y de columnas en el dataset");
    System.out.println("-------------------------------------------------------------------------");
    
    Columna columnaNombre = df.getColumnaPorEtiqueta("Columna3");

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

 
    // imprimo la fila segun la etiqueta elegida ------------------------------------------------

    String etiquetaFila3 = "2";
    
    //Fila FilaNombre = df2.getFilaPorEtiqueta(etiquetaFila);
    Fila FilaNombre = df.getFilaPorEtiqueta(etiquetaFila3);

    if (FilaNombre != null) {

        int cantidadDatos = FilaNombre.getCantDatos(); // obtengo la cantidad de datos de la fila

        System.out.println("Etiqueta de la Fila seleccionada: " + etiquetaFila3);

        System.out.println("Cantidad de Datos en la Fila " + etiquetaFila3 + ": " + cantidadDatos);

        System.out.println("Datos de la Fila "+ etiquetaFila3 + ":");

        for (int i = 0; i < df2.getNroColumnas(); i++) {

            Object dato4 = FilaNombre.getDato(i);

            System.out.print(dato4+ " ");
        }
    } else {

        System.out.println("La fila con etiqueta " + etiquetaFila3 + " no existe en el DataFrame.");
    }

    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");

     // Imprime: Cantidad de Filas, Cantidad de Columnas, Etiqueta a las Filas 
    // y columnas y muestra el tipo de datos de las columnas. 
    /*
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
    } */

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 5: obtener las etiquetas de las filas y columnas

    // Como usuario, quiero poder obtener las etiquetas de las filas y columnas en el dataset.

    df.imprimirEtiquetasFilas();

    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");

    System.out.println("Las etiquetas de las columnas son: " + df.getAllHeaderColumn());

    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");

    //--------------------------------------------------------------------------------------

    // Adicional - acceso indexado a nivel de fila y columna

    // Acceder una fila completa si se selecciona la etiqueta de la fila

    String etiquetaFila4 = "3";

    Fila fila = df.getFilaPorEtiqueta(etiquetaFila4);

    System.out.println("Las fila indexada por la etiqueta " + etiquetaFila4 + " contiene los datos " + fila);

    System.out.println(" ");
    System.out.println("#--------------------------------------------------------------------------");

    // Acceder una columna completa si se selecciona la etiqueta de la columna

    String etiquetaColumna1 = "Columna3";

    Columna columna = df.getColumnaPorEtiqueta(etiquetaColumna1);

    System.out.println("Las fila indexada por la etiqueta: " + etiquetaColumna1 + " contiene los datos " + columna);
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 6: obtener los tipos de datos de las columnas

    // Como usuario, quiero poder obtener los tipos de datos de las columnas en el dataset


    // YA IMPLEMENTADO EN USER STORIE 4

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 7: acceder a los datos de la estructura tabular

    // Como usuario, quiero poder acceder a los datos de la estructura tabular a través de un índice de fila y columna.

    //-----------------------------------------------------------------------------------------------------------------
    
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
    System.out.println("---------------------------------------------------------------------");
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ****************************************************************** METODOS DE ACCESO ****************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 8: GENERAR UNA VISTA REDUCIDA (SLICING)  

    // Como usuario, quiero la capacidad de seleccionar una vista reducida de la estructura tabular a 
    // través de una lista de etiquetas de índice (slicing).

    //-----------------------------------------------------------------------------------------------------
    // GENERAR UNA VISTA REDUCIDA (SLICING) - CORREGIR EL CODIGO
    //-----------------------------------------------------------------------------------------------------
     
    System.out.println("Impresión de una vista reducida del DataFrame (slicing)");
    System.out.println("---------------------------------------------------------------------");
    
    DataFrame df6 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    
    // Lista de etiquetas de filas a seleccionar

    List<String> etiquetasFilas = Arrays.asList("1", "3", "5");

    // Lista de etiquetas de columnas a seleccionar

    List<String> etiquetasColumnas = Arrays.asList("Columna1", "Columna4");

    // Llamada al método para seleccionar la vista reducida

    DataFrame vistaRed = df6.seleccionarVista(etiquetasFilas, etiquetasColumnas);

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
    // CASOS ESPECIALES DE VISTA REDUCIDA DEL DF - 

    // METODO PARA IMPRIMIR LAS PRIMERAS Y ULTIMAS FILAS

    System.out.println("Vistas especiales: primeras y ultimas filas");

    System.out.println("Informacion de las primeras filas del DataFrame (head)");

    CsvPrinter.head(df, 2);

    System.out.println("Informacion de las ultimas filas del DataFrame (tail)");

    CsvPrinter.tail(df, 2);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 9: filtro aplicado a los valores de las celdas

    // Como usuario, quiero poder seleccionar una parte de la estructura tabular utilizando un 
    // filtro aplicado a los valores de las celdas (query).


    // CODIGO A IMPLEMETAR
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 10: operaciones sobre él DF

    // Como usuario, quiero poder importar un archivo CSV y permitir que el sistema realice 
    // operaciones sobre él sin errores de lectura.

    
    // NO SE IMPLEMENTA 

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 11: exportar los datos de la tabla a otros formatos

    // Como usuario, quiero la opción de exportar los datos de la tabla a otros formatos, como CSV

    try {

        CsvExport.exportarComoCSV(df2, "C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\archivo.csv");

        System.out.println("Tu nuevo DataFrame se guardó correctamente en la ruta de archivo seleccionada");

    } catch (Exception e) {

        System.out.println("Ocurrió un error al exportar el archivo CSV: " + e.getMessage());

        }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ********************************************************  METODOS DE VISUALIZACION  ******************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 12: 
    
    // Como usuario, quiero ver los datos en forma de tabla para comprender la información de manera clara y concisa.

    CsvPrinter.imprimirPorFilas(df);

    CsvPrinter.imprimirColumnar(df);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 13:

    // Como usuario, quiero que la tabla sea configurable para mostrar las columnas relevantes.


    // CODIGO A IMPLEMETAR
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

     ////  USER STORY 14: ordenar los datos en la tabla según una columna específica

    // Como usuario, quiero poder ordenar los datos en la tabla según una columna específica.


    // METODO IMPLEMENTADO ?

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 15: buscar y filtrar datos en la tabla 

    // Como usuario, quiero la capacidad de buscar y filtrar datos en la tabla para encontrar información específica.


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



    // FILTRADO CODIGO A IMPLEMETAR EN USER STORY 9

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // **********************************************************  GESTION DE DATOS  **********************************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // ////  USER STORY 16: Acceder a una celda t setear nuevo valor  

    // Como usuario, quiero poder acceder directamente a una celda y asignar un nuevo valor para actualizar los datos.


    // Seteo un valor en el DataFrame definiendo las etiquetas Fila y Columna y asignando un nuevo valor
    System.out.println("---------------------------------------------------------------------");
    System.out.println("Seteo de un valor en el DataFrame definiendo las etiquetas Fila y Columna y se asigna un nuevo valor");
    System.out.println("");

    String etiquetaFila2 = "3"; // posición en Fila

    String etiquetaColumna2 = "Columna2"; // posición en Columna

    df.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 0);
    //df2.setValorDataFrame(etiquetaFila2, etiquetaColumna2, 0);


    // IMPRIMO EL DF ORIGINAL CON VALOR SETEADO Y LA COPIA PROFUNDA

    //CsvPrinter.imprimirColumnar(df2);

    System.out.println("Impresión del DataFrame");
    CsvPrinter.imprimirColumnar(df);

    System.out.println("Impresión de la copia profunda del DataFrame");

    CsvPrinter.imprimirColumnar(copiadf1);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////  USER STORY 17: insertar una nueva columna a partir de otra columna existente

    // Como usuario, quiero poder insertar una nueva columna a partir de otra columna existente.

    System.out.println("Impresión del DataFrame");

    CsvPrinter.imprimirColumnar(df);

    System.out.println("Agregar una columna ya existente del DataFrame");

    String etiquetaColumnaExistente = "Columna3";

    String etiquetaNuevaColumna = "Columna5";

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

    ////  USER STORY 18: 

    // Como usuario, quiero que las celdas con datos faltantes sean identificadas y marcadas con un formato "NA".

  
    // METODO IMPLEMENTADO ?

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // USER STORY 19: 

    // Como usuario, quiero que el sistema realice operaciones sin errores incluso cuando haya datos faltantes marcados como "NA".


    // METODO A IMPLEMENTAR

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // *******************************************  GESTION DEL DATASET  *******************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // USER STORY 20: insertar una nueva columna en el dataset

    // Como usuario, quiero poder insertar una nueva columna en el dataset utilizando una secuencia lineal nativa de Java.


    // METODO PARA AGREGAR UNA NUEVA COLUMNA DEL DATAFRAME

    System.out.println("Agregar una columna Nueva al DataFrame");
    
    String etiquetaColumnaNueva = "Columna5";
     
    DataFrame df5 = new DataFrame("C:\\Users\\Hernan\\Desktop\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    
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
    
    System.out.println("Impresión del DataFrame");
    CsvPrinter.imprimirColumnar(df5);


    // METODO PARA ELIMINAR UNA FILA DEL DATAFRAME
    System.out.println("Eliminar una Fila del DataFrame");
    df5.eliminarFila("4");
    CsvPrinter.imprimirColumnar(df5);

    //---------------------------------------------

    // METODO PARA ELIMINAR UNA COLUMNA DEL DATAFRAME

    System.out.println("#-------------------------------------------------");
    System.out.println("Eliminar una columna específica del dataset");
    System.out.println(" "); 

    System.out.println("Impresión del DataFrame");
    CsvPrinter.imprimirColumnar(df5);

    //CsvPrinter.imprimirColumnar(df);

    System.out.println("Eliminar una columna del DataFrame");
    df5.eliminarColumna("Columna2");
    CsvPrinter.imprimirColumnar(df5);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // USER STORY 22: ordenar las filas del dataset según un criterio
    
    // Como usuario, quiero poder ordenar las filas del dataset según un criterio, 
    // ya sea ascendente o descendente, en una o más columnas.


    // METODO IMPLEMENTADO ?

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //// USER STORY 20: agrupamiento o groupby - METODO A IMPLEMENTAR   ////

    // Como usuario, quiero la capacidad de dividir las filas en diferentes grupos (agrupamiento o groupby) 
    // según una o más columnas. Luego, quiero aplicar una operación de sumarización estadística a estos grupos.


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

