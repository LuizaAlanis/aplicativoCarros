package com.example.sqliteatividade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetalhesActivity extends AppCompatActivity {
    EditText editTextMarcaDet, editTextModeloDet, editTextAnoDet, editTextValorDet, editTextCorDet;
    Button buttonAlterar;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        editTextAnoDet = findViewById(R.id.editTextAnoDet);
        editTextCorDet = findViewById(R.id.editTextCorDet);
        editTextValorDet = findViewById(R.id.editTextValorDet);
        editTextModeloDet = findViewById(R.id.editTextModeloDet);
        editTextMarcaDet = findViewById(R.id.editTextMarcaDet);
        buttonAlterar = findViewById(R.id.buttonAlterar);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("Id");
        editTextMarcaDet.setText(bundle.getString("Marca"));
        editTextModeloDet.setText(bundle.getString("Modelo"));
        editTextAnoDet.setText(bundle.getInt("Ano")+"");
        editTextCorDet.setText(bundle.getString("Cor"));
        editTextValorDet.setText(bundle.getDouble("Valor")+"");

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DtoCarro dtoCarro = new DtoCarro();
                dtoCarro.setId(id);
                dtoCarro.setMarca(editTextMarcaDet.getText().toString());
                dtoCarro.setModelo(editTextModeloDet.getText().toString());
                dtoCarro.setCor(editTextCorDet.getText().toString());
                dtoCarro.setAno(Integer.parseInt(editTextAnoDet.getText().toString()));
                dtoCarro.setValor(Double.parseDouble(editTextValorDet.getText().toString()));

                DaoCarro daoCarro = new DaoCarro(DetalhesActivity.this);
                try{
                    long linhas = daoCarro.alterar(dtoCarro);
                    if(linhas > 0){
                        Toast.makeText(DetalhesActivity.this, "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(DetalhesActivity.this, MainActivity.class);
                        startActivity(main);
                    }else{
                        Toast.makeText(DetalhesActivity.this, "Não foi possivel alterar =(", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(DetalhesActivity.this, "Erro ao alterar =(" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
