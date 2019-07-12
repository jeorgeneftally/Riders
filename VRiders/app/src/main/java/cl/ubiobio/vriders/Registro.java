package cl.ubiobio.vriders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    public String user1,pass2;
    //defin objetos
    EditText TextUsuario;
    EditText Textcontrasena;
    Button UnirseARiders;
    private FirebaseAuth firebaseAuth;
    //definr barra de progreso
    ProgressDialog progressDialog;
    DatabaseReference myrootreference;
    Button cancelarRegistro;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_registro);
        cancelarRegistro=findViewById(R.id.btncancelarRegistro);
        myrootreference= FirebaseDatabase.getInstance().getReference();
        cancelarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registro.this,IniciarSesion.class);
                startActivity(intent);
            }
        });
        UnirseARiders=findViewById(R.id.registroBtn);
        TextUsuario=findViewById(R.id.nuevo_email);
        Textcontrasena=findViewById(R.id.nuevo_pass);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        UnirseARiders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });

        myrootreference=FirebaseDatabase.getInstance().getReference();










    }

    private void RegistrarUsuario(){
        //obtener usuario y conteraseña de los cuadros de texto
        String email=TextUsuario.getText().toString().trim();
        String constrasena=Textcontrasena.getText().toString().trim();
        user1=email;
        pass2=constrasena;
        // verificar que los cuadros de texto no esten vacios
        if (TextUtils.isEmpty(email)){
            Toast.makeText(Registro.this,"se ha registrado el usuario",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(constrasena)){
            Toast.makeText(Registro.this,"se ha registrado la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();
        // creando nuevo usuario

        firebaseAuth.createUserWithEmailAndPassword(email,constrasena).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //revisando si se realizo con exito
                        if (task.isSuccessful()){
                            enviardatos();
                            Toast.makeText(Registro.this,"se ha registrado el usuario",Toast.LENGTH_LONG).show();


                        }else{
                            //revisar si se presentan colisiones
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(Registro.this,"El usuarioi ya existe",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(Registro.this,"no se pudo registrar el usuario",Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });


    }

    private void enviardatos() {
        enviarDatosUsuario(user1,pass2);
    }

    private void enviarDatosUsuario(String email, String constrasena) {
        Map<String,Object> datosUsuarios=new HashMap<>();
        datosUsuarios.put("Email",email);
        datosUsuarios.put("Password",constrasena);
        datosUsuarios.put("Name","");
        datosUsuarios.put("telefono","");
        datosUsuarios.put("id_Usuario",FirebaseAuth.getInstance().getCurrentUser().getUid());
        //enviar los datos a la firebase
        myrootreference.child("Usuarios").push().setValue(datosUsuarios);
    }
}
