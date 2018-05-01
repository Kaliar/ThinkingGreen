package mx.itesm.thinkinggreen.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Fragments.RestaurantsListFrag;
import mx.itesm.thinkinggreen.Fragments.StoresListFrag;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class RestaurantsActiv extends AppCompatActivity implements LocationListener {

    private LocationManager gps;
    private final int GPS = 10;
    private Location userLocation;
    private double userLatitude;
    private double userLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        RestaurantsListFrag fragRestList = new RestaurantsListFrag(); // Fragment of a Person
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.frameRestaurants, fragRestList); // Set the PersonFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragTrans.commit(); // Schedule the operation into thread
        //Toast.makeText(this,"AAAAHHH",Toast.LENGTH_LONG).show();
    }

    private void configureGPS() {
        // Crea el administrador del gps
        gps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Pregunta si está prendido el GPS en el sistema
        if (!gps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Abrir Settings para prender el GPS, no se puede hacer con código
            turnOnGPS();
        }
    }

    private void turnOnGPS() {
        // El usuario lo debe encender, no se puede con programación
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.strGPSOff)
                .setCancelable(false)
                .setPositiveButton(R.string.strYes, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new
                                Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)); // Abre settings
                    }
                })
                .setNegativeButton(R.string.strNo, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == GPS && grantResults.length>0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Contestó que SI, otorga el permiso. Iniciar actualizaciones.
                gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

            } else {
                Log.i("onRequestPerm...","Contestó NO, indicarle cómo dar permiso...");
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("onLocationChanged-Place",location.getLatitude() + "");
        Log.i("onLocationChanged-Place",location.getLongitude() + "");
        userLocation = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    //TODO: Link RecyclerView with fragments
}
