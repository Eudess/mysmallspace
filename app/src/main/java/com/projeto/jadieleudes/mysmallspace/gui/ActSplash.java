package com.projeto.jadieleudes.mysmallspace.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.projeto.jadieleudes.mysmallspace.R;
import com.projeto.jadieleudes.mysmallspace.dominio.Usuario;
import com.projeto.jadieleudes.mysmallspace.services.UsuarioService;

/**
 * Classe responsável pela Tela de Carregamento Inicial.
 */

public class ActSplash extends AppCompatActivity {
    private UsuarioService usuarioService;
    private static final int SLEEP = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        /**
         * @see Thread que parará a aplicação um curto periódo para exibição da Tela de Boas-Vindas
         * e logo após chamará método que analisará se há uma sessão no banco de dados para logar
         * automaticamente.
         */

        Thread timeThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(SLEEP);
                } catch (InterruptedException e){
                    Log.d("Thread Tela Splash", e.getMessage());
                } finally {
                    usuarioService = new UsuarioService(getBaseContext());
                    Usuario usuario = usuarioService.verificarSessao();
                    if (usuario != null){
                        usuario = usuarioService.buscarUsuario(usuario.getId());
                        chamarValidacao(usuario);
                    } else {
                        mudarTelaLogin();
                    }
                }
            }
        };
        timeThread.start();
    }

    /**
     * Método que muda da tela atual para a Tela de Login @see {@link ActLogin}.
     */

    private void mudarTelaLogin(){
        Intent it = new Intent(getBaseContext(), ActLogin.class);
        startActivity(it);
        finish();
    }

    /**
     * Método que tentará logar automáticamente caso haja uma sessão no banco de dados, caso contrário,
     * chamará o método @see mudarTelaLogin.
     * @param usuario - Objeto do tipo usuário @see {@link Usuario}.
     */

    private void chamarValidacao(Usuario usuario){
        String login = usuario.getNick();
        String senha = usuario.getSenha();

        try {
            usuarioService.autoLogarEmail(login, senha);
            Intent it = new Intent(this, ActHome.class);
            startActivity(it);
            finish();
        }
        catch (Exception e) {
            mudarTelaLogin();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}
