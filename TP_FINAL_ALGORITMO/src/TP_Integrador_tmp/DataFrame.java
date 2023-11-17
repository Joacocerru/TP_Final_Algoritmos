
package TP_Integrador_tmp;

import java.util.ArrayList;
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


    public DataFrame() {

        this.dataColumnar = new ArrayList<>();
        this.dataFilas = new ArrayList<>();
        this.ColumnArray = new ArrayList<>();
        this.RowArray = new ArrayList<>();
        this.columnMap = new HashMap<>();
        this.rowMap = new HashMap<>();
        this._nroColumnas = 0;
        this._nroRegistros = 0;
    }


    //-------------------------------------------------------------------------------
    // CONSTRUCTOR - CON LECTURA CSV

    public DataFrame(String csvFile, String csvDelimiter, String headerSN) {
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


    public Fila getFilaPorEtiqueta(String etiquetaFila) {

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
    
        Columna tmpColumna = getColumnaPorEtiqueta(etiquetaColumna);
        Integer posFila = this.getPosicionFilaEtiqueta(etiquetafila);
        
        if ( tmpColumna == null || posFila == null)
            throw new NullPointerException ("Fila/Columna inexistente");
        
        return tmpColumna.getDato(posFila);
    }


    public Dato getValorPosicion(Integer posFila, Integer posColumna) {

        String tmpEtiquetaColumna = this.getColumna(posColumna).getEtiqueta();
        String tmpEtiquetaFila = this.getFila(posFila).getEtiqueta();
        
        return this.getValor(tmpEtiquetaFila, tmpEtiquetaColumna);
    }


    //-------------------------------------------------------------------------------------
    // METODO SETVALOR

    public void setValorDataFrame(String etiquetafila, String etiquetaColumna, Object Valor) {

        Columna tmpColumna = getColumnaPorEtiqueta(etiquetaColumna);
        Integer posFila = this.getPosicionFilaEtiqueta(etiquetafila);

        try {

            tmpColumna.setDato(posFila, Valor);
        } 
        catch (IndiceFueraDeRangoException e) {
        
            System.err.println("Error al establecer el valor: " + e.getMessage());
        }
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
    // Método para imprimir etiquetas de las filas 

    public void imprimirEtiquetasFilas() {

        System.out.print("Etiquetas de las filas: ");

        for (String etiqueta : rowMap.keySet()) {

            System.out.print(etiqueta + " ");
        }

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
        
        int cantidadColumnas = getNroColumnas();
    
        // Verificar si la columna existe
        if (!columnMap.containsKey(etiquetaColumna)) {
            System.out.println("La columna con etiqueta " + etiquetaColumna + " no existe.");
            return;
        }

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
    }


    //----------------------------------------------------------------------------------------------------------------
    // METODO PARA ELIMINAR UNA FILA DADA SU ETIQUETA
    
    public void eliminarFila(String etiquetaFila) {

        int cantidadFilas = getNroRegistros();

        // Verificar si la fila existe
        if (!rowMap.containsKey(etiquetaFila)) {
            System.out.println("La fila con etiqueta " + etiquetaFila + " no existe.");
            return;
        }

        // Obtener la posición de la fila
        int posicion = getPosicionFilaEtiqueta(etiquetaFila);

        // Eliminar la fila de dataFilas y RowArray
        dataFilas.remove(posicion);
        RowArray.remove(etiquetaFila);

        // Actualizar el rowMap
        rowMap.remove(etiquetaFila);

        // Actualizar la posición de las filas restantes en rowMap
        rowMap.clear();

        for (int i = 0; i < cantidadFilas-1; i++) {   
            String etiquetaActual = RowArray.get(i);
            rowMap.put(etiquetaActual, dataFilas.get(i));
        }

        // Actualizar el número de filas
        this.contarRegistros();

        // Actualiza los datos de columna de las filas
     
        for (int i=0; i < this.getNroColumnas(); i++){

            this.dataColumnar.get(i).removeFila(posicion);
            this.dataColumnar.get(i).restarCantRegistro();
        }

        this.contarRegistros();

    }


    //--------------------------------------------------------------------------------
    @Override
    public DataFrame clone() {

        try {
            // Clonar el objeto en sí
            DataFrame copiaEstructura = (DataFrame) super.clone();


            // Realizar una copia profunda de las columnas
            copiaEstructura.dataColumnar = new ArrayList<>();

            for (Columna columna : this.dataColumnar) 
            {
                Columna columnaCopia = columna.clone();
                copiaEstructura.dataColumnar.add(columnaCopia);
            }

            copiaEstructura.columnMap = new HashMap<>(this.columnMap);
            
            for (String keys: this.columnMap.keySet())
            {
                Columna columnacopia = this.columnMap.get(keys).clone();
                copiaEstructura.columnMap.put(keys, columnacopia);
            }
            

            // Realizar una copia profunda de las columnas
            copiaEstructura.dataFilas = new ArrayList<>();

            for (Fila filas : this.dataFilas) {
                Fila filaCopia = filas.clone();
                copiaEstructura.dataFilas.add(filaCopia);
            }

            copiaEstructura.rowMap = new HashMap<>();
            
            for (String keys: this.rowMap.keySet())
            {
                Fila filaCopia = this.rowMap.get(keys).clone();
                copiaEstructura.rowMap.put(keys, filaCopia);
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


    public void FiltroPorColumna(String Etiquetacolumna, int operacion, Object valorBuscado){// -1 Menor que , 0 igual que, 1 Mayor que 
        
        if (operacion !=0 && operacion !=-1 && operacion !=1){   
            // exception
        }

        DataFrame copiaDF = this.clone();
        int tmpColumna = this.getPosicicionColumnaEtiqueta(Etiquetacolumna);

        for (int i = 0; i < this.getNroRegistros(); i++) {

            Dato valorEncontrado = this.getValorPosicion(i, tmpColumna);

            if (valorBuscado.getClass() == valorEncontrado.getClass()) {

                if (valorBuscado instanceof Dato)  {

                    Dato valorDatoBuscado = (Dato) valorBuscado;
                    int comparacion = valorEncontrado.compareTo(valorDatoBuscado);

                    // if ( comparacion == operacion) 
                } 
            }
        }
    }


    public int buscarBinariaEnColumna(String Etiquetacolumna, Object valor) {

        Columna tmpColumna = this.getColumnaPorEtiqueta(Etiquetacolumna);
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

            if (datoMedio.equals(valor)) {
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
    public void clonarYAgregarColumna(String etiquetaColumnaExistente, String etiquetaNuevaColumna) {
        
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


    //-----------------------------------------------------------------------------------
    // METODO PARA AGREGAR UNA COLUMNA NUEVA AL DATAFRAME

    public void AgregarColumnaNueva(String etiquetaNuevaColumna, Columna columnaNueva) {
        
        // Verificar si la columna existente realmente existe
        //Columna columnaExistente = columnaNueva;

        if (columnaNueva != null) {
            // Clonar la columna existente
            //Columna nuevaColumna = columnaExistente.clone();
            
            // Establecer la etiqueta para la nueva columna
            columnaNueva.setEtiqueta(etiquetaNuevaColumna);

            // Agregar la nueva columna al DataFrame
            dataColumnar.add(columnaNueva);

            // Actualizar ColumnArray
            ColumnArray.add(etiquetaNuevaColumna);

            // Actualizar columnMap
            columnMap.put(etiquetaNuevaColumna, columnaNueva);
            
            this.contarColumnas();

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
		    for (String etiquetaColumna : etiquetasColumnas) {
		        Columna columnaOriginal = getColumnaPorEtiqueta(etiquetaColumna);
		        if (columnaOriginal != null) {
		            // Crear una nueva columna para la vista reducida
		            Columna columnaNueva = new Columna();
		
		            // Configurar la etiqueta de la nueva columna
		            columnaNueva.setEtiqueta(etiquetaColumna);
		
		            // Obtener los datos relevantes de las filas seleccionadas
		            for (String etiquetaFila : etiquetasFilas) {
		                Fila filaOriginal = getFilaPorEtiqueta(etiquetaFila);
		                if (filaOriginal != null) {
		                    // Obtener el dato de la fila original y agregarlo a la nueva columna
		                    Dato dato = filaOriginal.getDato(etiquetaColumna, this);
		                    columnaNueva.agregarDatoColumna(dato);
		                }
		            }
		
		            // Agregar la nueva columna al DataFrame
		            vistaRed.dataColumnar.add(columnaNueva);
		            vistaRed.ColumnArray.add(etiquetaColumna);
		            vistaRed.columnMap.put(etiquetaColumna, columnaNueva);
		        }
		    }
		    vistaRed.contarColumnas(); // Actualizar el contador de columnas
		
		    // Copiar las filas seleccionadas y actualizar etiquetasFilas
		    for (String etiquetaFila : etiquetasFilas) {
		        Fila filaOriginal = rowMap.get(etiquetaFila);
		        if (filaOriginal != null) {
		            // Crear una nueva fila para la vista reducida
		            Fila filaNueva = new Fila(etiquetaFila);
		
		            // Obtener los datos relevantes de las columnas seleccionadas
		            for (String etiquetaColumna : etiquetasColumnas) {
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
    /* 
    // METODO PARA CONCATENAR DOS DATAFRAME
    // Método para concatenar dos DataFrames verticalmente

    public DataFrame concatenar(DataFrame otroDataFrame) {
        
        // Verificar que ambos DataFrames tengan las mismas columnas

        if (!this.getAllHeaderColumn().equals(otroDataFrame.getAllHeaderColumn())) {

            throw new IllegalArgumentException("Los DataFrames tienen columnas diferentes y no se pueden concatenar.");
        }

        // Crear una nueva instancia de DataFrame para almacenar la concatenación

        DataFrame nuevaEstructura = new DataFrame();

        if (this.getNroRegistros() == 0 && otroDataFrame.getNroRegistros() == 0) {

            System.out.println("Ambas estructuras están vacías.");

            return nuevaEstructura;  // Retorna una estructura vacía si ambos DataFrames están vacíos
        }

        // Copiar las columnas y etiquetas

        for (String etiqueta : this.getAllHeaderColumn()) {

            nuevaEstructura.dataColumnar.add(this.getColumnaPorEtiqueta(etiqueta));

            nuevaEstructura.ColumnArray.add(etiqueta);

            nuevaEstructura.columnMap.put(etiqueta, this.getColumnaPorEtiqueta(etiqueta));
        }

        // Actualizar el contador de columnas

        nuevaEstructura.contarColumnas();

        // Concatenar las filas del primer DataFrame

        for (int i = 0; i < this.getNroRegistros(); i++) {

            Object[] datosFila = new Object[this.getNroColumnas()];

            for (int j = 0; j < this.getNroColumnas(); j++) {

                datosFila[j] = this.getValorPosicion(i, j);  // No es necesario clonar los valores
            }

            nuevaEstructura.dataFilas.add(new Fila(Integer.toString(nuevaEstructura.getNroRegistros()), datosFila));
            nuevaEstructura.rowMap.put(Integer.toString(nuevaEstructura.getNroRegistros() - 1),
            nuevaEstructura.dataFilas.get(nuevaEstructura.getNroRegistros() - 1));
            nuevaEstructura.RowArray.add(Integer.toString(nuevaEstructura.getNroRegistros() - 1));
        }

        // Concatenar las filas del segundo DataFrame

        for (int i = 0; i < otroDataFrame.getNroRegistros(); i++) {

            Object[] datosFila = new Object[otroDataFrame.getNroColumnas()];

            for (int j = 0; j < otroDataFrame.getNroColumnas(); j++) {

                datosFila[j] = otroDataFrame.getValorPosicion(i, j);  // No es necesario clonar los valores
            }

            nuevaEstructura.dataFilas.add(new Fila(Integer.toString(nuevaEstructura.getNroRegistros()), datosFila));
            nuevaEstructura.rowMap.put(Integer.toString(nuevaEstructura.getNroRegistros() - 1),
            nuevaEstructura.dataFilas.get(nuevaEstructura.getNroRegistros() - 1));
            nuevaEstructura.RowArray.add(Integer.toString(nuevaEstructura.getNroRegistros() - 1));
        }

        // Actualizar el contador de registros
        
        nuevaEstructura.contarRegistros();

        return nuevaEstructura;
    } */

//----------------------------------------------------------------------------------------------------------------
}
