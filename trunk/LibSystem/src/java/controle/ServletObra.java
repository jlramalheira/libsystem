/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoExemplar;
import dao.DaoObra;
import dao.DaoReserva;
import java.io.IOException;
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
                    List<Obra> obrasSearch = daoObra.list();

                    request.setAttribute("obras", obrasSearch);

                    dispatcher = request.getRequestDispatcher("obraList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "list":
                    List<Obra> obras = daoObra.list();
                    
                    if (request.getParameter("deleted") != null){
                        messages.add(new Message("Obra deletada com sucesso!", Message.TYPE_SUCCESS));
                        
                        request.setAttribute("messages", messages);
                    }

                    request.setAttribute("obras", obras);

                    dispatcher = request.getRequestDispatcher("obraList.jsp");
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
        daoObra = new DaoObra();
        messages = new ArrayList<>();
        if (action == null) {
            response.sendError(404);
        } else {
            Long idObra;
            DaoExemplar daoExemplar = new DaoExemplar();
            Exemplar exemplar = new Exemplar();
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


                    exemplar.setObra(obra);
                    exemplar.setStatus(Exemplar.DISPONIVEL);

                    for (int i = 0; i < numeroExemplares; i++) {
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

                        if (numeroExemplaresUpdate <= obra.getExemplares(Exemplar.DISPONIVEL)) {
                            messages.add(new Message("Impossível excluir exemplares emprestados!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);

                            dispatcher = request.getRequestDispatcher("obraUpdate.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            if (numeroExemplaresUpdate > obra.getExemplares()) {
                                exemplar.setObra(obra);
                                exemplar.setStatus(Exemplar.DISPONIVEL);
                                for (int i = 1; i <= (numeroExemplaresUpdate - obra.getExemplares()); i++) {
                                    daoExemplar.insert(exemplar);
                                }
                            } else {
                                List<Exemplar> exemplares = daoExemplar.listByObraAndStatus(obra, Exemplar.DISPONIVEL);
                                for (int i = 1; i <= (obra.getExemplares() - numeroExemplaresUpdate); i++) {
                                    daoExemplar.remove(exemplares.get(i - 1).getId());
                                }
                            }
                            daoObra.update(obra);
                            
                            response.sendRedirect("Obra?op=view&update=true&idObra"+obra.getId());
                        }
                    }
                    break;
                case "delete":
                    idObra = Long.parseLong(request.getParameter("idObra"));

                    obra = daoObra.get(idObra);

                    if (obra == null) {
                        response.sendError(404);
                    } else {
                        if (obra.hasEmprestado()){
                            messages.add(new Message("Impossível excluir Obra com exemplares emprestados!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);

                            dispatcher = request.getRequestDispatcher("obraUpdate.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            DaoReserva daoReserva = new DaoReserva();
                            List<Reserva> reservas = daoReserva.listByObra(obra);
                            for (Reserva reserva : reservas){
                                daoReserva.remove(reserva.getId());
                            }
                            
                            List<Exemplar> exemplares = daoExemplar.listByObra(obra);
                            for (Exemplar exemplarDel : exemplares){
                                daoExemplar.remove(exemplarDel.getId());
                            }
                            
                            daoObra.remove(idObra);
                            
                            response.sendRedirect("Obra?op=list&deleted=true");
                        }
                    }
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
