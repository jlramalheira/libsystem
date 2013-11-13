<%-- 
    Document   : perfilCreate
    Created on : 08/11/2013, 10:51:53
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
                <h1>Cadastrar perfil</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Perfil" role="form">
                     <fieldset>                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" value=""
                                       id="nome" class="form-control"
                                       placeholder="Nomenclatura do perfil"/>
                            </div>
                        </div>
                        
                     </fieldset>
                     <hr/>
                     <button type="submit" value="create" name="op"
                             class="btn btn-lg btn-primary">Cadastrar</button>
                </form>
                
            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
