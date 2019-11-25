package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitness.Database.DataBase;
import com.example.fitness.Models.EstadoFisico;

import com.example.fitness.Watchers.EstaturaWatcher;
import com.example.fitness.Watchers.PesoWatcher;

public class MiEstadoActivity extends AppCompatActivity {

    EstadoFisico estadoFisico;
    TextView txtPeso;
    TextView txtEdad;
    TextView txtGenero;
    TextView txtEstatura;
    TextView txtIMC;
    TextView txtPesoObjetivo;
    DataBase dataBase;
    Button btnGuardarEstadoFisico;
    String TAG = "fitness";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        estadoFisico = new EstadoFisico();
        txtIMC = (EditText)findViewById(R.id.txtImc);
        txtPeso = (EditText)findViewById(R.id.txtPeso);
        txtEstatura = (EditText)findViewById(R.id.txtEstatura);
        btnGuardarEstadoFisico = (Button) findViewById(R.id.idGuardarEstadoFisico);

        //txtPeso.addTextChangedListener(new PesoWatcher(new PesoWatcher((EditText) txtIMC, (EditText) txtPeso, (EditText) txtEstatura)));
        //txtEstatura.addTextChangedListener(new EstaturaWatcher((EditText) txtIMC, estadoFisico));
        btnGuardarEstadoFisico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "guardar clicked");
                guardar();
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miestado);
    }

    private void guardar() {
        txtPeso = (EditText)findViewById(R.id.txtPeso);
        txtEdad = (EditText)findViewById(R.id.txtEdad);
        txtGenero = (EditText)findViewById(R.id.txtGenero);
        txtEstatura = (EditText)findViewById(R.id.txtEstatura);
        txtIMC = (EditText)findViewById(R.id.txtImc);
        estadoFisico.setPeso(Float.parseFloat(txtPeso.toString()));
        estadoFisico.setEstatura(Float.parseFloat(txtEstatura.toString()));
        estadoFisico.setEdad(Integer.parseInt(txtEdad.toString()));
        estadoFisico.setGenero(Integer.parseInt(txtGenero.toString()));
        estadoFisico.setImc(Float.parseFloat(txtIMC.toString()));
        Log.v("fitness", "obtenido correctamente");
        Log.v("fitness", "guardado en db correctamente");
        Log.v("fitness",estadoFisico.getEdad()+"");
        Log.v("fitness",estadoFisico.getEstatura()+"");
        dataBase.storeEstadoFisico(estadoFisico);
        Log.v("fitness", "guardado en db correctamente");
        Log.v("fitness",estadoFisico.getEdad()+"");
        Log.v("fitness",estadoFisico.getEstatura()+"");
        Log.v("fitness",estadoFisico.getId()+"");
        estadoFisico = dataBase.getEstadoFisico();
        Log.v("fitness", "recuperando datos de la db");
        Log.v("fitness",estadoFisico.getEdad()+"");
        Log.v("fitness",estadoFisico.getEstatura()+"");
        Log.v("fitness",estadoFisico.getId()+"");
    }

    public static String calcularSubir(String codigo){
        return "";
    }

    public static String calcularConsumir(String codigo){
        return "";
    }


}
