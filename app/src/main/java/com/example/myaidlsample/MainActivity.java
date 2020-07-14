package com.example.myaidlsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.library.NetworkManager;
import com.example.library.listener.NetWorkObserver;
import com.example.library.type.NetType;

public class MainActivity extends AppCompatActivity implements NetWorkObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager.getDefault().setListener(this);
    }

    @Override
    public void onConnect(NetType netType) {
        Toast.makeText(this, "连接成功"+netType.name(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDisConnect() {
        Toast.makeText(this, "断开连接", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getDefault().unRegister();

    }
}
