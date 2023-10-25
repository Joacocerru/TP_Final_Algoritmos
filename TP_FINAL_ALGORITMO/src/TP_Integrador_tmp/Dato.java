package TP_Integrador_tmp;
import java.lang.Cloneable;

public class Dato  implements Cloneable
{
   public Object valor;    


//Constructor   
public Dato()
{
    this.valor = new Object();
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
public Object clone() throws CloneNotSupportedException{
    //try {
        Dato copia = (Dato) super.clone();

        if (this.valor instanceof Cloneable) {

            copia.valor = ((Cloneable) this.valor).clone();
            
        } else {
        // Si el valor no es Cloneable, 
        //crea una copia del valor directamente
            copia.valor = this.valor;
        }

        return copia;
    } //catch (CloneNotSupportedException e) {
        //throw new AssertionError();
    //}
//}


}