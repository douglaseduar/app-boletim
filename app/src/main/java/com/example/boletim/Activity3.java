package com.example.boletim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class Activity3 extends AppCompatActivity {
    SQLiteDatabase db;
    Button voltar, pesq, apro, repro;
    EditText nomepesquisa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

    nomepesquisa = (EditText) findViewById(R.id.nomepesq);
    pesq = (Button) findViewById(R.id.pesq);
    pesq.setOnClickListener(pesquisando);
    apro = (Button) findViewById(R.id.aprovado);
    apro.setOnClickListener(aprovando);
    repro = (Button) findViewById(R.id.reprovado);
    repro.setOnClickListener(reprovando);
    voltar = (Button) findViewById(R.id.voltar);
    voltar.setOnClickListener(voltando);
        db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
        StringBuilder sql1 = new StringBuilder();
        sql1.append("SELECT * FROM aluno");
        Cursor dados = db.rawQuery(sql1.toString(), null);
        int[] to = {R.id.tcpf, R.id.tnome, R.id.tstatus};
        String[] from = {"_id", "nome", "status"};
        try{
            SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.dados, dados, from, to, 0);
            ListView lvDados;
            lvDados = (ListView) findViewById(R.id.lvDadinho);
            lvDados.setAdapter(ad);
            lvDados.setOnItemClickListener(itemClicado);
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    View.OnClickListener pesquisando = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nomao;
            nomao = nomepesquisa.getText().toString();
            db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
            StringBuilder sql1 = new StringBuilder();
            sql1.append("SELECT * FROM aluno WHERE nome = '" + nomao + "'");
            Cursor dados = db.rawQuery(sql1.toString(), null);
            int[] to = {R.id.tcpf, R.id.tnome, R.id.tstatus};
            String[] from = {"_id", "nome", "status"};
            try{
                SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.dados, dados, from, to, 0);
                ListView lvDados;
                lvDados = (ListView) findViewById(R.id.lvDadinho);
                lvDados.setAdapter(ad);
                lvDados.setOnItemClickListener(itemClicado);
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    };
    View.OnClickListener aprovando = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
            StringBuilder sql1 = new StringBuilder();
            sql1.append("SELECT * FROM aluno WHERE status = 'APROVADO'");
            Cursor dados = db.rawQuery(sql1.toString(), null);
            int[] to = {R.id.tcpf, R.id.tnome, R.id.tstatus};
            String[] from = {"_id", "nome", "status"};
            try{
                SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.dados, dados, from, to, 0);
                ListView lvDados;
                lvDados = (ListView) findViewById(R.id.lvDadinho);
                lvDados.setAdapter(ad);
                lvDados.setOnItemClickListener(itemClicado);
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    };
    View.OnClickListener reprovando = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
            StringBuilder sql1 = new StringBuilder();
            sql1.append("SELECT * FROM aluno WHERE status = 'REPROVADO'");
            Cursor dados = db.rawQuery(sql1.toString(), null);
            int[] to = {R.id.tcpf, R.id.tnome, R.id.tstatus};
            String[] from = {"_id", "nome", "status"};
            try{
                SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.dados, dados, from, to, 0);
                ListView lvDados;
                lvDados = (ListView) findViewById(R.id.lvDadinho);
                lvDados.setAdapter(ad);
                lvDados.setOnItemClickListener(itemClicado);
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    };
    View.OnClickListener voltando = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i = new Intent(Activity3.this, MainActivity.class);
            startActivity(i);

        }
    };
    AdapterView.OnItemClickListener itemClicado = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            String mcpf ="", mnome="", mstatus="", mma="", mfi="", min="", mpr="", mpo="";
            ListView lvDados;
            lvDados = (ListView) findViewById(R.id.lvDadinho);
            View view = lvDados.getChildAt(arg2);
            String cpfr;
            TextView tvcpf = (TextView) view.findViewById(R.id.tcpf);
            db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
            StringBuilder sql2 = new StringBuilder();
            sql2.append("SELECT a.*, n.* FROM aluno AS a LEFT JOIN notas AS n ON a._id = n.fk_cpf WHERE a._id = " + tvcpf.getText().toString());
            Cursor d = db.rawQuery(sql2.toString(), null);
            if (d.moveToFirst()) {
                mcpf = d.getString(0);
                mnome = d.getString(1);
                mstatus = d.getString(2);
                mma = d.getString(5);
                mfi = d.getString(6);
                min = d.getString(7);
                mpr = d.getString(8);
                mpo = d.getString(9);
            }try{
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity3.this);
                builder.setTitle("Boletim");
                builder.setMessage("Nome: " + mnome + "\nCPF: " + mcpf + "\nStatus: " + mstatus + "\n\nMatematica: "
                        + mma + "\nFisica: " + mfi + "\nIngles: " + min + "\nProgramacao: "+ mpr + "\nPortugues: " + mpo);
                builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
                        StringBuilder sql3 = new StringBuilder();
                       // sql3.append("DELETE FROM notas WHERE fk_cpf = '" + tvcpf.getText().toString() + "';");
                        sql3.append("DELETE FROM aluno WHERE _id = '" + tvcpf.getText().toString() + "'");
                        db.execSQL(sql3.toString());
                        db = openOrCreateDatabase("boletim4.db", Context.MODE_PRIVATE, null);
                        StringBuilder sql1 = new StringBuilder();
                        sql1.append("SELECT * FROM aluno");
                        Cursor dados = db.rawQuery(sql1.toString(), null);
                        int[] to = {R.id.tcpf, R.id.tnome, R.id.tstatus};
                        String[] from = {"_id", "nome", "status"};
                        try{
                            SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.dados, dados, from, to, 0);
                            ListView lvDados;
                            lvDados = (ListView) findViewById(R.id.lvDadinho);
                            lvDados.setAdapter(ad);
                            lvDados.setOnItemClickListener(itemClicado);
                        }catch (Exception e){
                            Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.create().show();
            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Erro = " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    };

}