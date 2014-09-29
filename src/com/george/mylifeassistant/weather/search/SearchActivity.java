package com.george.mylifeassistant.weather.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.george.mylifeassistant.R;
import com.george.mylifeassistant.weather.Weatherconnection;

@SuppressLint("HandlerLeak")
public class SearchActivity extends Activity implements OnClickListener {
	AutoCompleteTextView companyname_tv;
	AlertDialog.Builder build;
	AlertDialog alert;
	AlertDialog.Builder builder;
	ListView kuaididetial_LV;
	TextView number_et, title_tv;
	Button kuaidisearch_btn, kuaidiback_btn;
	ArrayList<HashMap<String, String>> DataList;
	String message;
	String data;
	String PATH = "http://api.kuaidi100.com/api?id=d3d26f3c59bd5a9b&com=company&nu=post_id&order=asc";

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				
				MyAdapter adapter = new MyAdapter(SearchActivity.this, DataList);
				title_tv.setVisibility(View.VISIBLE);
				title_tv.setText("快递详情");
				kuaididetial_LV.setAdapter(adapter);
				kuaididetial_LV.setVisibility(View.VISIBLE);
				alert.dismiss();

			}
			if (msg.what == 2) {
				kuaididetial_LV.setVisibility(View.GONE);
				alert.dismiss();
				title_tv.setText("未能正确找到你的快递,请查看快递公司，和快递单号后再查询！");
				title_tv.setVisibility(View.VISIBLE);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchlayout);
		init();
	}

	private void init() {
		List<String> autoList = new ArrayList<String>();
		autoList.add("圆通快递");
		autoList.add("申通快递");
		autoList.add("顺丰快递");
		autoList.add("韵达快递");
		autoList.add("中通快递");
		autoList.add("ems");
		autoList.add("宅急送");
		kuaididetial_LV = (ListView) findViewById(R.id.kuaidiItem_lv);
		companyname_tv = (AutoCompleteTextView) findViewById(R.id.company_tv);

		ArrayAdapter<String> autoAdapter = new ArrayAdapter<String>(
				SearchActivity.this, R.layout.searchauotv, autoList);
		companyname_tv.setAdapter(autoAdapter);

		number_et = (TextView) findViewById(R.id.number_et);
		kuaidisearch_btn = (Button) findViewById(R.id.kuaidisearch_btn);
		kuaidiback_btn = (Button) findViewById(R.id.kuaidiback_btn);
		title_tv = (TextView) findViewById(R.id.title_tv);
		kuaidiback_btn.setOnClickListener(this);
		kuaidisearch_btn.setOnClickListener(this);

	}

	/***
	 * 开线程来下载所需文件
	 * 
	 */
	public class DownLoad extends Thread {

		String path;

		public DownLoad(String path) {
			// TODO Auto-generated constructor stub
			this.path = path;

		}

		@Override
		public void run() {
			super.run();
			Weatherconnection conn = new Weatherconnection();
			String result = conn.DownLoad(path);
			System.out.println(result);
			getJesonOrge(result);
		}
	}

	/**
	 * 
	 * 解析文件
	 * 
	 * @param DownLondmessage
	 */
	private void getJesonOrge(String DownLondmessage) {

		try {

			JSONObject jsonObj = new JSONObject(DownLondmessage);

			if (jsonObj.getString("message").equals("ok")) {
				DataList = new ArrayList<HashMap<String, String>>();
				JSONArray data = jsonObj.getJSONArray("data");

				for (int i = 0; i < data.length(); i++) {

					HashMap<String, String> map = new HashMap<String, String>();
					String time = data.getJSONObject(i).getString("time")
							.trim();
					String context = data.getJSONObject(i).getString("context")
							.replaceAll(" ", "");
					map.put("TIME", time);
					map.put("CONTENT", context);
					DataList.add(map);
				}

				handler.sendEmptyMessage(1);
			}
			if (!jsonObj.getString("message").equals("ok")) {

				handler.sendEmptyMessage(2);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		int id = arg0.getId();
		String getcompany = companyname_tv.getText().toString();
		String company = null;
		String urlpath;
		switch (id) {

		case R.id.kuaidisearch_btn:

			if (getcompany.equalsIgnoreCase("圆通快递")) {
				company = "yuantong";
			} else if (getcompany.equalsIgnoreCase("申通快递")) {
				company = "shengtong";
			} else if (getcompany.equalsIgnoreCase("顺丰快递")) {
				company = "shunfeng";
			} else if (getcompany.equalsIgnoreCase("韵达快递")) {
				company = "yunda";
			} else if (getcompany.equalsIgnoreCase("中通快递")) {
				company = "zhongtong";
			} else if (getcompany.equalsIgnoreCase("ems")) {
				company = "ems";
			} else if (getcompany.equalsIgnoreCase("宅急送")) {
				company = "zhaijisong";
			} else {
				Toast.makeText(SearchActivity.this, "快递公司名称错误！请检查后重新输入！", 1000)
						.show();
				break;
			}

			urlpath = PATH.replace("company", company).replace("post_id",
					number_et.getText().toString());

			new DownLoad(urlpath).start();
			getDialog();
			break;

		case R.id.kuaidiback_btn:

			finish();
			break;
		}

	}

	/**
	 * 
	 * 
	 */
	private void getDialog() {

		 build=new Builder(SearchActivity.this);
		 alert=build.create();
		alert.setMessage("正在玩命的加载中...");
		alert.show();
	}
}
