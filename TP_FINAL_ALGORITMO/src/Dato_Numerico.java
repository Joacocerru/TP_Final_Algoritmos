//package TP_FINAL_ALGORITMO;


public class Dato_Numerico extends Dato {
    
    public Dato_Numerico(String s)
    {
        this.valor = new Long(s);
    }

    public Dato_Numerico(int s)
    {
        super.valor = new Long(s);
    }

    public Object getDato()
    {
        return this.valor;
    }

    public void setValor (Object x)
    {
        this.valor = Long.parseLong(x.toString());
    }


}