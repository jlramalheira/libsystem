/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Emprestimo;
import model.Usuario;

/**
 *
 * @author joao
 */
public class DaoEmprestimo extends Dao<Emprestimo>{

    public DaoEmprestimo() {
        super(Emprestimo.class);
    }
    
    public List<Emprestimo> listByUsuario(Usuario usuario){
        criteria = newCriteria();
        return criteria
                .andEquals("usuario", usuario)
                .getResultList();
    }
    
}
