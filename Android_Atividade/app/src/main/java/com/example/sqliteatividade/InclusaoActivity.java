package com.example.sqliteatividade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InclusaoActivity extends AppCompatActivity {
    EditText editTextMarca, editTextModelo, editTextAno, editTextCor, editTextValor;
    Button buttonIncluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusao);

        editTextMarca = findViewById(R.id.editTextMarca);
        editTextModelo = findViewById(R.id.editTextModelo);
        editTextAno = findViewById(R.id.editTextAno);
        editTextCor = findViewById(R.id.editTextCor);
        editTextValor = findViewById(R.id.editTextValor);
        buttonIncluir = findViewById(R.id.buttonIncluir);

        buttonIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DtoCarro dtoCarro = new DtoCarro();
                dtoCarro.setMarca(editTextMarca.getText().toString());
                dtoCarro.setModelo(editTextModelo.getText().toString());
                dtoCarro.setCor(editTextCor.getText().toString());
                dtoCarro.setAno(Integer.parseInt(editTextAno.getText().toString()));
                dtoCarro.setValor(Double.parseDouble(editTextValor.getText().toString()));

                DaoCarro daoCarro = new DaoCarro(InclusaoActivity.this);
                try{
                    long linhas = daoCarro.inserir(dtoCarro);
                    if(linhas > 0){
                        Toast.makeText(InclusaoActivity.this, "Carro registrado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(InclusaoActivity.this, MainActivity.class);
                        startActivity(main);
                    }else{
                        Toast.makeText(InclusaoActivity.this, "Não foi possivel inserir", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(InclusaoActivity.this, "erro ao inserir"+ ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
