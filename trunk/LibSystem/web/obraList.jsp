<%-- 
    Document   : obraList
    Created on : 12/11/2013, 08:47:01
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
                <h1>Listar Obras</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="" role="form">                        
                    <div class="row">
                        <div class="form-group col-lg-6">
                            <label for="titulo">Título</label>
                            <input type="text" name="titulo" value=""
                                   id="titulo" class="form-control"
                                   placeholder="Título da obra"/>
                        </div>
                    </div>
                    <div id="searchfilter-panel" style="display: none;">
                        <div class="row">
                            <div class="form-group col-lg-3">
                                <label for="exemplares">Exemplares</label>
                                <input type="number" name="exemplares" min="1" value="1"
                                       id="exemplares" class="form-control"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="categoria">Categoria</label>
                                <select name="categoria"
                                        id="categoria" class="form-control">
                                    <option value="midia">Mídia</option>
                                    <option value="livro">Livro</option>
                                    <option value="periodico">Periódico</option>
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
