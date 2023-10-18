package TP_Integrador_tmp;


import java.util.List;

// ARMA ARRAYLIST CON VISION COLUMNAR

public class ArmaColumnar {

    public static void armaDataColumnar (List<String> header, List<String[]> data, int _nroColumnas, int _nroRegistros, List<Columna> dataColumnar) {

    dataColumnar.clear();

    for (int c=0; c < _nroColumnas; c++)
    {
        Columna nuevaColumna = new Columna();
        Dato[] tmpColumna = new Dato[ _nroRegistros];
        
        boolean esNumerica = true;
        boolean esBoolean = true;

        for (int f=0; f< _nroRegistros; f++)
        {
            String[] rowData = new String [_nroColumnas];
            rowData = data.get(f);
            String celda = rowData[c];
            String celda2 = celda.toUpperCase();

            if (celda.equals(""))
            {
                tmpColumna[f] = new Dato_NA();
            }
            else
            {
                tmpColumna[f] = new Dato_String(celda.trim());
            }   

            // verifica si es numerico    
                try {
                    if (!tmpColumna[f].isNA())
                    { Long.parseLong(rowData[c]); }
                } catch (NumberFormatException ex) 
                {
                    esNumerica = false;
                }
            // verifica si es boolean    
                if ( (!celda2.equals("TRUE")) && (!celda2.equals("FALSE")) && (!tmpColumna[f].isNA() ) )
                { 
                    esBoolean = false; 
                }
        }
        
        // Armado de la columna
        if (esNumerica) 
        {
            Dato[] tmpColumnaNum = new Dato[_nroRegistros];
                
                for (int ff=0; ff < _nroRegistros; ff++)
                {
                    if (tmpColumna[ff].isNA())
                        { tmpColumnaNum[ff] = new Dato_NA(); }
                    else
                        { tmpColumnaNum[ff] = new Dato_Numerico( tmpColumna[ff].getDato().toString() ); } 
                }
                nuevaColumna.setColumna(tmpColumnaNum, "Numerica"); 
                nuevaColumna.setEtiqueta(header.get(c));
        }
        if (esBoolean)
        {
            Dato[] tmpColumnaNum = new Dato[_nroRegistros];
                
            for (int ff=0; ff<_nroRegistros; ff++)
            {
                if (tmpColumna[ff].isNA())
                        { tmpColumnaNum[ff] = new Dato_NA(); }
                    else
                        { tmpColumnaNum[ff] = new Dato_Boolean ( tmpColumna[ff].getDato() ); }
            }
            nuevaColumna.setColumna(tmpColumnaNum, "Boolean"); 
            nuevaColumna.setEtiqueta(header.get(c));
        } 
        
        if (!esNumerica && !esBoolean)
        { 
            nuevaColumna.setColumna(tmpColumna, "String");
            nuevaColumna.setEtiqueta(header.get(c));
        }
        
        dataColumnar.add(nuevaColumna); 
    }
}
}
