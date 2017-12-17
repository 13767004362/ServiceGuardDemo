package com.xingen.serviceguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xingen.serviceguard.utils.ServiceUtils;
/**
 * Created by ${新根} on 2017/12/17.
 * blog博客:http://blog.csdn.net/hexingen
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDoubleRemoteService();
    }

    /**
     * 开启相互守护的两个服务
     */
    private void startDoubleRemoteService(){
        ServiceUtils.startNormalRemoteService(this);
        ServiceUtils.startGuardRemoteService(this);
    };
}
