<%-- 
    Document   : usuarioList
    Created on : 08/11/2013, 10:55:41
    Author     : max
    Description:
--%>

<%@page import="model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="dao.DaoPerfil"%>
<%@page import="model.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%List<Perfil> perfis = new DaoPerfil().list();
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    if (usuarios == null) {
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
                <h1>Listar usuários</h1>

                <form method="get" action="Usuario" role="form">                        
                    <div class="row">
                        <div class="form-group col-lg-8">
                            <label for="nome">Nome</label>
                            <input type="text" name="nome" value=""
                                   id="nome" class="form-control"
                                   placeholder="Seu nome completo"/>
                        </div>
                    </div>
                    <div id="searchfilter-panel" style="display: none;">
                        <div class="row"> 
                            <div class="form-group col-lg-5">
                                <label for="perfil">Perfil</label>
                                <select name="perfil"
                                        id="perfil" class="form-control">
                                    <option value="-1" selected="selected">Selecione</option>
                                    <%for (Perfil perfil : perfis) {%>
                                    <option value="<%=perfil.getId()%>"><%=perfil.getNome()%></option>
                                    <% }%>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="email">E-mail</label>
                                <input type="email" name="email" value=""
                                       id="email" class="form-control"
                                       placeholder="E-mail para recuperação de senha"/>
                            </div>
                        </div>
                    </div>
                    <button type="submit" name="op" value="search" 
                            class="btn btn-lg btn-default">Pesquisar</button>
                    <button type="button" value="" 
                            data-searchfilter="toggle" 
                            data-searchfilter-target="#searchfilter-panel" 
                            class="btn btn-lg btn-default">Mais opções</button>
                </form>
                <hr/>
                <% if (!usuarios.isEmpty()){ %>
                <table data-rowlink class="table table-striped table-hover table-responsive">
                    <thead>
                        <tr>
                            <th class="col-">#</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                    </thead>
                    <tbody>
                        <%for (Usuario usuario : usuarios){ %>
                        <tr data-rowlink-href="Usuario?op=view&idUsuario=<%=usuario.getId()%>">
                            <td><%=usuario.getId()%></td>
                            <td><%=usuario.getNome()%></td>
                            <td><%=usuario.getEmail()%></td>
                        </tr>
                        <% }%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="3"><%=usuarios.size() == 1 ? "1 resultado encontrado" : usuarios.size() + " resultados encontrados"%></th>
                        </tr>
                    </tfoot>
                </table>
                <%} else {%>
                <h3>Nenhum usuário encontrado!</h3>
                <%}%>
            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
