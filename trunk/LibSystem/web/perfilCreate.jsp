<%-- 
    Document   : perfilCreate
    Created on : 08/11/2013, 10:51:53
    Author     : max
    Description:
--%>

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
                    <fieldset>
                        <legend>Atividades gerais</legend>
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label for="tempo-emprestimo">Tempo de empréstimo</label>
                                <div class="input-group">                                    
                                    <input type="number" name="tempo-emprestimo" value="1"
                                           id="tempo-emprestimo" class="form-control"
                                           min="1"/>
                                    <span class="input-group-addon">dias</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label for="quantidade-emprestimo">Quantidade de empréstimo disponível</label>
                                <div class="input-group">                                    
                                    <input type="number" name="quantidade-emprestimo" value="1"
                                           id="quantidade-reserva" class="form-control"
                                           min="1"/>
                                    <span class="input-group-addon">dias</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label for="tempo-reserva">Tempo de reserva disponível</label>
                                <div class="input-group">                                    
                                    <input type="number" name="tempo-reserva" value="1"
                                           id="tempo-reserva" class="form-control"
                                           min="1"/>
                                    <span class="input-group-addon">dias</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label for="quantidade-reserva">Quantidade de reserva disponível</label>
                                <div class="input-group">                                    
                                    <input type="number" name="quantidade-reserva" value="1"
                                           id="quantidade-reserva" class="form-control"
                                           min="1"/>
                                    <span class="input-group-addon">dias</span>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>Atividades administrativas</legend>
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <div class="checkbox">
                                    <label for="gerenciar-perfil">
                                        <input type="checkbox" name="gerenciar-perfil" value="true"
                                               id="gerenciar-perfil" />
                                        Gerenciar perfis
                                    </label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <div class="checkbox">
                                    <label for="gerenciar-usuario">
                                        <input type="checkbox" name="gerenciar-usuario" value="true"
                                               id="gerenciar-usuario" />
                                        Gerenciar usuários
                                    </label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <div class="checkbox">
                                    <label for="gerenciar-obras">
                                        <input type="checkbox" name="gerenciar-obras" value="true"
                                               id="gerenciar-obras" />
                                        Gerenciar obras
                                    </label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <div class="checkbox">
                                    <label for="gerenciar-emprestimos">
                                        <input type="checkbox" name="gerenciar-emprestimos" value="true"
                                               id="gerenciar-emprestimos" />
                                        Gerenciar empréstimos
                                    </label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <div class="checkbox">
                                    <label for="gerenciar-reservas">
                                        <input type="checkbox" name="gerenciar-reservas" value="true"
                                               id="gerenciar-reservas" />
                                        Gerenciar reservas
                                    </label>
                                </div>
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
