<%-- 
    Document   : obraList
    Created on : 12/11/2013, 08:47:01
    Author     : max
    Description:
--%>

<%@page import="model.Categoria"%>
<%@page import="model.Obra"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
List<Obra> obras = (List<Obra>) request.getAttribute("obras");
 Usuario usuario = (Usuario) session.getAttribute("usuario");
if (obras == null || usuario == null){
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
            <%@include file="interfaceMenuObra.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Listar Obras</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="get" action="Obra" role="form">                        
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
                            <div class="form-group col-lg-8">
                                <label for="autor">Autor</label>
                                <input type="text" name="autor" value=""
                                       id="autor" class="form-control"
                                       placeholder="Autor da obra"/>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="editora">Editora</label>
                                <input type="text" name="editora" value=""
                                       id="autor" class="form-control"
                                       placeholder="Editora da obra"/>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-3">
                                <label for="anoInicio">Ano - Início</label>
                                <input type="number" name="anoInicio" min="0" value=""
                                       id="anoInicio" class="form-control"/>
                            </div>
                            <div class="form-group col-lg-3">
                                <label for="anoFim">Ano - Fim</label>
                                <input type="number" name="anoFim" min="0" value=""
                                       id="anoFim" class="form-control"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="categoria">Categoria</label>
                                <select name="categoria"
                                        id="categoria" class="form-control">
                                    <option value="">Selecione</option>
                                    <%for (Categoria categoria : Categoria.values()) {%>
                                    <option value="<%=categoria.name()%>"><%=categoria.valor()%></option>
                                    <%}%>
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
                <%if (!obras.isEmpty()){%>
                <table data-rowlink class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>#ID</th>
                            <th>Titulo</th>
                            <th>Autor</th>
                            <th>Categoria</th>
                            <th>Exemplares</th>
                            <th>Numero Emprestimos</th>
                    </thead>
                    <tbody>
                        <%for (Obra obra : obras){ %>
                        <tr data-rowlink-href="Obra?op=view&idObra=<%=obra.getId()%>">
                            <td><%=obra.getId()%></td>
                            <td><%=obra.getTitulo()%></td>
                            <td><%=obra.getAutor()%></td>
                            <td><%=obra.getCategoria().valor()%></td>
                            <td><%=obra.getNumeroExemplares()%></td>
                            <td><%=obra.getNumeroEmprestimos()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="6"><%=obras.size() == 1 ? "1 resultado encontrado" : obras.size() + " resultados encontrados"%></th>
                        </tr>
                    </tfoot>
                </table>
                <%} else { %>
                <h3>Nenhuma Obra encontrada!</h3>
                <%}%>
            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
