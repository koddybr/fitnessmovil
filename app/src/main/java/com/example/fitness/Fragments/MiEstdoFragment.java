package com.example.fitness.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitness.Database.DataBase;
import com.example.fitness.Models.Actividad;
import com.example.fitness.Models.EstadoFisico;
import com.example.fitness.R;

import com.example.fitness.Watchers.EstaturaWatcher;
import com.example.fitness.Watchers.PesoWatcher;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MiEstdoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MiEstdoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiEstdoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
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
    private View rootView;
    private Context context;

    public MiEstdoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiEstdoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MiEstdoFragment newInstance(String param1, String param2) {
        MiEstdoFragment fragment = new MiEstdoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.miestado, container, false);
        estadoFisico = new EstadoFisico();
        txtIMC = (EditText)rootView.findViewById(R.id.txtImc);
        txtPeso = (EditText)rootView.findViewById(R.id.txtPeso);
        txtEstatura = (EditText)rootView.findViewById(R.id.txtEstatura);
        btnGuardarEstadoFisico = (Button) rootView.findViewById(R.id.idGuardarEstadoFisico);
        txtPeso.addTextChangedListener(new PesoWatcher((EditText) txtIMC, (EditText) txtPeso, (EditText) txtEstatura));
        txtEstatura.addTextChangedListener(new PesoWatcher((EditText) txtIMC, (EditText) txtPeso, (EditText) txtEstatura));
        btnGuardarEstadoFisico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "guardar clicked");
                guardar();
            }
        });
        ArrayList<Actividad> actividades = dataBase.getActividades();
        String[] listaActividades = new String[actividades.size()];// {"Item 1", "Item 2", "Item 3", "Item 4"};
        for(int it=0; it<actividades.size(); it++){
            listaActividades[it] = actividades.get(it).getNombre()+" - "+actividades.get(it).getDescripcion();
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this.context,
                        R.layout.lista_actividades,
                        listaActividades);

        AutoCompleteTextView editTextFilledExposedDropdown =
                rootView.findViewById(R.id.txtActividad);
        editTextFilledExposedDropdown.setAdapter(adapter);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setContext(Context context){
        this.context = context;
        dataBase = new DataBase(context);
    }

    private void guardar() {
        txtPeso = (EditText)rootView.findViewById(R.id.txtPeso);
        txtEdad = (EditText)rootView.findViewById(R.id.txtEdad);
        txtGenero = (EditText)rootView.findViewById(R.id.txtGenero);
        txtEstatura = (EditText)rootView.findViewById(R.id.txtEstatura);
        txtIMC = (EditText)rootView.findViewById(R.id.txtImc);

        Log.v(TAG,txtPeso.getText().toString());
        estadoFisico.setPeso(Float.parseFloat(txtPeso.getText().toString()));
        estadoFisico.setEstatura(Float.parseFloat(txtEstatura.getText().toString()));
        estadoFisico.setEdad(Integer.parseInt(txtEdad.getText().toString()));
        estadoFisico.setGenero(Integer.parseInt(txtGenero.getText().toString()));
        //estadoFisico.setImc(Float.parseFloat(txtIMC.getText().toString()));
        estadoFisico.setImc(estadoFisico.getPeso()/(estadoFisico.getEstatura()*estadoFisico.getEstatura()));
        //calculando peso ideal
        Float pesoBase = (estadoFisico.getEstatura()*100)-100;
        Double pesoIdeal = pesoBase*0.90;
        //if(estadoFisico.getGenero() == 1) {
            estadoFisico.setPesoIdeal((float)(pesoIdeal*100)/100);
        //}

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
}
