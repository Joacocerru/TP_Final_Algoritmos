package TP_Integrador_tmp;

import java.util.List;

    // IMPRESION DF POR FILAS ---------------------------------------------------------------------------    
    // 'header' contiene el encabezado y 'data' contiene los datos del archivo CSV

public class CsvPrinter {
    
    public static void imprimirPorFilas (DataFrame df){

        imprimirPorFilasGral(df, df.getNroRegistros(), "A");
    }

    private static void imprimirPorFilasGral(DataFrame df, Integer tope, String Orden){ // Orden -> A (Ascend) D (Desc)
        
        if (df.isEmpty()) {
            System.out.println("No hay datos para imprimir en la visión por filas.");
            return;
        }


        if (tope > df.getNroRegistros()) {
            System.out.println("Error: La cantidad de filas a imprimir es mayor que la cantidad total de filas en el DataFrame.");
            return;
        }

        // Imprimir el encabezado
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("# Impresión por Filas");
        System.out.println("#-----------------------------------------------------------------------------");

        
        for (String fieldName: df.getAllHeaderColumn()) {
            System.out.print("\t");
            System.out.print(fieldName);
        }
        

        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
    
        
        // Imprimir los datos
            
        Integer limite = 0;
        if (tope > df.getNroRegistros())
            limite = df.getNroRegistros();
        else
            limite = tope;

        if (Orden == "A"){
            
            for (int i=0; i < limite; i++){
                //for (String etiqueta : df.getAllHeaderRows())
                String etiqueta = df.getHeaderRows(i); 
                System.out.print(etiqueta +" |"+"\t"); // Imprimir etiqueta de fila       
                Fila fila = df.getFilaPorEtiqueta(etiqueta); // Obtener la fila correspondiente
                
                
                for (int c = 0; c < df.getNroColumnas(); c++) {
                    System.out.print(fila.getDato(c) +"\t"+"\t");
                }
                

                System.out.println();
            }          
        

            System.out.println("#-------------------------------------------------------------------------------");

        }
        else{
            limite = df.getNroRegistros()-tope;
                

            for (int i=limite; i < df.getNroRegistros(); i++){
                //for (String etiqueta : df.getAllHeaderRows()) 
                String etiqueta = df.getHeaderRows(i); 
                System.out.print(etiqueta +" |"+"\t"); // Imprimir etiqueta de fila       
                Fila fila = df.getFilaPorEtiqueta(etiqueta); // Obtener la fila correspondiente  
                
                
                for (int c = 0; c < df.getNroColumnas(); c++) {
                    System.out.print(fila.getDato(c) +"\t");
                }

                System.out.println();

            }

            System.out.println("#-------------------------------------------------------------------------------");

        }
    }

    // IMPRESION DF POR COLUMNAS -  Método para imprimir la visión columna --------------------


    public static void imprimirColumnar(DataFrame df) {
        if (df.isEmpty()) {
            System.out.println("No hay datos para imprimir en la visión columnar.");
            return;
        }

        int numRows = df.isEmpty() ? 0 : df.getNroRegistros(); // Número de filas
    
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("# Impresión por Columnas");
        System.out.println("#-----------------------------------------------------------------------------");
        
        // IMPRIME HEADER
        for (String fieldName: df.getAllHeaderColumn()) {
            
            System.out.print("\t");
            System.out.print(fieldName);
        
        }
        
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");

        for (int f = 0; f < numRows; f++) // Recorre Filas 
        {    
            if (f == 0) 
            {
                for (int c = 0; c < df.getNroColumnas(); c++) {
                    System.out.print(""+"\t");
                    //System.out.print("Col " + (c) + ":");
                }
                System.out.println(""); 
            } 

            // ACCESO ORDERNADO A LAS FILAS
            Fila fila = df.rowMap.get( df.RowArray.get(f) ); // Fila Segun orden Actual
                //Fila fila = df.dataFilas.get(f); // Accede a la fila directamente
            String etiqueta = fila.getEtiqueta();

            System.out.print(etiqueta + " |"+"\t"); // Imprimir el índice de fila y la etiqueta

            for (int c = 0; c < df.getNroColumnas() ; c++) 
            {   
                //String tmpEtiCol = df.ColumnArray.get(c);
                Dato tmp = df.getValorPosicion(f, c);
                System.out.print( tmp.printValor() );
                System.out.print("\t"+"\t");
            } 

            System.out.println();

        }

        System.out.println("#--------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("");
    }

    public static void info(DataFrame df) {
        
        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   INFORMACIÓN GENERAL SOBRE EL DATAFRAME");
        System.out.println("#-----------------------------------------------------------------------------");
        

        System.out.print("Cantidad de filas: " + (df.getNroRegistros()) );
        System.out.print("Cantidad de columnas: " + (df.getNroColumnas()) );
        df.getEtiquetasFilas();

        System.out.print("Etiquetas de las columnas: " );
        for (String fieldName : df.getAllHeaderColumn()) {
            System.out.print(fieldName + "\t");
        }

        System.out.println();
        System.out.print("Tipo de datos de las columnas: " );
        for (int i= 0; i < df.getNroColumnas(); i++){

            System.out.print(df.getColumna(i).getTipoDato() + "\t");
        }
        System.out.println(" ");
        System.out.print("Cantidad de dato NA: " + (df.CantidadNA()) );
        System.out.println();
    }

    //IMPRIMIR INFO DE COLUMNA ELEGIDA POR ETIQUETA
    public static void infoColumna(DataFrame df,  String etiquetaColumna){

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   INFORMACIÓN SOBRE COLUMNA ELEGIDA");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");    

        Columna columnaNombre = df.getColumnaPorEtiqueta( etiquetaColumna);
    
        if (columnaNombre != null) {
    
            String nombreColumna = columnaNombre.getEtiqueta(); // obtengo etiqueta de la columna 
            int cantidadDatos = columnaNombre.getCantDatos(); // obtengo la cantidad de datos de la columna  
            String tipoDato = columnaNombre.getTipoDato(); // obtengo el tipo de dato de la columna 
             
            System.out.println("Etiqueta de la Columna selecionada: " + nombreColumna);
            System.out.println("Tipo de Dato de la Columna '" + nombreColumna + "': " + tipoDato);
            System.out.println("Cantidad de Datos en la Columna '" + nombreColumna + "': " + cantidadDatos);
            System.out.println("Datos de la Columna '"+ nombreColumna + "':");
    
            for (int i = 0; i < cantidadDatos; i++) {
                Dato dato3 = columnaNombre.getDato(i);
                System.out.println(dato3.getDato());
            }
        } else {
                    String nombreColumna = df.getHeaderColumn(1);
                    System.out.println("La columna "+ nombreColumna + " no existe en el DataFrame. Corroborar valores ingresados.");
        }

        System.out.println(" ");
    
    }

    //IMPRIMIR INFO DE FILA ELEGIDA POR ETIQUETA
    public static void infoFila(DataFrame df,  String etiquetaFila){ 

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   INFORMACIÓN SOBRE FILA ELEGIDA");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");
        
        Fila FilaNombre = df.getFilaPorEtiqueta(etiquetaFila);

        if (FilaNombre != null) {

            int cantidadDatos = FilaNombre.getCantDatos(); // obtengo la cantidad de datos de la fila

            System.out.println("Etiqueta de la Fila seleccionada: " + etiquetaFila);
            System.out.println("Cantidad de Datos en la 'Fila " + etiquetaFila + "': " + cantidadDatos);
            System.out.println("Datos de la 'Fila "+ etiquetaFila + "':");     /// ###2  DA UN RESULTADO QUE NO DEBERIA 

            for (int i = 0; i < df.getNroColumnas(); i++) {

                Object dato = FilaNombre.getDato(i);
                System.out.print(dato+ " ");
            }
        } else {

            System.out.println("La fila con etiqueta '" + etiquetaFila + "' no existe en el DataFrame. Corrobore los datos ingresados.");
        }

        System.out.println(" ");


    }


    public static void ImprimirFila (Object[] fila){

        for (int c = 0; c < fila.length ; c++) {   
            System.out.print(fila[c]);
            System.out.print("\t");
        }
        
        System.out.println("");
    }

    public static void ImprimirColumna (Object[] columna){

        for (int c = 0; c < columna.length ; c++) {   
            System.out.print(columna[c]);
            System.out.print("\t");
        }

        System.out.println("");
    }

    public static void head (DataFrame df, Integer cant){

        imprimirPorFilasGral(df, cant,"A");
    }

    public static void tail (DataFrame df, Integer cant){
        
        imprimirPorFilasGral(df, cant,"D");
    }


    //--------------------------------------------------------------
    // METODO PARA IMPRIMIR VISUALIZACION REDUCIDA    

    public static void imprimirVistaReducida(DataFrame df, List<String> etiquetasFilas, List<String> etiquetasColumnas) {
        
        // Imprimir encabezados de columnas
        for (String etiquetaColumna : etiquetasColumnas) {
            System.out.print("\t"+etiquetaColumna + " ");
        }

        System.out.println();

        // Imprimir filas
        for (String etiquetaFila : etiquetasFilas) {
            System.out.print(etiquetaFila + " |"+"\t");

            for (String etiquetaColumna : etiquetasColumnas) {
                Dato valor = df.getValor(etiquetaFila, etiquetaColumna);
                System.out.print(valor + "\t"+ "\t");
            }

            System.out.println();

        }
    }

}


