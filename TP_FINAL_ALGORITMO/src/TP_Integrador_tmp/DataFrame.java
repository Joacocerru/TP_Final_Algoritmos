
package TP_Integrador_tmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.ListIterator;
import java.util.Map;


public class DataFrame {

    private List<Columna> dataColumnar = new ArrayList<>(); // ArrayList para los datos - Array de columnas
    protected List<Fila> dataFilas = new ArrayList<>();     // Array de filas
    
    // HashMap llamado columnMap para mapear 
    // las etiquetas de las columnas a las instancias de Columna
    //********************************************************************
    private List<String> ColumnArray = new ArrayList<>(); // Array de Etiquetas de columnas
    private List<String> RowArray = new ArrayList<>(); // Array de Etiquetas de columnas

    private Map<String, Columna> columnMap = new HashMap<>();   // Indice para las columnas
    protected Map<String, Fila> rowMap = new HashMap<>();      // HashMap Fila Integer

    //********************************************************************

    private Integer _nroColumnas; // VAR CON CANT. DE COLUMNAS DS
    private Integer _nroRegistros; // VAR CON CANT FILAS DEL DS

// CONSTRUCTOR - CON LECTURA CSV
    public DataFrame(String csvFile, String csvDelimiter, String headerSN) 
    {
        List<String[]> data = new ArrayList<>(); // ArrayList para los datos - Registros
        List<String> header = new ArrayList<>(); // ArrayList para el encabezado

        this._nroColumnas = 0;
        this._nroRegistros = 0;

        // Carga la informacion de CSV en Data y genera los Headers
        if (headerSN.equals("S") )
        { CargarCsv.cargarDatosDesdeCsvConHead(header, data, csvFile, csvDelimiter);}
        else
        { CargarCsv.cargarDatosDesdeCsvSinHead(header, data, csvFile, csvDelimiter);}

        // Arma la estructura columnar en dataColumnar
        ArmaColumnar.armaDataColumnar(header, data, this.dataColumnar);
        
        
        // Genera Instancias de filas y las mapea con el HASHMAP de FILAS -
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Object[] rowData = data.get(rowIndex);
            String etiqueta = Integer.toString(rowIndex); // Establece una etiqueta para la fila
            Fila fila = new Fila(etiqueta, rowData); 
            dataFilas.add(fila);

            rowMap.put(etiqueta, fila);
            this.RowArray.add(etiqueta);
        }
        
        //***** crea instancias de Columna y las mapea utilizando las etiquetas *******************
        // Crea el array de etiquetas y el maps de columnas 

        for (int i = 0; i < header.size(); i++) 
        {
            String etiqueta = header.get(i);
            Columna columna = dataColumnar.get(i) ;

            columna.setEtiqueta(etiqueta);
            columnMap.put(etiqueta, columna);
            this.ColumnArray.add(etiqueta);
        }

        this.contarColumnas();
        this.contarRegistros();
    }

    //----------------------------------------------------
    // CUENTA COLUMNAS
    private void contarColumnas() 
    {
        this._nroColumnas = this.dataColumnar.size();    
    }

    // CUENTA REGISTROS
    private void contarRegistros() 
    {
        this._nroRegistros = this.dataColumnar.get(0).getCantDatos();
    }

//----------------------------------------------------
    // METODO GETTER NRO COLUMNAS

    public int getNroColumnas() {
        return this._nroColumnas;
    }

    // METODO GETTER NRO COLUMNAS

    public int getNroRegistros() {
    return this._nroRegistros;
    }

//----------------------------------------------------
// METODO GETTER DE UNA COLUMNA
// Eliminamos por lo que dijo el profesor de no usar la posicion
//    public List<Columna> getColumnaPorRangoIndice(int desde, int hasta) 
//    {
//        int i = 0;
//        List<Columna> listaColumnas = new ArrayList<>();

//        for (String clave: this.columnMap.keySet()) 
//        {
//            if (i >= desde && i <= hasta)
//            { listaColumnas.add( this.columnMap.get(clave) ); }
//            i++;
//        }
//        return listaColumnas ;
//    }

    //****************************************************************************
    //******* METODO PARA ACCEDER A COLUMNA POR ETIQUETA *********************

    public Columna getColumnaPorEtiqueta(String etiqueta) 
    {
        return this.columnMap.get(etiqueta);
    }

    public Integer getPosicicionColumnaEtiqueta (String etiqueta)
    {
        Integer posicion = -1;
        Boolean encontrado = false;

        for (int i=0; i<this.getNroColumnas();i++)
        {
            if ( this.ColumnArray.get(i).equals(etiqueta) )
            {
                posicion = i;
                encontrado = true;
            }    
        }

        if (encontrado == true)
            return posicion;
        else
            return null; // armar exception
    }

    public List<Columna> getColumnaListaEtiquetas(String[] etiquetas) 
    {
        int total = etiquetas.length;
        List<Columna> listaColumnas = new ArrayList<>();

        for (int i=0; i < total; i++)
        {
            
            listaColumnas.add( this.getColumnaPorEtiqueta( etiquetas[i]) );
        }

        return listaColumnas;
    }

    public Columna getColumna(Integer posicion)
    {
        String clave = this.ColumnArray.get(posicion);
        return this.getColumnaPorEtiqueta(clave);
    } 

    //****************************************************************************
    // METODO PARA ACCEDER A FILA POR LISTA DE ETIQUETAS ------------------------

    //----------------------------------------------------
    public Fila getFilaPorEtiqueta(String etiquetaFila) 
    {
        return this.rowMap.get(etiquetaFila);
    }

    public Fila getFila(Integer posicion) 
    {
        String clave = this.RowArray.get(posicion);
        return this.rowMap.get(clave) ;
    }

    public List<Fila> getFilaListaEtiquetas(String[] etiquetas) 
    {
        int total = etiquetas.length;
        List<Fila> listaFilas = new ArrayList<>();

        for (int i=0; i < total; i++)
        {
            listaFilas.add( this.getFilaPorEtiqueta(etiquetas[i]) );
        }
        
        return listaFilas;
    }


    public Integer getPosicionFilaEtiqueta (String etiquetaFila) 
    {
        Integer posicion = null;

        for (int i=0; i<this.getNroRegistros(); i++)
        {   
            if ( this.RowArray.get(i).equals(etiquetaFila))
                posicion = i;
        }
        return posicion;
    }
// METODO GETVALOR-----------------------------

    public Dato getValor(String etiquetafila, String etiquetaColumna) 
    {
        Columna tmpColumna = getColumnaPorEtiqueta(etiquetaColumna);
        Integer posFila = this.getPosicionFilaEtiqueta(etiquetafila);
        return tmpColumna.getDato(posFila);
    }

    public Dato getValorPosicion(Integer posFila, Integer posColumna) 
    {
        String tmpEtiquetaColumna = this.getColumna(posColumna).getEtiqueta();
        String tmpEtiquetaFila = this.getFila(posFila).getEtiqueta();
        
        return this.getValor(tmpEtiquetaFila, tmpEtiquetaColumna);
    }

//----------------------------------------------------
// METODO GETTER DEL HEADER

    public List<String> getAllHeaderColumn() 
    {
        List<String> claves = new ArrayList<>();

        for (int i=0; i< this.getNroColumnas(); i++)
        {
            claves.add( this.ColumnArray.get(i) );
        }
        
        return claves;
    }

    public String getHeaderColumn(int indice) 
    {
        return this.ColumnArray.get(indice);
    }

    public List<String> getAllHeaderRows() 
    {
        List<String> claves = new ArrayList<>();

        for (int i=0; i< this.getNroColumnas(); i++)
        {
            claves.add( this.RowArray.get(i) );
        }
        
        return claves;
    }

    public String getHeaderRows(int indice) 
    {
        return this.RowArray.get(indice);
    }

//----------------------------------------------------
// METODO 

public Boolean isEmpty() {
    if (this._nroRegistros == 0 )
        {return true;}
    else
        {return false;}
}

// Método para imprimir etiquetas de las filas ---------------------------

public void imprimirEtiquetasFilas() {
    System.out.print("Etiquetas de las filas: ");

    for (String etiqueta : rowMap.keySet()) {
        System.out.print(etiqueta + " ");
    }
    System.out.println(" ");
}


// METODOS SET DE VALOR -----
public void setValorPorEtiqueta (String etiquetaFila, String etiquetaColumna, Object nuevoValor)
{
    Columna tmpColumna = getColumnaPorEtiqueta(etiquetaColumna);
    Integer posFila = this.getPosicionFilaEtiqueta(etiquetaFila);
    tmpColumna.setDato (posFila, nuevoValor);

    Dato tmp_dato = tmpColumna.getDato(posFila);
    if (nuevoValor instanceof java.lang.String )
    {

    }
}


}

//--------------------------------------------------------------------------------------------------------------