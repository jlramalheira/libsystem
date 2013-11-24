/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Perfil;
import model.Usuario;

/**
 *
 * @author joao
 */
public class DaoUsuario extends Dao<Usuario> {

    public DaoUsuario() {
        super(Usuario.class);
    }

    public List<Usuario> listByLoginOrEmail(String login, String email) {
        criteria = newCriteria();
        return criteria
                .andStringLike("nome", login)
                .andStringLike("email", email)
                .getResultList();
    }

    public List<Usuario> listByNome(String nome) {
        criteria = newCriteria();
        return criteria
                .andStringLike("nome", "%" + nome + "%")
                .getResultList();
    }

    public List<Usuario> listByEmail(String email) {
        criteria = newCriteria();
        return criteria
                .andStringLike("email", email)
                .getResultList();
    }

    public Usuario getByLogin(String login) {
        criteria = newCriteria();
        List<Usuario> usuarios = criteria
                .andStringLike("login", login)
                .getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }
    
    public Usuario getByEmail(String email) {
        criteria = newCriteria();
        List<Usuario> usuarios = criteria
                .andStringLike("email", email)
                .getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
    }

    public List<Usuario> listByLogin(String login) {
        criteria = newCriteria();
        return criteria
                .andStringLike("login", login)
                .getResultList();
    }

    public List<Usuario> listByNomeEmail(String nome, String email) {
        criteria = newCriteria();
        return criteria
                .andStringLike("nome", "%" + nome + "%")
                .andStringLike("email", "%" + email + "%")
                .getResultList();
    }

    public List<Usuario> listByNomeEmailPerfil(String nome, String email, Perfil perfil) {
        criteria = newCriteria();
        return criteria
                .andStringLike("nome", "%" + nome + "%")
                .andStringLike("email", "%" + email + "%")
                .andEquals("perfil", perfil)
                .getResultList();
    }
}
