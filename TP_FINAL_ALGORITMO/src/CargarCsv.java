//package TP_FINAL_ALGORITMO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;

public class CargarCsv {

public static void cargarDatosDesdeCsvConHead(List<String> header, List<String[]> data, String csvFile, String csvDelimiter) 
{
 
    String line;

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        // Leer el encabezado (primera línea)
        if ((line = br.readLine()) != null) 
        {    
            // Dividir el encabezado en campos utilizando el delimitador
            String[] headerFields = line.split(csvDelimiter);
            header.clear(); // Limpia el encabezado actual
            for (String field : headerFields) 
            {
                header.add(field); // Agregar cada campo al encabezado
            }
        }
        

        // Leer el resto de las líneas de datos
        data.clear(); // Limpia los datos actuales
        while ((line = br.readLine()) != null) 
        {
            
            int cantComas = countOccurrences (line, ',');
            
            // Dividir la línea en campos utilizando el delimitador
            String[] fields = line.split(csvDelimiter);
            String[] fieldsCompleto = new String[cantComas+1];
            
            if (fields.length < (cantComas+1) )
            {
                for (int i=0; i < fields.length; i++)
                {
                    fieldsCompleto[i] = fields[i];
                }

                for (int x=fields.length; x < (cantComas+1); x++)
                {
                    fieldsCompleto[x] = "";
                }
            }
            else
            {
                fieldsCompleto = fields;    
            }
           
            data.add(fieldsCompleto); // Agregar los campos a la lista de datos
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    public static void cargarDatosDesdeCsvSinHead(List<String> header,List<String[]> data, String csvFile, String csvDelimiter) 
{
    
    String line;

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
    {
        // Leer el resto de las líneas de datos
        data.clear(); // Limpia los datos actuales
        while ((line = br.readLine()) != null) 
        {
            
            int cantComas = countOccurrences (line, ',');
            
            // Dividir la línea en campos utilizando el delimitador
            String[] fields = line.split(csvDelimiter);
            String[] fieldsCompleto = new String[cantComas+1];
            
            if (fields.length < (cantComas+1) )
            {
                for (int i=0; i < fields.length; i++)
                {
                    fieldsCompleto[i] = fields[i];
                }

                for (int x=fields.length; x < (cantComas+1); x++)
                {
                    fieldsCompleto[x] = "";
                }
            }
            else
            {
                fieldsCompleto = fields;    
            }
           
            data.add(fieldsCompleto); // Agregar los campos a la lista de datos
        }

        String[] nombColumna = {"A"	,"B" ,"C" ,"D" ,"E" ,"F" ,"G" ,"H" ,
        "I" ,"J" ,"K" ,"L" ,"LL","M" ,"N" ,"O" ,
        "P" ,"Q" ,"R" ,"S" ,"T" ,"U" ,"V" ,"W" ,
        "X" ,"Y" ,"Z"};
        
        for (int i=0; i < (data.get(0).length); i++)
        {   
            String field = nombColumna[i];
            header.add(field); // Agregar cada campo al encabezado
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private static int countOccurrences(String str, char ch) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }
     
        return counter;

    }
    

}
        
    


