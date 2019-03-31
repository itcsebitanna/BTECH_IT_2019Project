package com.agri.duraivel.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button b1;
    String usernmae;
    String passw;
    EditText unma, passwrd;
    ProgressDialog pd;
    TextView signup;
    RequestQueue SQueue;
    SharedPreferences sharedPreferences;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SQueue = Volley.newRequestQueue(this);
        sharedPreferences = getSharedPreferences("sp", Context.MODE_MULTI_PROCESS);
        b1 = (Button) findViewById(R.id.bt);
        unma = findViewById(R.id.e1);
        pd = new ProgressDialog(LoginActivity.this);
        passwrd = findViewById(R.id.e2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernmae = unma.getText().toString();
                passw = passwrd.getText().toString();
                if (!usernmae.equals("") && !passw.equals("")) {
                    pd.setMessage("Checking Credentials");
                    pd.setCanceledOnTouchOutside(false);
                    pd.setCancelable(false);
                    pd.show();
                    try {
                        login(usernmae, passw);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                } else {
                    pd.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Incorrect Username or Password").setTitle("Info")
                            .setCancelable(false)
                            .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                    alert.dismiss();
                                }
                            });
                    alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    public void login(final String username, final String passwd) throws UnknownHostException {
        String url = "192.168.43.169";

        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.169/Login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Intent i = new Intent(LoginActivity.this, GIS.class);
                Bundle extras = new Bundle();
                // extras.putString("mobil", phonenum);
                i.putExtras(extras);
                startActivity(i);
                finishAffinity();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", usernmae);
                editor.putString("password", passw);
                editor.putString("flag", "1");
                editor.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                String body = null;
                pd.dismiss();
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
                    message = "Incorrect Username or Password!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Inte...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(message).setTitle("Info")
                        .setCancelable(false)
                        .setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                alert.dismiss();

                            }
                        });
                alert = builder.create();
                alert.show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("Farmer_ID", username);
                map.put("Password", passwd);
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
