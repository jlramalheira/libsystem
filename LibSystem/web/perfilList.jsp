<%-- 
    Document   : perfilList
    Created on : 08/11/2013, 18:51:09
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
            <%@include file="interfaceMenuUsuario.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Listar Perfis</h1>

                <form method="post" action="Perfil" role="form">                        
                    <div class="row">
                        <div class="form-group col-lg-6">
                            <label for="nome">Nome</label>
                            <input type="text" name="nome" value=""
                                   id="nome" class="form-control"
                                   placeholder="Nomenclatura do perfil"/>
                        </div>
                    </div>
                    <button type="submit" name="" value=""
                            class="btn btn-lg btn-default">Pesquisar</button>
                </form>
                <hr/>
                <table data-rowlink class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nome</th>
                    </thead>
                    <tbody>
                        <tr data-rowlink-href="#">
                            <td>#</td>
                            <td>Data</td>
                        </tr>
                        <tr data-rowlink-href="#">
                            <td>#</td>
                            <td>Data</td>
                        </tr>
                        <tr data-rowlink-href="#">
                            <td>#</td>
                            <td>Data</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="2">3 resultados encontrados</th>
                        </tr>
                    </tfoot>
                </table>

            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
