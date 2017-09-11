package com.projeto.jadieleudes.mysmallspace.infra;


import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MinhaTask extends AsyncTask<Object, Object, String> {

    private ProgressBar progressBar;
    private TextView texto;
    private  int num = 0;
    private int total = 0;
    private static int PROGRESSO = 25;

    private static final int UM = 1;
    private static final int DOIS = 2;

    public MinhaTask(Context context, ProgressBar progressBar, TextView texto, int numero) {
        this.progressBar = progressBar;
        this.texto = texto;
        this.num = numero;
    }

    @Override
    protected void onPreExecute() {
        texto.setTextSize(22);
        if (this.num == UM){
            texto.setText("0%");
        }
        else if (this.num == DOIS){
            texto.setText("0% Obtendo Planta Baixa");
        }
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        try {

            Thread.sleep(1000);

            for (int i=0; i<4; i++) {
                publishProgress();
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        total += PROGRESSO;
        progressBar.incrementProgressBy(PROGRESSO);
        texto.setTextSize(22);
        if (this.num == UM){
            texto.setText(total + "%");
        }
        else if (this.num == DOIS){
            texto.setText(total + "% Obtendo Planta Baixa");
        }
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        texto.setTextSize(22);
        if (this.num == UM){
            texto.setText("Localização concluída");

        }
        else if (this.num == DOIS){
            texto.setText("Planta Baixa concluída");
        }
        texto.setGravity(Gravity.CENTER_HORIZONTAL);
        super.onPostExecute(result);
    }
}