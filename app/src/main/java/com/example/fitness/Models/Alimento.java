package com.example.fitness.Models;

import android.util.Log;

import com.example.fitness.Database.DataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Alimento {
    private int id;
    private int idApi;
    private String nombre;
    private String descripcion;
    private String marca;
    private int idMacronutriente;
    private int cantidad;
    private int calorias;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdMacronutriente() {
        return idMacronutriente;
    }

    public void setIdMacronutriente(int idMacronutriente) {
        this.idMacronutriente = idMacronutriente;
    }

    public static String getAlimentoName(){
        return "nombre de alimento";
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getIdApi() {
        return idApi;
    }

    public void setIdApi(int idApi) {
        this.idApi = idApi;
    }

    public static ArrayList<Alimento> convertirDesdeJSON(String jsonString){
        ArrayList<Alimento> alimentos = new ArrayList<>();
        Log.v("fitness","starting parsing aliemtos");
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int it = 0; it<jsonArray.length(); it++){
                JSONObject jsonObject = jsonArray.getJSONObject(it);
                Alimento alimento = new Alimento();
                Log.v("fitness","gettin: "+it);
                if(jsonObject.has("nombre")){
                    alimento.setNombre(jsonObject.getString("nombre"));
                }
                if(jsonObject.has("descripcion")){
                    alimento.setDescripcion(jsonObject.getString("descripcion"));
                }
                if(jsonObject.has("marca")){
                    alimento.setMarca(jsonObject.getString("marca"));
                }
                if(jsonObject.has("calorias")){
                    alimento.setCalorias(
                        Integer.parseInt(
                            jsonObject.getString("calorias")
                        )
                    );
                }
                if(jsonObject.has("macronutriente_id")){
                    alimento.setIdMacronutriente(Integer.parseInt(jsonObject.getString("macronutriente_id")));
                }
                if(jsonObject.has("cantidad")){
                    alimento.setCantidad(
                        Integer.parseInt(
                            jsonObject.getString("cantidad")
                        )
                    );
                }
                alimentos.add(alimento);
                Log.v("fitness",alimento.getNombre());
                Log.v("fitness",alimento.getIdMacronutriente()+"");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return alimentos;
    }

    public static void actualizarDB(DataBase db, ArrayList<Alimento> alimentos){
        ArrayList<Alimento> datos =  db.getAlimento();
        int encontrado = 0;
        for(int it = 0; it<datos.size(); it++)
        {
            for(int sr=0; sr<datos.size(); sr++)
            {
                if(datos.get(sr).getIdApi() == alimentos.get(it).getIdApi())
                {
                    encontrado = 1;
                    break;
                }
            }
            if(encontrado == 0)
            {
                db.storeAlimento(alimentos.get(it));
            }
        }
    }

}
