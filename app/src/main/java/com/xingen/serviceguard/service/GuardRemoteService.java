package com.xingen.serviceguard.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xingen.serviceguard.GuardInterface;
import com.xingen.serviceguard.utils.ServiceUtils;

/**
 * Created by ${新根} on 2017/12/17.
 * blog博客:http://blog.csdn.net/hexingen
 *
 * 一个远程服务，用于守护，正常远程服务（NormalRemoteService）
 */

public class GuardRemoteService extends Service {
    private final  String TAG=GuardRemoteService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,TAG+" 被创建");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,TAG+" 被开启");
        bindNormalRemoteService();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,TAG+" 被销毁");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,TAG+" 被绑定");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,TAG+" 被解绑");
        return super.onUnbind(intent);
    }
    /**
     * 跨进程通讯
     */
   private final GuardInterface.Stub mBinder=new GuardInterface.Stub() {
        @Override
        public String getGuardServiceName() throws RemoteException {
            return GuardRemoteService.this.TAG;
        }
    };
    /**
     * 建立绑定连接
     */
    private final ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                String guardServiceName=GuardInterface.Stub.asInterface(iBinder).getGuardServiceName();
                Log.i(TAG,TAG+"守护的远程服务是："+guardServiceName);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //被守护的服务进程关闭，重新开启，且绑定
            ServiceUtils.startNormalRemoteService(GuardRemoteService.this);
            bindNormalRemoteService();
        }
    };

    /**
     * 绑定被守护的服务
     */
    private void bindNormalRemoteService() {
        ServiceUtils.bindService(this,"com.xingen.serviceguard.service.NormalRemoteService"
                ,"com.xingen.serviceguard",serviceConnection );
    }
}
