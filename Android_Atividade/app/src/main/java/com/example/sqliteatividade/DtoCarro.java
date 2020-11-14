package com.example.sqliteatividade;

import android.support.annotation.NonNull;

public class DtoCarro {
    private int id, ano;
    private String marca, modelo, cor;
    private double valor;

    public DtoCarro() {
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + modelo + " - " + marca + " - " + ano + " - " + cor + " - " + valor;
    }

    public DtoCarro(int ano, String marca, String modelo, String cor, double valor) {
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
