<%-- 
    Document   : emprestimoCreate
    Created on : 12/11/2013, 08:57:27
    Author     : max
    Description:
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
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
            <%@include file="interfaceMenuEmprestimo.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Novo empréstimo</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Emprestimo" role="form" parsley-validate novalidate>
                    <fieldset>
                        <legend>Informações do empréstimo</legend>
                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="exemplar">Exemplar</label>
                                <input type="text" name="exemplar" min="0"
                                       id="exemplar" class="form-control" onblur="getObra()"
                                       placeholder="Código do exemplar"
                                       parsley-type="number"
                                       parsley-required="true"
                                       parsley-trigger="change"
                                       parsley-type-number-message="Informe um número"
                                       parsley-required-message="Campo obrigatório"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-12">
                                <label for="obra">Obra</label>
                                <p id="obra"> - </p>
                                <input type="number" name="idObra" value="" id="idObra"
                                       class="hide"
                                       parsley-trigger="change"
                                       parsley-type="number"
                                       parsley-required="true" 
                                       parsley-min="0"
                                       parsley-error-message="Selecione uma obra válida"
                                       />
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-10">
                                <label for="usuario">Usuário</label>
                                <select data-type="selectsearch" class="form-control" name="usuario">
                                    <option value=""></option>
                                    <% for (Usuario u : usuarios){%>
                                        <option value="<%=u.getId()%>"><%=u.getNome()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="data-saida">Data de saída</label>
                                <input type="date" name="data-saida" value=""
                                       id="data-saida" class="form-control"
                                       parsley-required="true"
                                       parsley-required-message="Selecione uma data válida"/>
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
        <script type="text/javascript">
            function getObra() {
                idExemplar = $('#exemplar').val();
                $.ajax({
                    url: 'Obra',
                    type: 'GET',
                    data: 'op=nameObra&idExemplar=' + idExemplar,
                    dataType: 'json',
                    success: function(json) {
                        $('#obra').text(json.titulo);
                        $('#idObra').val(json.id);
                    }
                });
            }
        </script>
    </body>
</html>
