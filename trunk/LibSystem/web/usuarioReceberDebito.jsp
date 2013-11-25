<%-- 
    Document   : usuarioReceberDebito
    Created on : 25/11/2013, 09:13:53
    Author     : max
    Description:
--%>
<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page import="model.Debito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Debito> debitos = (List<Debito>) request.getAttribute("debitos");
    Usuario usuarioView = (Usuario) request.getAttribute("usuario");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuarioView == null || usuario == null || debitos == null) {
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
                <h1>Receber débito</h1>                
                <%@include file="interfaceMessages.jsp" %>

                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>Débito</th>
                                    <td>Valor</td>
                                </tr>
                                <%
                                for(Debito d : debitos){%>
                                <tr>
                                    <td><%= d.getId() %></td>
                                    <td><%= d.getValor() %></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <form action="Usuario" method="POST">
                            <button type="submit" name="op" value="receberDebitos" 
                                    class="btn btn-block btn-primary btn-lg">
                                Receber débitos
                            </button>
                            <input type="hidden" name="idUsuario" value="<%= usuarioView.getId() %>" />
                        </form>               
                    </div>
                </div>



            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
