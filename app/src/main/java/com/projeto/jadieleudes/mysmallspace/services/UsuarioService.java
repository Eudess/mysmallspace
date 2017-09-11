package com.projeto.jadieleudes.mysmallspace.services;

import android.content.Context;

import com.projeto.jadieleudes.mysmallspace.infra.Criptografia;
import com.projeto.jadieleudes.mysmallspace.dominio.*;
import com.projeto.jadieleudes.mysmallspace.persistencia.*;

/**
 * Classe faz comunicação com a classe UsuarioDAO e validações, faz pesquisas no banco
 */

public class UsuarioService {
    private Sessao sessao = Sessao.getInstancia();
    private UsuarioDAO usuarioDAO;
    private Criptografia criptografia = new Criptografia();
    private Usuario usuario;
    private SessaoDAO sessaoDAO;

    /**
     * Constructor
     * @param context
     */

    public UsuarioService(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        sessaoDAO = new SessaoDAO(context);
    }

    /**
     * Método que recebe dados de um usuário a ser cadastrado, valida os dados e solicita seu cadastro
     * @param nome Nome da Pessoa
     * @param nick Nick do Usuario
     * @param email Email do Usuario
     * @param senha Senha do Usuario
     * @see UsuarioDAO
     * @see Usuario
     * @see Criptografia
     */

    public void cadastrar(String nome, String nick, String email, String senha) throws Exception {
        Usuario verificarEmail = usuarioDAO.getUsuarioEmail(email);
        if (verificarEmail != null) {
            throw new Exception("Email já cadastrado");
        } else {
            String senhaCriptografada = criptografia.criptografarSenha(senha);
            usuario = new Usuario();
            usuario.setSenha(senhaCriptografada);
            usuario.setEmail(email);
            usuario.setNick(nick);
            usuario.setNome(nome);
            long idUsuario = usuarioDAO.inserirUsuario(usuario);
            usuario.setId(idUsuario);
        }
    }

    /**
     * Método utilizado para logar automaticamente o usuario no sistema
     * @param email Email do usuario a ser logado
     * @param senha senha do usuari a ser logado
     */

    public void autoLogarEmail(String email, String senha) throws Exception {
        Usuario usuario = usuarioDAO.getUsuarioEmail(email);
        sessao.setUsuarioLogado(usuario);
        sessaoDAO.inserirIdUsuario(sessao);
        }

    /**
     * Método utilizado para logar usuario no sistema
     * @param email Email do Usuario a ser logado
     * @param senha Senha do Usuario logado
     * @see Criptografia
     * @see UsuarioDAO
     * @see SessaoDAO
     * @throws Exception Caso sejam fornecidos dados incorretos
     */

    public void logarEmail(String email, String senha) throws Exception {
        String senhaCriptografada = criptografia.criptografarSenha(senha);
        Usuario emailValido = usuarioDAO.getUsuarioEmailSenha(email, senhaCriptografada);

        if (emailValido == null) {
            throw new Exception("Usuário ou senha inválidos");
        }
        else {
            Usuario usuario = usuarioDAO.getUsuarioEmail(email);
            sessao.setUsuarioLogado(usuario);
            sessaoDAO.inserirIdUsuario(sessao);
        }
    }

    /**
     * Busca um Usuario no banco de dados
     * @param id Recebe id do usuario a ser buscado
     * @return Rertorna Usuario pesquisado
     * @see UsuarioDAO
     */

    public Usuario buscarUsuario(long id){
        return usuarioDAO.getUsuarioId(id); }

    /**
     * Método para verificar se há uma seção aberta para poder logar automaticamente se
     * for o caso
     * @return Retorna pessoa logada se houver uma, senão retorna null
     */

    public Usuario verificarSessao() {
        return sessaoDAO.getUsuarioLogado();
    }
}