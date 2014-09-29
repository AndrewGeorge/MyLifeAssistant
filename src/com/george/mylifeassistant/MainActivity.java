package com.george.mylifeassistant;

import com.dcs.test.Activity.BaseCalendar;
import com.george.mylifeassistant.notebook.NoteBookActivity;
import com.george.mylifeassistant.weather.WeatherMainActivity;
import com.george.mylifeassistant.weather.about.AboutActivity;
import com.george.mylifeassistant.weather.caculator.CaCulatorAvtivity;
import com.george.mylifeassistant.weather.contents.ContentsActivity;
import com.george.mylifeassistant.weather.search.SearchActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button weather_btn, noteBook_btn, search_btn,contents_btn,cacluater_btn,calender_btn,about_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inite();

	}
	public void inite() {
		
		myListener listener=new myListener();
		cacluater_btn=(Button) findViewById(R.id.calculator_btn);
		cacluater_btn.setOnClickListener(listener);
		search_btn = (Button) findViewById(R.id.search_btn);
		search_btn.setOnClickListener(listener);
		weather_btn = (Button) findViewById(R.id.weather_View);
		weather_btn.setOnClickListener(listener);
		noteBook_btn = (Button) findViewById(R.id.notebook_btn);
		noteBook_btn.setOnClickListener(listener);
		contents_btn=(Button) findViewById(R.id.contents_btn);
		contents_btn.setOnClickListener(listener);
		calender_btn=(Button) findViewById(R.id.calender_btn);
		calender_btn.setOnClickListener(listener);
		about_btn=(Button) findViewById(R.id.about_btn);
		about_btn.setOnClickListener(listener);
	}
	private class myListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {

			int id = arg0.getId();
			switch (id) {
			case R.id.weather_View:
				Intent intent1 = new Intent(MainActivity.this,
						WeatherMainActivity.class);
				startActivity(intent1);
				break;
			case R.id.notebook_btn:
				Intent intent2 = new Intent(MainActivity.this,
						NoteBookActivity.class);
				startActivity(intent2);
				break;
			case R.id.search_btn:
				Intent intent3=new Intent(MainActivity.this,SearchActivity.class);
				startActivity(intent3);
				break;
			case R.id.contents_btn:
				Intent intent4=new Intent(MainActivity.this,ContentsActivity.class);
				startActivity(intent4);
				break;
			case R.id.calculator_btn:
				Intent intent5=new Intent(MainActivity.this,CaCulatorAvtivity.class);
				startActivity(intent5);
				break;
			case R.id.calender_btn :
				Intent intent6=new Intent(MainActivity.this,BaseCalendar.class);
				startActivity(intent6);
				break;
			case R.id.about_btn:
				Intent intent7=new Intent(MainActivity.this,AboutActivity.class);
				startActivity(intent7);
				break;

			default:
				break;
			}

		}

	}

}
