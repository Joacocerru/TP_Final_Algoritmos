package TP_Integrador_tmp;

// IMPRESION DF POR FILAS ---------------------------------------------------------------------------    
// 'header' contiene el encabezado y 'data' contiene los datos del archivo CSV

public class CsvPrinter {
    
    public static void imprimirPorFilas(DataFrame df) {
        if (df.isEmpty()) {
            System.out.println("No hay datos para imprimir en la visión por filas.");
            return;
            }
            
            // Imprimir el encabezado
            System.out.println("#-----------------------------------------------------------------------------");
            System.out.println("# Impresión por Filas");
            System.out.println("#-----------------------------------------------------------------------------");

            for (String fieldName: df.getHeader()) {
                System.out.print("\t");
                System.out.print(fieldName);
            }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------");
    
            // Imprimir los datos
            //for (int i=0; i < df.getNroRegistros(); i++){

            for (String etiqueta : df.rowMap.keySet()) {
                System.out.print(etiqueta +" |"+"\t"); // Imprimir etiqueta de fila    
                
                Fila fila = df.rowMap.get(etiqueta); // Obtener la fila correspondiente
                
                for (int c = 0; c < df.getNroColumnas(); c++) {

                    System.out.print(fila.getDato(c) +"\t");
                }
                System.out.println();
                
                /* 
                Dato[] fila = df.getFila(i);
            
                for (Dato dato : fila) 
                {
                    System.out.print(dato.printValor() + "\t");
                }
                System.out.println();
                */
            }          
            System.out.println("#-------------------------------------------------------------------------------");
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

        for (int f = 0; f < numRows; f++) {
            
            if (f == 0) {
                for (int c = 0; c < df.getNroColumnas(); c++) {
                    System.out.print(""+"\t");
                    System.out.print("Col " + (c+1) + ":");
                }
                System.out.println(""); 
            }  

                Fila fila = df.dataFilas.get(f); // Accede a la fila directamente
                Integer etiqueta = fila.getEtiqueta();
                System.out.print(etiqueta + " |"+"\t"); // Imprimir el índice de fila y la etiqueta

            for (int c = 0; c < df.getNroColumnas() ; c++) {   

                //Object[] columna = dataColumnar.get(c);
                System.out.print( df.getValor(f, c).printValor() );
                System.out.print("\t");
                }   
            System.out.println();
        }
        System.out.println("#--------------------------------------------------------------------------");
    }

    public static void info(DataFrame df) {
        System.out.println("#--------------------------------------------------------------------------");
        System.out.println("# Informacion del dataframe");
        System.out.println(" ");
        System.out.print("Cantidad de filas: " + (df.getNroRegistros()) );
        System.out.println();

        System.out.print("Cantidad de columnas: " + (df.getNroColumnas()) );
        System.out.println();
        
        System.out.print("Etiquetas de las columnas: " );
        for (String fieldName : df.getHeader()) {
            System.out.print(fieldName + "\t");
        }
        System.out.println();
        
        System.out.print("Tipo de datos de las columnas: " );
        
        for (int i= 0; i < df.getNroColumnas(); i++)
        {
            System.out.print(df.getColumnaPorIndice(i).getTipoDato() + "\t");
        }
        System.out.println();

    }

    public static void ImprimirFila (Object[] fila)
    {
        for (int c = 0; c < fila.length ; c++) {   
            System.out.print(fila[c]);
            System.out.print("\t");
            }
        
        System.out.println("");
    }

    public static void ImprimirColumna (Object[] columna)
    {
        for (int c = 0; c < columna.length ; c++) {   
            System.out.print(columna[c]);
            System.out.print("\t");
            }
        System.out.println("");
    }
}


