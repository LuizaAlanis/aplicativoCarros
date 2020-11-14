package com.example.sqliteatividade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DaoCarro extends SQLiteOpenHelper {
    private final String TABELA = "TB_CARRO";

    public DaoCarro(@Nullable Context context) {
        super(context, "DB_CARRO", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String comando = "CREATE TABLE "+ TABELA +"(" +
                "ID INTEGER PRIMARY KEY, " +
                "MARCA VARCHAR(20) NOT NULL, " +
                "MODELO VARCHAR(20) NOT NULL, " +
                "COR VARCHAR(20) NOT NULL, " +
                "ANO INT(4) NOT NULL, " +
                "VALOR DECIMAL(6,2) NOT NULL)";

        db.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long inserir(DtoCarro carro){
        ContentValues values = new ContentValues();
        values.put("MARCA", carro.getMarca());
        values.put("MODELO", carro.getModelo());
        values.put("COR", carro.getCor());
        values.put("ANO", carro.getAno());
        values.put("VALOR", carro.getValor());

        return getWritableDatabase().insert(TABELA, null, values); //n° de linhas
    }

    public ArrayList<DtoCarro> consultar(String complemento){
        String comando = "SELECT * FROM "+ TABELA + complemento;
        Cursor cursor = getReadableDatabase().rawQuery(comando, null);
        ArrayList<DtoCarro> arrayListCarro = new ArrayList<>();

        while(cursor.moveToNext()){
            DtoCarro dtoCarro = new DtoCarro();
            dtoCarro.setId(cursor.getInt(0));
            dtoCarro.setMarca(cursor.getString(1));
            dtoCarro.setModelo(cursor.getString(2));
            dtoCarro.setCor(cursor.getString(3));
            dtoCarro.setAno(cursor.getInt(4));
            dtoCarro.setValor(cursor.getDouble(5));

            arrayListCarro.add(dtoCarro);
        }
        return arrayListCarro;
    }

    public int excluir(DtoCarro carro){
        String id = "id=?";
        String[] args = {carro.getId()+""};
        return getWritableDatabase().delete(TABELA,id,args);
    }

    public long alterar(DtoCarro carro){

        ContentValues values = new ContentValues();
        values.put("MARCA", carro.getMarca());
        values.put("MODELO", carro.getModelo());
        values.put("COR", carro.getCor());
        values.put("ANO", carro.getAno());
        values.put("VALOR", carro.getValor());

        String id = "id=?";
        String[] args = {carro.getId()+""};

        return getWritableDatabase().update(TABELA, values, id, args);
    }
}
