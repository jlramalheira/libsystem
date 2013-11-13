<%-- 
    Document   : obraView
    Created on : 12/11/2013, 08:38:37
    Author     : max
    Description:
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
            <%@include file="interfaceMenuObra.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Visualizar Obra</h1>                
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">Nome</th>
                                    <td>Data long data</td>
                                </tr>
                                <tr>
                                    <th>Categoria</th>
                                    <td>Mídia</td>
                                </tr>
                                <tr>
                                    <th>Exemplares totais</th>
                                    <td>123</td>
                                </tr>
                                <tr>
                                    <th>Exemplares disponíveis</th>
                                    <td>123</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <a href="Obra?op=update&id=#" 
                           class="btn btn-primary btn-block btn-lg">
                            Editar obra
                        </a>                  
                    </div>
                </div>



            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
