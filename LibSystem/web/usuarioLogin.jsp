<%-- 
    Document   : usuarioLogin
    Created on : 24/11/2013, 10:01:50
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
Usuario usuario = (Usuario) session.getAttribute("usuario");
if (usuario != null){
    response.sendRedirect("Usuario?op=view&idUsuario="+usuario.getId());
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
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <%@include file="interfaceMessages.jsp" %>
                    <form method="post" action="Usuario" role="form">
                        <fieldset>
                            <div class="row">
                                <div class="form-group col-lg-6">
                                    <label for="usuario">Usuário</label>
                                    <input type="text" name="usuario" value=""
                                           id="usuario" class="form-control"
                                           placeholder="Nome utilizado para acessar o sistema"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-lg-5">
                                    <label for="senha">Senha</label>
                                    <input type="password" name="senha" value=""
                                           id="senha" class="form-control"
                                           placeholder="Senha utilizada para acessar o sistema"/>
                                </div>
                            </div>
                            <a href="Usuario?op=recuperarSenha">Esqueceu sua senha?</a>
                        </fieldset>
                        <hr/>
                        <button type="submit" name="op" value="login"
                                class="btn btn-lg btn-primary">Entrar</button>
                    </form>

                </div>
            </div>            
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>