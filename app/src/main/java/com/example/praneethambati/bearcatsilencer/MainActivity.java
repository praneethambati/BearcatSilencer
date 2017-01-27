package com.example.praneethambati.bearcatsilencer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements LocationListener {

    Button locationBTN;
    EditText locationsSelectedET;
    TextView locationsValues;
    LocationManager locationManager;
    String provider;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationBTN = (Button) findViewById(R.id.locationBTN);

        locationsValues = (TextView) findViewById(R.id.locationsValues);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        try {
          location  = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        catch (SecurityException e){

        }

        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
           // latituteField.setText("Location not available");
           // longitudeField.setText("Location not available");
        }

        locationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LocationsActivity.class);
                startActivityForResult(i,1);
            }
        });




    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        locationsSelectedET = (EditText) findViewById(R.id.editText);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String stredittext=data.getStringExtra("locations");
                locationsSelectedET.setText(stredittext);

            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        locationsValues = (TextView) findViewById(R.id.locationsValues);
        locationsValues.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
