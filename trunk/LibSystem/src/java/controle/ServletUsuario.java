/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoPerfil;
import dao.DaoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.Usuario;
import util.Message;

/**
 *
 * @author joao
 */
@WebServlet(name = "usuario", urlPatterns = {"/usuario"})
public class ServletUsuario extends HttpServlet {
    
    private DaoUsuario daoUsuario;
    private Usuario usuario;
    private ArrayList<Message> messages;
    private RequestDispatcher dispatcher;
    private String action;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        action = request.getParameter("action");
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
        action = request.getParameter("action");
        if (action == null) {
            response.sendError(404);
        } else {
            switch (action) {
                case "create":                    
                    usuario = new Usuario();
                    try{
                        Long idPerfil = Long.parseLong(request.getParameter(""));
                        Perfil perfil = new DaoPerfil().get(idPerfil);
                        if (perfil == null){
                            throw new Exception();
                        }
                        
                        usuario.setLogin(request.getParameter(""));
                        usuario.setEmail(request.getParameter(""));
                        usuario.setNome(request.getParameter(""));
                        usuario.setSenha(request.getParameter(""));
                        
                        daoUsuario.insert(usuario);
                        
                        response.sendRedirect("usuario?action=view&new=true");
                    } catch (Exception ex){
                        //TODO ver se tem outro erro possivel
                        response.sendError(404);
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
