<%-- 
    Document   : usuarioLogin
    Created on : 24/11/2013, 10:01:50
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
        response.sendRedirect("Usuario?op=view&idUsuario=" + usuario.getId());
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
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <h1>Recuperar senha</h1>
                    <%@include file="interfaceMessages.jsp" %>
                    <form method="post" action="Usuario" role="form"
                          class="col-lg-4 col-lg-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <fieldset>
                                    <div class="row">
                                        <div class="form-group col-lg-12">
                                            <label for="email">Email</label>
                                            <input type="text" name="email" value=""
                                                   id="email" class="form-control"
                                                   placeholder="Email utilizado para acessar o sistema"/>
                                        </div>
                                    </div>
                                </fieldset>
                                <hr/>
                                <button type="submit" name="op" value="recuperarSenha"
                                        class="btn btn-lg btn-primary">Recuperar</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>            
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>