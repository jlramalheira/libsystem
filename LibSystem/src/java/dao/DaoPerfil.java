/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Perfil;

/**
 *
 * @author joao
 */
public class DaoPerfil extends Dao<Perfil>{

    public DaoPerfil() {
        super(Perfil.class);
    }
    
    public List<Perfil> listByNome(String nome){
        criteria = newCriteria();
        return criteria
                .andStringLike("nome", "%"+nome+"%")
                .getResultList();
    }
    
}
