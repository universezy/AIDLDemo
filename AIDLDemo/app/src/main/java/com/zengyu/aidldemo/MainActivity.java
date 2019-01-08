package com.zengyu.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private IDemo iDemo = null;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iDemo = IDemo.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iDemo = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void bindService() {
        Intent intent = new Intent();
        // 如果我们给Service添加了用于启动的filter：
        intent.setAction("启动的filter");
        // TODO 使用setComponent或者setPackage来指定Service所在的应用
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void testSending() {
        try {
            iDemo.seyHello("MainActivity");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
