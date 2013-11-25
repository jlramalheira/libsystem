/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Debito;
import model.Usuario;

/**
 *
 * @author joao
 */
public class DaoDebito extends Dao<Debito>{

    public DaoDebito() {
        super(Debito.class);
    }
    
    public List<Debito> listByUsuario(Usuario usuario){
        return em.createQuery("SELECT d FROM Debito d, Usuario u, Emprestimo e, Devolucao de "
                + "WHERE u.id = e.usuario.id AND de.id = d.debito.id AND e.devolucao.id = de.id AND u.id = "+usuario.getId()).getResultList();
    }
    
}
