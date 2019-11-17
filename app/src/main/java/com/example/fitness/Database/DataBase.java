package com.example.fitness.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.fitness.Models.Alimento;
import com.example.fitness.Models.Avance;
import com.example.fitness.Models.EstadoFisico;
import com.example.fitness.Models.Logro;
import com.example.fitness.Models.Macronutriente;
import com.example.fitness.Models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Koddybr on 03/10/2019.
 */
public class DataBase extends SQLiteOpenHelper{
    SimpleDateFormat sdf;
    public DataBase(Context context ){
        super(context,"fitlabel2.sqlite",null,2);
        Log.v("fitlabel","db created");
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryUsers());
        db.execSQL(queryMacronutrientes());
        db.execSQL(queryAlimentos());
        db.execSQL(queryAvances());
        db.execSQL(queryEstadosFisicos());
        db.execSQL(queryActividades());
        db.execSQL(queryLogros());
        db.execSQL(queryRecomendaciones());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropEstadosFisicos());
        db.execSQL(dropUsers());
        db.execSQL(dropAlimentos());
        db.execSQL(dropAvances());
        db.execSQL(dropActividades());
        db.execSQL(dropLogros());
        db.execSQL(dropRecomendaciones());
    }

    private String queryUsers(){
        String query;
        query = "CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY, " +
                "api_id INTEGER," +
                "name TEXT, " +
                "password TEXT, " +
                "nombre TEXT," +
                "apellidos TEXT," +
                "fecha_nacimiento TEXT," +
                "email TEXT)";
        return query;
    }

    private String queryMacronutrientes(){
        String query;
        query = "CREATE TABLE macronutrientes (" +
                "id INTEGER PRIMARY KEY," +
                "api_id INTEGER," +
                "nombre TEXT," +
                "peso TEXT," +
                "calorias TEXT," +
                "proteinas TEXT," +
                "carbohidratos TEXT," +
                "grasas TEXT)";
        return query;
    }

    private String queryAlimentos(){
        String query;
        query = "CREATE TABLE alimentos (" +
                "id INTEGER PRIMARY KEY," +
                "api_id INTEGER," +
                "nombre TEXT," +
                "descripcion TEXT," +
                "macronutriente_id TEXT," +
                "marca TEXT," +
                "cantidad TEXT," +
                "calorias TEXT)";
        return query;
    }

    private String queryEstadosFisicos(){
        String query;
        query = "CREATE TABLE estados_fisicos (" +
                "id INTEGER PRIMARY KEY," +
                "api_id INTEGER," +
                "peso TEXT," +
                "estatura TEXT," +
                "imc TEXT," +
                "genero TEXT," +
                "edad TEXT," +
                "actividad_id TEXT," +
                "peso_objetivo TEXT," +
                "peso_ideal TEXT)";
        return query;
    }
    private String queryActividades(){
        String query;
        query = "CREATE TABLE actividades (" +
                "id INTEGER PRIMARY KEY," +
                "api_id INTEGER," +
                "nombre TEXT," +
                "descripcion TEXT," +
                "factor TEXT)";
        return query;
    }
    private String queryAvances(){
        String query;
        query = "CREATE TABLE avances (" +
                "id INTEGER PRIMARY KEY," +
                "api_id INTEGER," +
                "user_id TEXT," +
                "peso_inicial TEXT," +
                "peso_actual TEXT)";
        return query;
    }
    private String queryLogros(){
        String query;
        query = "CREATE TABLE logros (" +
                "id INTEGER PRIMARY KEY," +
                "api_id INTEGER," +
                "user_id TEXT," +
                "peso_objetivo TEXT," +
                "peso_actual TEXT," +
                "logrado TEXT)";
        return query;
    }
    private String queryRecomendaciones(){
        String query;
        query = "CREATE TABLE recomendacinoes (" +
                "id INTEGER PRIMARY KEY," +
                "api_id INTEGER," +
                "aliment_id TEXT," +
                "calorias TEXT)";
        return query;
    }
    public void storeUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        String currentDateandTime = sdf.format(new Date());
        val.put("api_id", user.getIdApi());
        val.put("name", user.getName());
        val.put("email", user.getEmail());
        val.put("password", user.getPassword());
        val.put("nombre", user.getNombre());
        val.put("apellidos", user.getApellidos());
        val.put("fecha_nacimiento", user.getFechaNacimiento().toString());
        val.put("id_estadoFisico", user.getIdEstadoFisico());
        db.insert("users",null,val);
        db.close();
    }
    public void storeMacronutrientes(Macronutriente macronutriente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        String currentDateandTime = sdf.format(new Date());
        val.put("api_id", macronutriente.getIdApi());
        val.put("nombre", macronutriente.getNombre());
        val.put("peso", macronutriente.getPeso());
        val.put("calorias", macronutriente.getCalorias());
        val.put("proteinas", macronutriente.getProteinas());
        val.put("carbohidratos", macronutriente.getCarbohidratos());
        val.put("grasas", macronutriente.getGrasas());
        db.insert("macronutrientes",null,val);
        db.close();
    }
    public void storeAlimento(Alimento alimento){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        String currentDateandTime = sdf.format(new Date());
        val.put("api_id", alimento.getIdApi());
        val.put("nombre", alimento.getNombre());
        val.put("descripcion", alimento.getDescripcion());
        val.put("marca", alimento.getMarca());
        val.put("macronutriente_id", alimento.getIdMacronutriente());
        db.insert("alimentos",null,val);
        db.close();
    }

    public void storeEstadoFisico(EstadoFisico estadoFisico){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("api_id", estadoFisico.getIdApi());
        val.put("peso", estadoFisico.getPeso());
        val.put("estatura", estadoFisico.getEstatura());
        val.put("imc", estadoFisico.getImc());
        val.put("genero", estadoFisico.getGenero());
        val.put("edad", estadoFisico.getEdad());
        val.put("peso_objetivo", estadoFisico.getPesoObetivo());
        val.put("peso_ideal", estadoFisico.getPesoIdeal());
        val.put("actividad_id", estadoFisico.getIdActividad());
        db.insert("estados_fisicos",null,val);
        db.close();
    }

    public void storeAvance(Avance avance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("api_id", avance.getIdApi());
        val.put("peso_inicial", avance.getPesoInicial());
        val.put("peso_actual", avance.getPesoActual());
        val.put("user_id", avance.getIdUsuario());
        db.insert("avance",null,val);
        db.close();
    }

    public void storeLogro(Logro logro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("api_id", logro.getIdApi());
        val.put("peso_objetivo", logro.getPesoObjetivo());
        val.put("peso_actual", logro.getPesoActual());
        val.put("logrado", logro.getLogrado());
        db.insert("logros",null,val);
        db.close();
    }

    public User getUser(){
        SQLiteDatabase db =  this.getWritableDatabase();
        User user = new User();
        user.setName("");
        Cursor c = db.rawQuery("SELECT * FROM users",null);
        if(c.moveToFirst()){
            do{
                user.setId(c.getInt(0));

            }while (c.moveToNext());
        }
        return user;
    }
    public User getActividad(){
        SQLiteDatabase db =  this.getWritableDatabase();
        User user = new User();
        user.setName("");
        Cursor c = db.rawQuery("SELECT * FROM actividades",null);
        if(c.moveToFirst()){
            do{
                user.setId(c.getInt(0));

            }while (c.moveToNext());
        }
        return user;
    }

    public ArrayList<Alimento> getAlimento(){
        SQLiteDatabase db =  this.getWritableDatabase();
        ArrayList<Alimento> alimentos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM alimentos",null);
        if(c.moveToFirst()){
            do{
                Alimento alimento =  new Alimento();
                alimento.setId(c.getInt(0));
                alimento.setIdApi(c.getInt(1));
                alimento.setNombre(c.getString(2));
                alimento.setDescripcion(c.getString(3));
                alimento.setIdMacronutriente(Integer.parseInt(c.getString(4)));
                alimento.setMarca(c.getString(5));
                alimento.setCantidad(Integer.parseInt(c.getString(6)));
                alimento.setCalorias(Integer.parseInt(c.getString(7)));
                Log.v("fitness","nombreAlimento"+alimento.getNombre());
                alimentos.add(alimento);

            }while (c.moveToNext());
        }
        return alimentos;
    }
    public User getAvance(){
        SQLiteDatabase db =  this.getWritableDatabase();
        User user = new User();
        user.setName("");
        Cursor c = db.rawQuery("SELECT * FROM users",null);
        if(c.moveToFirst()){
            do{
                user.setId(c.getInt(0));

            }while (c.moveToNext());
        }
        return user;
    }
    public EstadoFisico getEstadoFisico(){
        SQLiteDatabase db =  this.getWritableDatabase();
        EstadoFisico estadoFisico = new EstadoFisico();

        Cursor c = db.rawQuery("SELECT * FROM estados_fisicos",null);
        if(c.moveToFirst()){
            do{
                estadoFisico.setId(c.getInt(0));
                estadoFisico.setPeso(Float.parseFloat(c.getString(1)));
                estadoFisico.setEstatura(Float.parseFloat(c.getString(2)));
                estadoFisico.setImc(Float.parseFloat(c.getString(3)));
                estadoFisico.setGenero(Integer.parseInt(c.getString(4)));
                estadoFisico.setEdad(Integer.parseInt(c.getString(5)));
                estadoFisico.setPesoObetivo(Float.parseFloat(c.getString(6)));
                estadoFisico.setPesoIdeal(Float.parseFloat(c.getString(7)));
            }while (c.moveToNext());
        }
        return estadoFisico;
    }
    public User getLogro(){
        SQLiteDatabase db =  this.getWritableDatabase();
        User user = new User();
        user.setName("");
        Cursor c = db.rawQuery("SELECT * FROM users",null);
        if(c.moveToFirst()){
            do{
                user.setId(c.getInt(0));

            }while (c.moveToNext());
        }
        return user;
    }
//
//
//    public ArrayList<Product> getProducts(){
//        ArrayList<Product> products = new ArrayList<Product>();
//        Product product;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Log.v("Brian","PRODUCTS");
//        Cursor c = db.rawQuery("SELECT * FROM products where deleted_at ="+"\"\"",null);
//        if(c.moveToFirst()){
//            do{
//                product =new Product();
//                product.setId(c.getInt(0));
//                product.setEnterpriceId(c.getInt(1));
//                product.setName(c.getString(2));
//                product.setDescription(c.getString(3));
//                product.setCode(c.getString(4));
//                product.setQuantity(Integer.parseInt(c.getString(5)));
//                product.setPrice(Float.parseFloat(c.getString(6)));
//                product.setStock(Integer.parseInt(c.getString(7)));
//                products.add(product);
//                Log.v("Brian","id: "+product.getId());
//                Log.v("Brian","name: "+product.getName());
//            }while (c.moveToNext());
//        }
//        return products;
//    }
//    public Enterprice getEnterprice(){
//        Enterprice enterprice = new Enterprice();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM enterprices where deleted_at ="+"\"\"",null);
//        Log.v("Brian","ENTERPRICES");
//        if(c.moveToFirst()){
//            do{
//                enterprice.setId(c.getInt(0));
//                enterprice.setBranchId(c.getInt(1));
//                enterprice.setName(c.getString(2));
//                enterprice.setBranch(c.getString(3));
//                enterprice.setOwner(c.getString(4));
//                enterprice.setAddress(c.getString(5));
//                enterprice.setPhone(c.getString(6));
//                enterprice.setCity(c.getString(7));
//                enterprice.setCountry(c.getString(8));
//                enterprice.setTitle(c.getString(9));
//                enterprice.setNit(c.getString(10));
//                enterprice.setAuthorization(c.getString(11));
//                enterprice.setDeadLine(c.getString(12));
//                enterprice.setLaw(c.getString(13));
//                enterprice.setActivity(c.getString(14));
//                Log.v("Brian","id: "+enterprice.getId());
//                Log.v("Brian","id: "+enterprice.getName());
//            }while (c.moveToNext());
//        }
//        return enterprice;
//    }
//    public void deleteUser(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues val = new ContentValues();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String currentDateandTime = sdf.format(new Date());
//        val.put("deleted_at",currentDateandTime);
//        db.update("users",val,"id="+id,null);
//        db.close();
//    }
    //    public void deleteProducst(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues val = new ContentValues();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String currentDateandTime = sdf.format(new Date());
//        val.put("deleted_at",currentDateandTime);
//        db.update("users",val,"id="+id,null);
//        db.close();
//    }
    private String dropUsers(){
        String query = "DROP TABLE IF EXISTS users";
        return query;
    }
    private String dropMacronutrientes(){
        String query = "DROP TABLE IF EXISTS macronutrientes";
        return query;
    }
    private String dropAlimentos(){
        String query = "DROP TABLE IF EXISTS alimentos";
        return query;
    }
    private String dropEstadosFisicos(){
        String query = "DROP TABLE IF EXISTS estadosFisicos";
        return query;
    }
    private String dropActividades(){
        String query = "DROP TABLE IF EXISTS actividades";
        return query;
    }
    private String dropAvances(){
        String query = "DROP TABLE IF EXISTS avances";
        return query;
    }
    private String dropLogros(){
        String query = "DROP TABLE IF EXISTS logros";
        return query;
    }
    private String dropRecomendaciones(){
        String query = "DROP TABLE IF EXISTS recomendaciones";
        return query;
    }
}