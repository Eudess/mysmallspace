package com.projeto.jadieleudes.mysmallspace.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto.jadieleudes.mysmallspace.R;
import com.projeto.jadieleudes.mysmallspace.infra.MinhaTask;

public class ObtendoLocalizacao extends AppCompatActivity {
    private static final int UM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_obtendo_localizacao);
        ProgressBar progress = (ProgressBar) findViewById(R.id.progress2);
        TextView texto = (TextView) findViewById(R.id.texto2);
        ImageView imagem = (ImageView) findViewById(R.id.localizacao);

        new MinhaTask(this, progress, texto, UM).execute();
        imagem.setVisibility(View.VISIBLE);

    }

    public void obterPlanta (View view) throws Exception{
        Intent it = new Intent(ObtendoLocalizacao.this, ObtendoDados.class);
        startActivity(it);
    }
}