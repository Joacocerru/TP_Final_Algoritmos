package TP_Integrador_tmp;

import java.util.Arrays;
import java.util.List;

public class Presentacion {
    public static void main(String[] args) {
    

    // USER STORY 1: CARGAR DATOS DESDE ARCHIVO .CSV   //
    DataFrame df = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");
    DataFrame iris = new DataFrame("C:\\Users\\Valentín\\OneDrive\\ESTUDIOS\\UNSAM\\ALGORITMOS\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\iris.csv", ",", "S");


    CsvPrinter.imprimirColumnar(df);
    CsvPrinter.imprimirColumnar(df2);
    CsvPrinter.imprimirColumnar(iris);

    
    CsvPrinter.info(df);                   
    CsvPrinter.info(df2); 
    CsvPrinter.info(iris); 

        


































    }
}