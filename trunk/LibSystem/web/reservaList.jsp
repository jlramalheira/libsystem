<%-- 
    Document   : reservaList
    Created on : 12/11/2013, 16:48:09
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
                            <div class="form-group col-lg-12">
                                <label>Status</label><br/>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="status" value="#"/>
                                    Aguardando
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="status" value="#"/>
                                    Disponível
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="status" value="#"/>
                                    Expirada
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="status" value="#"/>
                                    Cancelada
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="status" value="#"/>
                                    Efetuada
                                </label>
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
                <table data-rowlink class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>#ID</th>
                            <th>Input</th>
                            <th>Input</th>
                    </thead>
                    <tbody>
                        <tr data-rowlink-href="#">
                            <td>#</td>
                            <td>ABC</td>
                            <td>123</td>
                        </tr>
                        <tr data-rowlink-href="#">
                            <td>#</td>
                            <td>ABC</td>
                            <td>123</td>
                        </tr>
                        <tr data-rowlink-href="#">
                            <td>#</td>
                            <td>ABC</td>
                            <td>123</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="3">3 resultados encontrados</th>
                        </tr>
                    </tfoot>
                </table>

            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
