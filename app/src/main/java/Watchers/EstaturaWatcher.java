package Watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.fitness.Models.EstadoFisico;

public class EstaturaWatcher implements TextWatcher {

    EditText imc;
    EstadoFisico estadoFisico;
    public EstaturaWatcher(EditText imc, EstadoFisico estadoFisico)
    {
        this.imc = imc;
        this.estadoFisico = estadoFisico;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        this.imc.setText(Float.toString(this.estadoFisico.getImc()));
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
