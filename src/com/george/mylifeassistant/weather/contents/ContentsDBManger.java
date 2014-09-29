package com.george.mylifeassistant.weather.contents;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class ContentsDBManger {

	MySqlHelper daTaHelper;
	
	public ContentsDBManger(Context context) {
		// TODO Auto-generated constructor stub
		daTaHelper=new MySqlHelper(context);
	}
	public void deleteData(final long id, Context context) {

		SQLiteDatabase db = daTaHelper.getWritableDatabase();
		db.delete("contents", "_id=?", new String[] { String.valueOf(id) });
		db.close();
	}

	public void saveData(ContentValues values) {
		
		SQLiteDatabase db=daTaHelper.getWritableDatabase();
		db.insert("contents", null, values);
		db.close();

	}

	public List<Contents> queryData() {
		
		SQLiteDatabase db = daTaHelper.getWritableDatabase();
		List<Contents> listdata=new ArrayList<Contents>();
		
		
		Cursor cursor=db.query("contents", null, null, null, null, null, null);
		//遍历游标集来获取信息
		while (cursor.moveToNext()) {
			
			Contents contents=new Contents(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("name")),
					cursor.getString(cursor.getColumnIndex("number")));
			listdata.add(contents);
		}
		cursor.close();
		db.close();
		return listdata;
	}

}
