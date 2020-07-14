package com.example.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.library.Utils.Constants;
import com.example.library.Utils.NetWorkUtils;
import com.example.library.listener.NetWorkObserver;
import com.example.library.type.NetType;


public class NetStateReceiver2 extends BroadcastReceiver {
private NetType  netType;
private NetWorkObserver listener;

    public NetStateReceiver2() {
        //初始化
        netType = NetType.NONE;
    }

    public void setListener(NetWorkObserver listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //此方法不能做耗时操作 15sANR异常
        if(intent == null || intent.getAction() == null){
            //异常
            return;
        }
        //处理广播的事件
        if(intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)){
            Log.e(Constants.LOG_TAG,"网络发生了变更");
            netType = NetWorkUtils.getNetType();  //赋值网络类型
            if(NetWorkUtils.isNetWorkAvailable()){
                Log.e(Constants.LOG_TAG,"网络链接成功");
                if(listener!=null){
                    listener.onConnect(netType);
                }
            }else {
                Log.e(Constants.LOG_TAG,"网络断开链接");
                if(listener!=null){
                    listener.onDisConnect();
                }
            }
        }
    }
}
