<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
    <div class="list-group">
        <div class="list-group-item">
            <h4>Empréstimo</h4>
        </div>
        <%if (usuario.getPerfil().hasAcessoEmprestimo()) {%>
        <a href="Emprestimo?op=create" class="list-group-item">Novo empréstimo</a>
        <%}%>
        <a href="Emprestimo?op=list" class="list-group-item">Listar empréstimos</a>

        <div class="list-group-item">
            <h4>Reserva</h4>
        </div>
        <%if (usuario.getPerfil().hasAcessoReserva()) {%>
        <a href="Reserva?op=create" class="list-group-item">Nova reserva</a>
        <%}%>
        <a href="Reserva?op=list" class="list-group-item">Listar reservas</a>
    </div>
</aside>
