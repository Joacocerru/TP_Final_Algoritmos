
package TP_Integrador_tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TP_Integrador_tmp.Columna.IndiceFueraDeRangoException;

import java.lang.Cloneable;

public class DataFrame implements Cloneable{

    protected List<Columna> dataColumnar = new ArrayList<>(); // ArrayList para los datos - Array de columnas
    protected List<Fila> dataFilas = new ArrayList<>();     // Array de filas
     
    protected List<String> ColumnArray = new ArrayList<>(); // Array de Etiquetas de columnas
    protected List<String> RowArray = new ArrayList<>(); // Array de Etiquetas de columnas
    protected List<String> OriginalRowColumnArray = new ArrayList<>(); // Array de Etiquetas de columnas

    //------------------------------------------------------------------------------------------
    // HashMap llamado columnMap y rowMap para mapear 
    // las etiquetas de las columnas y Filas a las instancias de Columna y Fila


    protected Map<String, Columna> columnMap = new HashMap<>();   // Indice para las columnas
    protected Map<String, Fila> rowMap = new HashMap<>();      // HashMap Fila Integer
    //------------------------------------------------------------------------------------------

    private Integer _nroColumnas; // VAR CON CANT. DE COLUMNAS DS
    
    private Integer _nroRegistros; // VAR CON CANT FILAS DEL DS


    //-----------------------------------------------------------------------------------------
    // Constructor sin parámetros


    public DataFrame() 
    {
        this.dataColumnar = new ArrayList<>();
        this.dataFilas = new ArrayList<>();
        this.ColumnArray = new ArrayList<>();
        this.RowArray = new ArrayList<>();
        this.OriginalRowColumnArray = new ArrayList<>();
        this.columnMap = new HashMap<>();
        this.rowMap = new HashMap<>();
        this._nroColumnas = 0;
        this._nroRegistros = 0;
    }


    //-------------------------------------------------------------------------------
    // CONSTRUCTOR - CON LECTURA CSV

    public DataFrame(String csvFile, String csvDelimiter, String headerSN) 
    {
        List<String[]> data = new ArrayList<>(); // ArrayList para los datos - Registros
        List<String> header = new ArrayList<>(); // ArrayList para el encabezado

        this._nroColumnas = 0;
        this._nroRegistros = 0;
    
        //-------------------------------------------------------------------------------------
        // Carga la informacion de CSV en Data y genera los Headers

        if (headerSN.equals("S") )
        { CargarCsv.cargarDatosDesdeCsvConHead(header, data, csvFile, csvDelimiter);}
        else
        { CargarCsv.cargarDatosDesdeCsvSinHead(header, data, csvFile, csvDelimiter);}
        

        //------------------------------------------------------------------------------------
        // Arma la estructura columnar en dataColumnar
        ArmaColumnar.armaDataColumnar(header, data, this.dataColumnar);

        this.contarColumnas();
        this.contarRegistros();

        //------------------------------------------------------------------------------------
        // Genera Instancias de filas y las mapea con el HASHMAP de FILAS -
        for (int rowIndex = 0; rowIndex < this.getNroRegistros(); rowIndex++) {

            Dato[] rowData = new Dato[ this.getNroColumnas()];
            
            for ( int colIndex = 0; colIndex < this.getNroColumnas(); colIndex++){

                //Object[] rowData = data.get(rowIndex);
                rowData[colIndex] = this.dataColumnar.get(colIndex).listaDatos[rowIndex];
            }
        
            String etiqueta = Integer.toString(rowIndex); // Establece una etiqueta para la fila
            Fila fila = new Fila(etiqueta, rowData); 
            dataFilas.add(fila);

            rowMap.put(etiqueta, fila);
            this.RowArray.add(etiqueta);
            this.OriginalRowColumnArray.add(etiqueta);
        }

        //------------------------------------------------------------------------------------
        // crea instancias de Columna y las mapea utilizando las etiquetas 
        // Crea el array de etiquetas y el maps de columnas 

        for (int i = 0; i < header.size(); i++) {

            String etiqueta = header.get(i);
            Columna columna = dataColumnar.get(i) ;

            columna.setEtiqueta(etiqueta);
            columnMap.put(etiqueta, columna);
            this.ColumnArray.add(etiqueta);
        }

    }

    //--------------------------------------------------------------
    // CUENTA COLUMNAS

    private void contarColumnas() {

        this._nroColumnas = this.dataColumnar.size();    
    }


    //--------------------------------------------------------------
    // CUENTA REGISTROS

    private void contarRegistros() {

        this._nroRegistros = this.dataColumnar.get(0).getCantDatos();
    }

    //-------------------------------------------------------------
    // METODO GETTER NRO COLUMNAS

    public int getNroColumnas() {

        return this._nroColumnas;
    }


    //-------------------------------------------------------------
    // METODO GETTER NRO COLUMNAS

    public int getNroRegistros() {
        
        return this._nroRegistros;
    }


    //-------------------------------------------------------------------------------
    //METODO PARA ACCEDER A COLUMNA POR ETIQUETA 
    //------------------------------------------------------------------------------

    public Columna getColumnaPorEtiqueta(String etiqueta) {

        return this.columnMap.get(etiqueta);
    }

    public Integer getPosicicionColumnaEtiqueta (String etiqueta){

        Integer posicion = -1;
        Boolean encontrado = false;

        for (int i=0; i<this.getNroColumnas();i++){

            if ( this.ColumnArray.get(i).equals(etiqueta) ){

                posicion = i;
                encontrado = true;
            }    
        }

    if (encontrado == true)
        return posicion;
    else
        return null; // armar exception
    }

    public List<Columna> getColumnaListaEtiquetas(String[] etiquetas) {

        int total = etiquetas.length;
        List<Columna> listaColumnas = new ArrayList<>();

        for (int i=0; i < total; i++){
            
            listaColumnas.add( this.getColumnaPorEtiqueta( etiquetas[i]) );
        }

        return listaColumnas;

    }

    public Columna getColumna(Integer posicion){

        String clave = this.ColumnArray.get(posicion);
        return this.getColumnaPorEtiqueta(clave);

    } 

    //-----------------------------------------------------------------------
    // METODO PARA ACCEDER A FILA POR LISTA DE ETIQUETAS 
    //-----------------------------------------------------------------------


    public Fila getFilaPorEtiqueta(String etiquetaFila) 
    {

        return this.rowMap.get(etiquetaFila);
    }

    public Fila getFila(Integer posicion) {

        String clave = this.RowArray.get(posicion);
        return this.rowMap.get(clave) ;
    }

    public List<Fila> getFilaListaEtiquetas(String[] etiquetas) {

        int total = etiquetas.length;
        List<Fila> listaFilas = new ArrayList<>();

        for (int i=0; i < total; i++){

            listaFilas.add( this.getFilaPorEtiqueta(etiquetas[i]) );
        }
        
        return listaFilas;

    }

    public Integer getPosAbsolutaFilaEtiqueta (String etiquetaFila)
    {
        Integer posicion = null;

        for (int i=0; i<this.getNroRegistros(); i++)
        {       
            if ( this.OriginalRowColumnArray.get(i).equals(etiquetaFila))
            posicion = i;
        }

        return posicion;
    }

    public Integer getPosicionFilaEtiqueta (String etiquetaFila){
        
        Integer posicion = null;

        for (int i=0; i<this.getNroRegistros(); i++){   
            
            if ( this.RowArray.get(i).equals(etiquetaFila))
            posicion = i;
        }

        return posicion;
    }


    //----------------------------------------------------------------------    
    // METODO GETVALOR

    public Dato getValor(String etiquetafila, String etiquetaColumna) {
    
        //Calcula la columna desde ColumnMap con la etiqueta
        Columna tmpColumna = getColumnaPorEtiqueta(etiquetaColumna);

        // Calcula la posicion de la fila mirando la ubicacion en rowArray
        //Integer posFila = this.getPosicionFilaEtiqueta(etiquetafila);
        Integer posFila = this.getPosAbsolutaFilaEtiqueta(etiquetafila);
        
        if ( tmpColumna == null || posFila == null)
            throw new NullPointerException ("Fila/Columna inexistente");
        
        return tmpColumna.getDato(posFila);
    }


    public Dato getValorPosicion(Integer posFila, Integer posColumna) {
        //Calcula la etiqueta mirando ColumArray
        String tmpEtiquetaColumna = this.getColumna(posColumna).getEtiqueta();
        //Calcula etiqueta mirando RowArray
        String tmpEtiquetaFila = this.getFila(posFila).getEtiqueta();
        
        return this.getValor(tmpEtiquetaFila, tmpEtiquetaColumna);
    }


    //-------------------------------------------------------------------------------------
    // METODO SETVALOR

    public void setValorDataFrame(String etiquetafila, String etiquetaColumna, Object Valor) {

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   ACCEDER A UNA CELDA Y SETEAR NUEVO VALOR");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        System.err.println("En la columna '" + etiquetaColumna + "' y la fila '" + etiquetafila + "' seteamos por el valor '" + Valor + "'.");

        Columna tmpColumna = getColumnaPorEtiqueta(etiquetaColumna);
        Integer posFila = this.getPosicionFilaEtiqueta(etiquetafila);

        try {

            tmpColumna.setDato(posFila, Valor);
        } 
        catch (IndiceFueraDeRangoException e) {
        
            System.err.println("Error al establecer el valor: " + e.getMessage());
        }
        System.out.println(" ");
    }

    //-----------------------------------------------------------------------------
    // METODO GETTER DEL HEADER

    public List<String> getAllHeaderColumn() {
        List<String> claves = new ArrayList<>();

        for (int i=0; i< this.getNroColumnas(); i++){

            claves.add( this.ColumnArray.get(i) );
        }
        
        return claves;
    }

    public String getHeaderColumn(int indice) {

        return this.ColumnArray.get(indice);
    }

    public List<String> getAllHeaderRows() {

        List<String> claves = new ArrayList<>();

        for (int i=0; i< this.getNroColumnas(); i++){

            claves.add( this.RowArray.get(i) );
        }
        
        return claves;
    }

    public String getHeaderRows(int indice) {

        return this.RowArray.get(indice);
    }


    //-------------------------------------------------------------------------
    // METODO PARA VERIFICAR SI EL REGISTRO ESTA VACIO

    public Boolean isEmpty() {

        if (this._nroRegistros == 0 ){
            return true;
        }
        else{
            return false;
        }
    }



    //-----------------------------------------------------------------------------
    // Método para obtener todas las etiquetas de las filas

    public void getEtiquetasFilas() {

        System.out.print("Etiquetas de las filas: ");

        for (String etiqueta : rowMap.keySet()) {

            System.out.print(etiqueta + " ");
        }

        System.out.println(" ");
    }

    // Método para obtener todas las etiquetas de las columnas

    public void getEtiquetasColumnas() {

        System.out.print("Etiquetas de las Columnas: ");

        for (String etiqueta : columnMap.keySet()) {

            System.out.print(etiqueta + " ");
        }

        System.out.println(" ");
    }

    //-----------------------------------------------------------------------------
    // Método para imprimir etiquetas de las filas 

    public void imprimirEtiquetasFilas() {
        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   ETIQUETAS DE LAS FILAS");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");
    
        getEtiquetasFilas(); // Llamada al método getEtiquetasFilas
    
        System.out.println(" ");
    }


    // Método para imprimir etiquetas de las COLUMNAS

    public void imprimirEtiquetasColumnas() {

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   ETIQUETAS DE LAS COLUMNAS");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        System.out.print("Etiquetas de las Columnas: ");

        getEtiquetasColumnas(); 
    
        System.out.println(" ");
    }

    //-------------------------------------------------------------------------------
    // METODOS SET DE VALOR DE FILAS Y COLUMNAS

    public void setValorPorEtiqueta (String etiquetaFila, String etiquetaColumna, Object nuevoValor){

        Columna tmpColumna = getColumnaPorEtiqueta(etiquetaColumna);
        Integer posFila = this.getPosicionFilaEtiqueta(etiquetaFila);

        try {
            tmpColumna.setDato (posFila, nuevoValor);
    
        } 
        catch (IndiceFueraDeRangoException e) {
            System.err.println("Error al establecer el valor: " + e.getMessage()); 
        }
    
        Dato[] tmpDato = new Dato [this._nroColumnas];
   
        for (int col=0; col< this._nroColumnas; col++){
            tmpDato [col] = (this.getValorPosicion(posFila, col));
        }

        Fila fila = new Fila(etiquetaFila, tmpDato); 

        dataFilas.add(posFila, fila);
        rowMap.remove(etiquetaFila);
        rowMap.put(etiquetaFila, fila);
        this.RowArray.add(etiquetaFila);
    }

    //---------------------------------------------------------------------------------

    public void eliminarColumna(String etiquetaColumna) {

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   ELIMINA COLUMNA ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");
        
        int cantidadColumnas = getNroColumnas();
    
        // Verificar si la columna existe
        if (!columnMap.containsKey(etiquetaColumna)) {
            System.out.println("La columna con etiqueta " + etiquetaColumna + " no existe. Corroborar los parámetros de entrada...");
            return;
        }else {

            // Obtener la posición de la columna
        int posicion = getPosicicionColumnaEtiqueta(etiquetaColumna);

        // Eliminar la columna de dataColumnar y ColumnArray
        dataColumnar.remove(posicion);
        ColumnArray.remove(etiquetaColumna);

        // Actualizar el columnMap
        columnMap.remove(etiquetaColumna);

        // Actualizar la posición de las columnas restantes en columnMap
        columnMap.clear();
        for (int i = 0; i < cantidadColumnas-1; i++) {
        
            String etiquetaActual = ColumnArray.get(i);
            columnMap.put(etiquetaActual, dataColumnar.get(i));
        }

        // Actualizar el número de columnas
        this.contarColumnas();

        // Actualiza los datos de fila eliminando la columna
        for (int i=0; i < this.getNroRegistros(); i++){

            this.dataFilas.get(i).removeColumna(posicion);
        }
        
        System.out.println("Eliminamos la columna '" + etiquetaColumna + "' del dataframe.");


        }
    }


    //----------------------------------------------------------------------------------------------------------------
    // METODO PARA ELIMINAR UNA FILA DADA SU ETIQUETA
    
    public void eliminarFila(String etiquetaFila)
    {

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   ELIMINA FILA ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        int cantidadFilas = getNroRegistros();

        // Verificar si la fila existe
        if (!rowMap.containsKey(etiquetaFila)) {
            System.out.println("La fila con etiqueta " + etiquetaFila + " no existe. Corroborar los parámetros de entrada...");
            return;
        }else {
            
        // Obtener la posición de la fila
        int posicion = getPosicionFilaEtiqueta(etiquetaFila);
        int posicionAbs = this.getPosAbsolutaFilaEtiqueta(etiquetaFila);

        // Eliminar la fila de dataFilas y RowArray
        this.dataFilas.remove(posicionAbs);
        this.RowArray.remove(etiquetaFila);
        this.OriginalRowColumnArray.remove(etiquetaFila);

        // Actualizar la posición de las filas restantes en rowMap
        this.rowMap.clear();

        for (int i = 0; i < cantidadFilas-1; i++) {   
            String etiquetaActual = dataFilas.get(i).getEtiqueta();
            rowMap.put(etiquetaActual, dataFilas.get(i));
        }

        // Actualiza los datos de columna de las filas
     
        for (int i=0; i < this.getNroColumnas(); i++)
        {
            this.dataColumnar.get(i).removeFila(posicionAbs);
            this.dataColumnar.get(i).restarCantRegistro();
        }
        this.contarRegistros();
        System.out.println("Eliminamos la fila '" + etiquetaFila + "' del dataframe.");


        }
    }
//--------------------------------------------------------------------------------      
    @Override
    public DataFrame clone() {

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   Copia Profunda del DataFrame ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        try {
            // Clonar el objeto en sí
            DataFrame copiaEstructura = (DataFrame) super.clone();

            // Realizar una copia profunda de las columnas
            copiaEstructura.dataColumnar = new ArrayList<>();
            copiaEstructura.columnMap = new HashMap<>();
            copiaEstructura.OriginalRowColumnArray = new ArrayList<>(); // Array de Etiquetas de columnas

            for (int i=0; i < this.OriginalRowColumnArray.size(); i++)
            {
                String tmp = this.OriginalRowColumnArray.get(i);
                copiaEstructura.OriginalRowColumnArray.add(tmp); 
            }
            
            for (Columna columna : this.dataColumnar) 
            {
                Columna columnaCopia = columna.clone();
                copiaEstructura.dataColumnar.add(columnaCopia);
                copiaEstructura.columnMap.put(columnaCopia.getEtiqueta(), columnaCopia);
            }           

            // Realizar una copia profunda de las columnas
            copiaEstructura.dataFilas = new ArrayList<>();
            copiaEstructura.rowMap = new HashMap<>();

            for (Fila filas : this.dataFilas) 
            {
                Fila filaCopia = filas.clone();
                copiaEstructura.dataFilas.add(filaCopia);
                copiaEstructura.rowMap.put(filaCopia.getEtiqueta(), filaCopia);
            }

            // Clonar el columnMap y los arrays de etiquetas
            copiaEstructura.ColumnArray = new ArrayList<>(this.ColumnArray);
            copiaEstructura.RowArray = new ArrayList<>(this.RowArray);
            
            copiaEstructura.contarColumnas();
            copiaEstructura.contarRegistros();
            return copiaEstructura;
        } 
        catch (CloneNotSupportedException e) {
            
            throw new AssertionError();
        }
    }
    //--------------------------------------------------------------------------------------------------------------
    // METODO PARA DADO UN VALOR BUSCARLO EN LA ESTRUCTURA Y DEVOLVER 
    // SU POSICION (DANDO SUS ETIQUETAS)

    public String buscarValor(Object valorBuscado) {
        
        //for (String etiquetaColumna : ColumnArray) {

        for (int indiceColumna = 0; indiceColumna < this.getNroColumnas(); indiceColumna++) {
            
            Columna columna = this.getColumna(indiceColumna);
            
            for (int indiceFila = 0; indiceFila < this.getNroRegistros(); indiceFila++) {

                Dato dato = columna.getDato(indiceFila);

                if (valorBuscado.getClass() == dato.getClass()) {

                    if (valorBuscado instanceof Dato) {

                        Dato valorDatoBuscado = (Dato) valorBuscado;
                            
                        if (dato.compareTo(valorDatoBuscado) == 0){

                            String etiquetaCol = columna.getEtiqueta();
                            String etiquetaFila = this.getFila(indiceFila).getEtiqueta();
                            return "El elemento " + valorDatoBuscado.getDato() + " fue encontrado en fila: " + etiquetaFila + ", columna: " + etiquetaCol;
                        }
                    } 
                }
            }
        }
        return "Elemento " + valorBuscado + " no encontrado en el DataFrame.";

    }


    public  DataFrame FiltroPorColumna(String Etiquetacolumna, int operacion, Object valorBuscado) // -1 Menor que , 0 igual que, 1 Mayor que 
    {
        
        if (operacion !=0 && operacion !=-1 && operacion !=1){   
            // exception
        }
        
        Boolean buscadoNumerico = false;

        try 
        {
            Long x = Long.parseLong(valorBuscado.toString());
            buscadoNumerico = true;        
        } 
        catch (NumberFormatException ex)
        {
            buscadoNumerico = false;
        }
        

        Dato valorDatoBuscado = new Dato();

        if (buscadoNumerico == true)
        {
            Long x = Long.parseLong(valorBuscado.toString());
            valorDatoBuscado = new Dato_Numerico (x);
        }
        else
        {
            String valorStr = valorBuscado.toString();

            if (valorStr.equals("NA"))
            {
                valorDatoBuscado = new Dato_NA();
            }
            else if (valorStr.toUpperCase().equals("TRUE") || valorStr.toUpperCase().equals("FALSE") )
            {
                valorDatoBuscado = new Dato_Boolean(valorStr.toUpperCase());
            }
            else
            {
                valorDatoBuscado = new Dato_String(valorStr);
            }
        }

        DataFrame copiaDF = this.clone();    

        //columna a controlar
        //int tmpColumna = this.getPosicicionColumnaEtiqueta(Etiquetacolumna);

        Integer tmpColumna = this.getPosicicionColumnaEtiqueta(Etiquetacolumna);


        // Se recorren todas las filas
        for (int i = 0; i < this.getNroRegistros(); i++) {
            Dato valorEncontrado = this.getValorPosicion(i, tmpColumna);  // ####

            if (valorDatoBuscado.getTipoDato() == valorEncontrado.getTipoDato()) 
            {
                if (valorDatoBuscado instanceof Dato) 
                {
                    int comparacion = valorEncontrado.compareTo(valorDatoBuscado);
                    if ( comparacion != operacion) // elimino la fila
                        copiaDF.eliminarFila(this.RowArray.get(i)); 
                } 
            }
        }

        return copiaDF;
    }


    public int buscarBinariaEnColumna(String Etiquetacolumna, Object valor) 
    {
        Columna tmpColumna = this.getColumnaPorEtiqueta(Etiquetacolumna);
        String[] parametro = new String[1];
        parametro[0] = tmpColumna.getEtiqueta();
        this.orderPorColumnas(parametro);
        //CsvPrinter.imprimirPorFilas(this);
        return this.buscarNBinariaEnColumna(tmpColumna, valor);
    }


    public int buscarNBinariaEnColumna(Columna columna, Object valor) {
        // Realiza una búsqueda binaria en la columna
        // (los datos en la columna deber estar ordenados)

        int izquierda = 0;  
        int derecha = columna.getCantDatos() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            Dato datoMedio = columna.getDato(medio);

            if (datoMedio.equals(valor)) 
            {
                return medio;
            }

            if (datoMedio.compareTo((Dato) valor) < 0) {
                izquierda = medio + 1;
            } 
            else {
                derecha = medio - 1;
            }

        }

        return -1; // Elemento no encontrado en la columna
    }
    
    

    //----------------------------------------------------------------------------------------------------------------
    // METODO PARA AGREGAR UNA COLUMNA DEL DATAFRAME
    public void clonarYAgregarColumna(String etiquetaColumnaExistente, String etiquetaNuevaColumna){
        
        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   AGREGAR UNA COLUMNA AL DATAFRAME");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        System.out.println(" Duplicamos la columna existente '" + etiquetaColumnaExistente + "' y la insertamos al final del dataframe. ");
        
        // Verificar si la columna existente realmente existe
        Columna columnaExistente = this.getColumnaPorEtiqueta(etiquetaColumnaExistente);
        int posicion = this.getPosicicionColumnaEtiqueta(etiquetaColumnaExistente);

        if (columnaExistente != null) {

            // Clonar la columna existente
            Columna nuevaColumna = columnaExistente.clone();
            
            // Establecer la etiqueta para la nueva columna
            nuevaColumna.setEtiqueta(etiquetaNuevaColumna);

            // Agregar la nueva columna al DataFrame
            dataColumnar.add(nuevaColumna);

            // Actualizar ColumnArray
            ColumnArray.add(etiquetaNuevaColumna);

            // Actualizar columnMap
            columnMap.put(etiquetaNuevaColumna, nuevaColumna);
            
            this.contarColumnas();

            for (int i=0; i<this.getNroRegistros(); i++){

                Dato newDato = this.dataFilas.get(i).getDato(posicion);
                Dato DatoClonado = (Dato) newDato.clone();
                this.dataFilas.get(i).setDato(DatoClonado);
            }    
            

        } 
        else {
            System.out.println("Error: La columna '" + etiquetaColumnaExistente + "' no existe en el DataFrame.");
        }
    }


    // METODO PARA AGREGAR UNA COLUMNA CON SECUENCIA LINEAL

    public void agregarColumnaSecuencia(DataFrame df, String[] datosNuevaColumna,String etiquetaColumnaNueva, String tipoDato){

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   AGREGAR UNA COLUMNA NUEVA CON SECUENCIA LINEAL");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        // Crear un array de Dato
        Dato[] datosArray = new Dato[datosNuevaColumna.length];

        for (int i = 0; i < datosNuevaColumna.length; i++) {
            datosArray[i] = new Dato(datosNuevaColumna[i]);
        }
    
        // Crear la nueva columna
        Columna nuevaColumna = new Columna();
        nuevaColumna.setColumna(datosArray, tipoDato);
        DataFrame.AgregarColumnaNueva(df, etiquetaColumnaNueva, nuevaColumna);


        System.out.println("Agregamos la columna Nueva '" + etiquetaColumnaNueva+ "' al final del DataFrame.");




    }


    //-----------------------------------------------------------------------------------
    // METODO PARA AGREGAR UNA COLUMNA NUEVA AL DATAFRAME

    public static void AgregarColumnaNueva(DataFrame df, String etiquetaNuevaColumna, Columna columnaNueva) {
        
        // Verificar si la columna existente realmente existe
        //Columna columnaExistente = columnaNueva;

        if (columnaNueva != null) {
            // Clonar la columna existente
            //Columna nuevaColumna = columnaExistente.clone();
            
            // Establecer la etiqueta para la nueva columna
            columnaNueva.setEtiqueta(etiquetaNuevaColumna);

            // Agregar la nueva columna al DataFrame
            df.dataColumnar.add(columnaNueva);

            // Actualizar ColumnArray
            df.ColumnArray.add(etiquetaNuevaColumna);

            // Actualizar columnMap
            df.columnMap.put(etiquetaNuevaColumna, columnaNueva);
            
            df.contarColumnas();

        } 
        else {
            System.out.println("Error: La columna '" + etiquetaNuevaColumna + "' no existe en el DataFrame.");
        }
    }

    // METODO PARA GENERAR UNA VISTA REDUCIDA (SLICING)
    public DataFrame seleccionarVista(List<String> etiquetasFilas, List<String> etiquetasColumnas) {
        // Crear un nuevo DataFrame para la vista reducida
        DataFrame vistaRed = new DataFrame();
    
        // Copiar las columnas seleccionadas y actualizar etiquetasColumnas
        for (String etiquetaColumna : etiquetasColumnas) 
        {
            Columna columnaOriginal = getColumnaPorEtiqueta(etiquetaColumna);
            if (columnaOriginal != null) 
            {
                // Crear una nueva columna para la vista reducida
                Columna columnaNueva = new Columna();
    
                // Configurar la etiqueta de la nueva columna
                columnaNueva.setEtiqueta(etiquetaColumna);
                columnaNueva.setTipoDatos(columnaOriginal.getTipoDato());
    
                // Obtener los datos relevantes de las filas seleccionadas
                for (String etiquetaFila : etiquetasFilas) 
                {
                    Fila filaOriginal = getFilaPorEtiqueta(etiquetaFila);
                    if (filaOriginal != null) 
                    {
                        // Obtener el dato de la fila original y agregarlo a la nueva columna
                        Dato dato = filaOriginal.getDato(etiquetaColumna, this);
                        columnaNueva.agregarDatoColumna(dato);
                    }
                }
    
                // Agregar la nueva columna al DataFrame
                columnaNueva.setCantDatos();
                vistaRed.dataColumnar.add(columnaNueva);
                vistaRed.ColumnArray.add(etiquetaColumna);
                vistaRed.columnMap.put(etiquetaColumna, columnaNueva);
            }
        }
        vistaRed.contarRegistros();
        vistaRed.contarColumnas(); // Actualizar el contador de columnas
    
        // Copiar las filas seleccionadas y actualizar etiquetasFilas
        for (String etiquetaFila : etiquetasFilas) 
        {
            Fila filaOriginal = rowMap.get(etiquetaFila);
            if (filaOriginal != null) 
            {
                // Crear una nueva fila para la vista reducida
                Fila filaNueva = new Fila(etiquetaFila);
    
                // Obtener los datos relevantes de las columnas seleccionadas
                for (String etiquetaColumna : etiquetasColumnas) 
                {
                    // Obtener el dato de la columna original y agregarlo a la nueva fila
                    Dato dato = filaOriginal.getDato(etiquetaColumna, this);
                    filaNueva.agregarDatoNuevaFila(dato);
                }
    
                // Agregar la nueva fila al DataFrame
                vistaRed.dataFilas.add(filaNueva);
                vistaRed.RowArray.add(etiquetaFila);
                vistaRed.rowMap.put(etiquetaFila, filaNueva);
            }
        }
                vistaRed.contarRegistros(); // Actualizar el contador de registros
    
        return vistaRed;
    }
    
    //------------------------------------------------------------------------------------------------------------------

    // METODO PARA CONCATENAR DOS DATAFRAME
    // Método para concatenar dos DataFrames verticalmente

    public DataFrame concatenar(DataFrame otroDataFrame) 
    {    
        // Verificar que ambos DataFrames tengan las mismas columnas
        if (!this.getAllHeaderColumn().equals(otroDataFrame.getAllHeaderColumn())) {

            throw new IllegalArgumentException("Los DataFrames tienen columnas diferentes y no se pueden concatenar.");
        }

        // Crear una nueva instancia de DataFrame para almacenar la concatenación

        if (this.getNroRegistros() == 0 && otroDataFrame.getNroRegistros() == 0) 
        {
            System.out.println("Ambas estructuras están vacías.");
            return this;  // Retorna una estructura vacía si ambos DataFrames están vacíos
        }

        // clonamos la primera estructura
        DataFrame nuevaEstructura = this.clone();

        // concatena los datos columnares
        for (int i = 0; i < otroDataFrame.getNroColumnas(); i++) 
        {
            Columna nuevaColumna = otroDataFrame.getColumna(i).clone();

            for (Dato datoCol: nuevaColumna.listaDatos)
                nuevaEstructura.dataColumnar.get(i).agregarDatoColumna(datoCol);
            
            nuevaEstructura.dataColumnar.get(i).setCantDatos();
            nuevaEstructura.columnMap.put(nuevaEstructura.dataColumnar.get(i).getEtiqueta(), nuevaEstructura.dataColumnar.get(i));
            nuevaEstructura.contarRegistros();
            nuevaEstructura.contarColumnas();
        }

        // Concatenar las filas del segundo DataFrame

        for (int i = 0; i < otroDataFrame.getNroRegistros(); i++) 
        {
           //Object[] datosFila = new Object[otroDataFrame.getNroColumnas()];
            Fila nuevaFila = otroDataFrame.getFila(i).clone();
            Integer indice =  (nuevaEstructura.dataFilas.size()) + 1;
            nuevaFila.setEtiqueta(Integer.toString(indice));

            nuevaEstructura.dataFilas.add(nuevaFila);
            nuevaEstructura.rowMap.put(nuevaFila.getEtiqueta(), nuevaFila);
            nuevaEstructura.RowArray.add(nuevaFila.getEtiqueta());
            nuevaEstructura.contarRegistros();
            nuevaEstructura.contarColumnas();
        }

        // Actualizar el contador de registro
        nuevaEstructura.contarRegistros();
        nuevaEstructura.contarColumnas();

        return nuevaEstructura;
    } 

    //----------------------------------------------------------------------------------------------------------------

    public Fila getFilasColumnasListaEtiquetas(String etiquetasFila, String[] etiquetasColumna) 
    {
        int totalColumnas = etiquetasColumna.length;
        Dato[] newDato = new Dato[totalColumnas];
        for (int x=0; x < totalColumnas; x++)
        {
            int posCol = this.getPosicicionColumnaEtiqueta(etiquetasColumna[x]);
            newDato[x] = (this.getFilaPorEtiqueta(etiquetasFila)).getDato(posCol) ;
        }
        Fila newFila = new Fila("0", newDato);
    
        return newFila;
    }

    public void orderPorColumnas (String [] ColumnasOrden)
    {
        int n = this.RowArray.size();

        boolean huboCambio;
        do {
        
            huboCambio = false;
            for (int i=1; i<n;i++)
            {
                String etiquetaPrevia = this.RowArray.get(i-1);
                String etiquetaActual = this.RowArray.get(i);

                Fila filaPrevia = this.getFilasColumnasListaEtiquetas(etiquetaPrevia, ColumnasOrden); // ####
                Fila filaActual = this.getFilasColumnasListaEtiquetas(etiquetaActual, ColumnasOrden);
                int valorCompare = filaPrevia.compareTo(filaActual);
                //if ( (filaPrevia.compareTo(filaActual)) > 0 )
                if ( (valorCompare) > 0 )
                {
                    this.RowArray.set(i-1, etiquetaActual);
                    this.RowArray.set(i, etiquetaPrevia);
                    huboCambio = true;
                }
            }
            n--;
        } while (huboCambio == true);
    }

    //////////////////////////////////////////////////////////////////////////////
    public int CantidadNA ()
    {
        int cantNA = 0;
        for (int i=0; i< this._nroColumnas;i++)
        {
            Columna columna = this.dataColumnar.get(i);
            for (Dato datos: columna.listaDatos)
            {
                if (datos.isNA())
                    cantNA++;
            }
        }
        return cantNA;
    }

    public void sacarNAs(int defaultNum, String defaultStr, Boolean defaultBool)
    {
        this.sacarNAs(defaultBool);
        this.sacarNAs(defaultStr);
        this.sacarNAs(defaultNum);
    }

    public void sacarNAs(int defaultNum, String defaultStr)
    {
        this.sacarNAs(defaultStr);
        this.sacarNAs(defaultNum);
    }
    public void sacarNAs(int defaultNum)
    {
        for (int xFila=0; xFila < this._nroRegistros; xFila++)
        {
            Fila filaActual = this.dataFilas.get(xFila);
            for (int xColumna=0; xColumna < this._nroColumnas;xColumna++)
            {
                Columna columnaActual = this.dataColumnar.get(xColumna); 
                Dato datoActual = filaActual.getDato(xColumna);

                if (datoActual.isNA())
                {
                    if (columnaActual.getTipoDato() == "Numerica")
                    {
                        Dato nuevoDato = new Dato_Numerico(defaultNum);
                        try
                        {
                            this.dataColumnar.get(xColumna).reemplazaDato(xFila, nuevoDato);
                        } catch (IndiceFueraDeRangoException e) 
                            {
                                System.err.println("Error al establecer el valor: " + e.getMessage());
                            }
                    
                        this.dataFilas.get(xFila).reemplazarDato(xColumna, nuevoDato);;
                    }
                }
            }
        }   
    }

    public void sacarNAs(String defaultStr)
    {
        for (int xFila=0; xFila < this._nroRegistros; xFila++)
        {
            Fila filaActual = this.dataFilas.get(xFila);
            for (int xColumna=0; xColumna < this._nroColumnas;xColumna++)
            {
                Columna columnaActual = this.dataColumnar.get(xColumna); 
                Dato datoActual = filaActual.getDato(xColumna);

                if (datoActual.isNA())
                {
                    if (columnaActual.getTipoDato() == "String")
                    {
                        Dato nuevoDato = new Dato_String(defaultStr);
                        try
                        {
                            this.dataColumnar.get(xColumna).reemplazaDato(xFila, nuevoDato);
                        } catch (IndiceFueraDeRangoException e) 
                            {
                                System.err.println("Error al establecer el valor: " + e.getMessage());
                            }
                    
                        this.dataFilas.get(xFila).reemplazarDato(xColumna, nuevoDato);;
                    }
                }
            }
        }   
    }

    public void sacarNAs(Boolean defaultBool)
    {
        for (int xFila=0; xFila < this._nroRegistros; xFila++)
        {
            Fila filaActual = this.dataFilas.get(xFila);
            for (int xColumna=0; xColumna < this._nroColumnas;xColumna++)
            {
                Columna columnaActual = this.dataColumnar.get(xColumna); 
                Dato datoActual = filaActual.getDato(xColumna);

                if (datoActual.isNA())
                {
                    if (columnaActual.getTipoDato() == "Boolean")
                    {
                        Dato nuevoDato = new Dato_Boolean(defaultBool);
                        try
                        {
                            this.dataColumnar.get(xColumna).reemplazaDato(xFila, nuevoDato);
                        } catch (IndiceFueraDeRangoException e) 
                            {
                                System.err.println("Error al establecer el valor: " + e.getMessage());
                            }                
                        this.dataFilas.get(xFila).reemplazarDato(xColumna, nuevoDato);;
                    }
                }
            }
        }   
    }

    
    public static void  vistaReducida (DataFrame df, List<String> etiquetasColumnas, List<String> etiquetasFilas ){

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   IMPRESION DE UNA VISTA REDUCIDA (SLICING)");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        DataFrame vistaRed = df.seleccionarVista(etiquetasFilas, etiquetasColumnas);

        //CsvPrinter.imprimirColumnar(vistaRed);
        CsvPrinter.imprimirPorFilas(vistaRed);

    }

    public DataFrame seleccionarFilasAleatorias(double porcentaje) {
        // Obtén la lista de etiquetas de filas completa
        List<String> todasLasFilas = new ArrayList<>(rowMap.keySet());

        // Calcula cuántas filas seleccionar aleatoriamente
        int totalFilas = todasLasFilas.size();
        int filasASeleccionar = (int) Math.ceil((porcentaje ) * totalFilas);
        // Realiza la selección aleatoria de filas
        Collections.shuffle(todasLasFilas);
        
        List<String> filasSeleccionadas = todasLasFilas.subList(0, filasASeleccionar);

        // Obtén todas las etiquetas de columnas
        List<String> todasLasColumnas = new ArrayList<>(ColumnArray);
        
        // Llama a tu método existente para seleccionar la vista reducida
        return seleccionarVista(filasSeleccionadas, todasLasColumnas);
    }

    public static void muestreoAleatorio(DataFrame df, double porcentaje){

        System.out.println(" ");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println("   IMPRESION DE UNA VISTA REDUCIDA ALEATORIA (RANDOM SLICING)");
        System.out.println("#-----------------------------------------------------------------------------");
        System.out.println(" ");

        DataFrame dfSeleccionado = df.seleccionarFilasAleatorias(porcentaje);

        //CsvPrinter.imprimirColumnar(dfSeleccionado); 
        CsvPrinter.imprimirPorFilas(dfSeleccionado);

    }
}
