package com.example.project10;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class EchoThread implements Runnable {

	private ServerSocket ss = null;

	@Override
	public void run() {
		try {
			ss = new ServerSocket(5370);
			while (!ss.isClosed()) {
				Socket s = ss.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				String line = br.readLine();
				Log.d("try/while/readLine", "FAK Line: " + line);
				OutputStream os = s.getOutputStream();
				DataOutputStream out = new DataOutputStream(os);
				out.writeChars(reverse(line));
				out.close();
				br.close();
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("MyService catch: ", "FAK " + e.getMessage());
		} finally {
			closeSocket();
		}
	}

	public void close() {
		closeSocket();
	}

	private void closeSocket() {
		try {
			ss.close();
		}
		catch (IOException e) {
			Log.d("EchoThread",
					"FAK exception closing socket: " + e.getMessage());
		}
	}
	
	private String reverse(String line){
		return new StringBuilder(line).reverse().toString();
	}
}