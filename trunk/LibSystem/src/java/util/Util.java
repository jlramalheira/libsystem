/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 *
 * @author joao
 */
public class Util {
    
    
    public static String criptografarSenha(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        System.out.println(sen);
        return sen;
    }
    
    public static String geraSenhaAleatoria() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        String senhaAleatoria = myRandom.substring(0, 8);
        return senhaAleatoria;
    }
}
