package com.example.fitness.Models;

public class Logro {
    private int id;
    private int idApi;
    private float pesoObjetivo;
    private float pesoActual;
    private int logrado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPesoObjetivo() {
        return pesoObjetivo;
    }

    public void setPesoObjetivo(float pesoObjetivo) {
        this.pesoObjetivo = pesoObjetivo;
    }

    public float getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(float pesoActual) {
        this.pesoActual = pesoActual;
    }

    public int getLogrado() {
        return logrado;
    }

    public void setLogrado(int logrado) {
        this.logrado = logrado;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }
}
