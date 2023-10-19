//package TP_FINAL_ALGORITMO;


public class Dato_String extends Dato {
    
    public Dato_String()
    {
        this.valor = "";
    }

    public Dato_String(String s)
    {
        this.valor = new String(s);
    }

    public Dato_String(Integer s)
    {
        this.valor = new String();
        this.valor = Integer.toString(s);
    }

    public String getDato()
    {
        return this.valor.toString();
    }

    public void setValor (Object x)
    {
        this.valor = x.toString();
    }

    public String getTipoDato ()
    {
        return "String" ;
    }

    public boolean isNA ()
    {
        return false;
    }


}
