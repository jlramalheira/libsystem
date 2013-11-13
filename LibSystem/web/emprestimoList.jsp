<%-- 
    Document   : emprestimoList
    Created on : 12/11/2013, 10:46:37
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
            <%@include file="interfaceMenuEmprestimo.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Listar empréstimos</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="" role="form">                        
                    <div class="row">
                        <div class="form-group col-lg-12">
                            <label for="obra">Obra</label>
                            <input type="obra" name="usuario" value=""
                                   id="obra" class="form-control"
                                   placeholder="Nome da obra"/>
                        </div>
                    </div>
                    <div id="searchfilter-panel" style="display: none;">
                        <div class="row">
                            <div class="form-group col-lg-10">
                                <label for="usuario">Usuário</label>
                                <input type="text" name="usuario" value=""
                                       id="usuario" class="form-control"
                                       placeholder="Nome do usuário"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="data-saida-inicio">Data de saída - Início</label>
                                <input type="date" name="data-saida-inicio" value=""
                                       id="data-saida-inicio" class="form-control"/>
                            </div>
                            <div class="form-group col-lg-5">
                                <label for="data-saida-fim">Data de saída - Fim</label>
                                <input type="date" name="data-saida-fim" value=""
                                       id="data-saida-fim" class="form-control"/>
                            </div>                            
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="data-devolucao-inicio">Data de devolução prevista</label>
                                <input type="date" name="data-devolucao-inicio" value=""
                                       id="data-devolucao-inicio" class="form-control"/>
                            </div>
                        </div>
                        
                        <fieldset>
                            <legend>Pesquisar por devoluções</legend>
                            
                        </fieldset>
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
