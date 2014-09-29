package com.george.mylifeassistant.notebook;

import java.util.List;
import com.george.mylifeassistant.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyNoteAdapter extends BaseAdapter {

	Context c;
	List<NoteBook> listdata;

	public MyNoteAdapter(Context c,List<NoteBook> listdata) {
		this.c = c;
		this.listdata=listdata;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listdata.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		
		if (arg1==null) {
			
			arg1=LayoutInflater.from(c).inflate(R.layout.notebookitem, null);
		}
		TextView  notetitle=(TextView) arg1.findViewById(R.id.nodetitle_tv);
		notetitle.setText(listdata.get(arg0).date.toString()+":");
		TextView  notecontent=(TextView) arg1.findViewById(R.id.nodecontent_tv);
		notecontent.setText(listdata.get(arg0).content.toString());
		return arg1;
	}

}
