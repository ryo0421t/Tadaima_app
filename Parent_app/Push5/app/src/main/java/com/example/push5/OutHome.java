package com.example.push5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

public class OutHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_home);
        findViewById(R.id.ButtonGaishutu).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anime));
    }
}