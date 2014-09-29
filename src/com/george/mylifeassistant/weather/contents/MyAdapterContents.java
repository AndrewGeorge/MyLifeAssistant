package com.george.mylifeassistant.weather.contents;

import java.util.List;

import com.george.mylifeassistant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapterContents extends BaseAdapter {

	List<Contents> listdata;
	Context C;

	public MyAdapterContents(Context C,List<Contents> listdata) {

	this.C=C;
	this.listdata=listdata;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listdata.size();
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
		if(arg1==null){
			arg1=LayoutInflater.from(C).inflate(R.layout.contentsitemlayout, null);
		}
		TextView name_tv=(TextView) arg1.findViewById(R.id.contentitemname_tv);
		TextView number_tv=(TextView) arg1.findViewById(R.id.contentsitemnumber_tv);
		name_tv.setText(listdata.get(arg0).name);
		number_tv.setText(listdata.get(arg0).number);
		return arg1;
	}

}
