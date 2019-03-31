package com.agri.duraivel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IPEnter extends AppCompatActivity {
Button contin;
EditText ip;
String getIp;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipenter);
        sharedPreferences=getApplicationContext().getSharedPreferences("sp", Context.MODE_MULTI_PROCESS); // 0 - for private mode

        contin=(Button)findViewById(R.id.contin);
   ip=(EditText)findViewById(R.id.ipadd);
   contin.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           getIp=ip.getText().toString();
           if(!getIp.equals(""))
           {
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("ip",getIp);
               editor.commit();
               Intent i = new Intent(IPEnter.this,LoginActivity.class);
               startActivity(i);
               finishAffinity();
           }
       }
   });

    }
}
