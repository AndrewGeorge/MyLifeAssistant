package com.george.mylifeassistant.notebook;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.george.mylifeassistant.R;

@SuppressLint("SimpleDateFormat") public class AddNoteActivity extends Activity {

	EditText noteContent_edit;
	Button svae_btn, notSave_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnewnote);
		inite();
	}

	private void inite() {

		MyListener listener = new MyListener();
		noteContent_edit = (EditText) findViewById(R.id.notecotnent);
		svae_btn = (Button) findViewById(R.id.save_btn);
		notSave_btn = (Button) findViewById(R.id.notsave_btn);
		svae_btn.setOnClickListener(listener);
		notSave_btn.setOnClickListener(listener);

	}

	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {

			case R.id.notsave_btn:
				noteContent_edit.setText("");
				finish();
				break;
			case R.id.save_btn:
				insertData();
				Intent intent1 =new Intent(AddNoteActivity.this,
						NoteBookActivity.class);
				startActivity(intent1);
				finish();
				break;

			default:
				break;
			}

		}

	}
	
	private void insertData() {
		
		// TODO Auto-generated method stub
		String noteDetaiTitle;//note详细信息界面的Title
		String content;//note内容
		String dateContent;//内容中的日期
		
		Date date=new Date();//获取当前系统时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式化类
		SimpleDateFormat sdf1=new SimpleDateFormat("MM月dd日");//日期格式化类
		
		noteDetaiTitle=sdf.format(date);
		dateContent=sdf1.format(date);
		content=noteContent_edit.getText().toString();
		
		// 定义ContentValues,存入title和内容
		ContentValues values=new ContentValues();
		values.put("title", noteDetaiTitle);
		values.put("content", content);
		values.put("date", dateContent);
		
		//向数据库中添加数据
		NoteBookDBManger dbControl=new NoteBookDBManger(AddNoteActivity.this);
		dbControl.saveData(values);
		
		//setResult(AppFinal.SAVE_NEW_NOTE);


	}
	
	

}
