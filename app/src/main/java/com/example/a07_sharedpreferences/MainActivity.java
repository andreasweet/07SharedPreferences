package com.example.a07_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnGuardar;
    private Button btnBorrar;
    private ImageButton btnBorrarNombre;
    private ImageButton btnBorrarEdad;

    private SharedPreferences sharedPreferencesPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBorrar =findViewById(R.id.btnBorrar);
        btnBorrarNombre =findViewById(R.id.btnEliminaNombre);
        btnBorrarEdad =findViewById(R.id.btnEliminaEdad);

        sharedPreferencesPersonas = getSharedPreferences(Constantes.PERSONAS, MODE_PRIVATE);
        cargarDatos();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                int edad = Integer.parseInt(txtEdad.getText().toString());

                SharedPreferences.Editor editor = sharedPreferencesPersonas.edit();
                editor.putString(Constantes.NOMBRRE,nombre);
                editor.putInt(Constantes.EDAD, edad);
                //commit (sincrono)  / apply (asicrono)
                editor.apply();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferencesPersonas.edit();
                editor.clear();
                editor.apply();
            }
        });

        btnBorrarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferencesPersonas.edit();
                editor.remove(Constantes.NOMBRRE);
                editor.apply();
                txtNombre.setText("");
            }
        });

        btnBorrarEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferencesPersonas.edit();
                editor.remove(Constantes.EDAD);
                editor.apply();
                txtEdad.setText("");
            }
        });
    }


    private void cargarDatos(){
        String nombre = sharedPreferencesPersonas.getString(Constantes.NOMBRRE, "");
        if(!nombre.isEmpty()){
            txtNombre.setText(nombre);
        }
        int edad = sharedPreferencesPersonas.getInt(Constantes.EDAD, -1);
        if(edad != -1){
            txtEdad.setText(String.valueOf(edad));
        }
    }
}