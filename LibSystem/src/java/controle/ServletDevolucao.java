/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoDebito;
import dao.DaoDevolucao;
import dao.DaoEmprestimo;
import dao.DaoExemplar;
import dao.DaoReserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Debito;
import model.Devolucao;
import model.Emprestimo;
import model.Exemplar;
import model.Reserva;
import util.Message;

/**
 *
 * @author joao
 */
@WebServlet(name = "Devolucao", urlPatterns = {"/Devolucao"})
public class ServletDevolucao extends HttpServlet {

    private ArrayList<Message> messages;
    private RequestDispatcher dispatcher;
    private String action;
    private DaoDevolucao daoDevolucao;
    private Devolucao devolucao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoDevolucao = new DaoDevolucao();
        if (action == null) {
            response.sendError(404);
        } else {
            Long idEmprestimo;
            Emprestimo emprestimo;
            DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
            switch (action) {
                case "create":
                    idEmprestimo = Long.parseLong(request.getParameter("idEmprestimo"));

                    emprestimo = daoEmprestimo.get(idEmprestimo);

                    if (emprestimo == null) {
                        response.sendError(404);
                    } else {
                        devolucao = emprestimo.getDevolucao();
                        devolucao.setStatus(Devolucao.DEVOLVIDO);
                        
                        Exemplar exemplar = emprestimo.getExemplar();                        
                        exemplar.setStatus(Exemplar.DISPONIVEL);                        
                        new DaoExemplar().update(exemplar);
                        
                        Date hoje = Calendar.getInstance().getTime();
                        Debito debito = new Debito();
                        if (hoje.after(emprestimo.getDataDevolucaoPrevista())) {
                            long diferenca = hoje.getTime() - emprestimo.getDataDevolucaoPrevista().getTime();
                            int diferencaEmDias = (int) (diferenca / (1000 * 60 * 60 * 24));
                            //TODO rever valor, se é do perfil ou unico
                            debito.setValor(diferencaEmDias * 1.50);
                        }
                        new DaoDebito().insert(debito);
                        devolucao.setDebito(debito);
                        devolucao.setDataDevolucao(hoje);
                        
                        daoDevolucao.update(devolucao);
                        
                        Reserva reserva = new DaoReserva().getFirstReserva(emprestimo.getExemplar().getObra());
                        if (reserva !=  null){
                            reserva.setStatus(Reserva.DISPONIVEL);
                            reserva.setDiaDisponivel(reserva.getUsuario().getPerfil().getDiasReserva());
                           new DaoReserva().update(reserva);
                        }
                        
                        response.sendRedirect("Emprestimo?op=view&returned=true&idEmprestimo="+emprestimo.getId());
                    }
                    break;
                case "update":
                    break;
                case "delete":
                    break;
                case "view":
                    break;
                default:
                    response.sendError(404);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        action = request.getParameter("op");
        if (action == null) {
            response.sendError(404);
        } else {
            Long idDevolucao;
            switch (action) {
                case "create":
                    break;
                case "update":
                    break;
                case "delete":
                    break;
                case "view":
                    break;
                default:
                    response.sendError(404);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
