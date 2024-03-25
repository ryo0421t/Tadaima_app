package com.example.Push;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.TextView;
import android.widget.Toast;

import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBInstallation;
import com.nifcloud.mbaas.core.NCMBPush;
import com.nifcloud.mbaas.core.NCMBQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityTadaima extends AppCompatActivity {
    TextView textView;
    WifiManager wifiManager;
    WifiInfo connection;
    String display;
    String MySSID;

    //    グローバル変数
    MyGlobals myGlobals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NCMB.initialize(this.getApplicationContext(), "e5be8733ade5ada53f5a293fda93d16602c7c2d9d3f0e5c6ab928c2716098b11", "ad84b1d9d8d8dbdcaa8b2a08100768d6619642867d376709ad4723f36facf2e9");
        setContentView(R.layout.activity_tadaima);
        //        グローバル変数
        myGlobals = (MyGlobals)this.getApplication();
        textView = findViewById(R.id.TextView1);
        MySSID = "\"" + myGlobals.SEditText0Str +"\"";

//      Wifi
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},
                    1000);
        }

        findViewById(R.id.ButtonPush2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Wifi
                        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                        connection = wifiManager.getConnectionInfo();
                        display = connection.getSSID();
                        textView.setText(display);

                        TextView tv2 = findViewById(R.id.TextView2);
                        tv2.setText(MySSID);


                        if (MySSID.equals(((WifiInfo) connection).getSSID())){
                            try {


                                sendPush ();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(ActivityTadaima.this, ActivityIttekimasu.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(ActivityTadaima.this, "自宅のWi-Fiに接続してください", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        findViewById(R.id.ButtonWifi2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ActivityTadaima.this,SettingWifi2.class);
                        startActivity(intent);
                    }
                }
        );
    }
    private void sendPush () throws JSONException{
        JSONObject data = new JSONObject();
        NCMBPush push = new NCMBPush();
        NCMBQuery<NCMBInstallation>query=new NCMBQuery<>("installation");
        query.whereEqualTo("channels","Ch2");//”CH1に送る
        push.setSearchCondition(query);
        push.setTarget(new JSONArray("[android]"));//送り先指定
        push.setAction("com.example.push.RECEIVE_PUSH");
        push.setTitle("ただいまer");//メッセージタイトル
        push.setMessage("帰宅しました");//メッセージ内容
        push.setDialog(true);
        push.sendInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if (e != null) {
                    // エラー処理
                } else {
                    // プッシュ通知登録後の操作
                }
            }
        });
    }

}