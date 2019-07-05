package cl.ubiobio.vriders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CrearRuta extends AppCompatActivity {
    EditText Descripcion;
    EditText HoradeInicio;
    EditText HoradeReunion;
    EditText fecha;
    Spinner tipodeRuta;
    EditText contrasena;
    Button subircrearRuta;

    DatabaseReference myrootreference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ruta);

        // relacionamos los objetos java con los objetos xml
        Descripcion= findViewById(R.id.campo_description);
        HoradeInicio=findViewById(R.id.campo_inicio);
        HoradeReunion=findViewById(R.id.campo_reunion);
        fecha=findViewById(R.id.campo_fecha);
        tipodeRuta=findViewById(R.id.tipoRuta);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.tiposderuta,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipodeRuta.setAdapter(adapter);
        contrasena=findViewById(R.id.campo_password);
        myrootreference=FirebaseDatabase.getInstance().getReference();

        myrootreference.child("Rutas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot snapshot:dataSnapshot.getChildren()){
                    // traera los datos de los usuarios
                    Log.e("datos",""+snapshot.getValue());
                    myrootreference.child("Rutas").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Rutas listrutas=snapshot.getValue(Rutas.class);
                            String Descripcion=listrutas.getDescripcion();
                            String HoraInicio=listrutas.getHoraInicio();
                            String HoraReunion=listrutas.getHoraReunion();
                            String Fecha=listrutas.getFecha();
                            String TipoRuta=listrutas.getTipoRuta();
                            String Contrasena=listrutas.getContrasena();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        subircrearRuta=findViewById(R.id.btncrearuta);
        subircrearRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textDescripcion=Descripcion.getText().toString().trim();
                String textHoraInicio=HoradeInicio.getText().toString().trim();
                String textHoraReunion=HoradeReunion.getText().toString().trim();
                String textFecha=fecha.getText().toString().trim();
                String texttipodeRuta=tipodeRuta.getSelectedItem().toString();
                String textContrasena=contrasena.getText().toString().trim();
                enviarDatos(textDescripcion, textHoraInicio, textHoraReunion, textFecha, texttipodeRuta, textContrasena);

            }
        });









    }

    private void enviarDatos(String textDescripcion, String textHoraInicio, String textHoraReunion, String textFecha, String texttipodeRuta, String textContrasena) {
        Map<String,Object> datosRuta=new HashMap<>();
        datosRuta.put("Descripcion",textDescripcion);
        datosRuta.put("HoraInicio",textHoraInicio);
        datosRuta.put("HoraReunion",textHoraReunion);
        datosRuta.put("Fecha",textFecha);
        datosRuta.put("TipoRuta",texttipodeRuta);
        datosRuta.put("contrasena",textContrasena);

        myrootreference.child("Rutas").push().setValue(datosRuta);
    }
}