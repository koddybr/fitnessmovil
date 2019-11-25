package com.example.fitness.Models;

import android.util.Log;

import com.example.fitness.Database.DataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

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
    DataBase db;

    public EstadoFisico() {
        this.peso = 0;
        this.estatura = 0;
        this.imc = 0;
        this.genero = 0;
        this.edad = 0;
        this.pesoObetivo = 0;
        this.pesoIdeal = 0;
        this.idActividad = 0;
        //this.db = new DataBase(getA);
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
        this.imc = this.peso/(this.estatura*this.estatura);
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

    public static ArrayList<EstadoFisico> convertirDesdeJSON(String jsonString){
        ArrayList<EstadoFisico> estadosFisicos = new ArrayList<>();
        Log.v("fitness","p lossd" +
                "starting parsing estadosFisicos");
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int it = 0; it<jsonArray.length(); it++){
                JSONObject jsonObject = jsonArray.getJSONObject(it);
                EstadoFisico estadoFisico = new EstadoFisico();
                Log.v("fitness","gettin: "+it);
                if(jsonObject.has("peso")){
                    estadoFisico.setPeso(Float.parseFloat(jsonObject.getString("peso")));
                }
                if(jsonObject.has("estatura")){
                    estadoFisico.setEstatura(Float.parseFloat(jsonObject.getString("estatura")));
                }
                if(jsonObject.has("imc")){
                    estadoFisico.setImc(Float.parseFloat(jsonObject.getString("imc")));
                }
                if(jsonObject.has("genero")){
                    estadoFisico.setGenero(
                            Integer.parseInt(
                                    jsonObject.getString("genero")
                            )
                    );
                }
                if(jsonObject.has("edad")){
                    estadoFisico.setEdad(Integer.parseInt(jsonObject.getString("edad")));
                }
//                if(jsonObject.has("acciones")){
//                    estadoFisico.setAcciones(Integer.parseInt(jsonObject.getString("acciones")));
//                }
                if(jsonObject.has("peso y en estado objetivo")){
                    estadoFisico.setPesoObetivo(Integer.parseInt(jsonObject.getString("peso objetivo")));
                }
                if(jsonObject.has("peso estandar")){
                    estadoFisico.setPesoIdeal(
                            Float.parseFloat(
                                    jsonObject.getString("peso estandar")
                            )
                    );
                }
                estadosFisicos.add(estadoFisico);
                //Log.v("fitness",estadoFisico.getNombre());
                //Log.v("fitness",estadoFisico.getIdMacronutriente()+"");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return estadosFisicos;
    }

    public static void actualizarDB(DataBase db, ArrayList<EstadoFisico> estadosFisicos){
        ArrayList<EstadoFisico> datos =  db.getEstadosFisicos();
        int encontrado = 0;
        for(int it = 0; it<datos.size(); it++)
        {
            for(int sr=0; sr<datos.size(); sr++)
            {
                if(datos.get(sr).getIdApi() == estadosFisicos.get(it).getIdApi())
                {
                    encontrado = 1;
                    break;
                }
            }
            if(encontrado == 0)
            {
                db.storeEstadoFisico(estadosFisicos.get(it));
            }
        }
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }
    public static String consumir(String code) {

        return code+"<<<";
    }
    public static String subir(String code) {

        return code+"++";
    }
    private int calcularCalorias(){
        return 0;
    }
}
