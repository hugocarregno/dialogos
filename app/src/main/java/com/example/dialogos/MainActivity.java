package com.example.dialogos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private final CharSequence items[] = {"Azul", "Verde", "Rojo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mostrarDialogo();
        //mostrarDialogoLista();
        mostrarProgreso();
    }
//forma 1
    private void mostrarDialogo(){
        new AlertDialog.Builder(this)
                .setTitle("Salida")
                .setMessage("¿Desea cerrar la aplicación?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "se eligio no", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
    //fin forma 1

    //forma 2
    private void mostrarDialogoLista(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elige un color");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //fin forma 2

    //forma 3
    private void mostrarProgreso(){
        final Handler handler = new Handler();
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Espere...");
        pd.setMessage("Descargando...");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setProgress(0);
        pd.setMax(20);
        pd.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(pd.getProgress() < pd.getMax()){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pd.incrementProgressBy(2);
                        }
                    });
                }
                if(pd.getProgress() == pd.getMax()){
                    pd.dismiss();
                }
            }
        }).start();
    }
    //fin forma 3
}
