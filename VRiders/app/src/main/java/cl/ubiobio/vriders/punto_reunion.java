package cl.ubiobio.vriders;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class punto_reunion extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    public double reunionLat;
    public double reunionLng;
    FloatingActionButton agregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_reunion);
        agregar=findViewById(R.id.fab);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(punto_reunion.this,CrearRuta.class);
                intent.putExtra("latitud",String.valueOf(reunionLat)); //Envia Latitud a la actividad "CrearRuta"
                intent.putExtra("longitud",String.valueOf(reunionLng)); //Envia Longitud a la actividad "CrearRuta"
                Toast.makeText(getApplicationContext(), "Punto de reunión creado", Toast.LENGTH_SHORT).show();
                startActivity(intent);


            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Controles UI
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        }


        mMap.setOnMapLongClickListener(this); //click largo en el mapa
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Busca y Obtiene la latitud y longitud actual (determinada por gps)
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        // Crea un objeto LatLng con las coordenadas de la posición actual, y mueve la cámara a
        // esa posición, con un zoom de 15
        LatLng miPosicion = new LatLng(location.getLatitude(), location.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miPosicion,15));


    }


    /** cuando se realiza un click prologando (poco más de un segundo),
     * se activará este evento, el cual nos entregará la posición donde se realizo el click prolongado
     * y creará un margador con la información dispuesta a continuación y borrará otros elementos
     * existentes **/
    @Override
    public void onMapLongClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Este será tu punto de reunión");
        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        reunionLat= markerOptions.getPosition().latitude;
        reunionLng= markerOptions.getPosition().longitude;
        mMap.addMarker(markerOptions);
        //Toast.makeText(getApplicationContext(),String.valueOf((int)reunionLat), Toast.LENGTH_SHORT).show();
    }


    //Para los permisos de "Mostrar mi ubicación actual"
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show();
            }

        }
    }
}