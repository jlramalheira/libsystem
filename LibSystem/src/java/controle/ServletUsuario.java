/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoPerfil;
import dao.DaoUsuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.Usuario;
import email.Email;
import javax.servlet.http.HttpSession;
import util.Message;
import util.Util;

/**
 *
 * @author joao
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class ServletUsuario extends HttpServlet {

    private DaoUsuario daoUsuario;
    private Usuario usuario;
    private ArrayList<Message> messages;
    private RequestDispatcher dispatcher;
    private String action;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        action = request.getParameter("op");
        daoUsuario = new DaoUsuario();
        messages = new ArrayList<>();
        if (action == null) {
            response.sendError(404);
        } else {
            Long idUsuario;
            switch (action) {
                case "create":
                    dispatcher = request.getRequestDispatcher("usuarioCreate.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "update":
                    idUsuario = Long.parseLong(request.getParameter("idUsuario"));

                    usuario = daoUsuario.get(idUsuario);

                    if (usuario == null) {
                        response.sendError(404);
                    } else {
                        request.setAttribute("usuario", usuario);

                        dispatcher = request.getRequestDispatcher("usuarioUpdate.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "delete":
                    break;
                case "view":
                    idUsuario = Long.parseLong(request.getParameter("idUsuario"));

                    usuario = daoUsuario.get(idUsuario);

                    if (usuario == null) {
                        response.sendError(404);
                    } else {
                        if (request.getParameter("new") != null) {
                            messages.add(new Message("Usuário cadastrado com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("update") != null) {
                            messages.add(new Message("Usuário editado com sucesso!", Message.TYPE_SUCCESS));
                        }

                        request.setAttribute("usuario", usuario);
                        request.setAttribute("messages", messages);

                        dispatcher = request.getRequestDispatcher("usuarioView.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "search":
                    String nome = request.getParameter("nome");
                    String email = request.getParameter("email");
                    Long idPerfil = Long.parseLong(request.getParameter("perfil"));
                    List<Usuario> usuariosSearch;

                    if (idPerfil == -1) {
                        usuariosSearch = daoUsuario.listByNomeEmail(nome, email);
                    } else {
                        Perfil perfil = new DaoPerfil().get(idPerfil);
                        usuariosSearch = daoUsuario.listByNomeEmailPerfil(nome, email, perfil);
                    }

                    request.setAttribute("usuarios", usuariosSearch);

                    dispatcher = request.getRequestDispatcher("usuarioList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "list":
                    List<Usuario> usuarios = daoUsuario.list();

                    request.setAttribute("usuarios", usuarios);

                    dispatcher = request.getRequestDispatcher("usuarioList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "login":
                    dispatcher = request.getRequestDispatcher("usuarioLogin.jsp");
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
        daoUsuario = new DaoUsuario();
        messages = new ArrayList<>();

        HttpSession session = request.getSession(true);

        if (action == null) {
            response.sendError(404);
        } else {
            Long idUsuario;
            switch (action) {
                case "create":
                    usuario = new Usuario();
                    try {
                        Long idPerfil = Long.parseLong(request.getParameter("perfil"));
                        Perfil perfil = new DaoPerfil().get(idPerfil);

                        if (perfil == null) {
                            throw new Exception();
                        }

                        String login = request.getParameter("usuario");
                        String email = request.getParameter("email");

                        List<Usuario> usuarios = daoUsuario.listByEmail(email);

                        if (usuarios.isEmpty()) {
                            usuarios = daoUsuario.listByLogin(login);

                            if (usuarios.isEmpty()) {
                                usuario.setLogin(login);
                                usuario.setEmail(email);
                                usuario.setNome(request.getParameter("nome"));
                                usuario.setSenha(Util.criptografarSenha(request.getParameter("senha")));
                                usuario.setPerfil(perfil);

                                daoUsuario.insert(usuario);

                                Email.sendEmail(email, "Bem-vindo ao Columbus",
                                        "Olá " + usuario.getNome() + "\n\n"
                                        + "Seu cadastro foi efetuado com sucesso!\n\n"
                                        + "Atenciosamente,\nColumbus");

                                response.sendRedirect("Usuario?op=view&new=true&idUsuario=" + usuario.getId());
                            } else {
                                messages.add(new Message("Login já cadastrado", Message.TYPE_ERROR));

                                request.setAttribute("messages", messages);
                                dispatcher = request.getRequestDispatcher("usuarioCreate.jsp");
                                dispatcher.forward(request, response);
                            }
                        } else {
                            //TODO tratar erro
                            messages.add(new Message("Email já cadastrado", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);
                            dispatcher = request.getRequestDispatcher("usuarioCreate.jsp");
                            dispatcher.forward(request, response);
                        }

                    } catch (Exception ex) {
                        //TODO ver se tem outro erro possivel
                        response.sendError(404);
                    }
                    break;
                case "update":
                    idUsuario = Long.parseLong(request.getParameter("idUsuario"));

                    usuario = daoUsuario.get(idUsuario);

                    if (usuario == null) {
                        response.sendError(404);
                    } else {
                        Long idPerfil = Long.parseLong(request.getParameter("perfil"));
                        Perfil perfil = new DaoPerfil().get(idPerfil);

                        if (perfil == null) {
                            response.sendError(404);
                        } else {

                            String email = request.getParameter("email");

                            List<Usuario> usuarios = daoUsuario.listByEmail(email);

                            if (usuarios.isEmpty()) {
                                usuario.setEmail(email);
                                usuario.setNome(request.getParameter("nome"));
                                usuario.setPerfil(perfil);

                                daoUsuario.update(usuario);

                                response.sendRedirect("Usuario?op=view&update=true&idUsuario=" + usuario.getId());
                            } else {
                                if (usuarios.get(0).getEmail().equals(email)) {
                                    usuario.setEmail(email);
                                    usuario.setNome(request.getParameter("nome"));
                                    usuario.setPerfil(perfil);

                                    daoUsuario.update(usuario);

                                    response.sendRedirect("Usuario?op=view&update=true&idUsuario=" + usuario.getId());
                                } else {
                                    //TODO tratar erro
                                    messages.add(new Message("Email já cadastrado", Message.TYPE_ERROR));

                                    request.setAttribute("messages", messages);
                                    dispatcher = request.getRequestDispatcher("usuarioUpdate.jsp");
                                    dispatcher.forward(request, response);
                                }
                            }
                        }
                    }
                    break;
                case "delete":

                    break;
                case "login":
                    usuario = daoUsuario.getByLogin(request.getParameter(""));

                    if (usuario != null) {
                        if (usuario.getSenha().equals(Util.criptografarSenha(request.getParameter("")))) {
                            session.setAttribute("usuario", usuario);
                        } else {
                            messages.add(new Message("Senha inválida!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);
                            dispatcher = request.getRequestDispatcher("usuarioCreate.jsp");
                            dispatcher.forward(request, response);
                        }
                    } else {
                        messages.add(new Message("Login inválido!", Message.TYPE_ERROR));

                        request.setAttribute("messages", messages);
                        dispatcher = request.getRequestDispatcher("usuarioCreate.jsp");
                        dispatcher.forward(request, response);
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
