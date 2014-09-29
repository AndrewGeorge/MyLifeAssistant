package com.george.mylifeassistant.notebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.george.mylifeassistant.R;

public class NoteBookDitals extends Activity {
	Button back_btn;
	TextView title_tv, content_tv;

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ditialsnotecontent);
		intent = getIntent();
		System.out.println(intent.getStringExtra("TITLE"));
		init();
	}

	private void init() {
		title_tv = (TextView) findViewById(R.id.noteditaltitle_tv);
		back_btn = (Button) findViewById(R.id.back_btn);
		content_tv = (TextView) findViewById(R.id.noteditaicontent_tv);
		
		title_tv.setText(intent.getStringExtra("TITLE"));
		content_tv.setText(intent.getStringExtra("CONTENT"));
		
		back_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

	}

}
