package TP_Integrador_tmp;


public class Dato_NA extends Dato {
    
    public Dato_NA()
    {
        this.valor = new String();
        this.valor = "NA";
    }

    public Object getDato()
    {
        return this.valor;
    }

    public boolean isNA ()
    {
        return true;
    }

    public void setValor ()
    {
        this.valor = null;
    }

}