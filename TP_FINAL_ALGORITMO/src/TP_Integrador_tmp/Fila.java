package TP_Integrador_tmp;

public class Fila implements Comparable
{
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

    public int compareTo(Object o) 
    {
       int total = this.rowData.length;
       int i = 0;
       Fila x = (Fila) o;

        do 
        {
            Dato datoX = this.rowData[0];
            Dato datoY = x.rowData[0];
            int tmpCompare = datoX.compareTo(datoY);

            if ( tmpCompare == 0)
                i++;
            else
                return this.rowData[0].compareTo( x.rowData[0] );
        } while (i < total);

        return 0;
    }
}
//-----------------------------------------------------------------------