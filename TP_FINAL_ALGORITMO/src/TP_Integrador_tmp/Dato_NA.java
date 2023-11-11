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
    @Override
    public String toString() {
        return "NA";  // Devuelve una cadena que indica la ausencia de datos
    }
    @Override
    public Object clone() {
        return new Dato_NA(); // Clona la instancia de Dato_NA
    }
}