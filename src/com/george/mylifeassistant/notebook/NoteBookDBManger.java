package com.george.mylifeassistant.notebook;


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteBookDBManger {

	private NoteBookDataBaseHelper daTaHelper;

	public NoteBookDBManger(Context context) {
		// TODO Auto-generated constructor stub
		daTaHelper = new NoteBookDataBaseHelper(context);
	}

	public void deleteData(final long id, Context context) {

		SQLiteDatabase db = daTaHelper.getWritableDatabase();
		db.delete("note", "_id=?", new String[] { String.valueOf(id) });
		db.close();
	}

	public void saveData(ContentValues values) {
		
		SQLiteDatabase db=daTaHelper.getWritableDatabase();
		db.insert("note", null, values);
		db.close();

	}

	public List<NoteBook> queryData() {
		
		SQLiteDatabase db = daTaHelper.getWritableDatabase();
		List<NoteBook> listdata=new ArrayList<NoteBook>();
		
		//db.rawQuery(sql, selectionArgs)
		//db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, 
		//orderBy, limit, cancellationSignal)
		
		Cursor cursor=db.query("note", null, null, null, null, null, null);
		//遍历游标集来获取信息
		while (cursor.moveToNext()) {
			
			NoteBook noteBook=new NoteBook(cursor.getInt(cursor.getColumnIndex("_id")), 
					cursor.getString(cursor.getColumnIndex("title")), 
					cursor.getString(cursor.getColumnIndex("content")),
					cursor.getString(cursor.getColumnIndex("date")));
			listdata.add(noteBook);
			
		}
		
		cursor.close();
		db.close();
		return listdata;
	}

}
