package TP_Integrador_tmp;

public class Fila {
    //private Object[] rowData; // Almacena los datos de la fila
    private Dato[] rowData; // Almacena los datos de la fila
    private String etiqueta; // Etiqueta de la fila

    public Fila(String etiqueta, Dato[] rowData) {
        this.rowData = rowData;
        this.etiqueta = etiqueta;
    }

    public Dato getDato(int columna) 
    {
        if (columna >= 0 && columna < rowData.length) 
            return rowData[columna];
        else 
            return null; // Manejar el caso en que la columna esté fuera de rango
    }
    
    public int getCantDatos() {
        return rowData.length; // Devuelve la cantidad de datos en la fila
    }
    public String getEtiqueta() {
        return etiqueta;
    }
}
//-----------------------------------------------------------------------