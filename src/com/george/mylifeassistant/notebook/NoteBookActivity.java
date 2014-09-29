package com.george.mylifeassistant.notebook;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.george.mylifeassistant.R;

@SuppressLint("ShowToast") public class NoteBookActivity extends Activity {

	Button addnote_btn ,count_btn;
	ListView note_item;
	List<NoteBook> NotelistData;
	NoteBookDBManger dbControl;
	MyNoteAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notebooklayout);
		inite();
		
	}
	
	private void inite(){
		
		addnote_btn=(Button) findViewById(R.id.addnote_btn);
		count_btn=(Button) findViewById(R.id.notecount);
		note_item=(ListView) findViewById(R.id.note_listview);
		adapter=new MyNoteAdapter(NoteBookActivity.this,NotelistData=getData());
		note_item.setAdapter(adapter);
		note_item.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Intent intent=new Intent(NoteBookActivity.this,NoteBookDitals.class);
				intent.putExtra("TITLE", NotelistData.get(arg2).title);
				intent.putExtra("CONTENT",NotelistData.get(arg2).content);
				startActivity(intent);
			}
		});
		note_item.setOnItemLongClickListener(new OnItemLongClickListener() {
			int num;
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				num=arg2;
				AlertDialog.Builder builder=new Builder(NoteBookActivity.this);
				builder.setTitle("删除提示");
				builder.setMessage("你确定要删除吗？");
				builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
					dbControl.deleteData(NotelistData.get(num)._id,NoteBookActivity.this);
					adapter=new MyNoteAdapter(NoteBookActivity.this,NotelistData=getData());
					note_item.setAdapter(adapter);
					Toast.makeText(NoteBookActivity.this, "删除成功！", 500).show();
					
					}

					
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});
			 builder.show();
				return false;
			}
		});
		addnote_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

			Intent intent=new Intent(NoteBookActivity.this,AddNoteActivity.class);	
				startActivity(intent);
			}
		});
		
	}
	/**
	 * 
	 * 获取数据库中的数据
	 */
	private List<NoteBook> getData(){
		
		List<NoteBook>  listData=new ArrayList<NoteBook>();
		dbControl=new NoteBookDBManger(NoteBookActivity.this);
		listData=dbControl.queryData();
		count_btn.setText("全部"+listData.size());
		return listData;
		
	}


}
