package util;

/**
 * Classe de utilidade para a verificação dos mais variados tipos de dados
 *
 * @author Max
 */
public class AuthenticateData {
    /**
     * Autentica o CNS
     *
     * @return true se o CNS é válido, false se não
     */
    public static boolean authenticateCNS(String cns) {
        if ((cns == null) || (cns.matches("[0-9]+"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Autentica um nome
     *
     * @return true se o nome é válido, false se não
     */
    public static boolean authenticateNome(String nome) {
        if ((nome == null) || (nome.matches(".+"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se a string é uma idade
     *
     * @return retorna
     */
    public static boolean authenticateIdade(String idade) {
        if ((idade == null) || (idade.matches("[0-9]+"))) {
            return true;
        }
        return false;
    }
}
