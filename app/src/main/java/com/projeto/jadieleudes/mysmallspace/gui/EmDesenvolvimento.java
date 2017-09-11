package com.projeto.jadieleudes.mysmallspace.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.projeto.jadieleudes.mysmallspace.R;

public class EmDesenvolvimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_em_desenvolvimento);
    }

    public void voltar(View view) {
        Intent it = new Intent(EmDesenvolvimento.this, ActHome.class);
        startActivity(it);
    }
}
