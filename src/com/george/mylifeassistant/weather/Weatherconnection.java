package com.george.mylifeassistant.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Message;
import android.widget.Toast;

public class Weatherconnection {

	HttpURLConnection httpconn;
	InputStream inputs;
	String readline;
	BufferedReader bufr;
	StringBuilder sBuilder;

	public String DownLoad(String Path) {

		try {

			URL url = new URL(Path);
			httpconn = (HttpURLConnection) url.openConnection();
			// httpconn.setConnectTimeout(5000);
			// httpconn.setReadTimeout(5000);
			httpconn.setRequestMethod("GET");
			httpconn.connect();
			if (httpconn.getResponseCode() == 200) {
				inputs = httpconn.getInputStream();
			}
			bufr = new BufferedReader(new InputStreamReader(inputs));
			sBuilder = new StringBuilder();
			if ((readline = bufr.readLine()) != null) {

				sBuilder.append(readline);
			}

		} catch (MalformedURLException e) {

			// Toast.makeText(Weatherconnection.this, "哎呀！网络不给力呀！", 1).show();

		} catch (IOException e) {
			System.out.println("网络不给力啊 ！");
			// e.printStackTrace();
		}

		return sBuilder.toString();

	}
}
