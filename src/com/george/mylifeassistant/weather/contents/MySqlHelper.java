package com.george.mylifeassistant.weather.contents;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlHelper extends SQLiteOpenHelper {

	// 数据库的名字
	private final static String DB_NANE = "contentsDB";
	// 数据库的版本
	private final static int DB_VERSION = 1;

	// the name of the table
	private final static String TABLE_NAME = "contents";

	// The language to create the table
	private final String SQL_CREATE = "CREATE TABLE"+" "+TABLE_NAME+"(_id INTEGER PRIMARY KEY  AUTOINCREMENT ,name VARCHAR(50) NOT NULL , number VARCHAR(50) )";
	
	// the drop language of the Table

	private final String SQL_DROPTABLE = "DROP IF TABLE EXISTS" + TABLE_NAME;

	public MySqlHelper(Context context) {
		
		super(context, DB_NANE, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE);
	}

	/** 版本号升级的时候进行调用；如果数据库不存在就不调用 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

		db.execSQL(SQL_DROPTABLE);
		onCreate(db);
	}

}
