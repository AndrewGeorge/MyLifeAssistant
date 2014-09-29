package com.dcs.test.Activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.george.mylifeassistant.R;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/************************************************************************
 *	椤圭洰鍚嶅瓧	:甯︽墜鍔挎粦鍔ㄥ姛鑳界殑鏃ュ巻 
 * @author  angel銇収
 * @version 2012-10-08
銆�
************************************************************************/
public class CalendarGridViewAdapter extends BaseAdapter {

	private Calendar calStartDate = Calendar.getInstance();// 褰撳墠鏄剧ず鐨勬棩鍘�
	private Calendar calSelected = Calendar.getInstance(); // 閫夋嫨鐨勬棩鍘�
	
	public void setSelectedDate(Calendar cal)
	{
		calSelected=cal;
	}
	
	private Calendar calToday = Calendar.getInstance(); // 浠婃棩
	private int iMonthViewCurrentMonth = 0; // 褰撳墠瑙嗗浘鏈�
	// 鏍规嵁鏀瑰彉鐨勬棩鏈熸洿鏂版棩鍘�
	// 濉厖鏃ュ巻鎺т欢鐢�
	private void UpdateStartDateForMonth() {
		calStartDate.set(Calendar.DATE, 1); // 璁剧疆鎴愬綋鏈堢涓�ぉ
		iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);// 寰楀埌褰撳墠鏃ュ巻鏄剧ず鐨勬湀

		// 鏄熸湡涓�槸2 鏄熸湡澶╂槸1 濉厖鍓╀綑澶╂暟
		int iDay = 0;
		int iFirstDayOfWeek = Calendar.MONDAY;
		int iStartDay = iFirstDayOfWeek;
		if (iStartDay == Calendar.MONDAY) {
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
			if (iDay < 0)
				iDay = 6;
		}
		if (iStartDay == Calendar.SUNDAY) {
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
			if (iDay < 0)
				iDay = 6;
		}
		calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);

		calStartDate.add(Calendar.DAY_OF_MONTH, -1);// 鍛ㄦ棩绗竴浣�

	}
	ArrayList<java.util.Date> titles;
	private ArrayList<java.util.Date> getDates() {

		UpdateStartDateForMonth();

		ArrayList<java.util.Date> alArrayList = new ArrayList<java.util.Date>();
		//閬嶅巻鏁扮粍
		for (int i = 1; i <= 42; i++) {
			alArrayList.add(calStartDate.getTime());
			calStartDate.add(Calendar.DAY_OF_MONTH, 1);
		}

		return alArrayList;
	}

	private Activity activity;
	Resources resources;
	// construct
	public CalendarGridViewAdapter(Activity a,Calendar cal) {
		calStartDate=cal;
		activity = a;
		resources=activity.getResources();
		titles = getDates();
	}
	
	public CalendarGridViewAdapter(Activity a) {
		activity = a;
		resources=activity.getResources();
	}


	@Override
	public int getCount() {
		return titles.size();
	}

	@Override
	public Object getItem(int position) {
		return titles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout iv = new LinearLayout(activity);
		iv.setId(position + 5000);
		LinearLayout imageLayout = new LinearLayout(activity);
		imageLayout.setOrientation(0);
		iv.setGravity(Gravity.CENTER);
		iv.setOrientation(1);
		iv.setBackgroundColor(resources.getColor(R.color.white));

		Date myDate = (Date) getItem(position);
		Calendar calCalendar = Calendar.getInstance();
		calCalendar.setTime(myDate);

		final int iMonth = calCalendar.get(Calendar.MONTH);
		final int iDay = calCalendar.get(Calendar.DAY_OF_WEEK);

	
		// 鍒ゆ柇鍛ㄥ叚鍛ㄦ棩
		iv.setBackgroundColor(resources.getColor(R.color.white));
		if (iDay == 7) {
			// 鍛ㄥ叚
			iv.setBackgroundColor(resources.getColor(R.color.text_6));
		} else if (iDay == 1) {
			// 鍛ㄦ棩
			iv.setBackgroundColor(resources.getColor(R.color.text_7));
		} else {

		}
		// 鍒ゆ柇鍛ㄥ叚鍛ㄦ棩缁撴潫

		TextView txtToDay = new TextView(activity);// 鏃ユ湰鑰侀粍鍘�
		txtToDay.setGravity(Gravity.CENTER_HORIZONTAL);
		txtToDay.setTextSize(9);//瀛椾綋澶у皬
		if (equalsDate(calToday.getTime(), myDate)) {
			// 褰撳墠鏃ユ湡
			iv.setBackgroundColor(resources.getColor(R.color.event_center));
			txtToDay.setText("TODAY!");
		}

		// 璁剧疆鑳屾櫙棰滆壊
		if (equalsDate(calSelected.getTime(), myDate)) {
			// 閫夋嫨鐨�
			iv.setBackgroundColor(resources.getColor(R.color.selection));
		} else {
			if (equalsDate(calToday.getTime(), myDate)) {
				// 褰撳墠鏃ユ湡
				iv.setBackgroundColor(resources.getColor(R.color.calendar_zhe_day));
			}
		}
		// 璁剧疆鑳屾櫙棰滆壊缁撴潫

		// 鏃ユ湡寮�
		TextView txtDay = new TextView(activity);// 鏃ユ湡
		txtDay.setGravity(Gravity.CENTER_HORIZONTAL);

		// 鍒ゆ柇鏄惁鏄綋鍓嶆湀
		if (iMonth == iMonthViewCurrentMonth) {
			txtToDay.setTextColor(resources.getColor(R.color.ToDayText));
			txtDay.setTextColor(resources.getColor(R.color.Text));
		} else {
			txtDay.setTextColor(resources.getColor(R.color.noMonth));
			txtToDay.setTextColor(resources.getColor(R.color.noMonth));
		}

		int day = myDate.getDate(); // 鏃ユ湡
		txtDay.setText(String.valueOf(day));
		txtDay.setId(position + 500);
		iv.setTag(myDate);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		iv.addView(txtDay, lp);

		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		iv.addView(txtToDay, lp1);
		// 鏃ユ湡缁撴潫
		// iv.setOnClickListener(view_listener);

		return iv;
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	private Boolean equalsDate(Date date1, Date date2) {

		if (date1.getYear() == date2.getYear()
				&& date1.getMonth() == date2.getMonth()
				&& date1.getDate() == date2.getDate()) {
			return true;
		} else {
			return false;
		}

	}

}
