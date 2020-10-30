package com.example.a19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String text = intent.getStringExtra("texto");

        TextView texto = findViewById(R.id.text_header);
        texto.setText(text);
    }

    public void yes(View view) {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    public void no(View view) {
        Intent i = new Intent();
        setResult(RESULT_CANCELED, i);
        finish();
    }
}