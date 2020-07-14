package com.example.library.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.library.NetworkManager;
import com.example.library.type.NetType;

public class NetWorkUtils {
    /**
     * 网络是否可用
     * @return
     */
    public static boolean isNetWorkAvailable(){
        ConnectivityManager connMrg = (ConnectivityManager) NetworkManager.getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connMrg == null){
            return false;
        }
        //返回所有网络信息
        NetworkInfo[] infos = connMrg.getAllNetworkInfo();
        if(infos != null){
            for (NetworkInfo info : infos) {
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
    //获取当前网络类型
    public static NetType getNetType(){
        ConnectivityManager connMrg = (ConnectivityManager) NetworkManager.getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connMrg == null){
            return NetType.NONE;
        }
        NetworkInfo networkInfo = connMrg.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE;
        }
        int nType = networkInfo.getType();
        if(nType == ConnectivityManager.TYPE_MOBILE){
            if(networkInfo.getExtraInfo().toLowerCase().equalsIgnoreCase("cmnet")){
                return NetType.CMENT;
            }else {
                return NetType.CMWAP;
            }
        }else if(nType == ConnectivityManager.TYPE_WIFI){
            return NetType.WIFI;
        }
        return NetType.NONE;
    }
}
