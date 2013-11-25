/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.DaoPerfil;
import dao.DaoUsuario;
import model.Perfil;
import model.Usuario;

/**
 *
 * @author joao
 */
public class InitDB {
    public static void main(String[] args) {
        Perfil perfil = new Perfil();
        perfil.setNome("Administrador");
        perfil.setDiasReserva(10);
        perfil.setQuantidadeReservas(5);
        perfil.setDiasEmprestimo(10);
        perfil.setQuantidadeEmprestimos(5);
        perfil.setAcessoEmprestimo(true);
        perfil.setAcessoObra(true);
        perfil.setAcessoPerfil(true);
        perfil.setAcessoReserva(true);
        perfil.setAcessoUsuario(true);
        
        new DaoPerfil().insert(perfil);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Administrador");
        usuario.setLogin("admin");
        usuario.setSenha(Util.criptografarSenha("admin"));
        usuario.setEmail("columbussgb@gmail.com");
        usuario.setPerfil(perfil);
        
        new DaoUsuario().insert(usuario);
        
    }
}
