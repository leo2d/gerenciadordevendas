package com.leonardo.gerenciadordevendas.Helpers;

import java.text.NumberFormat;
import java.util.Locale;

public final class MoneyHelper {

    public static String formatarEmReal(double valor){
        Locale localeBR = new Locale( "pt", "BR" );
        NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

        return dinheiroBR.format(valor);
    }
}
