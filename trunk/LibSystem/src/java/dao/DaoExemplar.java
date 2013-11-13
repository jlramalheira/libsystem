/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Exemplar;
import model.Obra;

/**
 *
 * @author joao
 */
public class DaoExemplar extends Dao<Exemplar>{

    public DaoExemplar() {
        super(Exemplar.class);
    }
    
    public List<Exemplar> listByObra(Obra obra){
        criteria = newCriteria();
        return criteria
                .andEquals("obra", obra)
                .getResultList();
    }
    
    public int getExemplares(Obra obra){
        criteria = newCriteria();
        return criteria
                .andEquals("obra", obra)
                .getResultList().size();
    }
    
    public int getExemplaresByStatus(Obra obra, int status){
        criteria = newCriteria();
        return criteria
                .andEquals("obra", obra)
                .andEquals("status", status)
                .getResultList().size();
    }
    
    public List<Exemplar> listByObraAndStatus(Obra obra, int status){
        criteria = newCriteria();
        return criteria
                .andEquals("obra", obra)
                .andEquals("status", status)
                .getResultList();
    }
}
