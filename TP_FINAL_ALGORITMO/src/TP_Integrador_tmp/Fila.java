package TP_Integrador_tmp;

public class Fila implements Comparable{

    //private Object[] rowData; // Almacena los datos de la fila
    private Dato[] rowData; // Almacena los datos de la fila
    private String etiqueta; // Etiqueta de la fila

    public void setDato (Dato nuevoDato)
    {
        Dato[] NewrowData = new Dato[this.rowData.length+1];

        for (int i=0; i< this.rowData.length; i++)
            NewrowData[i] = this.rowData[i];
        
        NewrowData[this.rowData.length] = nuevoDato;
        
        this.rowData = NewrowData;
    }

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
            Dato datoX = this.rowData[i];
            Dato datoY = x.rowData[i];
            int tmpCompare = datoX.compareTo(datoY);

            if ( tmpCompare == 0)
                i++;
            else
                return tmpCompare ;
        } while (i < total);

        return 0;
    }

    public void removeColumna (int columna)
    {   
        Dato[] tmpRowData = new Dato[ this.getCantDatos()-1];

        for(int i=0, k=0; i<this.getCantDatos(); i++)
        {
            if(i!= columna)
            {
                tmpRowData[k] = this.rowData[i];
                k++;
            }
        }
        this.rowData = tmpRowData;
    }
}
