<%-- 
    Document   : reservaCreate
    Created on : 12/11/2013, 16:24:29
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
                <h1>Cadastrar Reserva</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Reserva" role="form">
                     <fieldset>
                        <legend>Informações da Obra</legend>
                        <div class="row">
                            <div class="form-group col-lg-10">
                                <label for="obra">Obra</label>
                                <input type="text" name="obra" value=""
                                       id="obra" class="form-control"
                                       placeholder="Nome da obra"/>
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
