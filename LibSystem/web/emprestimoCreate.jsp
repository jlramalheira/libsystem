<%-- 
    Document   : emprestimoCreate
    Created on : 12/11/2013, 08:57:27
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
                <h1>Novo empréstimo</h1>
                <%@include file="interfaceMessages.jsp" %>
                <form method="post" action="Emprestimo" role="form">
                    <input type="hidden" name="idObra" value="" id="idObra"/>
                    <fieldset>
                        <legend>Informações do empréstimo</legend>
                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="exemplar">Exemplar</label>
                                <input type="number" name="exemplar" min="0"
                                       id="exemplar" class="form-control" onblur="getObra()"
                                       placeholder="Código do exemplar"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-12">
                                <label for="obra">Obra</label>
                                <p id="obra"> - </p>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-10">
                                <label for="usuario">Usuário</label>
                                <input type="text" name="usuario" value=""
                                       id="usuario" class="form-control"
                                       placeholder="Nome do usuário"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-lg-5">
                                <label for="data-saida">Data de saída</label>
                                <input type="date" name="data-saida" value=""
                                       id="data-saida" class="form-control"/>
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
                        $('#idObra').text(json.id);
                    }
                });
            }
        </script>
    </body>
</html>
