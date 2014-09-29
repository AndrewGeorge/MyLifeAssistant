package com.george.mylifeassistant.weather.contents;
import com.george.mylifeassistant.R;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContentsActivity extends Activity implements OnClickListener {

	Button savecontents_btn, notsvaecontents_btn;
	EditText contentsname_et,contentsnumber_et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontentslayout);
		init();
	}

	private void init() {
		savecontents_btn = (Button) findViewById(R.id.savecontents_btn);
		savecontents_btn.setOnClickListener(this);
		notsvaecontents_btn = (Button) findViewById(R.id.notsavecontents_btn);
		notsvaecontents_btn.setOnClickListener(this);
		contentsname_et=(EditText) findViewById(R.id.contentsname_et);
		contentsnumber_et=(EditText) findViewById(R.id.contentsnumber_et);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		
		case R.id.savecontents_btn:
			if(contentsname_et.getText().toString()!=""){
				insertData();
				Toast.makeText(AddContentsActivity.this, "添加成功", 0).show();
				contentsname_et.setText("");
				contentsnumber_et.setText("");
			}
			break;
		case R.id.notsavecontents_btn:
				finish();
			break;

		default:
			break;
		}
	}
	
private void insertData() {
	
		// 定义ContentValues,存入title和内容
		ContentValues values=new ContentValues();
		values.put("name", contentsname_et.getText().toString());
		values.put("number", contentsnumber_et.getText().toString());
		//向数据库中添加数据
		ContentsDBManger dbControl=new ContentsDBManger(AddContentsActivity.this);
		dbControl.saveData(values);
		
		


	}
	
	

}
