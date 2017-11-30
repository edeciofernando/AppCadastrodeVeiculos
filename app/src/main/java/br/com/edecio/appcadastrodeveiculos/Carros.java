package br.com.edecio.appcadastrodeveiculos;

/**
 * Created by edecio on 06/11/2017.
 */

public class Carros {
    private long id;
    private String modelo;
    private int ano;
    private double preco;

    public Carros(long id, String modelo, int ano, double preco) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
