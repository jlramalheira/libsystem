<%-- 
    Document   : perfilView
    Created on : 08/11/2013, 18:25:28
    Author     : max
    Description:
--%>
<%@page import="model.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Perfil perfil = (Perfil) request.getAttribute("perfil");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (perfil == null || usuario == null) {
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
                <h1>Visualizar Perfil</h1>
                <%@include file="interfaceMessages.jsp" %>
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th><%=perfil.getNome()%></th>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <a href="Perfil?op=update&idPerfil=<%=perfil.getId()%>" 
                           class="btn btn-primary btn-block btn-lg">
                            Editar dados
                        </a>                  
                    </div>
                </div>



            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
