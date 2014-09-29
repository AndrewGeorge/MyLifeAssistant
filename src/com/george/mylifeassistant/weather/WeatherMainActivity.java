package com.george.mylifeassistant.weather;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.george.mylifeassistant.R;

@SuppressLint("HandlerLeak") public class WeatherMainActivity extends Activity {
	Button getweather;
	EditText city_chosed;
	ListView weatherItem;
	ImageView weatherpictuer;
	TextView name_city, temp_city, weather_city, index_city, wind_city,
			date_city;
	String downLoad;
	String cityChosed;
	ProgressDialog predialog;
	String cityCode;
	String cityName, data, temp1, weather1, wind1, index;
	List<Weather> weatherlist;
	List<Weather> list;
	public Handler handler = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0) {
			

			}
			if (msg.what == 1) {
				predialog.dismiss();
				name_city.setText(cityName);
				weather_city.setText(weather1);
				temp_city.setText(temp1);
				index_city.setText(index);
				wind_city.setText(wind1);
				date_city.setText(data);
				if (weather1.equals("多云转阴") || weather1.equals("阴转多云")) {
					weatherpictuer
							.setBackgroundResource(R.drawable.d_sunny_cloudy);
				} else if (weather1.equals("晴")) {
					weatherpictuer.setBackgroundResource(R.drawable.d_sunny);
				} else if (weather1.equals("多云")) {

					weatherpictuer.setBackgroundResource(R.drawable.d_cloudy);

				}else if (weather1.equals("晴转多云")) {

					weatherpictuer.setBackgroundResource(R.drawable.d_sunny_cloudy);
				}else if (weather1.equals("阴")) {

					weatherpictuer.setBackgroundResource(R.drawable.d_cloudy);
				}
				System.out.println(list.get(0).getTemp());
				MyAdapter adapter = new MyAdapter(WeatherMainActivity.this,
						list);
				weatherItem.setAdapter(adapter);
			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.george.mylifeassistant.R.layout.weatherlayout);
		inite();
	}

	/**
	 * 初始化数据
	 */
	@SuppressLint("CutPasteId")
	private void inite() {

		weatherpictuer = (ImageView) findViewById(R.id.weatherstate);
		city_chosed = (EditText) findViewById(R.id.city_weather);
		name_city = (TextView) findViewById(R.id.name_city);
		temp_city = (TextView) findViewById(R.id.temp_city);
		weather_city = (TextView) findViewById(R.id.weather_city);
		index_city = (TextView) findViewById(R.id.index_city);
		wind_city = (TextView) findViewById(R.id.wind_city);
		date_city = (TextView) findViewById(R.id.date_city);
		getweather = (Button) findViewById(R.id.go);
		weatherItem = (ListView) findViewById(R.id.weatherListView);
		getweather.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				predialog=new ProgressDialog(WeatherMainActivity.this);
				predialog.setMessage("正在加载天气信息...");
				predialog.show();
				cityChosed = city_chosed.getText().toString();
				if (cityChosed == "") {
					new DownLoad("http://m.weather.com.cn/data/101010100.html")
							.start();
				} else {
					new DownLoad("http://m.weather.com.cn/data/"
							+ getCityCode() + ".html").start();
				}

			}
		});
	}

	/***
	 * 获取城市Code
	 */
	private String getCityCode() {
		try {
			AssetManager assem = getAssets();// 获得Asset的管理器
			XmlPullParser xpp = Xml.newPullParser();// 获得XML解析器
			InputStream is = assem.open("citylist.xml");// 打开文件
			xpp.setInput(is, "utf-8");// 设置解析内容
			int event = xpp.getEventType();// 开始第一个事件爱你的发生
			int a = 0;
			// 使用循来扫描寻找我们需要的city
			while (event != XmlPullParser.END_DOCUMENT) {

				if (event == XmlPullParser.START_TAG) {

					if ("name".equals(xpp.getName())) {

						if (cityChosed.equalsIgnoreCase(xpp.nextText())) {

							a = 1;
						}
					}

					if ("code".equals(xpp.getName()) && a == 1) {

						cityCode = xpp.nextText();
						break;
					}
				}

				event = xpp.next();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cityCode;

	}

	public class DownLoad extends Thread {
		String path;
		String result;

		public DownLoad(String path) {
			// TODO Auto-generated constructor stub
			this.path = path;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			Weatherconnection conn = new Weatherconnection();
			result = conn.DownLoad(path);
			
			System.out.println(result);
			getJesonData(result);
			handler.sendEmptyMessage(0);
		}

	}

	/**
	 * 
	 * 获取JEson数据
	 * 
	 * @param path
	 * @return
	 */

	private List<Weather> getJesonData(String downLoadresult) {

		list = new ArrayList<Weather>();
		Weather weather;

		// 使用json来解析
		try {

			JSONObject js = new JSONObject(downLoadresult);	
			JSONObject j = js.getJSONObject("weatherinfo");
			cityName = j.getString("city");
			data = j.getString("date_y");
			temp1 = j.getString("temp1");

			weather1 = j.getString("weather1");
			wind1 = j.getString("wind1");
			index = j.getString("index");

			weather = null;
			for (int i = 2; i < 7; i++) {

				weather = new Weather();
				weather.setTemp(j.getString("temp" + i));
				weather.setWeather(j.getString("weather" + i));
				weather.setWind(j.getString("wind" + i));
				list.add(weather);
			}
			// 发送数据检验是否解析完毕
			handler.sendEmptyMessage(1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 判断网络连接状体
	 * 
	 * @return
	 */

	@SuppressWarnings("unused")
	private boolean getNetWorkState() {

		ConnectivityManager connManager = (ConnectivityManager) this
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = connManager.getActiveNetworkInfo();
		if (networkinfo == null) {
			return false;
		} else {
			return true;
		}

	}
}
