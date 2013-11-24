<%-- 
    Document   : usuarioLogin
    Created on : 24/11/2013, 10:01:50
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

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
                    <h1 class="text-danger">
                        Erro 404
                    </h1>
                    <%@include file="interfaceMessages.jsp" %>
                    <p class="lead">
                        A página requisitada não existe. Por favor, verifique os parâmetros de acesso.
                    </p>
                    
                </div>

            </div>
        </div>            
    </div>

    <%@include file="interfaceFooter.jsp" %>
</body>
</html>