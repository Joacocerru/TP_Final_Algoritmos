
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
    private Map<String, Fila> rowMap = new HashMap<>();      // HashMap Fila Integer

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

    //****************************************************************************
    // METODO PARA ACCEDER A FILA POR LISTA DE ETIQUETAS ------------------------

    //----------------------------------------------------
    public Fila getFila(String etiquetaFila) 
    {
        return this.rowMap.get(etiquetaFila);
    }

    public List<Fila> getFilaListaEtiquetas(Integer[] etiquetas) 
    {
        int total = etiquetas.length;
        List<Fila> listaFilas = new ArrayList<>();

        for (int i=0; i < total; i++)
        {
            Fila tmp = this.getFila(etiquetas[i]);
            
            listaFilas.add( );
        }
        return listaFilas;
    }


    public Integer getPosicionFilaEtiqueta (int etiquetaFila) 
    {
        Integer posicion = null;

        for (int i=0; i<this.getNroRegistros(); i++)
        {   
            if ( ( (Fila) this.rowMap.get(i) ).getEtiqueta().equals(etiquetaFila) )
                posicion = i;
        }
        return posicion;
    }
// METODO GETVALOR-----------------------------

    public Dato getValor(Integer fila, String etiquetaColumna) 
    {
        Columna tmp = getColumnaPorEtiqueta(etiquetaColumna);
        Integer posFila = this.getPosicionFilaEtiqueta(fila);
        return tmp.getDato(posFila);
    }
//----------------------------------------------------
// METODO GETTER DEL HEADER

    public List<String> getHeader() 
    {
        List<String> claves = new ArrayList<>();

        for (int i=0; i< this.getNroColumnas(); i++)
        {
            Columna col = this.columnMap.get(i);
            claves.add(col.getEtiqueta());
        }
        
        return claves;
    }

    public String getHeader(int indice) 
    {
        String headerSalida = new String("");
        headerSalida = ((Columna) this.columnMap.get(indice)).getEtiqueta();
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
    DataFrame df =  new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba1.csv", ",", "S");
    DataFrame df2 = new DataFrame("C:\\Documentos\\n67745\\Unsam\\Algoritmos 1\\Java\\TP_Final\\TP_Final_Algoritmos\\TP_FINAL_ALGORITMO\\prueba2.csv", ",", "N");
    
    //Object[][] matriz = { {"Marta","Luis","Nacho",1},{1,"Anabel","Julio",true},{"Maria","David",null,0} };

    String xx = df.getHeader(1);

    //Dato[] Fila1 = df.getFila(2);

    Columna Col2 = df2.getColumnaPorEtiqueta("3");
    String[] Listita = {"1","4"};
    List<Columna> lista2 = df2.getColumnaListaEtiquetas( Listita);

    CsvPrinter.info(df2);

    // Imprimir por filas utilizando CsvPrinter
    CsvPrinter.imprimirPorFilas(df2);

    // Imprimir columnar utilizando CsvPrinter
    CsvPrinter.imprimirColumnar(df2);
        
    // *************   Acceder a un valor específico por índice de fila y columna ***********************

        Integer fila = 3; // 
        String columna = "D"; // DEBE PODER PONERSE LA ETIQUETA (STRING)

        //Dato valor = df2.getValor(fila, columna);
        
   //     if (valor != null) {
   //     System.out.println("Valor en la fila " + (fila+1) + " y columna " + (columna) + ": " + valor.getDato());
   // } else {
   //     System.out.println("Índices fuera de rango.");
   // } 

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