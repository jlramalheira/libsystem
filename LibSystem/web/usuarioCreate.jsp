<%-- 
    Document   : usuarioCreate
    Created on : 05/11/2013, 19:34:30
    Author     : max
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
                <h1>Cadastrar usuário</h1>

                <form method="post" action="Usuario" role="form">
                    <fieldset>
                        <legend>Informações do sistema</legend>
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
                    </fieldset>

                    <fieldset>
                        <legend>Informações pessoais</legend>
                        <div class="row">
                            <div class="form-group col-lg-8">
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" value=""
                                       id="nome" class="form-control"
                                       placeholder="Seu nome completo"/>
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
                    </fieldset>
                    <hr/>
                    <button type="submit" name="op" value="create"
                            class="btn btn-lg btn-default">Cadastrar</button>
                </form>

            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
