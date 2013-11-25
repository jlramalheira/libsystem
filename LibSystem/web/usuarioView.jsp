<%-- 
    Document   : usuarioView
    Created on : 08/11/2013, 10:04:47
    Author     : max
    Description:
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Usuario usuarioView = (Usuario) request.getAttribute("usuario");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuarioView == null || usuario == null) {
        response.sendError(404);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="interfaceHead.jsp" %>
    </head>
    <body>
        <%@include file="interfaceHeader.jsp" %>

        <div class="container">            
            <%@include file="interfaceMenuUsuario.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Visualizar usuário</h1>
                <%@include file="interfaceMessages.jsp" %>
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>Nome</th>
                                    <td><%=usuarioView.getNome()%></td>
                                </tr>
                                <tr>
                                    <th>Email</th>
                                    <td><%=usuarioView.getEmail()%></td>
                                </tr>
                                <tr>
                                    <th>Perfil</th>
                                    <td><%=usuarioView.getPerfil().getNome()%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <a href="Usuario?op=update&idUsuario=<%=usuarioView.getId()%>" 
                           class="btn btn-primary btn-block btn-lg">
                            Editar dados
                        </a>
                        <a href="Usuario?op=alterarSenha&idUsuario=<%=usuarioView.getId()%>" 
                           class="btn btn-default btn-block btn-lg">
                            Alterar senha
                        </a> 
                        <%if (usuario.getValorDebitos() > 0) {%>
                        <a href="Usuario?op=pagarDebitos&idUsuario=<%=usuarioView.getId()%>" 
                           class="btn btn-default btn-block btn-lg">
                            Pagar debitos
                        </a> 
                        <% }%>
                    </div>
                </div>



            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
