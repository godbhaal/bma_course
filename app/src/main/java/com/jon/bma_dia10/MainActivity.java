package com.jon.bma_dia10;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private Marker marker;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Per saber on esta l'usuari
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // Cada 30 segons, si canvia 50metres
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 50, this);

        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, mapFragment);
        ft.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Barcelona
        LatLng latLng = new LatLng(41.388532, 2.167927);
        int zoom = 16;
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        googleMap.animateCamera(update);
        googleMap.setOnMapClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // Perque cada cop que posis un marker, elimini l'anterio
        if (marker != null) {
            marker.remove();
        }
        MarkerOptions options = new MarkerOptions().position(latLng);
        marker = googleMap.addMarker(options);

    }

    // La important
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Latitude: " + location.getLatitude() + " Long: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 16);

        // Detecta abans el LocationChanged que el onMapReady...
        try {
            googleMap.animateCamera(update);
        } catch (NullPointerException e){
            Log.e("NullPointerExeption", "D'ont worry");
        }

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
