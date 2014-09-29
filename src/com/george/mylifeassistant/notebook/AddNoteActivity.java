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
		String noteDetaiTitle;//note��ϸ��Ϣ�����Title
		String content;//note����
		String dateContent;//�����е�����
		
		Date date=new Date();//��ȡ��ǰϵͳʱ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//���ڸ�ʽ����
		SimpleDateFormat sdf1=new SimpleDateFormat("MM��dd��");//���ڸ�ʽ����
		
		noteDetaiTitle=sdf.format(date);
		dateContent=sdf1.format(date);
		content=noteContent_edit.getText().toString();
		
		// ����ContentValues,����title������
		ContentValues values=new ContentValues();
		values.put("title", noteDetaiTitle);
		values.put("content", content);
		values.put("date", dateContent);
		
		//�����ݿ����������
		NoteBookDBManger dbControl=new NoteBookDBManger(AddNoteActivity.this);
		dbControl.saveData(values);
		
		//setResult(AppFinal.SAVE_NEW_NOTE);


	}
	
	

}
