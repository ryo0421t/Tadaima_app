package com.example.Push;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBInstallation;
import com.nifcloud.mbaas.core.NCMBPush;
import com.nifcloud.mbaas.core.NCMBQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityIttekimasu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NCMB.initialize(this.getApplicationContext(), "e5be8733ade5ada53f5a293fda93d16602c7c2d9d3f0e5c6ab928c2716098b11", "ad84b1d9d8d8dbdcaa8b2a08100768d6619642867d376709ad4723f36facf2e9");
        setContentView(R.layout.activity_ittekimasu);

        findViewById(R.id.ButtonPush).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            sendPush ();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(ActivityIttekimasu.this, ActivityTadaima.class);
                        startActivity(intent);
                    }
                }
        );

        findViewById(R.id.ButtonWifi).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ActivityIttekimasu.this,SettingWifi2.class);
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
        push.setMessage("外出しました");//メッセージ内容
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
        }
        );
    }

}