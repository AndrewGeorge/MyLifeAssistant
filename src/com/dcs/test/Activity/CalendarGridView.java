package com.dcs.test.Activity;

import com.george.mylifeassistant.R;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;

/************************************************************************
 *	椤圭洰鍚嶅瓧	:甯︽墜鍔挎粦鍔ㄥ姛鑳界殑鏃ュ巻 
 * @author  angel銇収
 * @version 2012-10-08
銆�
************************************************************************/
public class CalendarGridView extends GridView{
	
	private Context mContext;

	public CalendarGridView(Context context) {
		super(context);
		mContext = context;
		setGirdView();
	}

	private void setGirdView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		
		setLayoutParams(params);
		setNumColumns(7);// 璁剧疆姣忚鍒楁暟
		setGravity(Gravity.CENTER_VERTICAL);// 浣嶇疆灞呬腑
		setVerticalSpacing(1);// 鍨傜洿闂撮殧
		setHorizontalSpacing(1);// 姘村钩闂撮殧
		//璁剧疆鑳屾櫙
		setBackgroundColor(getResources().getColor(R.color.calendar_background));
	//璁剧疆鍙傛暟
		WindowManager windowManager = ((Activity)mContext).getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int i = display.getWidth() / 7;
		int j = display.getWidth() - (i * 7);
		int x = j / 2;
		setPadding(x, 0, 0, 0);// 灞呬腑
	}
}
