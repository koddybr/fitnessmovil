package com.example.fitness.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Macronutriente {
    private int id;
    private int idApi;
    private String nombre;
    private float peso;
    private float calorias;
    private float proteinas;
    private float carbohidratos;
    private float grasas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        this.calorias = calorias;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public float getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(float carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public float getGrasas() {
        return grasas;
    }

    public void setGrasas(float grasas) {
        this.grasas = grasas;
    }

    public static ArrayList<Macronutriente> convertirDesdeJSON(String jsonString){
        ArrayList<Macronutriente> macronutrientes = new ArrayList<>();
        Log.v("fitness","p lossd" +
                "starting parsing macronutrientes");
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int it = 0; it<jsonArray.length(); it++){
                JSONObject jsonObject = jsonArray.getJSONObject(it);
                Macronutriente macronutriente = new Macronutriente();
                Log.v("fitness","gettin: "+it);
                if(jsonObject.has("nombre")){
                    macronutriente.setNombre(jsonObject.getString("nombre"));
                }
                if(jsonObject.has("peso neto")){
                    macronutriente.setPeso(Float.parseFloat(jsonObject.getString("peso neto")));
                }
                if(jsonObject.has("calorias")){
                    macronutriente.setCalorias(Float.parseFloat(jsonObject.getString("calorias")));
                }
                if(jsonObject.has("proteinas")){
                    macronutriente.setProteinas(
                            Integer.parseInt(
                                    jsonObject.getString("proteinas")
                            )
                    );
                }
                if(jsonObject.has("carbohidratos")){
                    macronutriente.setCarbohidratos(Integer.parseInt(jsonObject.getString("carbohidratos")));
                }
                if(jsonObject.has("grasas")){
                    macronutriente.setGrasas(
                            Integer.parseInt(
                                    jsonObject.getString("grasas")
                            )
                    );
                }
                macronutrientes.add(macronutriente);
                Log.v("fitness",macronutriente.getNombre());
                //Log.v("fitness",macronutriente.getIdMacronutriente()+"");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return macronutrientes;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }
}
