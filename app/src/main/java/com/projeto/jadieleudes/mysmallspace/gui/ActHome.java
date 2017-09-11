package com.projeto.jadieleudes.mysmallspace.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.projeto.jadieleudes.mysmallspace.R;

public class ActHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
    }

    public void baixarPlanta(View view){
        Intent it = new Intent(ActHome.this, EmDesenvolvimento.class);
        startActivity(it);
    }

    public void listarMateriais(View view){
        Intent it = new Intent(ActHome.this, EmDesenvolvimento.class);
        startActivity(it);
    }

    public void pinturaComodos(View view){
        Intent it = new Intent(ActHome.this, EmDesenvolvimento.class);
        startActivity(it);
    }

    public void dimensInterno(View view){
        Intent it = new Intent(ActHome.this, EmDesenvolvimento.class);
        startActivity(it);
    }
}
