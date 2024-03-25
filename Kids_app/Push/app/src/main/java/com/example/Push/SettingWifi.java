package com.example.Push;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;


public class SettingWifi extends AppCompatActivity {
    MyGlobals myGlobals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_wifi);
        myGlobals = (MyGlobals)this.getApplication();


//        共有プリファレンス
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final EditText set00 = (EditText) findViewById(R.id.SEditText00);

        findViewById(R.id.ButtonWifiCheck).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                      共有プリファレンス
                        String s0 = pref.getString("variable0","設定されていません");
                        set00.setText(s0);

                    }
                }
        );


        findViewById(R.id.ButtonWifiSave).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                      共有プリファレンス
                        SharedPreferences.Editor e = pref.edit();
                        e.putString("variable0", set00.getText().toString());
                        e.commit();

                        EditText et0 =findViewById(R.id.SEditText00);
                        myGlobals.SEditText0Str = et0.getText().toString();
                    }
                }
        );


        findViewById(R.id.ButtonWifiNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                      画面遷移
                        Intent intent = new Intent(SettingWifi.this, ActivityIttekimasu.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
