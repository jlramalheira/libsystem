<%-- 
    Document   : obraUpdate
    Created on : 12/11/2013, 08:50:55
    Author     : max
    Description:
--%>

<%@page import="model.Categoria"%>
<%@page import="model.Obra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
Obra obra = (Obra) request.getAttribute("obra");
 Usuario usuario = (Usuario) session.getAttribute("usuario");
if (obra == null || usuario == null){
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
                <h1>Atualizar Obra</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Obra" role="form">
                    <input type="hidden" name="idObra" value="<%=obra.getId()%>"
                    <fieldset>
                        <legend>Informações da obra</legend>

                        <div class="row">
                            <div class="form-group col-lg-10">
                                <label for="titulo">Título</label>
                                <input type="text" name="titulo" value="<%=obra.getTitulo()%>"
                                       id="titulo" class="form-control"
                                       placeholder="Título da obra"/>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-8">
                                <label for="autor">Autor</label>
                                <input type="text" name="autor" value="<%=obra.getAutor()%>"
                                       id="autor" class="form-control"
                                       placeholder="Autor da obra"/>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="editora">Editora</label>
                                <input type="text" name="editora" value="<%=obra.getEditora()%>"
                                       id="autor" class="form-control"
                                       placeholder="Editora da obra"/>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-3">
                                <label for="ano">Ano</label>
                                <input type="number" name="ano" min="0" value="<%=obra.getAno()%>"
                                       id="ano" class="form-control"/>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-3">
                                <label for="exemplares">Exemplares</label>
                                <input type="number" name="exemplares" min="1" value="<%=obra.getNumeroExemplares()%>"
                                       id="exemplares" class="form-control"/>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="categoria">Categoria</label>
                                <select name="categoria"
                                        id="categoria" class="form-control">
                                    <%for (Categoria categoria : Categoria.values()) {%>
                                    <option value="<%=categoria.name()%>" 
                                            <%=obra.getCategoria().name().equals(categoria.name())? "selected=\"selected\"" : ""%>><%=categoria.valor()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>

                    </fieldset>
                    <hr/>
                    <button type="submit" name="op" value="update"
                            class="btn btn-lg btn-primary">Atualizar</button>
                </form>

            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
