package cl.ubiobio.vriders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class puntos extends AppCompatActivity {
    Button accedermapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntos);
        accedermapa=findViewById(R.id.accederalmapa);
        accedermapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  Intent intent=new Intent(puntos.this,TrazarPuntos.class);
               // startActivity(intent);
            }
        });




    }

}
