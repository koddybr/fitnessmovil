package com.example.fitness.Watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.fitness.Models.EstadoFisico;

import java.text.DecimalFormat;

public class PesoWatcher implements TextWatcher {
    EditText imc;
    EditText peso;
    EditText altura;
    EstadoFisico estadoFisico;
    Float imcf;
    public PesoWatcher(EditText imc, EditText peso, EditText altura)
    {
        this.peso = peso;
        this.imc = imc;
        this.altura = altura;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Float pesof,alturaf;
        try {
            pesof = Float.parseFloat(peso.getText().toString());
            alturaf = Float.parseFloat(altura.getText().toString());
        }catch (NumberFormatException e) {
            pesof = 0f;
            alturaf = 0f;
        }
        if(alturaf == 0)
            alturaf = 1f;
        imcf = pesof/(alturaf*alturaf);
    }

    private Float getImcf(){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(this.imcf));
    }

    @Override
    public void afterTextChanged(Editable editable) {
        this.imc.setText(Float.toString(this.getImcf()));
    }
}
