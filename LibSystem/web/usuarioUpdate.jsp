<%-- 
    Document   : usuarioUpdate
    Created on : 08/11/2013, 18:16:09
    Author     : max
    Description:
--%>

<%@page import="model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="dao.DaoPerfil"%>
<%@page import="model.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  List<Perfil> perfis = new DaoPerfil().list();
    Usuario usuarioUpdate = (Usuario) request.getAttribute("usuario");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuarioUpdate == null) {
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
                <h1>Atualizar usuário</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Usuario" role="form">
                    <input type="hidden" name="idUsuario" value="<%=usuarioUpdate.getId()%>" />
                            <%if (usuario.getPerfil().hasAcessoUsuario()){ %>
                    <fieldset>
                        <legend>Informações do sistema</legend>

                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="perfil">Perfil</label>
                                <select name="perfil"
                                        id="perfil" class="form-control">
                                    <%for (Perfil perfil : perfis) {%>
                                    <option value="<%=perfil.getId()%>" <%=usuarioUpdate.getPerfil().getId() == perfil.getId()? "selected=\"selected\"" : "" %>><%=perfil.getNome()%></option>
                                    <% }%>
                                </select>
                            </div>
                        </div>
                    </fieldset>
                                <%}%>

                    <fieldset>
                        <legend>Informações pessoais</legend>
                        <div class="row">
                            <div class="form-group col-lg-8">
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" value="<%=usuarioUpdate.getNome()%>"
                                       id="nome" class="form-control"
                                       placeholder="Seu nome completo"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="email">E-mail</label>
                                <input type="email" name="email" value="<%=usuarioUpdate.getEmail()%>"
                                       id="email" class="form-control"
                                       placeholder="E-mail para recuperação de senha"/>
                            </div>
                        </div>
                    </fieldset>
                    <hr/>
                    <button type="submit" name="op" value="update"
                            class="btn btn-lg btn-primary">Atualizar</button>
                </form>

            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
