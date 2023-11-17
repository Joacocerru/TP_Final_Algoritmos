package TP_Integrador_tmp;

import java.io.FileWriter;
import java.io.IOException;

public class CsvExport extends CsvPrinter {

    public static void exportarComoCSV(DataFrame df, String rutaArchivo) {

        if (df.isEmpty()) {
            System.out.println("No hay datos para exportar.");
           return;
        }

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
           
            // Escribir el encabezado
            for (String fieldName : df.getAllHeaderColumn()) {
                writer.append(fieldName);
                writer.append(","); // ### El error del export esta acá. Tendía que detectar que hay una última letra y a esa no agregarle coma. Se podría utilizar if o while
            }
            
            writer.append("\n");

            // Escribir los datos
            for (String etiqueta : df.rowMap.keySet()) {
                Fila fila = df.rowMap.get(etiqueta); // Obtener la fila correspondiente

                for (int c = 0; c < df.getNroColumnas(); c++) {
                    writer.append(fila.getDato(c).toString());
                    writer.append(","); // ### El error del export esta acá. Tendía que detectar que hay una última letra y a esa no agregarle coma. Se podría utilizar if o while
                }
                writer.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}