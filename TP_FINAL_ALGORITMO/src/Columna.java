//package TP_FINAL_ALGORITMO;

public class Columna 
{
    private String tipoDato;
    private Integer _cantDatos;
    public Dato[] listaDatos;

    private String etiqueta; 

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
}
