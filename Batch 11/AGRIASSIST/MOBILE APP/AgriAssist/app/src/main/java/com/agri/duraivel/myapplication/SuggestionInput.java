package com.agri.duraivel.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class SuggestionInput extends AppCompatActivity {
    Spinner district, crop, season, periods;
    String districtArray[] = {"ERODE", "PUDUKKOTTAI", "THANJAVUR", "TIRUCHIRAPPALLI"};
    String cropArray[] = {"Banana", "Onion", "Rice"};
    String seasonArray[] = {"Autumn", "Kharif", "Whole Year"};
    String periodsArray[] = {"120-180", "15-20", "180-230", "20-30", "230-270", "30-60", "60-70", "70-80", "80-90", "90-120"};
    EditText edCropArea;
    Button btnSug;
    RequestQueue SQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_input);
        district = (Spinner) findViewById(R.id.district);
        edCropArea = (EditText) findViewById(R.id.cropArea);
        SQueue = Volley.newRequestQueue(this);
        btnSug = (Button) findViewById(R.id.but);
        crop = (Spinner) findViewById(R.id.crop);
        season = (Spinner) findViewById(R.id.season);
        periods = (Spinner) findViewById(R.id.periods);
        ArrayAdapter aDistrict = new ArrayAdapter(this, android.R.layout.simple_spinner_item, districtArray);
        aDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(aDistrict);

        ArrayAdapter aCrops = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cropArray);
        aDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crop.setAdapter(aCrops);

        ArrayAdapter aSeason = new ArrayAdapter(this, android.R.layout.simple_spinner_item, seasonArray);
        aSeason.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        season.setAdapter(aSeason);

        ArrayAdapter aPeriods = new ArrayAdapter(this, android.R.layout.simple_spinner_item, periodsArray);
        aSeason.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periods.setAdapter(aPeriods);

        btnSug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSuggestion(String.valueOf(district.getSelectedItemPosition()), String.valueOf(crop.getSelectedItemPosition()), String.valueOf(season.getSelectedItemPosition()), String.valueOf(periods.getSelectedItemPosition()), edCropArea.getText().toString());
            }
        });


    }

    public void getSuggestion(String district, String crop, String season, String periods, String cropArea) {
        Toast.makeText(getApplicationContext(), district + crop + season + periods, Toast.LENGTH_LONG).show();
        String url = "192.168.43.169:8080";
        StringRequest request = new StringRequest(Request.Method.GET, "http://" + url + "/polls/?dist=" + district + "&crop=" + crop + "&season=" + season + "&period=" + periods + "&carea=" + cropArea, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                Intent i = new Intent(SuggestionInput.this, FarmSuggestion.class);
                i.putExtra("Disease", response);

                startActivity(i);
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
                    message = "";
                }
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
        SQueue.add(request);
    }
}
