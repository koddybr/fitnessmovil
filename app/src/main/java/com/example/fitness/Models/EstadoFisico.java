package com.example.fitness.Models;

public class EstadoFisico {
    private int id;
    private float peso;
    private float estatura;
    private float imc;
    private int genero;
    private int edad;
    private float pesoObetivo;
    private float pesoIdeal;
    private int idActividad;
    private int idApi;

    public EstadoFisico() {
        this.peso = 0;
        this.estatura = 0;
        this.imc = 0;
        this.genero = 0;
        this.edad = 0;
        this.pesoObetivo = 0;
        this.pesoIdeal = 0;
        this.idActividad = 0;
    }

    public EstadoFisico(float peso, float estatura, float imc, int genero, int edad, float pesoObetivo, float pesoIdeal, int idActividad) {
        this.peso = peso;
        this.estatura = estatura;
        this.imc = imc;
        this.genero = genero;
        this.edad = edad;
        this.pesoObetivo = pesoObetivo;
        this.pesoIdeal = pesoIdeal;
        this.idActividad = idActividad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public float getImc() {
        if(this.estatura <= 0 ){
            return  0;
        }
        this.imc = this.peso/this.estatura;
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPesoObetivo() {
        return pesoObetivo;
    }

    public void setPesoObetivo(float pesoObetivo) {
        this.pesoObetivo = pesoObetivo;
    }

    public float getPesoIdeal() {
        return pesoIdeal;
    }

    public void setPesoIdeal(float pesoIdeal) {
        this.pesoIdeal = pesoIdeal;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }
}
