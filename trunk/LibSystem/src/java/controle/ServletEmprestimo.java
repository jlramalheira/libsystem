/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoEmprestimo;
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
import model.Emprestimo;
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
                case "list":
                    List<Emprestimo> emprestimos = daoEmprestimo.list();
                    
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
        action = request.getParameter("op");
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
