package com.george.mylifeassistant.weather.contents;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.george.mylifeassistant.MainActivity;
import com.george.mylifeassistant.R;

@SuppressLint("ShowToast")
public class ContentsActivity extends Activity {

	Button contwntsback_btn;
	ListView contentsitem_lv;
	ContentsDBManger dbmaner;
	List<Contents> listdata;
	MyAdapterContents adapter;
	int num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contentsmainlayout);
		init();
	}

	private void init() {

		contentsitem_lv = (ListView) findViewById(R.id.contentsitem_lv);
		dbmaner = new ContentsDBManger(ContentsActivity.this);

		System.out.println(dbmaner.queryData().size() + "");
		adapter = new MyAdapterContents(
				ContentsActivity.this, listdata = dbmaner.queryData());
		contentsitem_lv.setAdapter(adapter);
		contwntsback_btn = (Button) findViewById(R.id.contentsback_btn);
		contwntsback_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ContentsActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});
		Toast.makeText(ContentsActivity.this, "点击菜单按钮来添加新的联系人！", 1).show();
		contentsitem_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				String inputstr = listdata.get(arg2).number.toString();
				Intent phoneintent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + inputstr.toString()));
				startActivity(phoneintent);
			}
		});

		contentsitem_lv
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int arg2, long arg3) {
						num = arg2;
						AlertDialog.Builder builder = new AlertDialog.Builder(
								ContentsActivity.this);
						builder.setMessage("你确定要删除联系人"+listdata.get(num).name+"吗？");
						builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								finish();
							}
						});
						builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								dbmaner.deleteData(listdata.get(num)._id,
										ContentsActivity.this);
								Toast.makeText(ContentsActivity.this, "删除成功！", 1);
								adapter = new MyAdapterContents(
										ContentsActivity.this, listdata = dbmaner.queryData());
								contentsitem_lv.setAdapter(adapter);
								
							}
						});
						builder.show();
						
						return false;
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// 第一个参数，菜单项数组，菜单项的编号(也是item的id)，第三个参数排列书序，第四个参数
		menu.add(Menu.NONE, 1, 1, "添加").setIcon(R.drawable.contents_add);

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "添加", 0).show();

			Intent intent = new Intent(ContentsActivity.this,
					AddContentsActivity.class);
			startActivity(intent);
			finish();
			break;

		default:

			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
