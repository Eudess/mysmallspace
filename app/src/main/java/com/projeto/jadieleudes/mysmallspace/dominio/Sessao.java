package com.projeto.jadieleudes.mysmallspace.dominio;


/**
 * Classe utilizada para manter o status do usuario
 */

public class Sessao {
    private static Sessao instancia = new Sessao();
    private Usuario usuarioLogado = null;

    public static Sessao getInstancia(){

        return instancia;
    }

    public Usuario getUsuarioLogado() { return usuarioLogado; }

    public void setUsuarioLogado(Usuario novoUsuario) {
        this.usuarioLogado = novoUsuario;
    }

    public void invalidarSessao() {
        usuarioLogado = null;
    }
}