/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoExemplar;
import dao.DaoObra;
import dao.DaoReserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Exemplar;
import model.Obra;
import model.Reserva;
import util.Message;

/**
 *
 * @author joao
 */
@WebServlet(name = "Obra", urlPatterns = {"/Obra"})
public class ServletObra extends HttpServlet {

    private ArrayList<Message> messages;
    private RequestDispatcher dispatcher;
    private String action;
    private DaoObra daoObra;
    private Obra obra;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoObra = new DaoObra();
        messages = new ArrayList<>();
        if (action == null) {
            response.sendError(404);
        } else {
            Long idObra;
            switch (action) {
                case "create":
                    dispatcher = request.getRequestDispatcher("obraCreate.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "update":
                    idObra = Long.parseLong(request.getParameter("idObra"));

                    obra = daoObra.get(idObra);

                    if (obra == null) {
                        response.sendError(404);
                    } else {
                        request.setAttribute("obra", obra);

                        dispatcher = request.getRequestDispatcher("obraUpdate.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "delete":
                    idObra = Long.parseLong(request.getParameter("idObra"));

                    obra = daoObra.get(idObra);

                    if (obra == null) {
                        response.sendError(404);
                    } else {
                        if (obra.hasEmprestado()) {
                            messages.add(new Message("Impossível excluir Obra com exemplares emprestados!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);
                            request.setAttribute("obra", obra);

                            dispatcher = request.getRequestDispatcher("obraView.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            DaoExemplar daoExemplar = new DaoExemplar();
                            DaoReserva daoReserva = new DaoReserva();
                            List<Reserva> reservas = daoReserva.listByObra(obra);
                            for (Reserva reserva : reservas) {
                                daoReserva.remove(reserva.getId());
                            }

                            List<Exemplar> exemplares = daoExemplar.listByObra(obra);
                            for (Exemplar exemplar : exemplares) {
                                daoExemplar.remove(exemplar.getId());
                            }

                            daoObra.remove(idObra);

                            response.sendRedirect("Obra?op=list&deleted=true");
                        }
                    }
                    break;
                case "view":
                    idObra = Long.parseLong(request.getParameter("idObra"));

                    obra = daoObra.get(idObra);

                    if (obra == null) {
                        response.sendError(404);
                    } else {
                        if (request.getParameter("new") != null) {
                            messages.add(new Message("Obra cadastrada com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("update") != null) {
                            messages.add(new Message("Obra editada com sucesso!", Message.TYPE_SUCCESS));
                        }

                        request.setAttribute("obra", obra);
                        request.setAttribute("messages", messages);

                        dispatcher = request.getRequestDispatcher("obraView.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "search":
                    List<Obra> obrasSearch = daoObra.listByAll(request.getParameter("titulo"), request.getParameter("autor"), request.getParameter("editora"), request.getParameter("anoInicio"), request.getParameter("anoFim"), request.getParameter("categoria"));
                    request.setAttribute("obras", obrasSearch);

                    dispatcher = request.getRequestDispatcher("obraList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "list":
                    List<Obra> obras = daoObra.list();

                    if (request.getParameter("deleted") != null) {
                        messages.add(new Message("Obra deletada com sucesso!", Message.TYPE_SUCCESS));

                        request.setAttribute("messages", messages);
                    }

                    request.setAttribute("obras", obras);

                    dispatcher = request.getRequestDispatcher("obraList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "nameObra":
                    Long idExemplar = Long.parseLong(request.getParameter("idExemplar"));
                    //alterar, vem o id do exemplar e nao da obra
                    Exemplar exemplar = new DaoExemplar().get(idExemplar);
                    String titulo;
                    Long id;
                    if (exemplar == null) {
                        titulo = "ERROR: Obra não cadastrada no Sistema!";
                        id = (long) -1;
                    } else {
                        titulo = exemplar.getObra().getTitulo();
                        id = exemplar.getObra().getId();
                    }
                    String json = "{\"titulo\":\"" + titulo + "\" , "
                            + "\"id\":\"" + id + "\"}";
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                    out.flush();

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
        daoObra = new DaoObra();
        messages = new ArrayList<>();
        if (action == null) {
            response.sendError(404);
        } else {
            Long idObra;
            DaoExemplar daoExemplar = new DaoExemplar();
            Exemplar exemplar;
            switch (action) {
                case "create":
                    obra = new Obra();

                    obra.setAno(Integer.parseInt(request.getParameter("ano")));
                    obra.setAutor(request.getParameter("autor"));
                    obra.setCategoria(request.getParameter("categoria"));
                    obra.setEditora(request.getParameter("editora"));
                    obra.setTitulo(request.getParameter("titulo"));

                    daoObra.insert(obra);
                    int numeroExemplares = Integer.parseInt(request.getParameter("exemplares"));

                    for (int i = 0; i < numeroExemplares; i++) {
                        exemplar = new Exemplar();
                        exemplar.setObra(obra);
                        exemplar.setStatus(Exemplar.DISPONIVEL);
                        daoExemplar.insert(exemplar);
                    }


                    response.sendRedirect("Obra?op=view&new=true&idObra=" + obra.getId());

                    break;
                case "update":
                    idObra = Long.parseLong(request.getParameter("idObra"));

                    obra = daoObra.get(idObra);

                    if (obra == null) {
                        response.sendError(404);
                    } else {
                        obra.setAno(Integer.parseInt(request.getParameter("ano")));
                        obra.setAutor(request.getParameter("autor"));
                        obra.setCategoria(request.getParameter("categoria"));
                        obra.setEditora(request.getParameter("editora"));
                        obra.setTitulo(request.getParameter("titulo"));


                        int numeroExemplaresUpdate = Integer.parseInt(request.getParameter("exemplares"));

                        if (numeroExemplaresUpdate <= obra.getNumeroExemplares(Exemplar.EMPRESTADO)) {
                            messages.add(new Message("Impossível excluir exemplares emprestados!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);
                            request.setAttribute("obra", obra);

                            dispatcher = request.getRequestDispatcher("obraUpdate.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            if (numeroExemplaresUpdate > obra.getNumeroExemplares()) {
                                for (int i = 0; i <= (numeroExemplaresUpdate - obra.getNumeroExemplares() + 1); i++) {
                                    exemplar = new Exemplar();
                                    exemplar.setObra(obra);
                                    exemplar.setStatus(Exemplar.DISPONIVEL);
                                    daoExemplar.insert(exemplar);
                                }
                            } else {
                                List<Exemplar> exemplares = daoExemplar.listByObraAndStatus(obra, Exemplar.DISPONIVEL);
                                for (int i = 1; i <= (obra.getNumeroExemplares() - numeroExemplaresUpdate + 1); i++) {
                                    daoExemplar.remove(exemplares.get(i - 1).getId());
                                }
                            }
                            daoObra.update(obra);

                            response.sendRedirect("Obra?op=view&update=true&idObra=" + obra.getId());
                        }
                    }
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
