package com.projeto.jadieleudes.mysmallspace.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto.jadieleudes.mysmallspace.R;
import com.projeto.jadieleudes.mysmallspace.infra.MinhaTask;

public class ObtendoDados extends AppCompatActivity {
    private static final int DOIS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_obtendo_dados);

        ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        TextView texto = (TextView) findViewById(R.id.texto);

        new MinhaTask(this, progress, texto, DOIS).execute();
    }

    public void acessarDados (View view) throws Exception{
        Intent it = new Intent(ObtendoDados.this, ActHome.class);
        startActivity(it);
    }
}