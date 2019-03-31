package com.agri.duraivel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FarmSuggestion extends AppCompatActivity {
    TextView disTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_suggestion);
        disTextview = findViewById(R.id.disname);
        Intent i = getIntent();

        String disease = i.getStringExtra("Disease");
        String diseaseArray[] = {"Anthracnose", "Bacterial Leaf Blight", "Banana bract mosaic virus (BBMV)", "Banana streak disease (BSV)", "black sigatoka", "Blast", "Brownspot", "Bunchy top/curly top", "Colletotrichum blight", "Damping off", "False Smut", "Fusarium basal rot/basal rot", "Grain discolouration", "Infectious chlorosis (CMV)", "Iris yellow spot disease", "Leaf Streak", "Moko disease/bacterial wilt", "Mycosphaerella leaf spot", "Onion yellow dwarf disease", "Panama wilt", "Pink root rot", "Purple blotch", "Rice tungro disease", "Sheath Blight", "Stemphylium leaf blight", "Tip over or bacterial soft rot", "White rot (Sclerotial rot)", "yellow sigatoka,"};
        Toast.makeText(getApplicationContext(), disease, Toast.LENGTH_LONG).show();
        String diseaseName = diseaseArray[Integer.parseInt(disease)];
        disTextview.setText(diseaseName);
    }
}
