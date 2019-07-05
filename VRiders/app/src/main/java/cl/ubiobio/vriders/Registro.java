package cl.ubiobio.vriders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Registro extends AppCompatActivity {

    //defin objetos
    EditText TextUsuario;
    EditText Textcontrasena;
    Button UnirseARiders;
    private FirebaseAuth firebaseAuth;
    //definr barra de progreso
    ProgressDialog progressDialog;

    Button cancelarRegistro;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_registro);
        cancelarRegistro=findViewById(R.id.btncancelarRegistro);
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
    }

    private void RegistrarUsuario(){
        //obtener usuario y conteraseña de los cuadros de texto
        String email=TextUsuario.getText().toString().trim();
        String constrasena=Textcontrasena.getText().toString().trim();
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
}