<%-- 
    Document   : perfilUpdate
    Created on : 08/11/2013, 18:45:47
    Author     : max
    Description:
--%>

<%@page import="model.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Perfil perfil = (Perfil) request.getAttribute("perfil");
    if (perfil == null) {
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
            <%@include file="interfaceMenuUsuario.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Atualizar perfil</h1>
                
                <form method="post" action="Perfil" role="form">
                    <input type="hidden" name="idPerfil" value="<%=perfil.getId()%>" />
                     <fieldset>                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" value="<%=perfil.getNome()%>"
                                       id="nome" class="form-control"
                                       placeholder="Nomenclatura do perfil"/>
                            </div>
                        </div>
                        
                     </fieldset>
                     <hr/>
                     <button type="submit" value="update" name="op"
                             class="btn btn-lg btn-default">Atualizar</button>
                </form>
                
            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
