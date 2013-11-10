/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoExemplar;
import dao.DaoObra;
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
            switch (action) {
                case "create":
                    obra = new Obra();

                    obra.setAno(Integer.parseInt(request.getParameter("")));
                    obra.setAutor(request.getParameter(""));
                    obra.setCategoria(request.getParameter(""));
                    obra.setEditora(request.getParameter(""));
                    obra.setTitulo(request.getParameter(""));

                    int numeroExemplares = Integer.parseInt(request.getParameter(""));

                    Exemplar exemplar = new Exemplar();
                    exemplar.setObra(obra);
                    exemplar.setStatus(Exemplar.DISPONIVEL);
                    DaoExemplar daoExemplar = new DaoExemplar();

                    for (int i = 0; i < numeroExemplares; i++) {
                        daoExemplar.insert(exemplar);
                    }

                    daoObra.insert(obra);

                    response.sendRedirect("Obra?op=view&new=true&idObra=" + obra.getId());

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
