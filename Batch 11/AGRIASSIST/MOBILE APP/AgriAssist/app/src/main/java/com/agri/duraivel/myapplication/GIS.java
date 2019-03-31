package com.agri.duraivel.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GIS extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        LocationSource.OnLocationChangedListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,
LocationListener,
        View.OnClickListener{
        GoogleMap map;
        GoogleApiClient googleApiClient;
   SharedPreferences sharedPreferences;
    public LocationRequest mLocationRequest;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 100000;
    int count=0;
    RequestQueue SQueue;
    ArrayList<String> name,phoneno,address,latitude,longitude;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    public String bestProvider;
    double lati = 10.7905;
    double longi = 78.7047;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gis);
      name=new ArrayList<String>();
      phoneno=new ArrayList<String>();
      address=new ArrayList<String>();
      latitude=new ArrayList<String>();
      longitude=new ArrayList<String>();
      SQueue = Volley.newRequestQueue(this);
      sharedPreferences = getApplicationContext().getSharedPreferences("sp", Context.MODE_MULTI_PROCESS); // 0 - for private mode
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gi, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.tfm) {
            // Handle the camera action
        }
        else if (id == R.id.cstatus)
        {
            Intent a=new Intent(GIS.this,PushCropInfo.class);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        else if (id == R.id.getsuggestion)
        {
            Intent a=new Intent(GIS.this,SuggestionInput.class);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        else if (id == R.id.logout)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent a=new Intent(GIS.this,LoginActivity.class);
            startActivity(a);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finishAffinity();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();



    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
        map.clear();
        loadGPS();
        map.setMaxZoomPreference(14.0f);
        LatLng india = new LatLng(lati, longi);
        getFarmersDetails();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 11.0f));
       if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        map.setMyLocationEnabled(true);

    }

    public  void loadGPS()
    {

    }
    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onClick(View v) {

    }
    protected void createLocationRequest()
    {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }


    void  getFarmersDetails()
    {
        String url = "192.168.43.169";
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+url+"/LatLng.php", new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                //   Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try
                {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray jsonArray =jsonObj.getJSONArray("Farmers"); //To get the inventory as an array
                    int i;

                    for (i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject patient = jsonArray.getJSONObject(i);
                        name.add(patient.getString("Name"));
                        phoneno.add(patient.getString("Phoneno"));
                        address.add(patient.getString("Address"));
                        latitude.add(patient.getString("Latitude"));
                        longitude.add(patient.getString("Longitude"));
                        map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitude.get(i)),Double.parseDouble(longitude.get(i)))));
                      //  Toast.makeText(GIS.this, latitude.get(i), Toast.LENGTH_SHORT).show();

                    }

                }
                catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(),"Error in Parse",Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "No History";
                } else if (error instanceof AuthFailureError) {
                    message = "Authorization has been denied for this request!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! This may failed to update the tarrif details";
                }

            }
        });

        SQueue.add(request);
    }
}
