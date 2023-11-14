package TP_Integrador_tmp;

import java.lang.Cloneable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Dato implements Cloneable, Comparable<Dato>{

    protected Object valor;    

    // Constructor  -----------------------------------------------------------------
    
    public Dato(){ 

        this.valor = new Object();
    }

    public Dato(Object valor) {

        this.valor = valor;
    }

    //------------------------------------------------------------

    public Object getDato(){

        return this.valor;
    }

    public boolean isNA (){

        return false;
    }

    public void setValor (Object x){

        this.valor = x;
    }

    public String getTipoDato (){

        return "Object" ;
    }

    public String printValor(){

        return this.valor.toString();
    }


    //------------------------------------------------------------ 


    @Override
    public Object clone(){
        try {
            // Realiza una copia profunda del objeto Dato
            Dato copia = (Dato) super.clone();

            // Si el valor es clonable, se clona
            if (this.valor instanceof Cloneable) {
                copia.valor = ((Object) this.valor).clone();
            }

            return copia;

        } 
        catch (CloneNotSupportedException e) {
            throw new AssertionError("La clonación no es compatible");
        }
    }
    
    
    //------------------------------------------------------------


    @Override
    public int compareTo(Dato otroDato) {

        if (otroDato instanceof Dato_NA)
            return 1;
            
            if (this.valor instanceof Number && otroDato.valor instanceof Number) {
                // Ambos valores son números
                double valorThis = ((Number) this.valor).doubleValue();
                double valorOtro = ((Number) otroDato.valor).doubleValue();

                if (valorThis < valorOtro) {
                    return -1; // this < otroDato
                }   
                else if (valorThis > valorOtro) {
                    return 1; // this > otroDato
                } 
                else {
                return 0; // this == otroDato
                }
            } 

            if (this instanceof Dato_String && otroDato instanceof Dato_String) {
                // Ambos valores son instancias de Dato_String
                String valorThis = (String) this.valor;
                String valorOtro = (String) otroDato.valor;
                return valorThis.compareTo(valorOtro);
            } 

            if (this instanceof Dato_Boolean && otroDato instanceof Dato_Boolean) {
                // Maneja la comparación específica de Dato_Boolean
                boolean valorThis = (Boolean) this.valor;
                boolean valorOtro = (Boolean) otroDato.valor;

                if (valorThis == valorOtro) {
                    return 0; // this == otroDato
                } 
                else if (valorThis) {
                    return 1; // this == true y otroDato == false
                } 
                else {
                    return -1; // this == false y otroDato == true
                }
            }
        else {
            throw new UnsupportedOperationException("Los valores no son comparables");
        }
    }
    //------------------------------------------------------------
    }
    //------------------------------------------------------------