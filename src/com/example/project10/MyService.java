/*
 * Faith-Anne Kocadag
 * Assignment 10
 * April 23, 2014
 */

package com.example.project10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	private EchoThread echoThread;
	
	@Override
	public void onCreate() {
		Log.d("MyService", "FAK onCreate called");
		super.onCreate();
		Toast.makeText(this, "Starting Server", Toast.LENGTH_LONG).show();
		echoThread = new EchoThread();
		Thread th = new Thread(echoThread);
		th.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		Log.d("MyService", "FAK onDestroy called");
		super.onDestroy();
		Toast.makeText(this, "Stopping Server", Toast.LENGTH_LONG).show();
		echoThread.close();
	}
}