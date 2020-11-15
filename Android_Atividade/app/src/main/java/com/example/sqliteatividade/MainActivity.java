package com.example.sqliteatividade;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewCarro;
    Button buttonAdicionarNovo;
    ArrayList<DtoCarro> arrayListCarro;
    RadioButton radioButtonMaiorMenor;
    RadioGroup radioGroup;
    final DaoCarro daoCarro = new DaoCarro(MainActivity.this);
    DtoCarro carro;

    public void atualizarListView() {
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayListCarro);
        listViewCarro.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewCarro = findViewById(R.id.listViewCarro);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonMaiorMenor = findViewById(R.id.radioButtonMaiorMenor);
        buttonAdicionarNovo = findViewById(R.id.buttonAdicionarNovo);

        listViewCarro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                carro = arrayListCarro.get(position);
                registerForContextMenu(listViewCarro);
                return false;
            }
        });

        arrayListCarro = daoCarro.consultar("");
        atualizarListView();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == radioButtonMaiorMenor.getId())
                    arrayListCarro = daoCarro.consultar(" ORDER BY VALOR DESC");
                else
                    arrayListCarro = daoCarro.consultar(" ORDER BY VALOR ASC");
                atualizarListView();
            }
        });

        buttonAdicionarNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inclusao = new Intent(MainActivity.this, InclusaoActivity.class);
                startActivity(inclusao);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0, "Atualizar");
        menu.add(0,1,1, "Excluir");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == 0){
            Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
            intent.putExtra("Id", carro.getId());
            intent.putExtra("Marca", carro.getMarca());
            intent.putExtra("Modelo", carro.getModelo());
            intent.putExtra("Ano", carro.getAno());
            intent.putExtra("Cor", carro.getCor());
            intent.putExtra("Valor", carro.getValor());
            startActivity(intent);
        }
        else if(item.getItemId() == 1){
            excluir();
        }
        return super.onContextItemSelected(item);
    }

    private void excluir() {
        AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
        msg.setMessage("Deseja mesmo excluir?");
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int deletados = daoCarro.excluir(carro);
                if(deletados > 0) {
                    Toast.makeText(MainActivity.this, "Registro excluído com sucesso", Toast.LENGTH_SHORT).show();
                    arrayListCarro = daoCarro.consultar("");
                    atualizarListView();
                }else
                    Toast.makeText(MainActivity.this, "Erro ao excluir", Toast.LENGTH_SHORT).show();
            }
        });
        msg.setNegativeButton("Não", null);
        msg.show();
    }
}
