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
    //try {
    //    Dato copia = (Dato) super.clone();
    //    if (this.valor instanceof Cloneable) {
    //        copia.valor = ((Cloneable) this.valor).clone();      
    //    } else {
        // Si el valor no es Cloneable, 
        //crea una copia del valor directamente
    //        copia.valor = this.valor;
    //    }
    //    return copia;
    //} //catch (CloneNotSupportedException e) {
        //throw new AssertionError();
    //}
}

@Override
public int compareTo(Dato o) 
{ 
    return this.valor.toString().compareTo(o.valor.toString());
}

}