<%-- 
    Document   : usuarioList
    Created on : 08/11/2013, 10:55:41
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
                <h1>Listar usuários</h1>

                <form method="post" action="Usuario" role="form">                        
                    <div class="row">
                        <div class="form-group col-lg-8">
                            <label for="nome">Nome</label>
                            <input type="text" name="nome" value=""
                                   id="nome" class="form-control"
                                   placeholder="Seu nome completo"/>
                        </div>
                    </div>
                    <div id="searchfilter-panel" style="display: none;">
                        <div class="row"> 
                            <div class="form-group col-lg-5">
                                <label for="perfil">Perfil</label>
                                <select name="perfil"
                                        id="perfil" class="form-control">
                                    <option>Administrador</option>
                                    <option>Bibliotecário</option>
                                    <option>Aluno</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="email">E-mail</label>
                                <input type="email" name="email" value=""
                                       id="email" class="form-control"
                                       placeholder="E-mail para recuperação de senha"/>
                            </div>
                        </div>
                    </div>
                    <button type="submit" name="op" value="search" 
                            class="btn btn-lg btn-default">Pesquisar</button>
                    <button type="button" value="" 
                            data-searchfilter="toggle" 
                            data-searchfilter-target="#searchfilter-panel" 
                            class="btn btn-lg btn-default">Mais opções</button>
                </form>
                <hr/>
                <table data-rowlink class="table table-striped table-hover table-responsive">
                    <thead>
                        <tr>
                            <th class="col-">#</th>
                            <th>Nome</th>
                            <th>E-mail</th>
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
