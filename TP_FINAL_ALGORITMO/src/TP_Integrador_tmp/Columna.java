package TP_Integrador_tmp;

import java.lang.Cloneable;
import java.util.ArrayList;
import java.util.List;
public class Columna implements Cloneable{
    private String tipoDato; // Tipo de dato de la columna
    private Integer _cantDatos; // Cantidad Datos
    public Dato[] listaDatos;   //Array de datos de la columna

    protected String etiqueta; //Etiqueta de la columna
    protected List<Dato> datos;

public Columna()
{
        this.listaDatos = null;
        this.tipoDato = "Vacia";
        this._cantDatos = 0;
        this.datos = new ArrayList<>();
}

// ******* Nuevo constructor ************************

public Columna(String etiqueta) {
    this.etiqueta = etiqueta;
    this.listaDatos = null;
    this.tipoDato = "Vacia";
    this._cantDatos = 0;
    this.datos = new ArrayList<>(); // Inicializa la lista de datos
}

//**************************************************** 

public void setColumna (Dato[] datos, String tipo)
{
    this.listaDatos = datos;
    this.tipoDato = tipo;
    this._cantDatos = datos.length;
}

public void setEtiqueta(String etiqueta)
{
    this.etiqueta = etiqueta;
}


public Integer getCantDatos()
{
    return this._cantDatos;
}

public String getTipoDato()
{
    return this.tipoDato;
}
   
public Dato getDato (Integer indice)
{
    return this.listaDatos[indice];
}
//--------------------------------------------------------------------------
// METODO PARA SETEAR DATO DE LA COLUMNA POR POSICION FILA

public class IndiceFueraDeRangoException extends Exception {
    public IndiceFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}

public void setDato (Integer indice, Object Valor) throws IndiceFueraDeRangoException{
    if (indice >= 0 && indice < listaDatos.length) {
        listaDatos[indice].setValor(Valor);
    } else {
        // Lanza una excepción con un mensaje personalizado.
        throw new IndiceFueraDeRangoException("Índice fuera de rango: " + indice);
    }
}
//--------------------------------------------------------------------------
public String getEtiqueta() {
    return etiqueta;
}
// Implementación del método clone -------------------------------------------------
    @Override
    public Columna clone() {
        try {
            //-----------------------------------------------
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
    } catch (CloneNotSupportedException e) {
        throw new AssertionError("La clonación no es compatible");
    }   
}
//----------------------------------------------------------------------------------
}
