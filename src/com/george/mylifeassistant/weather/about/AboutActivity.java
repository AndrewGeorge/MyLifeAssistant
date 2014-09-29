package com.george.mylifeassistant.weather.about;

import com.george.mylifeassistant.R;
import com.george.mylifeassistant.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity {

Button about_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutlayout);
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		about_btn=(Button) findViewById(R.id.aboutback_btn);
		about_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
}
