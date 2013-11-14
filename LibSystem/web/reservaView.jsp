<%-- 
    Document   : reservaView
    Created on : 12/11/2013, 16:34:08
    Author     : max
    Description:
--%>
<%@page import="model.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Reserva reserva = (Reserva) request.getAttribute("reserva");
    if (reserva == null) {
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
                <h1>Visualizar Reserva</h1>
                <%@include file="interfaceMessages.jsp" %>
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">Obra</th>
                                    <td><%=reserva.getObra().getTitulo()%></td>
                                </tr>
                                <tr>
                                    <th>Status</th>
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
                                <%if (reserva.getStatus() == Reserva.DISPONIVEL){ %>
                                <tr>
                                    <th>Expira em</th>
                                    <td>08/08/2008</td>
                                </tr>
                                <%} else if(reserva.getStatus() == Reserva.EXPIRADA){%>
                                <tr>
                                    <th>Expirada em</th>
                                    <td>08/08/2008</td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <%if (reserva.getStatus() == Reserva.AGUARDANDO || reserva.getStatus() == Reserva.DISPONIVEL) {%>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <a href="Reserva?op=cancelar&idReserva=<%=reserva.getId()%>" 
                           class="btn btn-danger btn-block btn-lg">
                            Cancelar reserva
                        </a>                  
                    </div>
                    <%}%>
                </div>
            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
