/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoPerfil;
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
import model.Perfil;
import util.Message;

/**
 *
 * @author joao
 */
@WebServlet(name = "Perfil", urlPatterns = {"/Perfil"})
public class ServletPerfil extends HttpServlet {

    private ArrayList<Message> messages;
    private RequestDispatcher dispatcher;
    private String action;
    private DaoPerfil daoPerfil = new DaoPerfil();
    private Perfil perfil;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoPerfil = new DaoPerfil();
        messages = new ArrayList<>();


        if (action == null) {
            response.sendError(404);
        } else {
            Long idPerfil;
            try{
            switch (action) {
                case "create":
                    dispatcher = request.getRequestDispatcher("perfilCreate.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "update":
                    idPerfil = Long.parseLong(request.getParameter("idPerfil"));

                    perfil = daoPerfil.get(idPerfil);

                    if (perfil == null) {
                        response.sendError(404);
                    } else {
                        request.setAttribute("perfil", perfil);

                        dispatcher = request.getRequestDispatcher("perfilUpdate.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "delete":
                    break;
                case "view":
                    idPerfil = Long.parseLong(request.getParameter("idPerfil"));

                    perfil = daoPerfil.get(idPerfil);

                    if (perfil == null) {
                        response.sendError(404);
                    } else {
                        if (request.getParameter("new") != null) {
                            messages.add(new Message("Perfil cadastrado com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("update") != null) {
                            messages.add(new Message("Perfil editado com sucesso!", Message.TYPE_SUCCESS));
                        }

                        request.setAttribute("perfil", perfil);
                        request.setAttribute("messages", messages);

                        dispatcher = request.getRequestDispatcher("perfilView.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "search":
                    List<Perfil> perfisSearch = daoPerfil.listByNome(request.getParameter("nome"));
                    
                    request.setAttribute("perfis", perfisSearch);

                    dispatcher = request.getRequestDispatcher("perfilList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "list":
                    List<Perfil> perfis = daoPerfil.list();

                    request.setAttribute("perfis", perfis);

                    dispatcher = request.getRequestDispatcher("perfilList.jsp");
                    dispatcher.forward(request, response);
                default:
                    response.sendError(404);
            }
            } catch (NumberFormatException ne){
                //TODO enviar mensagem?
                response.sendError(404);
            } catch (NullPointerException npe){
                response.sendError(404);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoPerfil = new DaoPerfil();

        System.out.println(action);
        if (action == null) {
            response.sendError(404);
        } else {
            Long idPerfil;
            switch (action) {
                case "create":
                    perfil = new Perfil();

                    perfil.setNome(request.getParameter("nome"));
                    perfil.setDiasEmprestimo(Integer.parseInt(request.getParameter("tempo-emprestimo")));
                    perfil.setQuantidadeEmprestimos(Integer.parseInt(request.getParameter("quantidade-emprestimo")));
                    perfil.setDiasReserva(Integer.parseInt(request.getParameter("tempo-reserva")));
                    perfil.setQuantidadeReservas(Integer.parseInt(request.getParameter("quantidade-reserva")));
                    
                    if (request.getParameter("gerenciar-perfil") != null){
                        perfil.setAcessoPerfil(true);
                    }
                    if (request.getParameter("gerenciar-usuario") != null){
                        perfil.setAcessoUsuario(true);
                    }
                    if (request.getParameter("gerenciar-obras") != null){
                        perfil.setAcessoObra(true);
                    }
                    if (request.getParameter("gerenciar-emprestimos") != null){
                        perfil.setAcessoEmprestimo(true);
                    }
                    if (request.getParameter("gerenciar-reservas") != null){
                        perfil.setAcessoReserva(true);
                    }
                    
                    daoPerfil.insert(perfil);
                    response.sendRedirect("Perfil?op=view&new=true&idPerfil=" + perfil.getId());
                    break;
                case "update":
                    idPerfil = Long.parseLong(request.getParameter("idPerfil"));

                    perfil = daoPerfil.get(idPerfil);

                    if (perfil == null) {
                        response.sendError(404);
                    } else {
                        perfil.setNome(request.getParameter("nome"));

                        daoPerfil.update(perfil);
                        response.sendRedirect("Perfil?op=view&update=true&idPerfil=" + perfil.getId());
                    }

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
