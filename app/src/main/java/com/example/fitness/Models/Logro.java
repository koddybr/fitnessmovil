package com.example.fitness.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Logro {
    private int id;
    private int idApi;
    private int idUser;
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

    public static ArrayList<Logro> convertirDesdeJSON(String jsonString){
        ArrayList<Logro> logros = new ArrayList<>();
        Log.v("fitness","p lossd" +
                "starting parsing logros");
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int it = 0; it<jsonArray.length(); it++){
                JSONObject jsonObject = jsonArray.getJSONObject(it);
                Logro logro = new Logro();
                Log.v("fitness","gettin: "+it);
                if(jsonObject.has("peso objetivo")){
                    logro.setPesoObjetivo(Float.parseFloat(jsonObject.getString("peso objetivo")));
                }
                if(jsonObject.has("peso actual")){
                    logro.setPesoActual(Float.parseFloat(jsonObject.getString("peso actual")));
                }
                if(jsonObject.has("logrado")){
                    logro.setLogrado(Integer.parseInt(jsonObject.getString("logrado")));
                }

                logros.add(logro);
                //Log.v("fitness",logro.getNombre());
                //Log.v("fitness",logro.getIdMacronutriente()+"");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return logros;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
