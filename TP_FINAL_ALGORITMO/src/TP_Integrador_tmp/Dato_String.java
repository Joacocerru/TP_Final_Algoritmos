package TP_Integrador_tmp;


public class Dato_String extends Dato {
    
    public Dato_String(){

        this.valor = "";
    }

    public Dato_String(String s){

        this.valor = new String(s);
    }

    public Dato_String(Integer s){

        this.valor = new String();
        this.valor = Integer.toString(s);
    }

    public String getDato(){

        return this.valor.toString();
    }

    public void setValor (Object x){

        this.valor = x.toString();
    }

    public String getTipoDato (){

        return "String" ;
    }

    public boolean isNA (){

        return false;
    } 

    @Override
    public String toString() {
        if (valor != null) {
            return ((String) valor).toString();    // Devuelve simplemente el valor de tipo String
        } 
        else {
            return "NA";  // O cualquier otra representación que desees para valores nulos
        }
    }

    @Override
    public Object clone() {
        return new Dato_String((String) valor); // Clona la instancia de Dato_String
    }
    
}
