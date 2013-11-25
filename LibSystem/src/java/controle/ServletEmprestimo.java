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
import dao.DaoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Debito;
import model.Devolucao;
import model.Emprestimo;
import model.Exemplar;
import model.Reserva;
import model.Usuario;
import util.FormatDate;
import util.Message;

/**
 *
 * @author joao
 */
@WebServlet(name = "Emprestimo", urlPatterns = {"/Emprestimo"})
public class ServletEmprestimo extends HttpServlet {

    private ArrayList<Message> messages;
    private RequestDispatcher dispatcher;
    private String action;
    private DaoEmprestimo daoEmprestimo;
    private Emprestimo emprestimo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoEmprestimo = new DaoEmprestimo();
        messages = new ArrayList<>();

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (action == null || usuario == null) {
            response.sendError(404);
        } else {
            Long idEmprestimo;
            switch (action) {
                case "create":
                    List<Usuario> usuarios = new DaoUsuario().list();

                    request.setAttribute("usuarios", usuarios);

                    dispatcher = request.getRequestDispatcher("emprestimoCreate.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "update":
                    break;
                case "delete":
                    break;
                case "view":
                    idEmprestimo = Long.parseLong(request.getParameter("idEmprestimo"));

                    emprestimo = daoEmprestimo.get(idEmprestimo);

                    if (emprestimo == null) {
                        response.sendError(404);
                    } else {
                        if (request.getParameter("new") != null) {
                            messages.add(new Message("Empréstimo cadastrado com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("update") != null) {
                            messages.add(new Message("Empréstimo editado com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("returned") != null) {
                            messages.add(new Message("Devolução realizada com sucesso!", Message.TYPE_SUCCESS));
                        }

                        request.setAttribute("emprestimo", emprestimo);
                        request.setAttribute("messages", messages);

                        dispatcher = request.getRequestDispatcher("emprestimoView.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "list":
                    List<Emprestimo> emprestimos = null;
                    if (usuario.getPerfil().hasAcessoEmprestimo()) {
                        emprestimos = daoEmprestimo.list();
                    } else {
                        emprestimos = daoEmprestimo.listByUsuario(usuario);
                    }

                    request.setAttribute("emprestimos", emprestimos);

                    dispatcher = request.getRequestDispatcher("emprestimoList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "receber":
                    idEmprestimo = Long.parseLong(request.getParameter("idEmprestimo"));

                    emprestimo = daoEmprestimo.get(idEmprestimo);

                    if (emprestimo == null) {
                        response.sendError(404);
                    } else {
                        Debito debito = emprestimo.getDevolucao().getDebito();

                        debito.setValor(0);

                        new DaoDebito().update(debito);

                        messages.add(new Message("Debito Recebido com sucesso!", Message.TYPE_SUCCESS));

                        request.setAttribute("emprestimo", emprestimo);
                        request.setAttribute("messages", messages);

                        dispatcher = request.getRequestDispatcher("emprestimoView.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "renovar":
                    idEmprestimo = Long.parseLong(request.getParameter("idEmprestimo"));

                    emprestimo = daoEmprestimo.get(idEmprestimo);

                    if (emprestimo == null) {
                        response.sendError(404);
                    } else {
                        Exemplar exemplar = emprestimo.getExemplar();
                        if (exemplar.getObra().getNumeroReservas() <= exemplar.getObra().getNumeroExemplares(Exemplar.DISPONIVEL)) {
                            if (usuario.getValorDebitos() <= 0) {
                                emprestimo.setDataDevolucaoPrevistaRenovacao(emprestimo.getUsuario().getPerfil().getDiasEmprestimo());

                                messages.add(new Message("Exemplar renovado com sucesso!", Message.TYPE_SUCCESS));
                            } else {
                                messages.add(new Message("O usuário possui débitos pendentes!", Message.TYPE_ERROR));
                            }
                        } else {
                            messages.add(new Message("A obra esta reservada para outro usuário!", Message.TYPE_ERROR));
                        }
                        request.setAttribute("emprestimo", emprestimo);
                        request.setAttribute("messages", messages);
                        dispatcher = request.getRequestDispatcher("emprestimoView.jsp");
                        dispatcher.forward(request, response);

                    }
                    break;
                case "search":
                    String obra = request.getParameter("obra");
                    String nomeUsuario = request.getParameter("usuario");
                    String dataSaidaInicio = request.getParameter("data-saida-inicio");
                    String dataSaidaFim = request.getParameter("data-saida-fim");
                    String dataDevolucaoPrevistaInicio = request.getParameter("data-devolucao-prevista-inicio");
                    String dataDevolucaoPrevistaFim = request.getParameter("data-devolucao-prevista-fim");
                    String dataDevolucaoInicio = request.getParameter("data-devolucao-inicio");
                    String dataDevolucaoFim = request.getParameter("data-devolucao-fim");
                    int statusDevolucao = Integer.parseInt(request.getParameter("status"));
                    System.out.println("************************88");
                    System.out.println(dataSaidaFim);
                    System.out.println(dataSaidaInicio);
                    if (usuario.getPerfil().hasAcessoEmprestimo()) {
                        emprestimos = daoEmprestimo.listByAll(obra, nomeUsuario, dataSaidaInicio, dataSaidaFim, dataDevolucaoPrevistaInicio, dataDevolucaoPrevistaFim, dataDevolucaoInicio, dataDevolucaoFim, statusDevolucao);
                    } else {
                        emprestimos = daoEmprestimo.listByAllLessUsuario(obra, nomeUsuario, dataSaidaInicio, dataSaidaFim, dataDevolucaoPrevistaInicio, dataDevolucaoPrevistaFim, dataDevolucaoInicio, dataDevolucaoFim, statusDevolucao, usuario);
                    }
                    
                    request.setAttribute("emprestimos", emprestimos);
                    
                    dispatcher = request.getRequestDispatcher("emprestimoList.jsp");
                    dispatcher.forward(request, response);
                    break;
                default:
                    response.sendError(404);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoEmprestimo = new DaoEmprestimo();
        messages = new ArrayList<>();

        HttpSession session = request.getSession(true);

        try {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
        Long idUsuario = Long.parseLong(request.getParameter("usuario"));

        Usuario usuario = new DaoUsuario().get(idUsuario);

        if (action == null || usuarioLogado == null || usuario == null || !usuarioLogado.getPerfil().hasAcessoEmprestimo()) {
            response.sendError(404);
        } else {
            switch (action) {
                case "create":
                    emprestimo = new Emprestimo();
                    Long idExemplar = Long.parseLong(request.getParameter("exemplar"));
                    DaoExemplar daoExemplar = new DaoExemplar();
                    Exemplar exemplar = daoExemplar.get(idExemplar);

                    if (exemplar == null || emprestimo == null) {
                        response.sendError(404);
                    } else {
                        if (exemplar.getObra().getCategoria().emprestavel()) {
                            if (exemplar.getStatus() == Exemplar.DISPONIVEL) {
                                if (exemplar.getObra().getNumeroReservas() <= exemplar.getObra().getNumeroExemplares(Exemplar.DISPONIVEL)) {
                                    Reserva reserva = new DaoReserva().getByUsuarioObra(usuario, exemplar.getObra());
                                    //nao acha reserva ou se acha, a reserva eh a do usuario
                                    if (((reserva == null) && (exemplar.getObra().getNumeroReservas() < exemplar.getObra().getNumeroExemplares(Exemplar.DISPONIVEL)))
                                            || (reserva != null && reserva.getUsuario().getId() == usuario.getId())) {
                                        if (usuario.canEmprestar()) {
                                            if (usuario.getValorDebitos() <= 0) {
                                                if (reserva != null && reserva.getUsuario().getId() == usuario.getId()){
                                                    reserva.setStatus(Reserva.EFETUADA);
                                                    
                                                    new DaoReserva().update(reserva);
                                                }
                                                Devolucao devolucao = new Devolucao();
                                                devolucao.setStatus(Devolucao.NORMAL);

                                                new DaoDevolucao().insert(devolucao);

                                                exemplar.setStatus(Exemplar.EMPRESTADO);

                                                daoExemplar.update(exemplar);

                                                emprestimo.setExemplar(exemplar);
                                                emprestimo.setDataEmprestimo(FormatDate.stringToDateUs(request.getParameter("data-saida")));
                                                emprestimo.setUsuario(usuario);
                                                emprestimo.setDataDevolucaoPrevista(usuario.getPerfil().getDiasEmprestimo());
                                                emprestimo.setDevolucao(devolucao);
                                                daoEmprestimo.insert(emprestimo);

                                                response.sendRedirect("Emprestimo?op=view&new=true&idEmprestimo=" + emprestimo.getId());
                                            } else {
                                                messages.add(new Message("O usuário possui débitos pendentes!", Message.TYPE_ERROR));

                                                List<Usuario> usuarios = new DaoUsuario().list();

                                                request.setAttribute("usuarios", usuarios);
                                                request.setAttribute("messages", messages);
                                                dispatcher = request.getRequestDispatcher("emprestimoCreate.jsp");
                                                dispatcher.forward(request, response);
                                            }
                                        } else {
                                            messages.add(new Message("O usuário não pode mais realizar emprestimos!", Message.TYPE_ERROR));

                                            List<Usuario> usuarios = new DaoUsuario().list();

                                            request.setAttribute("usuarios", usuarios);
                                            request.setAttribute("messages", messages);
                                            dispatcher = request.getRequestDispatcher("emprestimoCreate.jsp");
                                            dispatcher.forward(request, response);
                                        }
                                    } else {
                                        messages.add(new Message("Este Exemplar esta reservado!", Message.TYPE_ERROR));

                                        List<Usuario> usuarios = new DaoUsuario().list();

                                        request.setAttribute("usuarios", usuarios);
                                        request.setAttribute("messages", messages);
                                        dispatcher = request.getRequestDispatcher("emprestimoCreate.jsp");
                                        dispatcher.forward(request, response);
                                    }
                                } else {
                                    messages.add(new Message("Os exemplares desta obra estão reservados!", Message.TYPE_ERROR));

                                    List<Usuario> usuarios = new DaoUsuario().list();

                                    request.setAttribute("usuarios", usuarios);
                                    request.setAttribute("messages", messages);
                                    dispatcher = request.getRequestDispatcher("emprestimoCreate.jsp");
                                    dispatcher.forward(request, response);
                                }
                            } else {
                                messages.add(new Message("Este Exemplar não esta disponível!", Message.TYPE_ERROR));

                                List<Usuario> usuarios = new DaoUsuario().list();

                                request.setAttribute("usuarios", usuarios);
                                request.setAttribute("messages", messages);
                                dispatcher = request.getRequestDispatcher("emprestimoCreate.jsp");
                                dispatcher.forward(request, response);
                            }
                        } else {
                            messages.add(new Message(exemplar.getObra().getCategoria().valor() + " não pode ser emprestado!", Message.TYPE_ERROR));

                            List<Usuario> usuarios = new DaoUsuario().list();

                            request.setAttribute("usuarios", usuarios);
                            request.setAttribute("messages", messages);
                            dispatcher = request.getRequestDispatcher("emprestimoCreate.jsp");
                            dispatcher.forward(request, response);
                        }
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
        } catch (NumberFormatException ne) {
            response.sendError(404);
        } catch (NullPointerException ex) {
            response.sendError(404);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
