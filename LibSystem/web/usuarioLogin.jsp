<%-- 
    Document   : usuarioLogin
    Created on : 24/11/2013, 10:01:50
    Author     : joao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
        response.sendRedirect("Usuario?op=view&idUsuario=" + usuario.getId());
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
                    <h1>
                        Faça seu login
                    </h1>
                    <%@include file="interfaceMessages.jsp" %>
                    <div class="row">
                        <form method="post" action="Usuario" role="form"
                              class="col-lg-4 col-lg-offset-4" parsley-validate>
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <fieldset>

                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="usuario">Usuário</label>
                                                <input type="text" name="usuario" value=""
                                                       id="usuario" class="form-control"
                                                       placeholder="Nome utilizado para acessar o sistema"
                                                       parsley-required="true"
                                                       parsley-trigger="change"
                                                       parsley-required-message="Campo obrigatório"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="senha">Senha</label>
                                                <input type="password" name="senha" value=""
                                                       id="senha" class="form-control"
                                                       placeholder="Senha utilizada para acessar o sistema"
                                                       parsley-required="true"
                                                       parsley-trigger="change"
                                                       parsley-required-message="Campo obrigatório"/>
                                            </div>
                                        </div>
                                        <a href="Usuario?op=recuperarSenha">Esqueceu sua senha?</a>
                                    </fieldset>
                                    <hr/>
                                    <button type="submit" name="op" value="login"
                                            class="btn btn-lg btn-primary">Entrar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>            
    </div>

    <%@include file="interfaceFooter.jsp" %>
</body>
</html>