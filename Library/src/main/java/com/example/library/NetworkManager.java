package com.example.library;

import android.app.Application;
import android.content.IntentFilter;

import com.example.library.Utils.Constants;
import com.example.library.listener.NetWorkObserver;

//网络管理类
public class NetworkManager {

    private static volatile NetworkManager instance;
    private Application application;
    private NetStateReceiver2 receiver2;

    private NetworkManager() {
        receiver2 = new NetStateReceiver2();
    }

    public void setListener(NetWorkObserver listener) {
        if(receiver2!=null){
            receiver2.setListener(listener);
        }
    }

    public static NetworkManager getDefault() {
        if (instance == null) {
            synchronized (NetworkManager.class) {
                if (instance == null) {
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }

    public Application getApplication(){
        if(application == null){
            throw new RuntimeException("不能为空");
        }
        return application;
    }
    public void init(Application application){
        this.application = application;
        //动态广播注册（7.0兼容）
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
        application.registerReceiver(receiver2,filter);
    }

    public void unRegister() {
        application.unregisterReceiver(receiver2);
    }
}
