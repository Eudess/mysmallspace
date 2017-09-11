package com.projeto.jadieleudes.mysmallspace.infra;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.projeto.jadieleudes.mysmallspace.persistencia.UsuarioDAO;


public class ValidacaoService {
    private UsuarioDAO usuarioDAO;

    public ValidacaoService(Context context) {
        this.usuarioDAO = new UsuarioDAO(context);
    }

    public boolean isCampoVazio(String campo) {
        return (TextUtils.isEmpty(campo) || campo.trim().isEmpty());
    }

    public boolean isEmail(String campo) {
        return (Patterns.EMAIL_ADDRESS.matcher(campo).matches());
    }

    public boolean isEmailValido(String email) {
        return (!isCampoVazio(email) && isEmail(email) && (usuarioDAO.getUsuarioEmail(email) == null));
    }

    public boolean isSenhaValida(String senha) {
        if (isCampoVazio(senha)) {
            return false;
        } else {
            String rex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12})";
            return (senha.matches(rex));
        }
    }
}