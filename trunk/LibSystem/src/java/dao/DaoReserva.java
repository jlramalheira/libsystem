/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Obra;
import model.Reserva;
import model.Usuario;

/**
 *
 * @author joao
 */
public class DaoReserva extends Dao<Reserva> {

    public DaoReserva() {
        super(Reserva.class);
    }

    public List<Reserva> listByUsuario(Usuario usuario) {
        criteria = newCriteria();
        return criteria
                .andEquals("usuario", usuario)
                .getResultList();
    }
    
    public List<Reserva> listByObra(Obra obra) {
        criteria = newCriteria();
        return criteria
                .andEquals("obra", obra)
                .getResultList();
    }

    public List<Reserva> listByUsuarioObra(Usuario usuario, Obra obra) {
        criteria = newCriteria();
        return criteria
                .andEquals("usuario", usuario)
                .andEquals("obra", obra)
                .getResultList();
    }
}
