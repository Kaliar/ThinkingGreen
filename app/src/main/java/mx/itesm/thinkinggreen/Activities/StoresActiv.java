package mx.itesm.thinkinggreen.Activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import mx.itesm.thinkinggreen.Fragments.RestaurantsListFrag;
import mx.itesm.thinkinggreen.Fragments.StoresListFrag;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class StoresActiv extends AppCompatActivity implements LocationListener{

    private LocationManager gps;
    public static Location userLocation;
    protected static final Location defaultLocation = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        //setTitle("Tiendas ZeroWaste");
        setContentView(R.layout.activity_stores);
        checkGPSPermissions();
        Log.i("Restautan onCreaste","toy pidiendo chance");
        //Toast.makeText(this,"AAAAHHH",Toast.LENGTH_LONG).show();

    }

    private void loadList(){
        Log.i("loadList","cargando cards");
        StoresListFrag fragStoreList = new StoresListFrag(); // Fragment of a Person
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.frameStores, fragStoreList); // Set the PersonFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragTrans.commit(); // Schedule the operation into thread
    }

    private void configureGPS() {
        // Create the GPS
        gps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // GPS is off
        if (!gps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Abrir Settings para prender el GPS, no se puede hacer con código
            turnOnGPS();
        }
        getUserLocation();
    }

    private void getUserLocation(){
        // Has the permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,  "CONFIGURANDO GPS" , Toast.LENGTH_LONG).show();
            gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            userLocation = gps.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Log.i("Tienda",userLocation.getLatitude() + "");
            Log.i("Tienda",userLocation.getLongitude() + "");
        }
        else {
            userLocation = defaultLocation;
        }
    }

    private void turnOnGPS() {
        // Ask the user to turn on the GPS
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
                        userLocation = defaultLocation;
                    }
                });
        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    private void checkGPSPermissions() {
        int coarsePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int finePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        Log.i("request Tienda", "PERMISSION GRANTED: " + PackageManager.PERMISSION_GRANTED);
        Log.i("request Tienda:", "COARSE GRANTED: " + coarsePermission);
        Log.i("request Tienda:", "FINE GRANTED: " + finePermission);

        if (coarsePermission == PackageManager.PERMISSION_GRANTED
                && finePermission == PackageManager.PERMISSION_GRANTED){
            configureGPS();
        }
        else{
            // Ask for a permission
            userLocation = defaultLocation;
        }
        loadList();
    }

    @Override
    public void onLocationChanged(Location location) {
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
}
