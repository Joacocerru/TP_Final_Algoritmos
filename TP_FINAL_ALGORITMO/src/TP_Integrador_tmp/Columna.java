package TP_Integrador_tmp;

import javax.lang.model.util.ElementScanner6;

public class Columna 
{
    private String tipoDato; // Tipo de dato de la columna
    private Integer _cantDatos; // Cantidad Datos
    public Dato[] listaDatos;   //Array de datos de la columna

    private String etiqueta; //Etiqueta de la columna

public Columna()
{
        this.listaDatos = null;
        this.tipoDato = "Vacia";
        this._cantDatos = 0;
}

// ******* Nuevo constructor ************************

public Columna(String etiqueta) {
    this.etiqueta = etiqueta;
    this.listaDatos = null;
    this.tipoDato = "Vacia";
    this._cantDatos = 0;
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

public String getEtiqueta() {
    return etiqueta;
}


public class IndiceFueraDeRangoException extends Exception {
    public IndiceFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}

public void setDato (Integer posicion, Object nuevoValor) throws IndiceFueraDeRangoException
{
    if (posicion < 0 || posicion > this.listaDatos.length)
        throw new IndiceFueraDeRangoException("Índice fuera de rango: " + posicion);

    if ( nuevoValor instanceof java.lang.String )
    {
        if ( this.tipoDato == "Boolean" && nuevoValor.toString().toUpperCase().equals("TRUE") )
        {
            this.listaDatos[posicion].setValor(nuevoValor);
        }
        if ( this.tipoDato == "Boolean" && nuevoValor.toString().toUpperCase().equals("FALSE") )
        {
            this.listaDatos[posicion].setValor(nuevoValor);
        }


        if ( this.tipoDato == "String" && nuevoValor.toString().equals("") )
        {
            // crear un nuevo dato NA y asignarlo
        }
        
        if ( this.tipoDato == "String" && this.listaDatos[posicion].isNA())
        {
            this.listaDatos[posicion] = new Dato_String(nuevoValor.toString().trim());
        }
        else if ( this.tipoDato == "String" && !this.listaDatos[posicion].isNA())
        {
            this.listaDatos[posicion].setValor(nuevoValor);
        }
        else 
        {
            // EXCEPCION
        }
    }

    if (nuevoValor instanceof java.lang.Number)
    {
        if ( this.tipoDato == "long" )
        {
            this.listaDatos[posicion].setValor(nuevoValor);
        }else if ( this.tipoDato == "String" )
        {
            this.listaDatos[posicion].setValor(nuevoValor);
        }else if ( this.tipoDato == "Boolean" && (Long) nuevoValor == 0 )
        {
            this.listaDatos[posicion].setValor("FALSE");
        }else if ( this.tipoDato == "Boolean" && (Long) nuevoValor == 1 )
        {
            this.listaDatos[posicion].setValor("TRUE");
        }
    }
}

}
