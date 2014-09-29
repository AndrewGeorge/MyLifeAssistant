package com.george.mylifeassistant.weather.search;

import java.util.ArrayList;
import java.util.HashMap;

import com.george.mylifeassistant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	ArrayList<HashMap<String, String>> Datalist;
	Context c;

	public MyAdapter(Context c, ArrayList<HashMap<String, String>> Datalist) {
		this.c = c;
		this.Datalist = Datalist;

	}

	@Override
	public int getCount() {
		
		return Datalist.size();
		
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		if (arg1 == null) {

			arg1 = LayoutInflater.from(c).inflate(R.layout.kuaidiitemlayout,
					null);
		}
		TextView date_tv = (TextView) arg1.findViewById(R.id.kuaididate_tv);
		TextView detial_tv = (TextView) arg1.findViewById(R.id.kuaididetial_tv);
		date_tv.setText(Datalist.get(arg0).get("TIME"));
		detial_tv.setText(Datalist.get(arg0).get("CONTENT"));
		
		return arg1;
	}

}
