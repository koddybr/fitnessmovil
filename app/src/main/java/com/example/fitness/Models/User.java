package com.example.fitness.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private int id;
    private int idApi;
    private String name;
    private String email;
    private String password;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private int idEstadoFisico;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdEstadoFisico() {
        return idEstadoFisico;
    }

    public void setIdEstadoFisico(int idEstadoFisico) {
        this.idEstadoFisico = idEstadoFisico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<User> convertirDesdeJSON(String jsonString){
        ArrayList<User> users = new ArrayList<>();
        Log.v("fitness","p lossd" +
                "starting parsing users");
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int it = 0; it<jsonArray.length(); it++){
                JSONObject jsonObject = jsonArray.getJSONObject(it);
                User user = new User();
                Log.v("fitness","gettin: "+it);
                if(jsonObject.has("nombres")){
                    user.setNombre(jsonObject.getString("nombres"));
                }
                if(jsonObject.has("apellidos")){
                    user.setApellidos(jsonObject.getString("apellidos"));
                }
                if(jsonObject.has("fecha_nacimiento")){
                    user.setFechaNacimiento(null);
                }
                if(jsonObject.has("nombre de usuario")){
                    user.setNombre(
                    //        Integer.parseInt(
                                    jsonObject.getString("nombre de usuario")
                            //)
                    );
                }
                if(jsonObject.has("correo")){
                    user.setEmail(jsonObject.getString("correo"));
                }

                users.add(user);
                Log.v("fitness",user.getNombre());
                //Log.v("fitness",user.getIdMacronutriente()+"");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void actualizarDB(DataBase db, ArrayList<User> users){
        ArrayList<User> datos =  db.getUsers();
        int encontrado = 0;
        for(int it = 0; it<datos.size(); it++)
        {
            for(int sr=0; sr<datos.size(); sr++)
            {
                if(datos.get(sr).getIdApi() == users.get(it).getIdApi())
                {
                    encontrado = 1;
                    break;
                }
            }
            if(encontrado == 0)
            {
                db.storeUser(users.get(it));
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
