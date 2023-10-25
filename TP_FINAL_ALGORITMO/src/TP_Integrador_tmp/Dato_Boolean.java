package TP_Integrador_tmp;


public class Dato_Boolean extends Dato {
    
    public Dato_Boolean(Boolean s)
    {
        this.valor = new Boolean(s);
    }

    public Dato_Boolean(Object s)
    {
        this.valor = Boolean.parseBoolean(s.toString().toUpperCase());
    }

    public Object getDato()
    {
        return this.valor;
    }

    public void setValor (Object x)
    {
        this.valor = Boolean.parseBoolean(x.toString().toUpperCase()); 
    }

    
}