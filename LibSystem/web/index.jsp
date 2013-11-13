<%-- 
    Document   : index
    Created on : 21/10/2013, 23:48:39
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <div class="jumbotron">
                        <h1>Hello, world!</h1>
                        <p>
                            O sistema Columbus&TRADE; permite uma completa manipulação e controle de bibliotecas ou acervos literários e artísticos.
                        </p>
                        <hr/>
                        <a href="Usuario?op=login"
                           class="btn btn-lg btn-primary">
                            Faça seu login
                        </a>

                    </div>
                </div>
            </div>            
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
