<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Mostrar de navegação</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/LibSystem">Columbus&TRADE;</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <%if (usuario != null) {%>
            <ul class="nav navbar-nav">
                <%if (usuario.getPerfil().hasAcessoUsuario()) {%>
                    <li><a href="Usuario?op=list">Usuários</a></li>
                <%}%>
                <li><a href="Obra?op=list">Obras</a></li>
                <li><a href="Emprestimo?op=list">Empréstimos</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Usuario?op=view&idUsuario=<%=usuario.getId()%>"><span class="glyphicon glyphicon-user"></span> <%=usuario.getNome()%></a></li>
                <li><a href="Usuario?op=loggout">Sair</a></li>
            </ul>
            <%} else {%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Usuario?op=login">Entrar</a></li>
            </ul>
            <%}%>
        </div><!-- /.navbar-collapse -->
    </nav>
</header>
