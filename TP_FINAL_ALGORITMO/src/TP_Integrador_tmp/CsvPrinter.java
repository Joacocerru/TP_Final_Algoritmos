package TP_Integrador_tmp;

import java.util.List;

    // IMPRESION DF POR FILAS ---------------------------------------------------------------------------    
    // 'header' contiene el encabezado y 'data' contiene los datos del archivo CSV

public class CsvPrinter {
    
    public static void imprimirPorFilas (DataFrame df){

        imprimirPorFilasGral(df, df.getNroRegistros(), "A");
    }
    public static void imprimirPorFilasGral(DataFrame df, Integer tope, String Orden){
    
        if (df.isEmpty()) {
            System.out.println("No hay datos para imprimir en la visión por filas.");
            return;
        }
    
        if (tope > df.getNroRegistros()) {
            System.out.println("Error: La cantidad de filas a imprimir es mayor que la cantidad total de filas en el DataFrame.");
            return;
        }
    
        // Imprimir el encabezado
        
        System.out.print("   ");
    
        for (String fieldName : df.getAllHeaderColumn()) {
            System.out.print(String.format("%-" + (fieldName.length() + 2) + "s", fieldName));
        }
    
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
    
        // Imprimir los datos
        Integer limite = 0;
        if (tope > df.getNroRegistros())
            limite = df.getNroRegistros();
        else
            limite = tope;
    
        if (Orden.equals("A")){
            for (int i = 0; i < limite; i++) {
                String etiqueta = df.getHeaderRows(i);
                System.out.print(etiqueta + " |" + " ");
                Fila fila = df.getFilaPorEtiqueta(etiqueta);
    
                for (int c = 0; c < df.getNroColumnas(); c++) {
                    String dato = fila.getDato(c).printValor();
                    String etiquetaColumna = df.getAllHeaderColumn().get(c);
                    int espacio = Math.max(etiquetaColumna.length(), dato.length()) + 3;
                    System.out.print(String.format("%-" + espacio + "s", dato));
                }
    
                System.out.println();
            }
    
            System.out.println("#-------------------------------------------------------------------------------");
        } else {
            limite = df.getNroRegistros() - tope;
    
            for (int i = limite; i < df.getNroRegistros(); i++) {
                String etiqueta = df.getHeaderRows(i);
                System.out.print(etiqueta + " |" + " ");
                Fila fila = df.getFilaPorEtiqueta(etiqueta);
    
                for (int c = 0; c < df.getNroColumnas(); c++) {
                    String dato = fila.getDato(c).printValor();
                    String etiquetaColumna = df.getAllHeaderColumn().get(c);
                    int espacio = Math.max(etiquetaColumna.length(), dato.length()) + 3;
                    System.out.print(String.format("%-" + espacio + "s", dato));
                }
    
                System.out.println();
            }
    
            System.out.println("#-------------------------------------------------------------------------------");
        }
    }

    public static void imprimirColumnar(DataFrame df) {
        if (df.isEmpty()) {
            System.out.println("No hay datos para imprimir en la visión columnar.");
            return;
        }
    
        int numRows = df.isEmpty() ? 0 : df.getNroRegistros(); // Número de filas
        //System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
    
        try {
            // Obtener la longitud máxima de cada columna
            int[] maxLengths = new int[df.getNroColumnas()];
            for (int c = 0; c < df.getNroColumnas(); c++) {
                int maxLength = df.getAllHeaderColumn().get(c).length();
                for (int f = 0; f < numRows; f++) {
                    Dato tmp = df.getValorPosicion(f, c);
                    String value = (tmp != null) ? tmp.printValor() : "Valor nulo";
                    maxLength = Math.max(maxLength, value.length());
                }
                maxLengths[c] = maxLength;
            }
    
            // IMPRIME HEADER
            for (int c = 0; c < df.getNroColumnas(); c++) {
                String fieldName = df.getAllHeaderColumn().get(c);
                if (c == 0){
                System.out.print("     ");
                }
                System.out.print(String.format("%-" + (maxLengths[c] + 1) + "s", fieldName));
                System.out.print("\t");
            }
    
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------");
    
            for (int f = 0; f < numRows; f++) // Recorre Filas
            {
                // ACCESO ORDERNADO A LAS FILAS
                Fila fila = df.rowMap.get(df.RowArray.get(f)); // Fila Segun orden Actual
    
                String etiqueta = fila.getEtiqueta();
                if (f < 10){
                System.out.print(String.format("%s  | ", etiqueta)); // Imprimir el índice de fila y la etiqueta
                }else{
                System.out.print(String.format("%s | ", etiqueta)); //
                }
                for (int c = 0; c < df.getNroColumnas(); c++) {
                    Dato tmp = df.getValorPosicion(f, c);
                    String value = (tmp != null) ? tmp.printValor() : "Valor nulo";
                    /* 
                    if (df.getAllHeaderColumn().get(c).equals("Worldchampion")) {
                        System.out.print("\t");
                        System.out.print(String.format("%-" + (maxLengths[c] + 1) + "s", value));
                        System.out.print("\t");
                    } else {
                        System.out.print(String.format("%-" + (maxLengths[c] + 1) + "s", value));
                        System.out.print("\t");
                    }*/
                    System.out.print(String.format("%-" + (maxLengths[c] + 1) + "s", value));
                    System.out.print("\t");
                }
    
                System.out.println();
            }
        } catch (Exception e) {
            // Manejar cualquier excepción
            System.out.println("");
            System.err.println("Ha ocurrido un error a la hora de ejecutar el método. Por favor corroborar que los parámetros ingresados sean correctos....");
            System.out.println("");
        }
    }
     
    public static void info(DataFrame df) {
        
        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   INFORMACION GENERAL SOBRE EL DATAFRAME");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");


        try {

            System.out.println("Cantidad de filas: " + (df.getNroRegistros()) );
            System.out.println("Cantidad de columnas: " + (df.getNroColumnas()) );

            df.getEtiquetasFilas(); // Etiquetas de las filas
            df.getEtiquetasColumnas(); // Etiquetas de las columnas
        
            System.out.print("Tipo de datos de las columnas: " );
            for (int i= 0; i < df.getNroColumnas(); i++){

                System.out.print(df.getColumna(i).getTipoDato() + "\t");
            }
            System.out.println(" ");
            System.out.print("Cantidad de dato NA: " + (df.CantidadNA()) );
            System.out.println();
        } 
        catch (Exception e) {
            // Manejar cualquier excepción 
            System.out.println("");
            System.err.println("Ha ocurrido un error" + e.getMessage() + "a la hora de ejecutar el método. Por favor corroborar que los parámetros ingresados sean correctos....");
            System.out.println("");
        }
    }

    //IMPRIMIR INFO DE COLUMNA ELEGIDA POR ETIQUETA
    public static void infoColumna(DataFrame df,  String etiquetaColumna){

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   INFORMACION SOBRE COLUMNA ELEGIDA");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");    

        try {

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
                    System.out.print(dato3.getDato()+", ");
                }

            } else {
                        String nombreColumna = df.getHeaderColumn(1);
                        System.out.println("La columna "+ nombreColumna + " no existe en el DataFrame. Corroborar valores ingresados.");
            }


        } catch (Exception e) {
            // Manejar cualquier excepción 
            System.out.println("");
            System.err.println("Ha ocurrido un error" + e.getMessage() + "a la hora de ejecutar el método. Por favor corroborar que los parámetros ingresados sean correctos....");
            System.out.println("");

        }


        System.out.println(" ");
    
    }

    //IMPRIMIR INFO DE FILA ELEGIDA POR ETIQUETA
    public static void infoFila(DataFrame df,  String etiquetaFila){ 

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   INFORMACION SOBRE FILA ELEGIDA");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        try {
        
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

        }
        catch (Exception e) {
            // Manejar cualquier excepción 
            System.out.println("");
            System.err.println("Ha ocurrido un error" + e.getMessage() + "a la hora de ejecutar el método. Por favor corroborar que los parámetros ingresados sean correctos....");
            System.out.println("");

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

        System.out.println(" ");

        System.out.println("Informacion de las primeras filas (head)");
        System.out.println("#-------------------------------------------------------");
        imprimirPorFilasGral(df, cant,"A");
    }

    public static void tail (DataFrame df, Integer cant){

        System.out.println(" ");
        System.out.println("#-------------------------------------------------------");
        System.out.println("Informacion de las ultimas filas (tail)");
        
        imprimirPorFilasGral(df, cant,"D");
    }


    //--------------------------------------------------------------
    // METODO PARA IMPRIMIR VISUALIZACION REDUCIDA    

    public static void imprimirVistaReducida(DataFrame df, List<String> etiquetasFilas, List<String> etiquetasColumnas) {
        
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Impresión por pantalla de los datos dadas las etiquetas fila y columna");
        System.out.println("------------------------------------------------------------------------------------");
 
    try {
        // Obtener la longitud máxima de cada columna
        int[] maxLengths = new int[etiquetasColumnas.size()];
        for (int c = 0; c < etiquetasColumnas.size(); c++) {
            int maxLength = etiquetasColumnas.get(c).length();
            for (String etiquetaFila : etiquetasFilas) {
                Dato tmp = df.getValor(etiquetaFila, etiquetasColumnas.get(c));
                String value = (tmp != null) ? tmp.printValor() : "Valor nulo";
                maxLength = Math.max(maxLength, value.length());
            }
            maxLengths[c] = maxLength;
        }

        // Imprimir encabezados de columnas
        for (int c = 0; c < etiquetasColumnas.size(); c++) {
            String etiquetaColumna = etiquetasColumnas.get(c);
            System.out.print(String.format("%-" + (maxLengths[c] + 1) + "s", "    " + etiquetaColumna));
        }
        
        System.out.println(); // Agrega un salto de línea después de imprimir las etiquetas de las columnas

        // Imprimir filas
        for (String etiquetaFila : etiquetasFilas) {
            System.out.print(String.format("%s |", etiquetaFila));

            for (int c = 0; c < etiquetasColumnas.size(); c++) {
                Dato valor = df.getValor(etiquetaFila, etiquetasColumnas.get(c));
                String value = (valor != null) ? valor.printValor() : "Valor nulo";
                System.out.print(String.format("%-" + (maxLengths[c] + 1) + "s", value));
                System.out.print("\t");
            }

            System.out.println();
        }

        System.out.println("------------------------------------------------------------------------------------");

    } catch (Exception e) {
        // Manejar cualquier excepción 
        System.out.println("");
        System.err.println("Ha ocurrido un error" + e.getMessage() + "a la hora de ejecutar el método. Por favor corroborar que los parámetros ingresados sean correctos....");
        System.out.println("");
    }
}

}


