package com.example.fitness.Connection;

import android.util.Base64;
import android.util.Log;

import com.example.fitness.Models.Actividad;
import com.example.fitness.Models.Alimento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Server {
    private final static String SERVER = "157.245.165.151";
    private final static String PROTOCOL = "http://";
    private final static String LOGIN = "/login";
    private final static String GET_ALIMENTOS = "/get_alimentos";
    private final static String GET_MACRONUTRIENTES = "get_macronutrientes";
    private final static String GET_ESTADOS_FISICOS = "get_estados_fisicos";
    private final static String GET_ACTIVIDADES = "get_niveles_actividades";
    private final static String GET_RECOMENDACIONES = "get_recomendaciones";
    private final static String GET_LOGROS = "get_logros";
    private final static String GET_AVANCES = "get_avances";
    private final static String GET_USUARIOS = "get_usuarios";

    private final static String STORE_CLIENT = "/client";
    private final static String SEARCH_CLIENT = "/client/";

    private String domain;
    private String user;
    private String password;
    private String string_gotten;

    public Server() {
    }

    public Server(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String login(String email, String password) {
        String link = PROTOCOL  + SERVER + LOGIN;
        int codestatus = 0;
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.v("Direcccion-----> ", link);
            Log.v("Brian", "user:" + email);
            Log.v("Brian", "pass:" + password);
            String basicAuth = "Basic " + new String(Base64.encode(( email + ":" + password).getBytes(), Base64.NO_WRAP));
            con.setRequestProperty("Authorization", basicAuth);
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setInstanceFollowRedirects(true);
            codestatus = con.getResponseCode();
            readStream(con.getInputStream());
        } catch (Exception e) {
            Log.v("Brian", "adsf");
            string_gotten = "";
            //codestatus = 500;
            e.printStackTrace();
        }
        return string_gotten;
    }

//    public Client searchClient(String nit) {
//        String link = PROTOCOL + domain + SERVER + SEARCH_CLIENT + nit;
//        Client cli = new Client();
//        int codestatus = 0;
//        try {
//            URL url = new URL(link);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            Log.v("Direcccion-----> ", link);
//            Log.v("Brian", "user:" + user);
//            Log.v("Brian", "pass:" + password);
//            String basicAuth = "Basic " + new String(Base64.encode((domain + "." + user + ":" + password).getBytes(), Base64.NO_WRAP));
//            con.setRequestProperty("Authorization", basicAuth);
//            con.setConnectTimeout(30000);
//            con.setReadTimeout(30000);
//            con.setInstanceFollowRedirects(true);
//            codestatus = con.getResponseCode();
//            //readStream(con.getInputStream());
//            cli = readClient(con.getInputStream());
//        } catch (Exception e) {
//            Log.v("Brian", "adsf");
//            //codestatus = 500;
//            e.printStackTrace();
//        }
//        return cli;
//    }
//
//    public int saveClient(Client client, String path) {
//        String parameters = client.parseString();
//        int codestatus = 0;
//        String link = PROTOCOL + domain + SERVER + path;
//        Log.v("Brian", "link: " + link);
//        try {
//            byte[] data = parameters.getBytes();
//            int lenghtData = data.length;
//            URL url = new URL(link);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            String auth = "Basic " + new String(Base64.encode((domain + "." + user + ":" + password).getBytes(), Base64.NO_WRAP));
//            con.setRequestProperty("Authorization", auth);
//            con.setConnectTimeout(30000);
//            con.setReadTimeout(30000);
//            con.setInstanceFollowRedirects(true);
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("Content-Length", Integer.toString(lenghtData));
//            con.setUseCaches(false);
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.write(data);
//            codestatus = con.getResponseCode();
//            Log.v("Brian", "status " + codestatus);
//
//            //readStream(con.getInputStream());
//        } catch (Exception e) {
//            Log.v("Brian", "fucking error: " + e.getStackTrace());
//        }
//        return codestatus;
//    }

    private void readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                builder.append(line);
            }
            //setRespuesta(builder.toString());
            string_gotten = builder.toString();
            Log.v("Brian", "respuesta :" + builder.toString());
        } catch (IOException e) {
            Log.v("Brian", "exception al leer");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Alimento> getAlimentos(String email, String password) {
        String link = PROTOCOL  + SERVER + GET_ALIMENTOS;
        int codestatus = 0;
        ArrayList<Alimento> alimentos = new ArrayList<>();
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.v("Direcccion-----> ", link);
            Log.v("Brian", "user:" + email);
            Log.v("Brian", "pass:" + password);
            String basicAuth = "Basic " + new String(Base64.encode(( email + ":" + password).getBytes(), Base64.NO_WRAP));
            con.setRequestProperty("Authorization", basicAuth);
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setInstanceFollowRedirects(true);
            codestatus = con.getResponseCode();
            alimentos = readAlimentos(con.getInputStream());
        } catch (Exception e) {
            Log.v("Brian", "adsf");
            string_gotten = "";
            alimentos = null;
            //codestatus = 500;
            e.printStackTrace();
        }
        return alimentos;
    }

    private ArrayList<Alimento> readAlimentos(InputStream in) {
        ArrayList<Alimento> alimentos = new ArrayList<>();
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                builder.append(line);
            }
            Alimento.convertirDesdeJSON(builder.toString());
            Log.v("Brian", "respuesta :" + builder.toString());
        } catch (IOException e) {
            alimentos = null;
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return alimentos;
    }
    public ArrayList<Actividad> getActividades(String email, String password) {
        String link = PROTOCOL  + SERVER + GET_ACTIVIDADES;
        int codestatus = 0;
        ArrayList<Actividad> actividades = new ArrayList<>();
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.v("Direcccion-----> ", link);
            Log.v("Brian", "user:" + email);
            Log.v("Brian", "pass:" + password);
            String basicAuth = "Basic " + new String(Base64.encode(( email + ":" + password).getBytes(), Base64.NO_WRAP));
            con.setRequestProperty("Authorization", basicAuth);
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setInstanceFollowRedirects(true);
            codestatus = con.getResponseCode();
            actividades = readActividades(con.getInputStream());
        } catch (Exception e) {
            Log.v("Brian", "adsf");
            string_gotten = "";
            actividades = null;
            //codestatus = 500;
            e.printStackTrace();
        }
        return actividades;
    }

    private ArrayList<Actividad> readActividades(InputStream in) {
        ArrayList<Actividad> actividades = new ArrayList<>();
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                builder.append(line);
            }
            Actividad.convertirDesdeJSON(builder.toString());
            Log.v("Brian", "respuesta :" + builder.toString());
        } catch (IOException e) {
            actividades = null;
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return actividades;
    }
//
//    public int postMethod(Client client, String path) {
//
//        String parameters = client.parseString();
//        int codestatus = 0;
//        String link = PROTOCOL + domain + SERVER + path;
//        try {
//            byte[] data = parameters.getBytes();
//            int lenghtData = data.length;
//            URL url = new URL(link);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            String auth = "Basic " + new String(Base64.encode((domain + "." + user + ":" + password).getBytes(), Base64.NO_WRAP));
//            con.setRequestProperty("Authorization", auth);
//            con.setConnectTimeout(30000);
//            con.setReadTimeout(30000);
//            con.setInstanceFollowRedirects(true);
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("Content-Length", Integer.toString(lenghtData));
//            con.setUseCaches(false);
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.write(data);
//            codestatus = con.getResponseCode();
//            Log.v("Brian", "status " + codestatus);
//
//            readStream(con.getInputStream());
//        } catch (Exception e) {
//            Log.v("Brian", "fucking error: " + e.getStackTrace());
//        }
//        return codestatus;
//    }
//
//    public Invoice saveInvoice(Invoice invoice, String path) {
//        Invoice inv = new Invoice();
//        String parameters = invoice.parseString();
//        int codestatus = 0;
//        String link = PROTOCOL + domain + SERVER + path;
//        Log.v("Brian", "link: " + link);
//        try {
//            byte[] data = parameters.getBytes();
//            int lenghtData = data.length;
//            URL url = new URL(link);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            String auth = "Basic " + new String(Base64.encode((domain + "." + user + ":" + password).getBytes(), Base64.NO_WRAP));
//            con.setRequestProperty("Authorization", auth);
//            con.setConnectTimeout(30000);
//            con.setReadTimeout(30000);
//            con.setInstanceFollowRedirects(true);
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("Content-Length", Integer.toString(lenghtData));
//            con.setUseCaches(false);
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.write(data);
//            codestatus = con.getResponseCode();
//            Log.v("Brian", "status " + codestatus);
//            inv = readInvoice(con.getInputStream());
//            //readStream(con.getInputStream());
//        } catch (Exception e) {
//            Log.v("Brian", "fucking error: " + e.getStackTrace());
//        }
//        return inv;
//    }
//
//    private Invoice readInvoice(InputStream in) {
//        Invoice invoice;
//        BufferedReader reader = null;
//        StringBuilder builder = new StringBuilder();
//        try {
//            reader = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//                builder.append(line);
//            }
//            invoice = new Invoice();
//            invoice.parseBasicFromJson(builder.toString());
//            Log.v("Brian", "respuesta :" + builder.toString());
//        } catch (IOException e) {
//            invoice = null;
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return invoice;
//    }
}
