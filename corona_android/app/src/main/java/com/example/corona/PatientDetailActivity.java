package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PatientDetailActivity extends AppCompatActivity {
    private TextView text;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        intent = getIntent();

        text = (TextView)findViewById(R.id.detail_text);
        text.setText(intent.getExtras().getString("detail"));
    }
}
