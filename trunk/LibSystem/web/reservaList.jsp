<%-- 
    Document   : reservaList
    Created on : 12/11/2013, 16:48:09
    Author     : max
    Description:
--%>

<%@page import="java.util.List"%>
<%@page import="model.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
List<Reserva> reservas = (List<Reserva>)request.getAttribute("reservas");
 Usuario usuario = (Usuario) session.getAttribute("usuario");
if (reservas == null || usuario == null){
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
            <%@include file="interfaceMenuEmprestimo.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Listar Reservas</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="" role="form">                        
                    <div class="row">
                        <div class="form-group col-lg-10">
                            <label for="obra">Obra</label>
                            <input type="text" name="obra" value=""
                                   id="obra" class="form-control"
                                   placeholder="Nome da obra"/>
                        </div>
                    </div>
                    <div id="searchfilter-panel" style="display: none;">
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="status">Status</label>
                                <select class="form-control" id="status">
                                    <option value="">Selecione um status</option>
                                    <option value="<%= Reserva.AGUARDANDO %>">Aguardando</option>
                                    <option value="<%= Reserva.CANCELADA %>">Cancelada</option>
                                    <option value="<%= Reserva.DISPONIVEL %>">Disponivel</option>
                                    <option value="<%= Reserva.EFETUADA %>">Efetuada</option>
                                    <option value="<%= Reserva.EXPIRADA %>">Expirada</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <button type="submit" name="op" value="search" 
                            class="btn btn-lg btn-primary">Pesquisar</button>
                    <button type="button" value="" 
                            data-searchfilter="toggle" 
                            data-searchfilter-target="#searchfilter-panel" 
                            class="btn btn-lg btn-default">Mais opções</button>
                </form>
                <hr/>
                <%if(!reservas.isEmpty()){ %>
                <table data-rowlink class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>#ID</th>
                            <th>Título</th>
                            <th>Usuário</th>
                            <th>Status</th>
                    </thead>
                    <tbody>
                        <%for (Reserva reserva : reservas) { %>
                        <tr data-rowlink-href="Reserva?op=view&idReserva=<%=reserva.getId()%>">
                            <td><%=reserva.getId()%></td>
                            <td><%=reserva.getObra().getTitulo()%></td>
                            <td><%=reserva.getUsuario().getNome() %></td>
                            <td>
                                        <%switch (reserva.getStatus()) {
                                                case Reserva.AGUARDANDO:%>
                                        <span class="label label-default">Aguardando</span>
                                        <% break;
                                            case Reserva.EFETUADA:%>
                                        <span class="label label-info">Efetuada</span>
                                        <% break;
                                            case Reserva.DISPONIVEL:%>
                                        <span class="label label-success">Disponível</span>
                                        <% break;
                                            case Reserva.EXPIRADA:%>
                                        <span class="label label-warning">Expirada</span>
                                        <% break;
                                            case Reserva.CANCELADA:%>
                                        <span class="label label-danger">Cancelada</span>
                                        <%}%>
                                    </td>
                        </tr>
                        <%}%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="4"><%=reservas.size() == 1 ? "1 resultado encontrado" : reservas.size() + " resultados encontrados"%></th>
                        </tr>
                    </tfoot>
                </table>
                <%} else {%>
                <h3>Nenhuma Reserva encontrada!</h3>
                <%}%>
            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
