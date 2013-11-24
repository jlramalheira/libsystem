<%-- 
    Document   : obraCreate
    Created on : 08/11/2013, 19:06:32
    Author     : max
    Description:
--%>

<%@page import="model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
 Usuario usuario = (Usuario) session.getAttribute("usuario");
 if (usuario == null){
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
                <h1>Cadastrar Obra</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Obra" role="form">
                    <fieldset>
                        <legend>Informações da obra</legend>

                        <div class="row">
                            <div class="form-group col-lg-10">
                                <label for="titulo">Título</label>
                                <input type="text" name="titulo" value=""
                                       id="titulo" class="form-control"
                                       placeholder="Título da obra"/>
                            </div>
                        </div>

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
                                <label for="ano">Ano</label>
                                <input type="number" name="ano" min="0" value="2013"
                                       id="ano" class="form-control"/>
                            </div>
                        </div>

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
                                    <%for (Categoria categoria : Categoria.values()) {%>
                                    <option value="<%=categoria.name()%>"><%=categoria.valor()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>

                    </fieldset>
                    <hr/>
                    <button type="submit" name="op" value="create"
                            class="btn btn-lg btn-primary">Cadastrar</button>
                </form>

            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
