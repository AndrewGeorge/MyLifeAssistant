package com.dcs.test.Activity;
import com.george.mylifeassistant.R;
import java.util.Calendar;
import java.util.Date;

import com.dcs.test.Tools.NumberHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.LinearLayout.LayoutParams;
/************************************************************************
 *	椤圭洰鍚嶅瓧	:甯︽墜鍔挎粦鍔ㄥ姛鑳界殑鏃ュ巻 
 * @author  angel銇収
 * @version 2012-10-08
銆�
************************************************************************/
public class BaseCalendar extends Activity implements OnTouchListener {

	//鐢ㄤ簬鍒ゆ柇鎵嬪娍
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	//鍔ㄧ敾
	private Animation slideLeftIn;
	private Animation slideLeftOut;
	private Animation slideRightIn;
	private Animation slideRightOut;
	private ViewFlipper viewFlipper;
	GestureDetector mGesture = null;

	@Override
	//鑾峰彇鎵嬪娍action;
	public boolean onTouch(View v, MotionEvent event) {
		return mGesture.onTouchEvent(event);
	}

	AnimationListener animationListener=new AnimationListener() {
		@Override
		public void onAnimationStart(Animation animation) {
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			//褰撳姩鐢诲畬鎴愬悗璋冪敤
			CreateGirdView();
		}
	};
	
	//SimpleOnGestureListener 鏄疉ndroid SDK鎻愪緵鐨勪竴涓猯istener绫绘潵渚︽祴鍚勭涓嶅悓鐨勬墜鍔�
	class GestureListener extends SimpleOnGestureListener {
		@Override
		//鍦╫nFling鏂规硶涓� 鍒ゆ柇鏄笉鏄竴涓悎鐞嗙殑swipe鍔ㄤ綔;
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE	&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					viewFlipper.setInAnimation(slideLeftIn);
					viewFlipper.setOutAnimation(slideLeftOut);
					viewFlipper.showNext();
					setNextViewItem();
					
					return true;

				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					//杩欓噷鐨剉iewFlipper鏄惈鏈夊涓獀iew鐨勪竴涓猚ontainer, 鍙互寰堟柟渚跨殑璋冪敤prev/next view; 

					viewFlipper.setInAnimation(slideRightIn);
					viewFlipper.setOutAnimation(slideRightOut);
					viewFlipper.showPrevious();
					setPrevViewItem();
					
					return true;

				}
			} catch (Exception e) {
				// nothing
			}
			return false;
		}
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// ListView lv = getListView();
			//寰楀埌褰撳墠閫変腑鐨勬槸绗嚑涓崟鍏冩牸
			int pos = gView2.pointToPosition((int) e.getX(), (int) e.getY());
			LinearLayout txtDay = (LinearLayout) gView2.findViewById(pos + 5000);
			if (txtDay != null) {
				if (txtDay.getTag() != null) {
					Date date = (Date) txtDay.getTag();
					calSelected.setTime(date);

					gAdapter.setSelectedDate(calSelected);
					gAdapter.notifyDataSetChanged();

					gAdapter1.setSelectedDate(calSelected);
					gAdapter1.notifyDataSetChanged();

					gAdapter3.setSelectedDate(calSelected);
					gAdapter3.notifyDataSetChanged();
				}
			}

			Log.i("TEST", "onSingleTapUp -  pos=" + pos);

			return false;
		}
	}

	// 鍩烘湰鍙橀噺
	private Context mContext = BaseCalendar.this;
	private GridView title_gView;
	private GridView gView1;// 涓婁竴涓湀
	private GridView gView2;// 褰撳墠鏈�
	private GridView gView3;// 涓嬩竴涓湀
	
	boolean bIsSelection = false;// 鏄惁鏄�鎷╀簨浠跺彂鐢�
	private Calendar calStartDate = Calendar.getInstance();// 褰撳墠鏄剧ず鐨勬棩鍘�
	private Calendar calSelected = Calendar.getInstance(); // 閫夋嫨鐨勬棩鍘�
	private Calendar calToday = Calendar.getInstance(); // 浠婃棩
	private CalendarGridViewAdapter gAdapter;
	private CalendarGridViewAdapter gAdapter1;
	private CalendarGridViewAdapter gAdapter3;
	// 椤堕儴鎸夐挳
	private Button btnToday = null;
	private RelativeLayout mainLayout;

	private int iMonthViewCurrentMonth = 0; // 褰撳墠瑙嗗浘鏈�
	private int iMonthViewCurrentYear = 0; // 褰撳墠瑙嗗浘骞�
	private int iFirstDayOfWeek = Calendar.MONDAY;

	private static final int mainLayoutID = 88; // 璁剧疆涓诲竷灞�D
	private static final int titleLayoutID = 77; // title甯冨眬ID
	private static final int caltitleLayoutID = 66; // title甯冨眬ID
	private static final int calLayoutID = 55; // 鏃ュ巻甯冨眬ID
	/** 搴曢儴鑿滃崟鏂囧瓧 **/
	String[] menu_toolbar_name_array;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(generateContentView());
		UpdateStartDateForMonth();
		
		//娣诲姞Animation瀹炵幇涓嶅悓鍔ㄧ敾鏁堟灉
		slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
		slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
		slideRightIn = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
		slideRightOut = AnimationUtils.loadAnimation(this,R.anim.slide_right_out);
		
		slideLeftIn.setAnimationListener(animationListener);
		slideLeftOut.setAnimationListener(animationListener);
		slideRightIn.setAnimationListener(animationListener);
		slideRightOut.setAnimationListener(animationListener);
		
		mGesture = new GestureDetector(this, new GestureListener());
	}

	AlertDialog.OnKeyListener onKeyListener = new AlertDialog.OnKeyListener() {

		@Override
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				BaseCalendar.this.finish();
			}
			return false;

		}

	};

	
	// 鐢熸垚鍐呭瑙嗗浘
	private View generateContentView() {
		// 鍒涘缓涓�釜鍨傜洿鐨勭嚎鎬у竷灞�紙鏁翠綋鍐呭锛�
		viewFlipper = new ViewFlipper(this);
		viewFlipper.setId(calLayoutID);
		// 鍒涘缓涓�釜鍨傜洿鐨勭嚎鎬у竷灞�紙鏁翠綋鍐呭锛�
		mainLayout = new RelativeLayout(this); 
		RelativeLayout.LayoutParams params_main = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mainLayout.setLayoutParams(params_main);
		mainLayout.setId(mainLayoutID);
		mainLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		
		// 鐢熸垚椤堕儴鎸夐挳甯冨眬
		LinearLayout layTopControls = createLayout(LinearLayout.HORIZONTAL); 
		// 鐢熸垚椤堕儴鎸夐挳 锛堜笂涓�湀锛屼笅涓�湀锛屽綋鍓嶆湀锛�
		generateTopButtons(layTopControls); 
		RelativeLayout.LayoutParams params_title = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		params_title.topMargin = 5;

		layTopControls.setId(titleLayoutID);
		mainLayout.addView(layTopControls, params_title);

		calStartDate = getCalendarStartDate();

		setTitleGirdView();
		RelativeLayout.LayoutParams params_cal_title = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		params_cal_title.addRule(RelativeLayout.BELOW, titleLayoutID);
		
		mainLayout.addView(title_gView, params_cal_title);

		CreateGirdView();

		RelativeLayout.LayoutParams params_cal = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		params_cal.addRule(RelativeLayout.BELOW, caltitleLayoutID);

		mainLayout.addView(viewFlipper, params_cal);
		
		LinearLayout br = new LinearLayout(this);
		RelativeLayout.LayoutParams params_br = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, 1);
		params_br.addRule(RelativeLayout.BELOW, calLayoutID);
		//璁剧疆鑳屾櫙鑹�
		br.setBackgroundColor(getResources().getColor(R.color.calendar_background));
		mainLayout.addView(br, params_br);

		return mainLayout;

	}

	// 鍒涘缓涓�釜绾挎�甯冨眬
	// 鍙傛暟锛氭柟鍚�
	private LinearLayout createLayout(int iOrientation) {
		LinearLayout lay = new LinearLayout(this);
		LayoutParams params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,// *fill_parent锛屽～婊＄埗鎺т欢鐨勭┖鐧�
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		params.topMargin = 10;
		// 璁剧疆甯冨眬鍙傛暟
		// *wrap_content锛岃〃绀哄ぇ灏忓垰濂借冻澶熸樉绀哄綋鍓嶆帶浠堕噷鐨勫唴瀹�
		lay.setLayoutParams(params);
		// 璁剧疆鏂瑰悜
		lay.setOrientation(iOrientation);
		lay.setGravity(Gravity.LEFT);
		return lay;
	}

	// 鐢熸垚椤堕儴鎸夐挳
	// 鍙傛暟锛氬竷灞�
	private void generateTopButtons(LinearLayout layTopControls) {
		// 鍒涘缓涓�釜褰撳墠鏈堟寜閽紙涓棿鐨勬寜閽級
		btnToday = new Button(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = 20;
		btnToday.setLayoutParams(lp);
		btnToday.setTextSize(25);
		btnToday.setBackgroundResource(Color.TRANSPARENT);//
		
		// 璁剧疆褰撳墠鏈堟寜閽殑鑳屾櫙棰滆壊涓烘寜閽粯璁ら鑹�

		// 褰撳墠鏈堢殑鐐瑰嚮浜嬩欢鐨勭洃鍚�
		btnToday.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				setToDayViewItem();
			}
		});

		layTopControls.setGravity(Gravity.CENTER_HORIZONTAL);
		layTopControls.addView(btnToday);

	}

	private void setTitleGirdView() {

		title_gView = setGirdView();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		
		title_gView.setLayoutParams(params);
		title_gView.setVerticalSpacing(0);// 鍨傜洿闂撮殧
		title_gView.setHorizontalSpacing(0);// 姘村钩闂撮殧
		TitleGridAdapter titleAdapter = new TitleGridAdapter(this);
		title_gView.setAdapter(titleAdapter);// 璁剧疆鑿滃崟Adapter
		title_gView.setId(caltitleLayoutID);
	}

	private void CreateGirdView() {

		Calendar tempSelected1 = Calendar.getInstance(); // 涓存椂
		Calendar tempSelected2 = Calendar.getInstance(); // 涓存椂
		Calendar tempSelected3 = Calendar.getInstance(); // 涓存椂
		tempSelected1.setTime(calStartDate.getTime());
		tempSelected2.setTime(calStartDate.getTime());
		tempSelected3.setTime(calStartDate.getTime());

		gView1 = new CalendarGridView(mContext);
		tempSelected1.add(Calendar.MONTH, -1);
		gAdapter1 = new CalendarGridViewAdapter(this, tempSelected1);
		gView1.setAdapter(gAdapter1);// 璁剧疆鑿滃崟Adapter
		gView1.setId(calLayoutID);

		gView2 = new CalendarGridView(mContext);
		gAdapter = new CalendarGridViewAdapter(this, tempSelected2);
		gView2.setAdapter(gAdapter);// 璁剧疆鑿滃崟Adapter
		gView2.setId(calLayoutID);

		gView3 = new CalendarGridView(mContext);
		tempSelected3.add(Calendar.MONTH, 1);
		gAdapter3 = new CalendarGridViewAdapter(this, tempSelected3);
		gView3.setAdapter(gAdapter3);// 璁剧疆鑿滃崟Adapter
		gView3.setId(calLayoutID);

		gView2.setOnTouchListener(this);
		gView1.setOnTouchListener(this);
		gView3.setOnTouchListener(this);

		if (viewFlipper.getChildCount() != 0) {
			viewFlipper.removeAllViews();
		}

		viewFlipper.addView(gView2);
		viewFlipper.addView(gView3);
		viewFlipper.addView(gView1);

		String s = calStartDate.get(Calendar.YEAR)
				+ "-"
				+ NumberHelper.LeftPad_Tow_Zero(calStartDate
						.get(Calendar.MONTH) + 1);

		btnToday.setText(s);
	}

	private GridView setGirdView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		GridView gridView = new GridView(this);
		gridView.setLayoutParams(params);
		gridView.setNumColumns(7);// 璁剧疆姣忚鍒楁暟
		gridView.setGravity(Gravity.CENTER_VERTICAL);// 浣嶇疆灞呬腑
		gridView.setVerticalSpacing(1);// 鍨傜洿闂撮殧
		gridView.setHorizontalSpacing(1);// 姘村钩闂撮殧
		gridView.setBackgroundColor(getResources().getColor(
				R.color.calendar_background));//璁剧疆鑳屾櫙
		//璁剧疆鏄剧ず鍙傛暟
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int i = display.getWidth() / 7;
		int j = display.getWidth() - (i * 7);
		int x = j / 2;
		gridView.setPadding(x, 0, 0, 0);// 灞呬腑

		return gridView;
	}

	// 涓婁竴涓湀
	private void setPrevViewItem() {
		iMonthViewCurrentMonth--;// 褰撳墠閫夋嫨鏈�-
		// 濡傛灉褰撳墠鏈堜负璐熸暟鐨勮瘽鏄剧ず涓婁竴骞�
		if (iMonthViewCurrentMonth == -1) {
			iMonthViewCurrentMonth = 11;
			iMonthViewCurrentYear--;
		}
		calStartDate.set(Calendar.DAY_OF_MONTH, 1); // 璁剧疆鏃ヤ负褰撴湀1鏃�
		calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth); // 璁剧疆鏈�
		calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear); // 璁剧疆骞�

	}

	// 褰撴湀
	private void setToDayViewItem() {

		calSelected.setTimeInMillis(calToday.getTimeInMillis());
		calSelected.setFirstDayOfWeek(iFirstDayOfWeek);
		calStartDate.setTimeInMillis(calToday.getTimeInMillis());
		calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);

	}

	// 涓嬩竴涓湀
	private void setNextViewItem() {
		iMonthViewCurrentMonth++;
		if (iMonthViewCurrentMonth == 12) {
			iMonthViewCurrentMonth = 0;
			iMonthViewCurrentYear++;
		}
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);
		calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
		calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);

	}

	// 鏍规嵁鏀瑰彉鐨勬棩鏈熸洿鏂版棩鍘�
	// 濉厖鏃ュ巻鎺т欢鐢�
	private void UpdateStartDateForMonth() {
		// 璁剧疆鎴愬綋鏈堢涓�ぉ
		calStartDate.set(Calendar.DATE, 1); 
		// 寰楀埌褰撳墠鏃ュ巻鏄剧ず鐨勬湀
		iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);
		// 寰楀埌褰撳墠鏃ュ巻鏄剧ず鐨勫勾
		iMonthViewCurrentYear = calStartDate.get(Calendar.YEAR);

		String s = calStartDate.get(Calendar.YEAR)
				+ "-"
				+ NumberHelper.LeftPad_Tow_Zero(calStartDate
						.get(Calendar.MONTH) + 1);
		btnToday.setText(s);

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

	}

	private Calendar getCalendarStartDate() {
		calToday.setTimeInMillis(System.currentTimeMillis());
		calToday.setFirstDayOfWeek(iFirstDayOfWeek);

		if (calSelected.getTimeInMillis() == 0) {
			calStartDate.setTimeInMillis(System.currentTimeMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		} else {
			calStartDate.setTimeInMillis(calSelected.getTimeInMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		}

		return calStartDate;
	}
	//鑷畾涔塧dapter
	public class TitleGridAdapter extends BaseAdapter {
		//灏唗itles瀛樺叆鏁扮粍
		int[] titles = new int[] { R.string.Sun, R.string.Mon, R.string.Tue,
				com.george.mylifeassistant.R.string.Wed, com.george.mylifeassistant.R.string.Thu, R.string.Fri, R.string.Sat };

		private Activity activity;

		// construct
		public TitleGridAdapter(Activity a) {
			activity = a;
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public Object getItem(int position) {
			return titles[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		//璁剧疆澶栬
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout iv = new LinearLayout(activity);
			TextView txtDay = new TextView(activity);
			txtDay.setFocusable(false);
			txtDay.setBackgroundColor(Color.TRANSPARENT);
			iv.setOrientation(1);

			txtDay.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

			int i = (Integer) getItem(position);

			txtDay.setTextColor(Color.WHITE);
			Resources res = getResources();

			if (i == com.george.mylifeassistant.R.string.Sat) {
				// 鍛ㄥ叚
				txtDay.setBackgroundColor(res.getColor(com.george.mylifeassistant.R.color.title_text_6));
			} else if (i == com.george.mylifeassistant.R.string.Sun) {
				// 鍛ㄦ棩
				txtDay.setBackgroundColor(res.getColor(com.george.mylifeassistant.R.color.title_text_7));
			} else {

			}

			txtDay.setText((Integer) getItem(position));

			iv.addView(txtDay, lp);

			return iv;
		}
	}

}
