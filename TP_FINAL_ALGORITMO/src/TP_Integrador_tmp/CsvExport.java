package TP_Integrador_tmp;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvExport extends CsvPrinter {

    public static void exportarComoCSV(DataFrame df, String rutaArchivo) {

        if (df.isEmpty()) { // Verifica si el df esta vacio
            System.out.println("No hay datos dentro del DataFrame para exportar.");
           return;
        }

        Path path = Paths.get(rutaArchivo); // Verifica si la rutaArchivo es valida
        if (!Files.exists(path)) {
            System.out.println("La ruta de archivo no es válida.");
            return;
        } else {
            try (FileWriter writer = new FileWriter(rutaArchivo)) { // Usamos FileWriter
           
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

            System.out.println("Tu nuevo DataFrame se guardó correctamente en la ruta de archivo seleccionada");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        
    }
}