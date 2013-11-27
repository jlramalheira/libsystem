/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.DaoDebito;
import dao.DaoEmprestimo;
import dao.DaoPerfil;
import dao.DaoReserva;
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
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import model.Debito;
import model.Emprestimo;
import model.Reserva;
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

        HttpSession session = request.getSession(true);
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
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
                    if (usuario == null || usuarioLogado == null) {
                        response.sendError(404);
                    } else {
                        //Não pode editar dados de outro usuário
                        if (usuarioLogado.getId() == usuario.getId()) {
                            request.setAttribute("usuario", usuario);
                        } else {
                            request.setAttribute("usuario", usuarioLogado);
                        }
                        dispatcher = request.getRequestDispatcher("usuarioUpdate.jsp");
                        dispatcher.forward(request, response);
                    }
                    break;
                case "alterarSenha":
                    dispatcher = request.getRequestDispatcher("usuarioAlterarSenha.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "view":
                    idUsuario = Long.parseLong(request.getParameter("idUsuario"));

                    usuario = daoUsuario.get(idUsuario);

                    if (usuario == null || usuarioLogado == null) {
                        response.sendError(404);
                    } else {
                        //Usuario sem privilegio nao pode ver dados dos outros usuarios
                        if ((usuarioLogado.getId() != usuario.getId()) && (!usuarioLogado.getPerfil().hasAcessoUsuario())) {
                            request.setAttribute("usuario", usuarioLogado);
                        } else {
                            request.setAttribute("usuario", usuario);
                        }
                        if (request.getParameter("new") != null) {
                            messages.add(new Message("Usuário cadastrado com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("update") != null) {
                            messages.add(new Message("Usuário editado com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("senha") != null) {
                            messages.add(new Message("Senha alterada com sucesso!", Message.TYPE_SUCCESS));
                        } else if (request.getParameter("debito") != null) {
                            messages.add(new Message("Debitos recebidos com sucesso!", Message.TYPE_SUCCESS));
                        }

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

                    if (request.getParameter("new") != null) {
                        messages.add(new Message("Usuário deletado com sucesso!", Message.TYPE_SUCCESS));
                        request.setAttribute("messages", messages);
                    }

                    dispatcher = request.getRequestDispatcher("usuarioList.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "login":
                    if (request.getParameter("recover") != null) {
                        messages.add(new Message("Sua nova senha foi enviada ao seu email!", Message.TYPE_SUCCESS));
                        request.setAttribute("messages", messages);
                    }

                    dispatcher = request.getRequestDispatcher("usuarioLogin.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "recuperarSenha":
                    dispatcher = request.getRequestDispatcher("usuarioRecuperarSenha.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "loggout":
                    session.invalidate();
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                case "receberDebitos":
                    idUsuario = Long.parseLong(request.getParameter("idUsuario"));

                    usuario = daoUsuario.get(idUsuario);

                    if (usuario == null || usuarioLogado == null) {
                        response.sendError(404);
                    } else {
                        List<Debito> debitos = new DaoDebito().listByUsuario(usuario);

                        request.setAttribute("usuario", usuario);
                        request.setAttribute("debitos", debitos);

                        dispatcher = request.getRequestDispatcher("usuarioReceberDebito.jsp");
                        dispatcher.forward(request, response);
                    }
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

        try {
            HttpSession session = request.getSession(true);
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
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
                            if (usuarioLogado.getId() != usuario.getId()) {
                                response.sendError(404);
                            } else {
                                Perfil perfil = usuario.getPerfil();
                                if (usuarioLogado.getPerfil().hasAcessoUsuario()) {
                                    Long idPerfil = Long.parseLong(request.getParameter("perfil"));
                                    perfil = new DaoPerfil().get(idPerfil);
                                }
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
                                            messages.add(new Message("Email já cadastrado", Message.TYPE_ERROR));

                                            request.setAttribute("messages", messages);
                                            dispatcher = request.getRequestDispatcher("usuarioUpdate.jsp");
                                            dispatcher.forward(request, response);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "alterarSenha":
                        String senhaAntiga = request.getParameter("passwordOld");
                        if (usuarioLogado.getSenha().equals(Util.criptografarSenha(senhaAntiga))) {
                            String senhaNew = request.getParameter("passwordNew");

                            usuarioLogado.setSenha(Util.criptografarSenha(senhaNew));

                            daoUsuario.update(usuarioLogado);

                            String email = usuarioLogado.getEmail();
                            Email.sendEmail(email, "Alteração Senha sistema Columbus",
                                    "Olá " + usuario.getNome() + "\n\n"
                                    + "Sua senha foi alterada para: " + senhaNew + "\n\n"
                                    + "Atenciosamente,\nColumbus");

                            response.sendRedirect("Usuario?op=view&senha=true&idUsuario=" + usuarioLogado.getId());
                        } else {
                            messages.add(new Message("Senha Antiga inválida!", Message.TYPE_ERROR));

                            request.setAttribute("usuario", usuarioLogado);
                            request.setAttribute("messages", messages);

                            dispatcher = request.getRequestDispatcher("usuarioAlterarSenha.jsp");
                            dispatcher.forward(request, response);
                        }
                        break;
                    case "login":
                        usuario = daoUsuario.getByLogin(request.getParameter("usuario"));

                        if (usuario != null) {
                            if (usuario.getSenha().equals(Util.criptografarSenha(request.getParameter("senha")))) {
                                session.setAttribute("usuario", usuario);

                                response.sendRedirect("Usuario?op=view&recover=true&idUsuario=" + usuario.getId());
                            } else {
                                messages.add(new Message("Senha inválida!", Message.TYPE_ERROR));

                                request.setAttribute("messages", messages);
                                dispatcher = request.getRequestDispatcher("usuarioLogin.jsp");
                                dispatcher.forward(request, response);
                            }
                        } else {
                            messages.add(new Message("Login inválido!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);
                            dispatcher = request.getRequestDispatcher("usuarioLogin.jsp");
                            dispatcher.forward(request, response);
                        }
                        break;
                    case "recuperarSenha":
                        usuario = daoUsuario.getByEmail(request.getParameter("email"));

                        if (usuario != null) {
                            String senha = Util.geraSenhaAleatoria();
                            usuario.setSenha(Util.criptografarSenha(senha));

                            daoUsuario.update(usuario);

                            String email = usuario.getEmail();
                            try {
                                Email.sendEmail(email, "Alteração Senha sistema Columbus",
                                        "Olá " + usuario.getNome() + "\n\n"
                                        + "Sua senha foi alterada para: " + senha + "\n"
                                        + "Recomendamos que você altere sua senha!\n\n"
                                        + "Atenciosamente,\nColumbus");
                            } catch (Exception ex) {
                                response.sendError(404);
                            }

                            response.sendRedirect("Usuario?op=login");
                        } else {
                            messages.add(new Message("Email inválido!", Message.TYPE_ERROR));

                            request.setAttribute("messages", messages);
                            dispatcher = request.getRequestDispatcher("usuarioRecuperarSenha.jsp");
                            dispatcher.forward(request, response);
                        }
                        break;
                    case "receberDebitos":
                        idUsuario = Long.parseLong(request.getParameter("idUsuario"));

                        usuario = daoUsuario.get(idUsuario);

                        if (usuario == null || usuarioLogado == null || !usuarioLogado.getPerfil().hasAcessoEmprestimo()) {
                            response.sendError(404);
                        } else {
                            DaoDebito daoDebito = new DaoDebito();
                            List<Debito> debitos = daoDebito.listByUsuario(usuario);

                            for (Debito debito : debitos) {
                                debito.setValor(0);
                                daoDebito.update(debito);
                            }

                            response.sendRedirect("Usuario?op=view&debito=true&idUsuario=" + usuario.getId());
                        }
                        break;
                    case "delete":
                        idUsuario = Long.parseLong(request.getParameter("idUsuario"));

                        usuario = daoUsuario.get(idUsuario);

                        if (usuario == null) {
                            response.sendError(404);
                        } else {
                            List<Reserva> reservas = new DaoReserva().listByUsuario(usuario);
                            if (reservas.isEmpty()) {
                                List<Emprestimo> emprestimos = new DaoEmprestimo().listByUsuario(usuario);
                                if (emprestimos.isEmpty()) {
                                    daoUsuario.remove(idUsuario);

                                    response.sendRedirect("Usuario?op=list&delete=true");
                                } else {
                                    messages.add(new Message("Impossível excluir pois o usuário possui emprestimos!", Message.TYPE_ERROR));

                                    request.setAttribute("messages", messages);
                                    request.setAttribute("usuario", usuario);

                                    dispatcher = request.getRequestDispatcher("usuarioRecuperarSenha.jsp");
                                    dispatcher.forward(request, response);
                                }
                            } else {
                                messages.add(new Message("Impossível excluir pois o usuário possui reservas!", Message.TYPE_ERROR));

                                request.setAttribute("messages", messages);
                                request.setAttribute("usuario", usuario);

                                dispatcher = request.getRequestDispatcher("usuarioRecuperarSenha.jsp");
                                dispatcher.forward(request, response);
                            }
                        }
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
