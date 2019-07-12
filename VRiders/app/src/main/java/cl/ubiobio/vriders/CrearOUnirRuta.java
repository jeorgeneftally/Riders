package cl.ubiobio.vriders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrearOUnirRuta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_o_unir_ruta);
        Button crearRuta=findViewById(R.id.btncrearRuta);
        Button unirseRuta=findViewById(R.id.btnunirseRuta);
        Button ultimaRuta=findViewById(R.id.verrutas);
        crearRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CrearOUnirRuta.this,CrearRuta.class);
                startActivity(intent);
            }
        });
        ultimaRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CrearOUnirRuta.this,nombreRuta.class);
                startActivity(intent);
            }
        });
        unirseRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CrearOUnirRuta.this,Filtros.class);
                startActivity(intent);
            }
        });



    }

}
