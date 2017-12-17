package com.xingen.serviceguard.utils;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/**
 * Created by ${新根} on 2017/12/17.
 * blog博客:http://blog.csdn.net/hexingen
 *
 * 开启和绑定服务的工具类
 */

public class ServiceUtils {
    /**
     * 绑定指定包名下的服务
     * @param context
     * @param action
     * @param packageName
     * @param serviceConnection
     */
    public static void bindService(Context context, String action, String packageName, ServiceConnection serviceConnection) {
        Intent intent = new Intent(action);
        intent.setPackage(packageName);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    /**
     * 开启指定包名下的服务
     * @param context
     * @param action
     * @param packageName
     */
    public static  void startService(Context context, String action, String packageName){
        Intent intent = new Intent(action);
        intent.setPackage(packageName);
        context.startService(intent);
    }
    public  static  void  startNormalRemoteService(Context context){
        startService(context,"com.xingen.serviceguard.service.NormalRemoteService"
                ,"com.xingen.serviceguard");
    }
    public  static  void  startGuardRemoteService(Context context){
        startService(context,"com.xingen.serviceguard.service.GuardRemoteService"
                ,"com.xingen.serviceguard");
    }
}
