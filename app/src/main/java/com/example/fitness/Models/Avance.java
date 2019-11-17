package com.example.fitness.Models;

public class Avance {
    private int id;
    private int idApi;
    private int idUsuario;
    private float pesoInicial;
    private float pesoActual;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(float peso_inicial) {
        this.pesoInicial = peso_inicial;
    }

    public float getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(float peso_actual) {
        this.pesoActual = peso_actual;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }
}
