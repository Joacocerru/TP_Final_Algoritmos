
//package TP_FINAL_ALGORITMO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class DataFrame {
    private List<Columna> dataColumnar = new ArrayList<>(); // ArrayList para los datos - Registros
    protected List<Fila> dataFilas = new ArrayList<>();
    
    // HashMap llamado columnMap para mapear 
    // las etiquetas de las columnas a las instancias de Columna
    //********************************************************************
    private Map<String, Columna> columnMap = new HashMap<>(); 
    protected Map<Integer, Fila> rowMap = new HashMap<>(); // HashMap Fila Integer
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

        if (headerSN.equals("S") )
        { CargarCsv.cargarDatosDesdeCsvConHead(header, data, csvFile, csvDelimiter);}
        else
        { CargarCsv.cargarDatosDesdeCsvSinHead(header, data, csvFile, csvDelimiter);}

        // Genera Instancias de filas y las mapea con el HASHMAP de FILAS ------------------------

        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Object[] rowData = data.get(rowIndex);
            Integer etiqueta = (rowIndex); // Establece una etiqueta para la fila
            Fila fila = new Fila(etiqueta, rowData); 
            dataFilas.add(fila);
            rowMap.put(rowIndex, fila);
        }

        //*********************************************************************************************
        
        ArmaColumnar.armaDataColumnar(header, data, data.get(0).length, data.size(), this.dataColumnar);

        //***** crea instancias de Columna y las mapea utilizando las etiquetas ***********************
        for (int i = 0; i < header.size(); i++) 
        {
            String etiqueta = header.get(i);
            Columna columna = dataColumnar.get(i) ;
            columnMap.put(etiqueta, columna);
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

    public Columna getColumnaPorIndice(int indice) 
    {
        int i = 0;

        for (String clave: this.columnMap.keySet()) 
        {
            if (i == indice)
            { return this.columnMap.get(clave); }
            i++;
        }
        
        return null ;
    }

    public List<Columna> getColumnaPorRangoIndice(int desde, int hasta) 
    {
        int i = 0;
        List<Columna> listaColumnas = new ArrayList<>();

        for (String clave: this.columnMap.keySet()) 
        {
            if (i >= desde && i <= hasta)
            { listaColumnas.add( this.columnMap.get(clave) ); }
            i++;
        }
        return listaColumnas ;
    }

    //******* METODO PARA ACCEDER A COLUMNA POR ETIQUETA *********************

    public Columna getColumnaPorEtiqueta(String etiqueta) 
    {
        return columnMap.get(etiqueta);
    }

    public List<Columna> getColumnaListaEtiquetas(String[] etiquetas) 
    {
        int total = etiquetas.length;
        List<Columna> listaColumnas = new ArrayList<>();

        for (int i=0; i < total; i++)
        {
            listaColumnas.add( this.columnMap.get(etiquetas[i]));
        }

        return listaColumnas;
    }


    // METODO PARA ACCEDER A FILA POR ETIQUETA ----------------------------------

    public Fila getFilaPorEtiqueta(Integer etiqueta) 
    {
        return rowMap.get(etiqueta);
    }

// METODO PARA ACCEDER A FILA POR LISTA DE ETIQUETAS -------------------------------------------------------------

    public List<Fila> getFilaListaEtiquetas(Integer[] etiquetas) 
    {
        int total = etiquetas.length;
        List<Fila> listaFilas = new ArrayList<>();

        for (int i=0; i < total; i++)
        {
            listaFilas.add( this.rowMap.get(etiquetas[i]));
        }
        return listaFilas;
    }

//----------------------------------------------------

    public Dato[] getFila(int indice) 
    {
        Dato[] fila = new Dato[this._nroColumnas];
        
        for (int i=0; i<this.getNroColumnas(); i++)
        {   
            Columna tmpCol = this.getColumnaPorIndice(i);
            fila[i] = tmpCol.getDato(indice);
        }
        return fila;
    }
// METODO GETVALOR-----------------------------

    public Dato getValor(int fila, int columna) 
    {
            Columna tmp = getColumnaPorIndice(columna);
        return tmp.getDato(fila);
    }
// METODO GETVALOR-----------------------------

    public Dato getValor(int fila, String columna) 
    {
            Columna tmp = getColumnaPorEtiqueta(columna);
        return tmp.getDato(fila);
    }
//----------------------------------------------------
// METODO GETTER DEL HEADER

    public List<String> getHeader() 
    {
        List<String> claves = new ArrayList<>();

        for (String clave:this.columnMap.keySet()) 
        {
            claves.add(clave);
        }
        
        return claves;
    }

    public String getHeader(int Columna) 
    {
        int i=0;
        String headerSalida = new String("");

        for (String clave:this.columnMap.keySet()) 
        {
            if (i == Columna)
            { headerSalida = clave; }
            i++;
        }
            return headerSalida;
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
    for (Integer etiqueta : rowMap.keySet()) {
        System.out.print(etiqueta + " ");
    }
    System.out.println(" ");
}


//************************************************************************   
// METODO MAIN -------------------------------------------------------------------------------------------------
    
    public static void main(String[] args) {

    //DataFrame df = new DataFrame("C:\\Users\\Hernan\\Desktop\\New folder (2)\\TP_Integrador\\src\\TP_Integrador\\prueba1.csv", ",");
        
    //DataFrame df = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba1.csv", ",", "S");
    //DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\PrimerActividadJava\\TP_Integrador\\src\\TP_Integrador\\prueba2.csv", ",", "N");
    DataFrame df =  new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");
    
    Object[][] matriz = { {"Marta","Luis","Nacho",1},{1,"Anabel","Julio",true},{"Maria","David",null,0} };

    String xx = df.getHeader(1);

    Dato[] Fila1 = df.getFila(2);

    Dato dato = df.getValor(2,2);
    Dato dato2 = df.getValor(0,2);  

    Columna Col1 = df2.getColumnaPorIndice(2);
    Columna Col2 = df2.getColumnaPorEtiqueta("C");
    List<Columna> lista = df2.getColumnaPorRangoIndice(2, 4);
    String[] Listita = {"A","D"};
    List<Columna> lista2 = df2.getColumnaListaEtiquetas( Listita);

    CsvPrinter.info(df2);

    // Imprimir por filas utilizando CsvPrinter
    CsvPrinter.imprimirPorFilas(df2);

    // Imprimir columnar utilizando CsvPrinter
    CsvPrinter.imprimirColumnar(df2);
        
    // *************   Acceder a un valor específico por índice de fila y columna ***********************

        Integer fila = 3; // 
        String columna = "D"; // DEBE PODER PONERSE LA ETIQUETA (STRING)

        Dato valor = df2.getValor(fila, columna);
        
        if (valor != null) {
        System.out.println("Valor en la fila " + (fila+1) + " y columna " + (columna) + ": " + valor.getDato());
    } else {
        System.out.println("Índices fuera de rango.");
    } 

    // **********************************************************************************************
    
    CsvPrinter.info(df2);

    // ********* acceder a las columnas utilizando las etiquetas del encabezado ******************
    
    //DataFrame miDataFrame = new DataFrame("prueba1.csv", ",", "S");
    Columna columnaNombre = df2.getColumnaPorEtiqueta("D");

    // ********* imprimo la columna segun la etiqueta elegida **************************************

    if (columnaNombre != null) {

        String nombreColumna = columnaNombre.getEtiqueta(); // obtengo etiqueta de la columna 2
        int cantidadDatos = columnaNombre.getCantDatos(); // obtengo la cantidad de datos de la columna 2 
        String tipoDato = columnaNombre.getTipoDato(); // obtengo el tipo de dato de la columna 2

        System.out.println("Nombre de la Columna: " + nombreColumna);
        System.out.println("Tipo de Dato de la Columna " + nombreColumna + ": " + tipoDato);
        System.out.println("Cantidad de Datos en la Columna " + nombreColumna + ": " + cantidadDatos);
    
        System.out.println("Datos de la Columna "+ nombreColumna + ":");
        for (int i = 0; i < cantidadDatos; i++) {
            Dato dato3 = columnaNombre.getDato(i);
            System.out.println(dato3.getDato());
        }
    } else {
        String nombreColumna = df.getHeader(1);
        System.out.println("La columna "+ nombreColumna + " no existe en el DataFrame.");
    }
}
}
//--------------------------------------------------------------------------------------------------------------
