package cl.ubiobio.vriders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class buscarruta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscarruta);
        Button btnsearchruta=findViewById(R.id.btnsearchrutas);
        btnsearchruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(buscarruta.this,Filtros.class);
                startActivity(intent);
            }
        });

    }

}
