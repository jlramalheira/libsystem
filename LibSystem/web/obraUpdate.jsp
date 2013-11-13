<%-- 
    Document   : obraUpdate
    Created on : 12/11/2013, 08:50:55
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
                <h1>Atualizar Obra</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Obra" role="form">
                    <fieldset>
                        <legend>Informações da obra</legend>

                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="titulo">Título</label>
                                <input type="text" name="titulo" value=""
                                       id="titulo" class="form-control"
                                       placeholder="Título da obra"/>
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
                                    <option value="midia">Mídia</option>
                                    <option value="livro">Livro</option>
                                    <option value="periodico">Periódico</option>
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