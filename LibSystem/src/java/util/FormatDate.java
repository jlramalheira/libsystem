package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que implementa formatações básicas de medições de tempo
 *
 * @author Max
 */
public class FormatDate {

    private static SimpleDateFormat formatDataUs = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatDataBr = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat formatHoraMinuto = new SimpleDateFormat("H:mm");

    public static String hourToString(Date hora) {
        if (hora != null) {
            return formatHoraMinuto.format(hora);
        }
        return "";
    }

    public static Date stringToHour(String hora) {
        try {
            return formatHoraMinuto.parse(hora);
        } catch (ParseException ex) {
            Logger.getLogger(FormatDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String dateToString(Date data) {
        if (data != null) {
            return formatDataBr.format(data);
        }
        return "";
    }

    public static Date stringToDate(String data) {
        try {
            return formatDataBr.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(FormatDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String dateToStringUs(Date data){
        if (data != null) {
            return formatDataUs.format(data);
        }
        return "";
    }

    public static Date stringToDateUs(String data) {
        try {
            return formatDataUs.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(FormatDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
