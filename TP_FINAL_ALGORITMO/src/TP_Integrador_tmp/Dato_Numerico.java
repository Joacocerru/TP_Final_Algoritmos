package TP_Integrador_tmp;


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
public String toString() {
    if (valor != null) {
        return valor.toString();  // Convierte el número a una cadena
    } else {
        return "NA";  // O cualquier otra representación que desees para valores nulos
    }
    }

    @Override
    public Object clone() {
    if (valor instanceof String) {
        return new Dato_Numerico((String) valor);
    } else if (valor instanceof Integer) {
        return new Dato_Numerico((int) valor);
    } else if (valor instanceof Long) {
        // Convierte el valor Long a int y luego crea un nuevo Dato_Numerico
        return new Dato_Numerico(((Long) valor).intValue());
    } else {
        throw new UnsupportedOperationException("Tipo de dato no compatible en Dato_Numerico.");
    }
}
}