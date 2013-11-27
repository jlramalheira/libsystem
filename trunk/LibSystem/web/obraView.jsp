<%-- 
    Document   : obraView
    Created on : 12/11/2013, 08:38:37
    Author     : max
    Description:
--%>
<%@page import="java.util.List"%>
<%@page import="model.Categoria"%>
<%@page import="model.Exemplar"%>
<%@page import="model.Obra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Obra obra = (Obra) request.getAttribute("obra");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<Exemplar> exemplares = (List<Exemplar>) request.getAttribute("exemplares");
    if (obra == null || usuario == null || exemplares == null) {
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
            <%@include file="interfaceMenuObra.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Visualizar Obra</h1>
                <%@include file="interfaceMessages.jsp" %>
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">Nome</th>
                                    <td><%=obra.getTitulo()%></td>
                                </tr>
                                <tr>
                                    <th>Autor</th>
                                    <td><%=obra.getAutor()%></td>
                                </tr>
                                <tr>
                                    <th>Ano</th>
                                    <td><%=obra.getAno()%></td>
                                </tr>
                                <tr>
                                    <th>Editora</th>
                                    <td><%=obra.getEditora()%></td>
                                </tr>
                                <tr>
                                    <th>Categoria</th>
                                    <td><%=obra.getCategoria().valor()%></td>
                                </tr>
                                <tr>
                                    <th>Exemplares</th>
                                    <td><%=obra.getNumeroExemplares()%></td>
                                </tr>
                                <tr>
                                    <th>Exemplares disponíveis</th>
                                    <td><%=obra.getNumeroExemplares(Exemplar.DISPONIVEL)%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <a href="Obra?op=update&idObra=<%=obra.getId()%>" 
                           class="btn btn-primary btn-block btn-lg">
                            Editar obra
                        </a>
                        <a href="Obra?op=delete&idObra=<%=obra.getId()%>" 
                           class="btn btn-danger btn-block btn-lg">
                            Excluir obra
                        </a> 
                    </div>
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <h2>Exemplares</h2>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>#ID</th>
                                    <th>Titulo</th>
                            </thead>
                            <tbody>
                                <%for (Exemplar exemplar : exemplares) {%>
                                <tr>
                                    <td><%=exemplar.getId()%></td>
                                    <td><%=exemplar.getStatus() == Exemplar.DISPONIVEL ? "<span class=\"label label-success\">Disponível</span>" : "<span class=\"label label-info\">Emprestado</span>"%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>



            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
