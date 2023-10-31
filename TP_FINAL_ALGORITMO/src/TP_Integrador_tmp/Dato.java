package TP_Integrador_tmp;
import java.lang.Cloneable;

public class Dato  implements Cloneable, Comparable<Dato>
{
   public Object valor;    


//Constructor   
public Dato()
{
    this.valor = new Object();
}

public Dato(Object x)
{
    this.valor = new Object();
    this.valor = x;
}

public Object getDato()
{
    return this.valor;
}

public String getDatoToString()
{
    return this.valor.toString();
}

public boolean isNA ()
{
    return false;
}

public void setValor (Object x)
{
    this.valor = x;
}

public String getTipoDato ()
{
   return "Object" ;
}

public String printValor()
{
    return this.valor.toString();
}

@Override
public Object clone() throws CloneNotSupportedException
{
    Dato newDato = new Dato(this.valor);
    return newDato;
}

@Override
public int compareTo(Dato otroDato) 
{ 
    //return this.valor.toString().compareTo(o.valor.toString());
    if (this.isNA() )
        return -1;
    
    if (this.valor instanceof Number && (otroDato.valor instanceof Number || otroDato instanceof Dato_NA) ) 
    {
        if (otroDato.isNA() )
            return 1; // comparamos un Nro y un NA
        else
            {
                // Ambos valores son números
                double valorThis = ((Number) this.valor).doubleValue();
                double valorOtro = ((Number) otroDato.valor).doubleValue();

                if (valorThis < valorOtro)
                    return -1; // this < otroDato
                else if (valorThis > valorOtro)
                    return 1; // this > otroDato
                else
                    return 0; // this == otroDato
            }
    } 
    
    if (this instanceof Dato_String && (otroDato instanceof Dato_String || otroDato instanceof Dato_NA) ) 
    {
        if (otroDato.isNA() )
            return 1; // comparamos un Nro y un NA
        else
            {
                // Ambos valores son instancias de Dato_String
                String valorThis = (String) this.valor;
                String valorOtro = (String) otroDato.valor;
                return valorThis.compareTo(valorOtro);
            }
    } 

    if (this instanceof Dato_Boolean && (otroDato instanceof Dato_Boolean || otroDato instanceof Dato_NA) ) 
    {
        if (otroDato.isNA() )
            return 1; // comparamos un Nro y un NA
        else
            {
                // Maneja la comparación específica de Dato_Boolean
                boolean valorThis = (Boolean) this.valor;
                boolean valorOtro = (Boolean) otroDato.valor;

                if (valorThis == valorOtro)
                    return 0; // this == otroDato
                else if (valorThis)
                    return 1; // this == true y otroDato == false
                else
                    return -1; // this == false y otroDato == true
            }
    }
    else 
    {
        throw new UnsupportedOperationException("Los valores no son comparables");
    }
}

}