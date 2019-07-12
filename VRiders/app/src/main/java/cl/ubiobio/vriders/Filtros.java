package cl.ubiobio.vriders;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Filtros extends AppCompatActivity {
    RecyclerView contenedorRutas;
    List<Rutas> lasrutas =new ArrayList<>();
    Adapter adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtros);
        contenedorRutas =findViewById(R.id.contenedor);
        contenedorRutas.setHasFixedSize(true);
        contenedorRutas.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(lasrutas);
        contenedorRutas.setAdapter(adapter);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Rutas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Rutas rutas=snapshot.getValue(Rutas.class);
                    lasrutas.add(rutas);
                }
                adapter=new Adapter(lasrutas);
                contenedorRutas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

