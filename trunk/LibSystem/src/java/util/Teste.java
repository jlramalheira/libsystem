/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author joao
 */
public class Teste {
    public static void main(String[] args) {
        Date hoje = FormatDate.stringToDate("24/11/2013");
        Date comparada = FormatDate.stringToDate("24/11/2013");
        System.out.println(hoje);
        System.out.println(comparada);
        System.out.println((hoje.getTime() - comparada.getTime()) / (24*60*60*1000) + 1);
        
        long diferenca = hoje.getTime() - comparada.getTime();
        int diferencaEmDias = (int) (diferenca / (1000*60*60*24));
        long horasRestantes = (diferenca /1000) / 60 / 60 %24;
        int result = (int) (diferencaEmDias + (horasRestantes /24d));
        System.out.println(diferencaEmDias);
        System.out.println(result);
    }
}
