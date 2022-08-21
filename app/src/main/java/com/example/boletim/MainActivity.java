package com.example.boletim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button cadastro, relatorio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    cadastro = (Button) findViewById(R.id.cadastrar);
    relatorio = (Button) findViewById(R.id.relatorio);
    relatorio.setOnClickListener(cadastrando);
    cadastro.setOnClickListener(relatando);


    }
    View.OnClickListener cadastrando = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i = new Intent(MainActivity.this, Activity3.class);
            startActivity(i);

        }
    };
    View.OnClickListener relatando = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i = new Intent(MainActivity.this, Activity2.class);
            startActivity(i);

        }
    };
}