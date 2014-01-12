/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
/**
 *
 * @author amauter
 */
public class StringFormatter {
          static public String customFormat(String pattern, long value, DecimalFormatSymbols symbols ) {            
            DecimalFormat myFormatter = new DecimalFormat(pattern);
            myFormatter.setDecimalFormatSymbols(symbols);
            
            String output = myFormatter.format(value);
            return output;
       }
}
