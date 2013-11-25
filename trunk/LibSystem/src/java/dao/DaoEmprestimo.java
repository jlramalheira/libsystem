/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Emprestimo;
import model.Usuario;

/**
 *
 * @author joao
 */
public class DaoEmprestimo extends Dao<Emprestimo> {

    public DaoEmprestimo() {
        super(Emprestimo.class);
    }

    public List<Emprestimo> listByUsuario(Usuario usuario) {
        criteria = newCriteria();
        return criteria
                .andEquals("usuario", usuario)
                .getResultList();
    }

    public List<Emprestimo> listByAll(String titulo, String nome, String dataSaidaIncio, String dataSaidaFim, String dataPrevistaInicio, String dataPrevistaFim,
            String datadevolucaoInicio, String dataDevolucaoFim, int statusDevolucao) {
        String query = "SELECT e FROM Emprestimo e, Obra o, Usuario u, Devolucao d, Exemplar ex WHERE o.titulo LIKE '%" + titulo + "%' AND u.nome LIKE '%" + nome + "%'";

        if (!dataSaidaIncio.isEmpty()) {
            query += " AND e.dataEmprestimo >= '" + dataSaidaIncio + "'";
        }
        if (!dataSaidaFim.isEmpty()) {
            query += " AND e.dataEmprestimo <= '" + dataSaidaFim + "'";
        }
        if (!dataPrevistaInicio.isEmpty()) {
            query += " AND e.dataDevolucaoPrevista >= '" + dataPrevistaInicio + "'";
        }
        if (!dataPrevistaFim.isEmpty()) {
            query += " AND e.dataDevolucaoPrevista <= '" + dataPrevistaFim + "'";
        }
        if (!datadevolucaoInicio.isEmpty()) {
            query += " AND d.dataDevolucao >= '" + datadevolucaoInicio + "'";
        }
        if (!dataDevolucaoFim.isEmpty()) {
            query += " AND d.dataDevolucao <= '" + dataDevolucaoFim + "'";
        }
        if (statusDevolucao > 0) {
            query += "AND d.status = " + statusDevolucao;
        }
        query += " AND e.usuario.id = u.id AND e.exemplar.id = ex.id AND ex.obra.id = o.id AND e.devolucao.id = d.id";
        System.out.println(query);
        return em.createQuery(query).getResultList();
    }

    public List<Emprestimo> listByAllLessUsuario(String titulo, String nome, String dataSaidaIncio, String dataSaidaFim, String dataPrevistaInicio, String dataPrevistaFim,
            String datadevolucaoInicio, String dataDevolucaoFim, int statusDevolucao, Usuario usuario) {
        String query = "SELECT e FROM Emprestimo e, Obra o, Usuario u, Devolucao d, Exemplar ex WHERE o.titulo LIKE '%" + titulo + "%' AND u.nome LIKE '%" + nome + "%'";

        if (!dataSaidaIncio.isEmpty()) {
            query += " AND e.dataEmprestimo >= '" + dataSaidaIncio + "'";
        }
        if (!dataSaidaFim.isEmpty()) {
            query += " AND e.dataEmprestimo <= '" + dataSaidaFim + "'";
        }
        if (!dataPrevistaInicio.isEmpty()) {
            query += " AND e.dataDevolucaoPrevista >= '" + dataPrevistaInicio + "'";
        }
        if (!dataPrevistaFim.isEmpty()) {
            query += " AND e.dataDevolucaoPrevista <= '" + dataPrevistaFim + "'";
        }
        if (!datadevolucaoInicio.isEmpty()) {
            query += " AND d.dataDevolucao >= '" + datadevolucaoInicio + "'";
        }
        if (!dataDevolucaoFim.isEmpty()) {
            query += " AND d.dataDevolucao <= '" + dataDevolucaoFim + "'";
        }
        if (statusDevolucao > 0) {
            query += "AND d.status = " + statusDevolucao;
        }
        query += " AND e.usuario.id = u.id AND e.exemplar.id = ex.id AND ex.obra.id = o.id AND e.devolucao.id = d.id AND u.id = " + usuario.getId();

        return em.createQuery(query).getResultList();
    }
}
