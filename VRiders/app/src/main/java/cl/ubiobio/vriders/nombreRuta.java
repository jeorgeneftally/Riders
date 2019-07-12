package cl.ubiobio.vriders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class nombreRuta extends AppCompatActivity {
    private static final int PEDIR_CAMARA=1;
    private ImageView contenedorImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nombreruta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contenedorImagen=findViewById(R.id.contenedorimagen);

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarCamara();
              /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void iniciarCamara(){
        Intent tomarFoto=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFoto.resolveActivity(getPackageManager())!=null){
            startActivityForResult(tomarFoto,PEDIR_CAMARA);
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent fotodata){
        if(requestCode==PEDIR_CAMARA && resultCode== RESULT_OK){
            Bundle extras=fotodata.getExtras();
            Bitmap imagen= (Bitmap) extras.get("data");
            contenedorImagen.setImageBitmap(imagen);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
