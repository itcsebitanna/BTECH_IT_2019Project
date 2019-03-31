package com.agri.duraivel.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import com.google.android.gms.maps.model.SquareCap;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PushCropInfo extends AppCompatActivity {
    EditText cropID, cropName, cropSeason, cropDisease, cropPesticides, cropRemedies, cropArea;
    RequestQueue SQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_info);
        SQueue = Volley.newRequestQueue(this);
        cropID = findViewById(R.id.cropID);
        cropName = findViewById(R.id.cropName);
        cropSeason = findViewById(R.id.cropSeason);
        cropDisease = findViewById(R.id.cropDisease);
        cropPesticides = findViewById(R.id.cropPesticide);
        cropRemedies = findViewById(R.id.cropRemedies);
        cropArea = findViewById(R.id.cropArea);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        populateCropInfo();

    }

    private void populateCropInfo() {
        CropInfoModel cropInfoModel = new CropInfoModel();
        cropInfoModel.setCropID(cropID.getText().toString().trim());
        cropInfoModel.setCropArea(cropArea.getText().toString().trim());
        cropInfoModel.setCropDisease(cropDisease.getText().toString().trim());
        cropInfoModel.setCropPesticides(cropPesticides.getText().toString().trim());
        cropInfoModel.setCropName(cropName.getText().toString().trim());
        cropInfoModel.setCropSeason(cropSeason.getText().toString().trim());
        cropInfoModel.setCropRemedies(cropRemedies.getText().toString().trim());
        pushCropInfo(cropInfoModel);
    }

    private void pushCropInfo(final CropInfoModel cropInfoModel) {
        String url = "192.168.43.169";
        StringRequest request = new StringRequest(Request.Method.POST, "http://" + url + "/pushCropData.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent homeIntent = new Intent(PushCropInfo.this, GIS.class);
                startActivity(homeIntent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                String body = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    message = body;
                } else if (error instanceof AuthFailureError) {
                    message = "Authentication Failure";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("CropID", cropInfoModel.getCropID());
                map.put("CropName", cropInfoModel.getCropName());
                map.put("CropArea", cropInfoModel.getCropArea());
                map.put("CropDisease", cropInfoModel.getCropDisease());
                map.put("CropPesticide", cropInfoModel.getCropDisease());
                map.put("CropRemedies", cropInfoModel.getCropRemedies());
                map.put("CropSeason", cropInfoModel.getCropSeason());
                return map;
            }
        };
        SQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                2000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}

