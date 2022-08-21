package com.example.boletim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    SQLiteDatabase db;
    Button voltar, gravar;
    EditText nome, cpf, matematica, portugues, fisica, programacao, ingles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
        StringBuilder sql1 = new StringBuilder();
        sql1.append("CREATE TABLE IF NOT EXISTS aluno(");
        sql1.append("_id varchar(100) PRIMARY KEY,");
        sql1.append("nome varchar(100), status varchar(100))");
        try{
            db.execSQL(sql1.toString());
            Toast.makeText(getBaseContext(), "Deu boa", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(getBaseContext(), "Erro = " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS notas(");
        sql.append("idn Integer PRIMARY KEY,");
        sql.append("fk_cpf varchar(100),");
        sql.append("matematica varchar(100),");
        sql.append("portugues varchar(100),");
        sql.append("fisica varchar(100),");
        sql.append("programacao varchar(100),");
        sql.append("ingles varchar(100),");
        sql.append("FOREIGN KEY(fk_cpf) REFERENCES aluno(_id))");
        try{
            db.execSQL(sql.toString());
        }catch (Exception ex){
            Toast.makeText(getBaseContext(), "Deu boa2", Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(), "Erro = " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        voltar = (Button) findViewById(R.id.voltar1);
        voltar.setOnClickListener(voltando1);
        gravar = (Button) findViewById(R.id.gravar);
        gravar.setOnClickListener(gravando);
        nome = (EditText) findViewById(R.id.nome);
        cpf = (EditText) findViewById(R.id.cpf);
        matematica = (EditText) findViewById(R.id.matematica);
        portugues = (EditText) findViewById(R.id.portugues);
        fisica = (EditText) findViewById(R.id.fisica);
        programacao = (EditText) findViewById(R.id.programacao);
        ingles = (EditText) findViewById(R.id.ingles);

    }
    View.OnClickListener gravando = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String nomes, cpfs, aux = "";
            Float mat, port, fis, prog, ing;

            nomes = nome.getText().toString();
            cpfs = cpf.getText().toString();
            mat = Float.parseFloat(matematica.getText().toString());
            port = Float.parseFloat(portugues.getText().toString());
            fis = Float.parseFloat(fisica.getText().toString());
            prog = Float.parseFloat(programacao.getText().toString());
            ing = Float.parseFloat(ingles.getText().toString());

            if(mat < 6 || port < 6 || fis < 6 || prog < 6 || ing < 6){
                aux = "REPROVADO" ; }else{aux = "APROVADO";}

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO aluno (_id, nome, status) VALUES ('" + cpfs + "', '" + nomes + "', '" + aux + "')");
            try{
                db.execSQL(sql.toString());
                Toast.makeText(getBaseContext(), "Ok - ALUNO INSERIDO COM SUCESSO!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            StringBuilder sql1 = new StringBuilder();
            sql1.append("INSERT INTO notas (fk_cpf, matematica, portugues, fisica, programacao, ingles)");
            sql1.append("VALUES ('" + cpfs + "', '" + mat + "', '" + port + "', '" + fis + "', '" + prog + "', '" + ing + "')");
            try{
                db.execSQL(sql1.toString());
                Toast.makeText(getBaseContext(), "Ok - NOTAS INSERIDAS COM SUCESSO!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    };



    View.OnClickListener voltando1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i = new Intent(Activity2.this, MainActivity.class);
            startActivity(i);

        }
    };
}