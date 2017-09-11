package com.projeto.jadieleudes.mysmallspace.gui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.projeto.jadieleudes.mysmallspace.R;
import com.projeto.jadieleudes.mysmallspace.infra.GuiUtil;
import com.projeto.jadieleudes.mysmallspace.infra.ValidacaoService;
import com.projeto.jadieleudes.mysmallspace.services.UsuarioService;

/**
 * Classe responsável pela Tela de Cadastro.
 */

public class ActSignUp extends AppCompatActivity {
    private EditText edtNome, edtNick, edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign_up);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtNick = (EditText) findViewById(R.id.edtNick);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
    }

    /**
     * Método passará os dados digitados pelo usuário para serem validados pela Classe @see {@link ValidacaoService},
     * após uma válidação positiva, os dados serão enviados para a Classe @see {@link UsuarioService}
     * para serem gravadas no banco de dados. Mensagens de erro serão mostradas caso a validação ou o registro
     * no banco de dados falhem.
     * @param view - Referência ao Botão Cadastrar @see {@link View} e {@link com.projeto.jadieleudes.mysmallspace.R.layout}.
     */

    public void validarCadastrar(View view){
        String nome     = edtNome.getText().toString();
        String nick     = edtNick.getText().toString();
        String email    = edtEmail.getText().toString();
        String senha    = edtSenha.getText().toString();

        ValidacaoService validacaoCadastro = new ValidacaoService(getApplicationContext());
        boolean valid = true;
        if (!validacaoCadastro.isSenhaValida(senha)){
            edtSenha.requestFocus();
            edtSenha.setError(getString(R.string.msg_senha_fora_padrão));
            valid = false;
        }
        if (!validacaoCadastro.isEmailValido(email)){
            edtEmail.requestFocus();
            edtEmail.setError(getString(R.string.msg_email_invalido));
            valid = false;
        }
        if (validacaoCadastro.isCampoVazio(nick)){
            edtNick.requestFocus();
            edtNick.setError(getString(R.string.msg_nick_invalido));
            valid = false;
        }

        if (validacaoCadastro.isCampoVazio(nome)){
            edtNome.requestFocus();
            edtNome.setError(getString(R.string.msg_nome_invalido));
            valid = false;
        }

        if (valid) {
            UsuarioService service = new UsuarioService(getApplicationContext());
            try {
                service.cadastrar(nome, nick, email, senha);
                GuiUtil.myToast(this, getString(R.string.msg_cadastro_sucesso));
                finish();
            } catch (Exception e) {
                GuiUtil.myToast(this, e);
            }
        }
    }

    /**
     * Retorna para a @see {@link ActLogin}.
     * @param view - Referência ao Botão Cancelar @see {@link View} e {@link com.projeto.jadieleudes.mysmallspace.R.layout}.
     */

    public void cancelarCadastro(View view){
        finish();
    }
}