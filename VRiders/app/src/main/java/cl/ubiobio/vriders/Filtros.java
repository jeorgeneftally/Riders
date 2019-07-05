package cl.ubiobio.vriders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cl.ubiobio.vriders.R;

public class Filtros extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtros);
        Button ver1=findViewById(R.id.btnoption1);
        Button ver2=findViewById(R.id.btnoption2);
        Button ver3=findViewById(R.id.btnoption3);
        Button ver4=findViewById(R.id.btnoption4);
        Button ver5=findViewById(R.id.btnoption5);
        Button ver6=findViewById(R.id.btnoption6);
        Button ver7=findViewById(R.id.btnoption7);
        Button ver8=findViewById(R.id.btnoption8);

        ver1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });
        ver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });

        ver3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });
        ver4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });
        ver5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });
        ver6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });

        ver7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });
        ver8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Filtros.this, nombreRuta.class);
                startActivity(intent);

            }
        });


    }
}

