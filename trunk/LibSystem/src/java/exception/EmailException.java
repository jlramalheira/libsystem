/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author joao
 */
public class EmailException extends Exception{
    
    public EmailException(){
        super("Fala ao enviar email de informação");
    }
}
