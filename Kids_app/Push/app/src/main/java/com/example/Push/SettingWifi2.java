package com.example.Push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;


public class SettingWifi2 extends AppCompatActivity {
    MyGlobals myGlobals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_wifi2);
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
//                      グローバル変数
//                        EditText set00 = findViewById(R.id.SEditText00);
//                        myGlobals.SEditText1Str = "\"" + set00.getText() + "\"";

                        EditText et0 =findViewById(R.id.SEditText00);
                        myGlobals.SEditText0Str = et0.getText().toString();
                    }
                }
        );

        findViewById(R.id.ButtonWifiNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();
                    }
                }
        );

    }


}
