package com.george.mylifeassistant.weather;

import java.util.List;
import com.george.mylifeassistant.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	Context c;
	List<Weather> list;
	public MyAdapter(Context c,List<Weather> list) {
		
		this.list=list;
		this.c=c;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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

	@SuppressWarnings("unused")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		if (arg1==null) {
			
			arg1=LayoutInflater.from(c).inflate(R.layout.weatheriteam, null);
		}
			ImageView weatherstateitem=(ImageView) arg1.findViewById(R.id.weatherstateitem);
			TextView  weatherdateitem=(TextView) arg1.findViewById(R.id.weatherdateitem);
			TextView weatheritem=(TextView) arg1.findViewById(R.id.weatheritem);
			TextView weathertempitem=(TextView) arg1.findViewById(R.id.weathertempitem);
			
			
			weathertempitem.setText(list.get(arg0).getTemp());
			weatheritem.setText(list.get(arg0).getWeather());
			
			if (list.get(arg0).getWeather().equals("晴")) {
				weatherstateitem.setBackgroundResource(R.drawable.d_sunny);
			}else if (list.get(arg0).getWeather().equals("晴转多云")||list.get(arg0).getWeather().equals("多云转晴")) {
				weatherstateitem.setBackgroundResource(R.drawable.d_sunny_cloudy);
			}else if (list.get(arg0).getWeather().equals("多云")) {
				weatherstateitem.setBackgroundResource(R.drawable.d_cloudy);
			} else if(list.get(arg0).getWeather().equals("多云转阴")||
					list.get(arg0).getWeather().equals("阴转多云")){
				weatherstateitem.setBackgroundResource(R.drawable.d_cloudy);
			}
			
			
		return arg1;
	}
	
	
}
