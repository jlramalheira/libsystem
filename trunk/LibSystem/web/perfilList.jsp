<%-- 
    Document   : perfilList
    Created on : 08/11/2013, 18:51:09
    Author     : max
    Description:
--%>

<%@page import="java.util.List"%>
<%@page import="model.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%List<Perfil> perfis = (List<Perfil>) request.getAttribute("perfis");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (perfis == null || usuario == null) {
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
                <h1>Listar Perfis</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="get" action="Perfil" role="form">                        
                    <div class="row">
                        <div class="form-group col-lg-6">
                            <label for="nome">Nome</label>
                            <input type="text" name="nome" value=""
                                   id="nome" class="form-control"
                                   placeholder="Nomenclatura do perfil"/>
                        </div>
                    </div>
                    <button type="submit" name="op" value="search"
                            class="btn btn-lg btn-primary">Pesquisar</button>
                </form>
                <hr/>
                <%if (!perfis.isEmpty()) {%>
                <table data-rowlink class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nome</th>
                    </thead>
                    <tbody>
                        <% for (Perfil perfil : perfis) {%>
                        <tr data-rowlink-href="Perfil?op=view&idPerfil=<%=perfil.getId()%>">
                            <td><%=perfil.getId()%></td>
                            <td><%=perfil.getNome()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="2"><%=perfis.size() == 1 ? "1 resultado encontrado" : perfis.size() + " resultados encontrados"%></th>
                        </tr>
                    </tfoot>
                </table>
                <% } else {%>
                <h3>Nenhum perfil encontrado!</h3>
                <%}%>
            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
