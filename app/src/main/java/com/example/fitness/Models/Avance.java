package com.example.fitness.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public static ArrayList<Avance> convertirDesdeJSON(String jsonString){
        ArrayList<Avance> avances = new ArrayList<>();
        Log.v("fitness","p lossd" +
                "starting parsing avances");
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int it = 0; it<jsonArray.length(); it++){
                JSONObject jsonObject = jsonArray.getJSONObject(it);
                Avance avance = new Avance();
                Log.v("fitness","gettin: "+it);
                if(jsonObject.has("peso inicial")){
                    avance.setPesoInicial(Float.parseFloat(jsonObject.getString("peso inicial")));
                }
                if(jsonObject.has("peso actual")){
                    avance.setPesoActual(Float.parseFloat(jsonObject.getString("peso actual")));
                }


                avances.add(avance);
                //Log.v("fitness",avance.getNombre());
                ////Log.v("fitness",avance.getIdMacronutriente()+"");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return avances;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }
}
