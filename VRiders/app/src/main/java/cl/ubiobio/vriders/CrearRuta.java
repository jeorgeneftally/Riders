package cl.ubiobio.vriders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CrearRuta extends AppCompatActivity {
    EditText nombre;
    EditText HoradeInicio;
    EditText HoradeReunion;
    EditText fecha;
    ProgressDialog progressDialog;
    Button subircrearRuta;
    Button cancelarSolicitud;
    //objeto que instancia la base de datos
    DatabaseReference myrootreference;

    Button puntoInicio;

    public String reunionLat="";
    public String reunionLng="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ruta);

        puntoInicio=findViewById(R.id.btnPuntoInicio);
        puntoInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CrearRuta.this,punto_reunion.class);
                startActivity(intent);
            }
        });




        //Recibe las coordenadas desde la actividad "punto_reunion"
        Intent mIntent= getIntent();
        reunionLat= mIntent.getStringExtra("latitud");
        reunionLng= mIntent.getStringExtra("longitud");

        //Toast.makeText(getApplicationContext(), reunionLat, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), reunionLng, Toast.LENGTH_SHORT).show();




        cancelarSolicitud=findViewById(R.id.btncancelarRegistro);
        progressDialog=new ProgressDialog(this);
        // relacionamos los objetos java con los objetos xml
        nombre = findViewById(R.id.campo_description);
        HoradeInicio=findViewById(R.id.campo_inicio);
        HoradeReunion=findViewById(R.id.campo_reunion);
        fecha=findViewById(R.id.campo_fecha);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.tiposderuta,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myrootreference=FirebaseDatabase.getInstance().getReference();


        myrootreference.child("Rutas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot snapshot:dataSnapshot.getChildren()){
                    // traera los datos de los usuarios
                    Log.e("datos",""+snapshot.getValue());
                    // refetcia a la tabla rutas
                    myrootreference.child("Rutas").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // obtener los datos de kas rutas
                            Rutas listrutas=snapshot.getValue(Rutas.class);
                            String nombre=listrutas.getNombre();
                            String HoraInicio=listrutas.getHoraInicio();
                            String HoraReunion=listrutas.getHoraReunion();
                            String Fecha=listrutas.getFecha();
                            String id_Usuario=listrutas.getId_Usuario();

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

       // cancelarSolicitud.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
         //       Intent intent= new Intent(CrearRuta.this,CrearOUnirRuta.class);
           //     startActivity(intent);
            //}
        //});





        subircrearRuta=findViewById(R.id.btncrearuta);
        subircrearRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Realizando consulta");
                progressDialog.show();
                //gaurdar los datos de los cuadros de texto en variables
                String textDescripcion= nombre.getText().toString().trim();
                String textHoraInicio=HoradeInicio.getText().toString().trim();
                String textHoraReunion=HoradeReunion.getText().toString().trim();
                String textFecha=fecha.getText().toString().trim();
                String textIdUsuario=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //verificar que los campos no esten vacias y enviar mensajes correspondientes
                if (TextUtils.isEmpty(textDescripcion)|| TextUtils.isEmpty(textHoraInicio) || TextUtils.isEmpty(textHoraReunion) ||
                        TextUtils.isEmpty(textFecha)){
                    Toast.makeText(CrearRuta.this,"por favor complete todos los campos",Toast.LENGTH_LONG).show();


                }else{
                    //si los campos son validos llamara a la funcion encargada de enviar los datos a la fireabase
                    Toast.makeText(CrearRuta.this,"Ruta creada con exito",Toast.LENGTH_LONG).show();
                    enviarDatos(textDescripcion, textHoraInicio, textHoraReunion, textFecha,textIdUsuario,reunionLat,reunionLng);


                }
                progressDialog.dismiss();


            }
        });









    }

    private void enviarDatos(String textDescripcion, String textHoraInicio, String textHoraReunion, String textFecha,String textIdUsuario,String reunionLat,String reunionLng ) {
        //mapear los datos en un objeto ruta
        Map<String,Object> datosRuta=new HashMap<>();
        datosRuta.put("nombre",textDescripcion);
        datosRuta.put("HoraInicio",textHoraInicio);
        datosRuta.put("HoraReunion",textHoraReunion);
        datosRuta.put("Fecha",textFecha);
        datosRuta.put("id_Usuario",textIdUsuario);
        datosRuta.put("latitud",reunionLat);
        datosRuta.put("longitud",reunionLng);
        //enviar los datos a la firebase
        myrootreference.child("Rutas").push().setValue(datosRuta);
        Intent intent=new Intent(CrearRuta.this,CrearOUnirRuta.class);
        startActivity(intent);

    }
}