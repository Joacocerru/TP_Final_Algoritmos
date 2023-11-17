package TP_Integrador_tmp;
import java.lang.Cloneable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fila implements Comparable,Cloneable {

    //private Object[] rowData; // Almacena los datos de la fila
    private Dato[] rowData; // Almacena los datos de la fila
    private String etiqueta; // Etiqueta de la fila
    private DataFrame dataFrame;

    public Fila(String etiqueta, Dato[] rowData) {
        this.rowData = rowData;
        this.etiqueta = etiqueta;
    }

    // Constructor de la clase Fila que acepta solo la etiqueta
    public Fila(String etiqueta) {
        this.etiqueta = etiqueta;
        this.rowData = new Dato[0];  
    }

    public Fila(String etiqueta, Dato[] rowData, DataFrame dataFrame) {
        this.etiqueta = etiqueta;
        this.rowData = rowData;
        this.dataFrame = dataFrame;
    }


    public void setDato (Dato nuevoDato)
    {
        Dato[] NewrowData = new Dato[this.rowData.length+1];

        for (int i=0; i< this.rowData.length; i++)
            NewrowData[i] = this.rowData[i];
        
        NewrowData[this.rowData.length] = nuevoDato;
        
        this.rowData = NewrowData;
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
//----------------------------------------------------------------------------------------------
// Implementación del método clone 
@Override
public Fila clone() 
{        
    //private Dato[] rowData; // Almacena los datos de la fila
    //private String etiqueta; // Etiqueta de la fila
    
    try {  
        // Clona la estructura principal (Columna)
        Fila copia = (Fila) super.clone();
        
        // Realiza una copia profunda de la lista de datos (arreglo)
        if (rowData != null) 
        {
            copia.rowData = new Dato[rowData.length];

            for (int i = 0; i < rowData.length; i++)
            {
                if (rowData[i] instanceof Cloneable) 
                {
                    copia.rowData[i] = (Dato) rowData[i].clone();

                } else 
                {
                    // Maneja el caso en el que el dato no es clonable (ajusta esto según tus necesidades)
                    copia.rowData[i] = rowData[i]; // Si no es clonable, agrega la referencia directa
                }
            }
        }
           
        return copia;

        } 
        catch (CloneNotSupportedException e) 
        {    
            throw new AssertionError("La clonación no es compatible");
        }   

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
    
    
    public void agregarDatoAlFinal(Dato nuevoDato) 
    {
    	// Crear una nueva lista que contenga los datos existentes
    	List<Dato> nuevosDatos = new ArrayList<>(Arrays.asList(this.rowData));
    	
    	// Agregar el nuevo dato al final de la lista
    	nuevosDatos.add(nuevoDato);
    	
    	// Actualizar el arreglo de datos de la fila
    	this.rowData = nuevosDatos.toArray(new Dato[0]);
    }

    public void agregarDatoNuevaFila(Dato nuevoDato) 
    {
    	     // Clonar el nuevo dato
    	Dato datoClonado = (Dato) nuevoDato.clone();
    	
    	// Crear un nuevo arreglo con un tamaño mayor
    	Dato[] nuevoRowData = new Dato[rowData.length + 1];
    	
    	// Copiar los datos existentes al nuevo arreglo
    	System.arraycopy(rowData, 0, nuevoRowData, 0, rowData.length);
    	
    	// Agregar el nuevo dato clonado al final
    	nuevoRowData[nuevoRowData.length - 1] = datoClonado;
    	
    	// Actualizar el arreglo de datos en la fila
    	rowData = nuevoRowData;
		}
		
		public Dato getDato(String etiquetaColumna, DataFrame dataframe) 
		{
        int indiceColumna = getPosicionColumnaEtiqueta(etiquetaColumna,dataframe);
        if (indiceColumna != -1) {
            return rowData[indiceColumna];
        } else {
            // Manejo de error, puedes lanzar una excepción o devolver un valor por defecto
            throw new IllegalArgumentException("La etiqueta de columna proporcionada no existe en la fila.");
        }
    }
    
    private int getPosicionColumnaEtiqueta(String etiquetaColumna, DataFrame dataframe) 
    {
        for (int i = 0; i < rowData.length; i++) 
        {
            if (dataframe.getColumna(i).getEtiqueta().equals(etiquetaColumna)) 
            {
                return i;
            }
        }
        return -1;  // Devolver -1 si la etiqueta no se encuentra
    }

    @Override
    public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(": ");
    for (int i = 0; i < rowData.length; i++) {
        result.append(rowData[i]);
        if (i < rowData.length - 1) {
            result.append(", ");
        }
    }
    result.append(".");
    return result.toString();
}
//---------------------------------------------------------

}
