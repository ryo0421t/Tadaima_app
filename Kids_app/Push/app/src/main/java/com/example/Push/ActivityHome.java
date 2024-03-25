package com.example.Push;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class ActivityHome extends AppCompatActivity {
    //    グローバル変数
    MyGlobals myGlobals;

    //    プリファレンス
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //        グローバル変数
        myGlobals = (MyGlobals)this.getApplication();

        //        共有プリファレンス
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

//          プリファレンスの準備
        preferences = getSharedPreferences("Preference Name", MODE_PRIVATE);
        editor = preferences.edit();

        if (preferences.getBoolean("Launched", false) == false) {

//            初期起動時の処理
            findViewById(R.id.ButtonStart).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            画面遷移
                            Intent intent = new Intent(ActivityHome.this, SettingWifi.class);
                            startActivity(intent);
                        }
                    }
            );

//          プリファレンスの書き換え
            editor.putBoolean("Launched", true);
            editor.commit();
        } else {

//          二回目以降の処理
            findViewById(R.id.ButtonStart).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //                      共有プリファレンス
                            String s11 = pref.getString("variable0","設定されていません");
                            myGlobals.SEditText0Str = s11;

//                            画面遷移
                            Intent intent = new Intent(ActivityHome.this, ActivityIttekimasu.class);
                            startActivity(intent);
                        }
                    }
            );

        }
    }
}


