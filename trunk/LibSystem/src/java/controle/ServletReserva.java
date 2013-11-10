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

        if (action == null) {
            response.sendError(404);
        } else {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoReserva = new DaoReserva();
        messages = new ArrayList<>();

        HttpSession session = request.getSession(true);

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (action == null || usuario == null) {
            response.sendError(404);
        } else {
            switch (action) {
                case "create":
                    reserva = new Reserva();

                    Long idObra = Long.parseLong(request.getParameter("idObra"));

                    Obra obra = new DaoObra().get(idObra);

                    if (obra == null) {
                        response.sendError(404);
                    } else {
                        if (!obra.hasDisponivel()) {
                            if (usuario.canReservar()) {
                                List<Reserva> reservas = daoReserva.listByUsuarioObra(usuario, obra);
                                if (reservas.isEmpty()) {
                                    reserva.setObra(obra);
                                    reserva.setUsuario(null);
                                    reserva.setStatus(Reserva.EMESPERA);
                                    reserva.setDiaReserva(Calendar.getInstance().getTime());

                                    daoReserva.insert(reserva);

                                    response.sendRedirect("Reserva?op=view&new=true&idReserva=" + reserva.getId());
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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
