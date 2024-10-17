package com.example.fitnesstracking;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setText("Welcome to settings");
    }
}
