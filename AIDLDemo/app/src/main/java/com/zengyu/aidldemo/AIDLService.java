package com.zengyu.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
    private final IDemo.Stub mBinder = new IDemo.Stub() {
        @Override
        public String seyHello(String from) throws RemoteException {
            return "Hello client, I receive your msg: " + from;
        }
    };

    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
