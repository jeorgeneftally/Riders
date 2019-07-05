package cl.ubiobio.vriders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IniciarSesion extends AppCompatActivity {
    Button Registarse;
    Button iniciarSesion;
    EditText usuario;
    EditText contrasena;
    private FirebaseAuth firebaseAuth;
    //definr barra de progreso
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        Registarse=findViewById(R.id.btnRegistrarUsuario);
        Registarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IniciarSesion.this,Registro.class);
                startActivity(intent);
            }
        });
        iniciarSesion=findViewById(R.id.iniciarSesion);
        usuario=findViewById(R.id.Usuario);
        contrasena=findViewById(R.id.contraseña);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSesion();
            }
        });

    }

    private void IniciarSesion(){
        //obtener usuario y conteraseña de los cuadros de texto
        String email=usuario.getText().toString().trim();
        String constrasena=contrasena.getText().toString().trim();
        // verificar que los cuadros de texto no esten vacios
        if (TextUtils.isEmpty(email)){
            Toast.makeText(IniciarSesion.this,"se ha registrado el usuario",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(constrasena)){
            Toast.makeText(IniciarSesion.this,"se ha registrado la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();
        // consultar si el usuario existe
        firebaseAuth.signInWithEmailAndPassword(email,constrasena).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //revisando si se realizo con exito
                        if (task.isSuccessful()){
                            Toast.makeText(IniciarSesion.this,"Bienvenido",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(IniciarSesion.this,CrearOUnirRuta.class);
                            startActivity(intent);
                        }else{
                            //revisar si se presentan colisiones

                            Toast.makeText(IniciarSesion.this,"no existe el usuario",Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }

}
