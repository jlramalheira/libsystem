<%-- 
    Document   : emprestimoView
    Created on : 12/11/2013, 10:23:29
    Author     : max
    Description:
--%>
<%@page import="model.Devolucao"%>
<%@page import="util.FormatDate"%>
<%@page import="model.Emprestimo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Emprestimo emprestimo = (Emprestimo) request.getAttribute("emprestimo");
    if (emprestimo == null) {
        response.sendError(404);
    }
%>
<!DOCTYPE html>
<html>%
    <head>
        <%@include file="interfaceHead.jsp" %>
    </head>
    <body>
        <%@include file="interfaceHeader.jsp" %>

        <div class="container">
            <%@include file="interfaceMenuEmprestimo.jsp" %>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <h1>Visualizar empréstimo</h1>
                <%@include file="interfaceMessages.jsp" %>
                <div class="row">
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">Obra</th>
                                    <td><%=emprestimo.getExemplar().getObra().getTitulo()%></td>
                                </tr>
                                <tr>
                                    <th>Exemplar</th>
                                    <td><%=emprestimo.getExemplar().getId()%></td>
                                </tr>
                                <tr>
                                    <th>Usuário</th>
                                    <td>Nome do Usuário</td>
                                </tr>
                                <tr>
                                    <th>Data de saída</th>
                                    <td><%=FormatDate.dateToString(emprestimo.getDataEmprestimo())%></td>
                                </tr>
                                <tr>
                                    <th>Data de devolução prevista</th>
                                    <td><%=FormatDate.dateToString(emprestimo.getDataDevolucaoPrevista())%></td>
                                </tr>
                            </tbody>
                        </table>

                        <h2>Devolução</h2>
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">Situação</th>
                                    <td>
                                        <%switch (emprestimo.getDevolucao().getStatus()) {
                                                case Devolucao.NORMAL:%>

                                        <span class="label label-info">Normal</span>
                                        <% break;
                                            case Devolucao.DEVOLVIDO:%>
                                        <span class="label label-success">Devolvido</span>
                                        <% break;
                                            case Devolucao.ATRASADO:%>
                                        <span class="label label-warning">Atrasado</span>
                                        <%}%>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Data da devolução</th>
                                    <td><%=emprestimo.getDevolucao().getDataDevolucao() == null ? "Não devolvido" : FormatDate.dateToString(emprestimo.getDevolucao().getDataDevolucao()) %></td>
                                </tr>
                                <tr>
                                    <th>Débito</th>
                                    <td>R$ <%=emprestimo.getDevolucao().getDebito() != null ? emprestimo.getDevolucao().getDebito().getValor() : "0,00"%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <a href="Devolucao?op=create&idEmprestimo=#" 
                           class="btn btn-primary btn-block btn-lg">
                            Realizar devolução
                        </a>
                        <a href="Emprestimo?op=renovar&id=#" 
                           class="btn btn-default btn-block btn-lg">
                            Renovar empréstimo
                        </a>
                        <a href="Emprestimo?op=receber&id=#" 
                           class="btn btn-default btn-block btn-lg">
                            Receber débito
                        </a>
                    </div>
                </div>



            </div>
        </div>

        <%@include file="interfaceFooter.jsp" %>
    </body>
</html>
