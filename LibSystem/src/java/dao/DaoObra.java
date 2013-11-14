/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Categoria;
import model.Obra;

/**
 *
 * @author joao
 */
public class DaoObra extends Dao<Obra> {

    public DaoObra() {
        super(Obra.class);
    }

    public List<Obra> listByAll(String titulo, String autor, String editora, String anoInicio, String anoFim, String categoria) {
        criteria = newCriteria();
        if (!titulo.isEmpty()) {
            criteria.andStringLike("titulo", "%" + titulo + "%");
        }
        if (!autor.isEmpty()) {
            criteria.andStringLike("autor", "%" + autor + "%");
        }
        if (!editora.isEmpty()) {
            criteria.andStringLike("editora", "%" + editora + "%");
        }
        if (!anoInicio.isEmpty()) {
            int anoI = Integer.parseInt(anoInicio);
            if (!anoFim.isEmpty()) {
                int anoF = Integer.parseInt(anoFim);
                criteria.andGreaterOrEqualTo("ano", anoI);
                criteria.andLessOrEqualTo("ano", anoF);
            } else {
                criteria.andGreaterOrEqualTo("ano", anoI);
            }
        } else {
            if (!anoFim.isEmpty()) {
                int anoF = Integer.parseInt(anoFim);
                criteria.andLessOrEqualTo("ano", anoF);
            }
        }
        if (!categoria.isEmpty()){
            criteria.andEquals("categoria", Categoria.valueOf(categoria));
        }
        return criteria.getResultList();
    }
    
    public List<Obra> listOderCategoria(){
        criteria = newCriteria();
        return criteria.orderByAsc("categoria").getResultList();
    }
}
