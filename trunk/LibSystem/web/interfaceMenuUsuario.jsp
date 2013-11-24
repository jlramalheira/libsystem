<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
    <div class="list-group">
        <%if(usuario.getPerfil().hasAcessoUsuario()){ %>
        <div class="list-group-item">
            <h4>Usuário</h4>
        </div>
        <a href="Usuario?op=create" class="list-group-item">Cadastrar usuário</a>
        <a href="Usuario?op=list" class="list-group-item">Listar usuários</a>
        <%}
        if(usuario.getPerfil().hasAcessoPerfil()) {%>
        <div class="list-group-item">
            <h4>Perfil</h4>
        </div>
        <a href="Perfil?op=create" class="list-group-item">Cadastrar perfis</a>
        <a href="Perfil?op=list" class="list-group-item">Listar perfis</a>
        <%}%>
    </div>
</aside>
