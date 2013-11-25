/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoObra;
import dao.DaoReserva;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Obra;
import model.Reserva;
import model.Usuario;
import util.Message;

/**
 *
 * @author joao
 */
@WebServlet(name = "Reserva", urlPatterns = {"/Reserva"})
public class ServletReserva extends HttpServlet {

    private ArrayList<Message> messages;
    private RequestDispatcher dispatcher;
    private String action;
    private DaoReserva daoReserva;
    private Reserva reserva;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoReserva = new DaoReserva();
        messages = new ArrayList<>();

        try {
            HttpSession session = request.getSession(true);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (action == null || usuario == null) {
                response.sendError(404);
            } else {
                Long idReserva;
                switch (action) {
                    case "create":
                        List<Obra> obras = new DaoObra().listOderCategoria();

                        request.setAttribute("obras", obras);

                        dispatcher = request.getRequestDispatcher("reservaCreate.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case "search":
                        int status = -1;
                        List<Reserva> reservasSearch;
                        if (!request.getParameter("status").isEmpty()) {
                            status = Integer.parseInt(request.getParameter("status"));
                            reservasSearch = daoReserva.listByTituloobraStatus(request.getParameter("obra"), status);
                        } else {
                            reservasSearch = daoReserva.listByTituloobra(request.getParameter("obra"));
                        }

                        request.setAttribute("reservas", reservasSearch);

                        dispatcher = request.getRequestDispatcher("reservaList.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case "cancelar":
                        idReserva = Long.parseLong(request.getParameter("idReserva"));

                        reserva = daoReserva.get(idReserva);

                        if (reserva == null) {
                            response.sendError(404);
                        } else {
                            reserva.setStatus(Reserva.CANCELADA);

                            daoReserva.update(reserva);

                            if (reserva.getStatus() == Reserva.DISPONIVEL) {
                                Reserva proximaReserva = daoReserva.getNextReserva(reserva.getObra());

                                proximaReserva.setStatus(Reserva.DISPONIVEL);

                                daoReserva.update(reserva);
                            }

                            request.setAttribute("reserva", reserva);

                            dispatcher = request.getRequestDispatcher("reservaView.jsp");
                            dispatcher.forward(request, response);
                        }
                        break;
                    case "view":
                        idReserva = Long.parseLong(request.getParameter("idReserva"));

                        reserva = daoReserva.get(idReserva);

                        if (reserva == null) {
                            response.sendError(404);
                        } else {
                            if (request.getParameter("new") != null) {
                                messages.add(new Message("Obra cadastrada com sucesso!", Message.TYPE_SUCCESS));
                            } else if (request.getParameter("update") != null) {
                                messages.add(new Message("Obra editada com sucesso!", Message.TYPE_SUCCESS));
                            }

                            request.setAttribute("reserva", reserva);
                            request.setAttribute("messages", messages);

                            dispatcher = request.getRequestDispatcher("reservaView.jsp");
                            dispatcher.forward(request, response);
                        }
                        break;
                    case "list":
                        List<Reserva> reservas = null;
                        if (usuario.getPerfil().hasAcessoReserva()) {
                            reservas = daoReserva.list();
                        } else {
                            reservas = daoReserva.listByUsuario(usuario);
                        }

                        request.setAttribute("reservas", reservas);

                        dispatcher = request.getRequestDispatcher("reservaList.jsp");
                        dispatcher.forward(request, response);
                        break;
                    default:
                        response.sendError(404);
                }
            }
        } catch (NumberFormatException ne) {
            response.sendError(404);
        } catch (NullPointerException np) {
            response.sendError(404);
        } catch (Exception ex) {
            response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoReserva = new DaoReserva();
        messages = new ArrayList<>();
        try {
            HttpSession session = request.getSession(true);

            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (action == null || usuario == null) {
                response.sendError(404);
            } else {
                switch (action) {
                    case "create":
                        //para mensagens de erro precisa ser setado novamente obras na requisição
                        List<Obra> obras = new DaoObra().listOderCategoria();
                        request.setAttribute("obras", obras);
                        reserva = new Reserva();

                        Long idObra = Long.parseLong(request.getParameter("obra"));

                        Obra obra = new DaoObra().get(idObra);

                        if (obra == null) {
                            response.sendError(404);
                        } else if (!obra.hasDisponivel()) {
                            if (usuario.canReservar()) {
                                List<Reserva> reservas = daoReserva.listByUsuarioObra(usuario, obra);
                                if (reservas.isEmpty()) {
                                    if (obra.getCategoria().emprestavel()) {
                                        reserva.setObra(obra);
                                        reserva.setUsuario(usuario);
                                        reserva.setStatus(Reserva.AGUARDANDO);
                                        reserva.setDiaReserva(Calendar.getInstance().getTime());

                                        daoReserva.insert(reserva);

                                        response.sendRedirect("Reserva?op=view&new=true&idReserva=" + reserva.getId());
                                    } else {
                                        messages.add(new Message("Esta Obra não pode ser emprestada e nem reservada!", Message.TYPE_ERROR));

                                        request.setAttribute("messages", messages);

                                        dispatcher = request.getRequestDispatcher("reservaCreate.jsp");
                                        dispatcher.forward(request, response);
                                    }
                                } else {
                                    messages.add(new Message("Usuário já possui reserva desta Obra!", Message.TYPE_ERROR));

                                    request.setAttribute("messages", messages);

                                    dispatcher = request.getRequestDispatcher("reservaCreate.jsp");
                                    dispatcher.forward(request, response);
                                }
                            } else {
                                messages.add(new Message("Usuário não pode efetuar mais reservas!", Message.TYPE_ERROR));

                                request.setAttribute("messages", messages);

                                dispatcher = request.getRequestDispatcher("reservaCreate.jsp");
                                dispatcher.forward(request, response);
                            }
                        } else {
                            messages.add(new Message("Existe exemplares disponível desta Obra!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);

                            dispatcher = request.getRequestDispatcher("reservaCreate.jsp");
                            dispatcher.forward(request, response);
                        }

                        break;
                    case "update":
                        break;
                    case "delete":
                        break;
                    default:
                        response.sendError(404);
                }
            }
        } catch (NumberFormatException ne) {
            response.sendError(404);
        } catch (NullPointerException np) {
            response.sendError(404);
        } catch (Exception ex) {
            response.sendError(404);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
