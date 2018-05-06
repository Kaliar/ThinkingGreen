package mx.itesm.thinkinggreen.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
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
    private final int GPS_ON = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        //setTitle("Tiendas ZeroWaste");
        setContentView(R.layout.activity_stores);

        defaultLocation.setAltitude(19.4153107);    // Initialize default coordiantes
        defaultLocation.setLongitude(-99.1804615);
        checkGPSPermissions(); // Check if location access is granted
    }

    private void loadList(){
        Log.i("loadList","cargando cards");
        StoresListFrag fragStoreList = new StoresListFrag(); // Fragment of a Person
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.frameStores, fragStoreList); // Set the PersonFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragTrans.commit(); // Schedule the operation into thread
    }

    private void checkGPSPermissions() {
        int coarsePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int finePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        Log.i("StorActiv CHECKPERMISSO", "PERMISSION GRANTED: " + PackageManager.PERMISSION_GRANTED);
        Log.i("StorActiv CHECKPERMISSO", "COARSE GRANTED: " + coarsePermission);
        Log.i("StorActiv CHECKPERMISSO", "FINE GRANTED: " + finePermission);

        if (coarsePermission == PackageManager.PERMISSION_GRANTED
                && finePermission == PackageManager.PERMISSION_GRANTED) {   // All GPS permissions granted
            Log.i("StorActiv CHECKPERMISSO", "Gonna configure the GPS");
            configureGPS();
        }
        else { // Use default location
            Log.i("StorActiv CHECKPERMISSO", "Permissions rejected, using default location");
            userLocation = defaultLocation;
            loadList();
        }
    }

    private void configureGPS() {
        // Create the GPS
        gps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // GPS is off
        if (!gps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Open Settings
            Log.i("StorActiv confGPS", "GPS is off, ask if wanna turn it on");
            turnOnGPS();
            Log.i("StorActiv confGPS", "Already asked");
        }
        else {    // GPS is on
            getUserLocation();
            loadList();
        }
    }

    private void turnOnGPS() {
        // Ask the user to turn on the GPS
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.strGPSOffStore)
                .setCancelable(false)
                .setPositiveButton(R.string.strYes, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        Log.i("StorActiv TURNGPS", "Opening settings");
                        startActivityForResult(new
                                Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), GPS_ON);
                    }
                })
                .setNegativeButton(R.string.strNo, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        Log.i("StorActiv TURNGPS", "Turning GPS on request rejected");
                        dialog.cancel();
                        userLocation = defaultLocation;
                        loadList();
                    }
                });
        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == GPS_ON) {
            Log.i("onActRes", "Returned from Settings");
            if (gps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.i("onActRes", "GPS is on");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            } else {
                Log.i("onActRes", "GPS is off");
                checkGPSPermissions();
            }
        }
    }

    private void getUserLocation() {
        // Has the permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && gps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            userLocation = gps.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (userLocation == null) {
                userLocation = defaultLocation;
                Toast.makeText(this, R.string.strGPSUnknown, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, R.string.strGPSUpdate, Toast.LENGTH_SHORT).show();
            }
            Log.i("StorActiv", userLocation.getAltitude() + "");
            Log.i("StorActiv", userLocation.getLongitude() + "");
        } else {
            userLocation = defaultLocation;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //Log.i("onLocationChanged","CAMBIO LA UBICACION");
        userLocation = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.i("onProviderEnabled","GPS turned on");
        getUserLocation();
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.i("onProviderDisabled","GPS turned off");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            userLocation = gps.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (userLocation == null) {
                userLocation = defaultLocation;
            }
        }
    }
}
