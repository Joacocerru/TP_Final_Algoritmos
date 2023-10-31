package TP_Integrador_tmp;

import javax.lang.model.util.ElementScanner6;

import javafx.geometry.Orientation;

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

    @Override
    public int compareTo(Dato o) 
    { 
        Long ori = (Long) this.valor;

        if (o.isNA())
            return 1;
        
        Long des = (Long) o.valor;
 
        return ori.compareTo(des);
    }

}