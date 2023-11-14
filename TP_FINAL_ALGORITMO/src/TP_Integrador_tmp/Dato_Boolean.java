package TP_Integrador_tmp;


public class Dato_Boolean extends Dato {
    
    public Dato_Boolean(Boolean s)
    {
        this.valor = new Boolean(s);
    }

    public Dato_Boolean(Object s)
    {
        this.valor = Boolean.parseBoolean(s.toString());
    }

    public Object getDato()
    {
        return this.valor;
    }

    public void setValor (Object x)
    {
        this.valor = Boolean.parseBoolean(x.toString()); 
    }

    // Constructor y otros métodos
    
    @Override
    public String toString() {
        return Boolean.toString((Boolean) this.valor);  // Convierte el booleano a una cadena
    }
    @Override
    public Object clone() {
    return new Dato_Boolean((boolean) valor); // Clona la instancia de Dato_Boolean
    }
}