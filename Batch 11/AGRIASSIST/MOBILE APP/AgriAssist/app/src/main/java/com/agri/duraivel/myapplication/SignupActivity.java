package com.agri.duraivel.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    EditText names,phn,address,pass,mail;
    Button signup;
    RequestQueue SQueue;
    SharedPreferences sharedPreferences;
    String username;
    AlertDialog alert;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        SQueue= Volley.newRequestQueue(this);
        names=(EditText)findViewById(R.id.fn);
        phn=(EditText)findViewById(R.id.Phoneno);
        address=(EditText)findViewById(R.id.address);
        pass=(EditText)findViewById(R.id.pass);
        mail=(EditText)findViewById(R.id.emailid);
        signup=(Button)findViewById(R.id.but);
        pd=new ProgressDialog(SignupActivity.this);
        pd.setMessage("Registering your details");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(names.getText().toString().trim().equals(""))
                {
                    names.setError("Name Cannot be Empty");}

                else if(address.getText().toString().trim().equals(""))
                {
                    address.setError("Street Cannot be Empty");
                }
                else if(phn.getText().toString().trim().equals(""))
                {
                    phn.setError("Phone No Cannot be Empty");
                }
                else if(pass.getText().toString().trim().equals(""))
                {
                    pass.setError("Password Cannot be Empty");
                }
                else
                {
                    String name= names.getText().toString();
                    String phon= phn.getText().toString();
                    String addres=address.getText().toString();
                    String passwrd=pass.getText().toString();
                    String mailid=mail.getText().toString();
                    signup(name,phon,addres,passwrd,mailid);
                }
            }
        });
    }




    void signup(final String fname, final String phonenum, final String address, final String password,final String mail)
    {
        String url = "192.168.43.169";
        sharedPreferences = getApplicationContext().getSharedPreferences("sp", Context.MODE_MULTI_PROCESS); // 0 - for private mode
        if(sharedPreferences.contains("ip"))
        {
      url ="192.168.43.169";
        }
        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+url+"/Signup.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                builder.setMessage("You Have Registerd Successfully").setTitle("Info")
                        .setCancelable(false)
                        .setPositiveButton("GO TO LOGIN", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                alert.dismiss();
                                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("mobil", phonenum);
                                i.putExtras(extras);
                                startActivity(i);
                            }
                        });
                alert = builder.create();
                alert.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                String body=null;
                pd.dismiss();
                if (error instanceof NetworkError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                }
                else if (error instanceof ServerError) {
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    message=body;
                }
                else if (error instanceof AuthFailureError)
                {
                    message = "Authentication Failure";
                }
                else if (error instanceof ParseError)
                {
                    message = "Parsing error! Please try again after some time!!";
                }
                else if (error instanceof NoConnectionError)
                {
                    message = "Cannot connect to Internet...Please check your connection!";
                }
                else if (error instanceof TimeoutError)
                {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                builder.setMessage(message).setTitle("Info")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                alert.dismiss();
                            }
                        });
                alert = builder.create();
                alert.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map = new HashMap<String, String>();
                map.put("Username", fname);
                map.put("EmailID", mail);
                map.put("Phoneno", phonenum);
                map.put("Address", address);
                map.put("Passw", password);
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
