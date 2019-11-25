package com.example.fitness.Models;

import android.util.Log;

import com.example.fitness.Database.DataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Actividad {
    private int id;
    private int idApi;
    private String nombre;
    private String descripcion;
    private float factor;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public static ArrayList<Actividad> convertirDesdeJSON(String jsonString){
        ArrayList<Actividad> actividades = new ArrayList<>();
        Log.v("fitness","p lossd" +
                "starting parsing actividades");
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int it = 0; it<jsonArray.length(); it++){
                JSONObject jsonObject = jsonArray.getJSONObject(it);
                Actividad actividad = new Actividad();
                Log.v("fitness","gettin: "+it);
                if(jsonObject.has("nombre")){
                    actividad.setNombre(jsonObject.getString("nombre"));
                }
                if(jsonObject.has("factor")){
                    actividad.setFactor(Float.parseFloat(jsonObject.getString("factor")));
                }
                if(jsonObject.has("descripcion")){
                    actividad.setDescripcion(jsonObject.getString("descripcion"));
                }
                actividades.add(actividad);
                Log.v("fitness",actividad.getNombre());
                //Log.v("fitness",actividad.getIdMacronutriente()+"");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return actividades;
    }

    public static void actualizarDB(DataBase db, ArrayList<Actividad> actividades){
        ArrayList<Actividad> datos =  db.getActividades();
        int encontrado = 0;
        for(int it = 0; it<datos.size(); it++)
        {
            for(int sr=0; sr<datos.size(); sr++)
            {
                if(datos.get(sr).getIdApi() == actividades.get(it).getIdApi())
                {
                    encontrado = 1;
                    break;
                }
            }
            if(encontrado == 0)
            {
                db.storeActividad(actividades.get(it));
            }
        }
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }

}
