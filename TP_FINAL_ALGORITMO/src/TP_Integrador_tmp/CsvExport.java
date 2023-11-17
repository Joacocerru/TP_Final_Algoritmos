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
            int columnCount = df.getNroColumnas();
            int currentColumn = 0;
            for (String fieldName : df.getAllHeaderColumn()) {
                writer.append(fieldName);
                currentColumn++;
                if (currentColumn < columnCount) {
                    writer.append(",");
                }
            }
            
            writer.append("\n");

            // Escribir los datos
            for (String etiqueta : df.rowMap.keySet()) {
                Fila fila = df.rowMap.get(etiqueta); // Obtener la fila correspondiente

                currentColumn = 0;
                for (int c = 0; c < columnCount; c++) {
                    writer.append(fila.getDato(c).toString());
                    currentColumn++;
                    if (currentColumn < columnCount) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}