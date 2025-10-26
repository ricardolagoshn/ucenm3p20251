package com.example.ucenm3p20251;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ucenm3p20251.configuraciones.SQLiteConexion;
import com.example.ucenm3p20251.configuraciones.Transacciones;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Declaracion de variables globales
    EditText nombres, apellidos, edad, correo;
    ImageView imageView;
    Button btnagregar, btnfoto;

    private static final int PERMISO_CAMARA = 101;
    private String fotoBase64 = null;
    private File fotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        imageView = (ImageView) findViewById(R.id.imageView);

        btnagregar = (Button) findViewById(R.id.btnagregar);
        btnfoto = (Button) findViewById(R.id.btnfoto);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddPersona();
                /*
                Intent intent = new Intent(MainActivity.this, ActivitySegunda.class);

                intent.putExtra("nombres", nombres.getText().toString());
                intent.putExtra("apellidos", apellidos.getText().toString());
                intent.putExtra("edad", edad.getText().toString());

                startActivity(intent);
                 */

            }
        });


        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Permisos();
            }
        });



    }

    private void Permisos() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.CAMERA}, PERMISO_CAMARA);
        }
        else
        {
            OpenCamara();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if(requestCode == PERMISO_CAMARA)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                OpenCamara();
            }
            else
            {
                Toast.makeText(this, "Permiso de Camara denegado "  , Toast.LENGTH_LONG).show();
            }
        }
    }

    private void OpenCamara() {
    }

    private void AddPersona()
    {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBNAME, null, 1);
        SQLiteDatabase db =  conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad,Integer.parseInt(edad.getText().toString()));
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.foto, "");

        long resultado = db.insert(Transacciones.TablePersonas, null, valores);

        if(resultado > 0)
        {
            Toast.makeText(this, "Registro Ingresado " + resultado , Toast.LENGTH_LONG).show();
            Clean();
        }
        else
        {
            Toast.makeText(this, "Error no ingreso " + resultado , Toast.LENGTH_LONG).show();
            Clean();
        }

    }

    private void Clean()
    {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
    }
}