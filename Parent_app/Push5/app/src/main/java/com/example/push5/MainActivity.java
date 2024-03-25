package com.example.push5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBInstallation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity<setChannel> extends AppCompatActivity {

    TextView _message;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NCMB.initialize(this.getApplicationContext(), "e5be8733ade5ada53f5a293fda93d16602c7c2d9d3f0e5c6ab928c2716098b11", "ad84b1d9d8d8dbdcaa8b2a08100768d6619642867d376709ad4723f36facf2e9");
        setContentView(R.layout.activity_main);

        String go="外出しました";
        String home="帰宅しました";
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final TextView set00 = (TextView) findViewById(R.id.txtMessage);


        findViewById(R.id.ButtonStart).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setChannel();
                        Toast.makeText(MainActivity.this,"チャンネルを設定しました。",Toast.LENGTH_SHORT).show();

//                        FirebaseMessagingService a=new  CustomFirebaseMessagingService();
//                        a.onMessageReceived();
                    }
                }
        );
        findViewById(R.id.ButtonCheck).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        SharedPreferences.Editor c = pref.edit();
//                        e.putString("variable1",set00 .getText().toString());
//                        e.commit();


                        String s1 = pref.getString("variable1","null");


                        if (s1.equals(go)){
                            Intent intent =new Intent(MainActivity.this, OutHome.class);
                            startActivity(intent);
                        }else if(s1.equals(home)){
                            Intent intent =new Intent(MainActivity.this, InHome.class);
                            startActivity(intent);
                        }else  {
                            Toast.makeText(MainActivity.this,"通知が来ていません",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }
    @Override
    public void onResume() {
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        super.onResume();
        //**************** ペイロード、リッチプッシュを処理する ***************
        Intent intent = getIntent();

        //プッシュ通知IDを表示
        SharedPreferences s =getSharedPreferences( "name_and_age", Context.MODE_PRIVATE );

        _message = (TextView) findViewById(R.id.txtMessage);
        message = intent.getStringExtra("message");
        _message.setText(message);
//        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        if(_message.getText().equals("")==false) {
            SharedPreferences.Editor c = pref.edit();
            c.putString("variable1", _message.getText().toString());
            c.commit();
        }

        //プッシュ通知のペイロードを表示
        if (intent.getStringExtra("com.nifcloud.mbaas.Data") != null) {
            try {
                JSONObject json = new JSONObject(intent.getStringExtra("com.nifcloud.mbaas.Data"));

                if (json != null) {

                }
            } catch (JSONException e) {
                //エラー処理
            }
        }
        intent.removeExtra("com.nifcloud.mbaas.RichUrl");
    }
    private void setChannel(){//チャンネル設定　ch1
        NCMBInstallation installation = NCMBInstallation.getCurrentInstallation();
        JSONArray channels = new JSONArray();
        channels.put("Ch2");
        installation.setChannels(channels);
        installation.saveInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if (e != null) {
                    //エラー発生時の処理
                } else {
                    //成功時の処理

            }}
        });
    }
}