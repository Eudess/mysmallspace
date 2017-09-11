package com.projeto.jadieleudes.mysmallspace.dominio;

/**
 * Classe responsável por criar os objetos Usuario
 */

public class Usuario {
    private String email;
    private String senha;
    private String nick;
    private String nome;
    private long id;

    public String getEmail(){

        return this.email;
    }

    public void setEmail(String novoEmail){

        this.email = novoEmail;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String novaSenha){

        this.senha = novaSenha;
    }

    public String getNick(){

        return this.nick;
    }

    public void setNick(String novoNick){
        this.nick = novoNick;
    }

    public long getId(){

        return this.id;
    }

    public void setId(long novoId){

        this.id = novoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}