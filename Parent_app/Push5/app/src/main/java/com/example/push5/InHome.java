package com.example.push5;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

public class InHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_home);
        findViewById(R.id.ButtonZaitaku).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anime));
    }
}