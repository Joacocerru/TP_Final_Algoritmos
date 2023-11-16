package TP_Integrador_tmp;

import java.lang.Cloneable;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class Columna implements Cloneable
{

    private String tipoDato; // Tipo de dato de la columna
    private Integer _cantDatos; // Cantidad Datos
    public Dato[] listaDatos;   //Array de datos de la columna

    protected String etiqueta; //Etiqueta de la columna
    protected List<Dato> datos;
    //protected List<Dato> Nuevodato = new ArrayList<>();

    public Columna(){

    this.listaDatos = null;
    this.tipoDato = "Vacia";
    this._cantDatos = 0;
    this.datos = new ArrayList<>();
    }


    //--------------------------------------------------------------------
    // Nuevo constructor con parametro etiqueta

    public Columna(String etiqueta) {
        
        this.etiqueta = etiqueta;
        this.listaDatos = null;
        this.tipoDato = "Vacia";
        this._cantDatos = 0;
        this.datos = new ArrayList<>(); // Inicializa la lista de datos
    }


    //-------------------------------------------------------------------- 
    // METODO PARA SETEAR UN ARRAY DE DATOS DE LA COLUMNA

    public void setColumna (Dato[] datos, String tipo){

        this.listaDatos = datos;
        this.tipoDato = tipo;
        this._cantDatos = datos.length;
    }


    //--------------------------------------------------------------------
    // METODO PARA SETEAR LA ETIQUETA

    public void calcularCantDatos() {

        this._cantDatos = listaDatos.length;
    }

    public void setEtiqueta(String etiqueta){

        this.etiqueta = etiqueta;
    }


    //------------------------------------------------------------------------
    // METODO PARA OBTENER CANTIDAD DE DATOS
    public Integer getCantDatos(){

        return this._cantDatos;
    }


    //--------------------------------------------------------------------------
    // METODO PARA OBTENER TIPO DE DATO
    public String getTipoDato(){

        return this.tipoDato;
    }


    //---------------------------------------------------------------------
    // METODO PARA OBTENER DATO DE LA COLUMNA POR POSICION
    public Dato getDato (Integer indice){

        return this.listaDatos[indice];
    }
    

    //--------------------------------------------------------------------------
    // METODO PARA SETEAR DATO DE LA COLUMNA POR POSICION FILA

    public class IndiceFueraDeRangoException extends Exception {
        
        public IndiceFueraDeRangoException(String mensaje) {
            super(mensaje);
        }

    }


    //------------------------------------------------------------------------
    // METODO PARA SETEAR DATO DE LA COLUMNA POR POSICION
    public void setDato (Integer indice, Object Valor) throws IndiceFueraDeRangoException{
        
        if (indice >= 0 && indice < listaDatos.length) {
            
            listaDatos[indice].setValor(Valor);
        } 
        else {

            // Lanza una excepción con un mensaje personalizado.
            throw new IndiceFueraDeRangoException("Índice fuera de rango: " + indice);
        }
    }


    //-------------------------------------------------------------------------------------
    // METODO PARA OBTENER ETIQUETA
    public String getEtiqueta() {
        
        return etiqueta;
    }


    //----------------------------------------------------------------------------------------------
    // Implementación del método clone 
    @Override
    public Columna clone() {
        
        try {  
        // Clona la estructura principal (Columna)
        Columna copia = (Columna) super.clone();
        // Realiza una copia profunda de la lista de datos (arreglo)

        if (listaDatos != null) {
            copia.listaDatos = new Dato[listaDatos.length];

            for (int i = 0; i < listaDatos.length; i++) {

                if (listaDatos[i] instanceof Cloneable) {

                    copia.listaDatos[i] = (Dato) listaDatos[i].clone();

                } else {
                    // Maneja el caso en el que el dato no es clonable (ajusta esto según tus necesidades)
                    copia.listaDatos[i] = listaDatos[i]; // Si no es clonable, agrega la referencia directa
                }
            }
        }
           
        return copia;

        } 
        catch (CloneNotSupportedException e) {
            
            throw new AssertionError("La clonación no es compatible");
        }   

    }

    //----------------------------------------------------------------------------------
    /* 
    public void agregarDato(Dato nuevoDato) {
        datos.add(nuevoDato);
    }
    */

    public void removeFila (int fila){   

        //listaDatos

        Dato[] tmpColData = new Dato[ this.getCantDatos()];

        for(int i=0, k=0; i<this.getCantDatos(); i++){
            
            if(i!= fila){

                tmpColData[k] = this.listaDatos[i];
                k++;
            }
        }
        
        this.listaDatos = tmpColData;
    }


    //-------------------------------------------------------------------

    public void sumarCantRegistro(){

        this._cantDatos++;
    }

    public void restarCantRegistro(){
        
        this._cantDatos--;
    }

    public void agregarDatoColumna(Dato dato) 
    {
        // Verificar si la lista de datos es nula
        if (listaDatos == null) 
        {
            listaDatos = new Dato[1];
            // Clonar el dato antes de agregarlo
            listaDatos[0] = (Dato) dato.clone();
        } else 
        {
            // Crear un nuevo array con un tamaño mayor
            Dato[] nuevoArray = new Dato[listaDatos.length + 1];
    
            // Copiar los datos existentes al nuevo array
            System.arraycopy(listaDatos, 0, nuevoArray, 0, listaDatos.length);
    
            // Clonar el dato antes de agregarlo al final del nuevo array
            nuevoArray[listaDatos.length] = (Dato) dato.clone();
    
            // Actualizar la referencia al nuevo array
            listaDatos = nuevoArray;
        }
    }

    //----------------------------------------------------------------------------------
}

