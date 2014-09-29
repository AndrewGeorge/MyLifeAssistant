package com.george.mylifeassistant.weather.caculator;
import com.george.mylifeassistant.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CaCulatorAvtivity extends Activity implements
		android.view.View.OnClickListener {
	String str = "";
	Button clear_btn, delete_btn, chu_btn, cheng_btn, add_btn, jian_btn,
			equal_btn, dian_btn, one_btn, two_btn, three_btn, four_btn,
			five_btn, sixe_btn, seven_btn, eight_btn, nine_btn, zeor_btn;
	TextView show_tv;
	
	//布尔值标示是否触发对应事件
	boolean dian = false;
	boolean flagadd = false;
	boolean flagjian = false;
	boolean flagchu = false;
	boolean flagcheng = false;
	
	//字符串截取的变量
	int flaadd;
	int flajian;
	int flachu;
	int flacheng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.caculator);
		initView();
		
	}
	/**
	 * 
	 *初始化数据
	 */
	private void initView() {

		show_tv = (TextView) findViewById(R.id.show_tv);

		show_tv = (TextView) findViewById(R.id.show_tv);
		show_tv.setOnClickListener(this);
		clear_btn = (Button) findViewById(R.id.clear_btn);
		clear_btn.setOnClickListener(this);
		delete_btn = (Button) findViewById(R.id.delete_btn);
		delete_btn.setOnClickListener(this);
		chu_btn = (Button) findViewById(R.id.chu_btn);
		chu_btn.setOnClickListener(this);
		cheng_btn = (Button) findViewById(R.id.cheng_btn);
		cheng_btn.setOnClickListener(this);
		add_btn = (Button) findViewById(R.id.add_btn);
		add_btn.setOnClickListener(this);
		jian_btn = (Button) findViewById(R.id.jian_btn);
		jian_btn.setOnClickListener(this);
		equal_btn = (Button) findViewById(R.id.equal_btn);
		equal_btn.setOnClickListener(this);
		dian_btn = (Button) findViewById(R.id.dian_btn);
		dian_btn.setOnClickListener(this);
		one_btn = (Button) findViewById(R.id.one_btn);
		one_btn.setOnClickListener(this);
		two_btn = (Button) findViewById(R.id.two_btn);
		two_btn.setOnClickListener(this);
		three_btn = (Button) findViewById(R.id.three_btn);
		three_btn.setOnClickListener(this);
		four_btn = (Button) findViewById(R.id.foure_btn);
		four_btn.setOnClickListener(this);
		five_btn = (Button) findViewById(R.id.five_btn);
		five_btn.setOnClickListener(this);
		sixe_btn = (Button) findViewById(R.id.sixe_btn);
		sixe_btn.setOnClickListener(this);
		seven_btn = (Button) findViewById(R.id.seven_btn);
		seven_btn.setOnClickListener(this);
		eight_btn = (Button) findViewById(R.id.eight_btn);
		eight_btn.setOnClickListener(this);
		nine_btn = (Button) findViewById(R.id.nine_btn);
		nine_btn.setOnClickListener(this);
		zeor_btn = (Button) findViewById(R.id.zeor_btn);
		zeor_btn.setOnClickListener(this);
	}
/****
 * 
 * Button 监听事件
 */
	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {

		case R.id.clear_btn:
			str = "";
			dian = false;
			flagadd = false;
			flagjian = false;
			flagchu = false;
			flagcheng = false;
			break;
		case R.id.delete_btn:
			if (str.length() > 0) {

				str = str.substring(0, str.length() - 1);
			}
			if (str.length() == 1) {

				boolean dian = false;
			}
			break;
		case R.id.chu_btn:
			if (flagcheng == true) {

				str = cheng(str.substring(0, flacheng),
						str.substring(flacheng + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagchu == true) {

				str = chu(str.substring(0, flachu),
						str.substring(flachu + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagadd == true) {

				str =Add(str.substring(0, flaadd),
						str.substring(flaadd + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagjian == true) {

				str = jian(str.substring(0, flajian),
						str.substring(flajian + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			flachu = str.length();
			str = str + "/";
			flagchu = true;
			break;
		case R.id.cheng_btn:
			if (flagcheng == true) {

				str = cheng(str.substring(0, flacheng),
						str.substring(flacheng + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagchu == true) {

				str = chu(str.substring(0, flachu),
						str.substring(flachu + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagadd == true) {

				str =Add(str.substring(0, flaadd),
						str.substring(flaadd + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagjian == true) {

				str = jian(str.substring(0, flajian),
						str.substring(flajian + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			flacheng = str.length();
			str = str + "*";
			flagcheng = true;
			break;
		case R.id.jian_btn:
			if (flagcheng == true) {

				str = cheng(str.substring(0, flacheng),
						str.substring(flacheng + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagchu == true) {

				str = chu(str.substring(0, flachu),
						str.substring(flachu + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagadd == true) {

				str =Add(str.substring(0, flaadd),
						str.substring(flaadd + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagjian == true) {

				str = jian(str.substring(0, flajian),
						str.substring(flajian + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			flajian = str.length();
			str = str + "-";
			flagjian = true;
			break;
		case R.id.add_btn:
			if (flagcheng == true) {

				str = cheng(str.substring(0, flacheng),
						str.substring(flacheng + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagchu == true) {

				str = chu(str.substring(0, flachu),
						str.substring(flachu + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagadd == true) {

				str =Add(str.substring(0, flaadd),
						str.substring(flaadd + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagjian == true) {

				str = jian(str.substring(0, flajian),
						str.substring(flajian + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			flaadd = str.length();
			str = str + "+";
			flagadd = true;
			break;
		case R.id.equal_btn:

			if (flagadd == true) {

				str = Add(str.substring(0, flaadd),
						str.substring(flaadd + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagjian == true) {

				str = jian(str.substring(0, flajian),
						str.substring(flajian + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagchu == true) {

				str = chu(str.substring(0, flachu),
						str.substring(flachu + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			if (flagcheng == true) {

				str = cheng(str.substring(0, flacheng),
						str.substring(flacheng + 1, str.length()));
				flagadd = false;
				flagjian = false;
				flagcheng = false;
				flagchu = false;
			}
			break;
		case R.id.dian_btn:

			if (dian == false && str.length() == 0) {

				str = str + "0.";
				dian = true;
			}
			if (dian == false && str.length() > 0) {

				str = str + ".";
				dian = true;
			}

			break;
		case R.id.zeor_btn:
			str = str + "0";
			break;
		case R.id.one_btn:
			str = str + "1";
			break;
		case R.id.two_btn:
			str = str + "2";
			break;
		case R.id.three_btn:
			str = str + "3";
			break;
		case R.id.foure_btn:
			str = str + "4";
			break;
		case R.id.five_btn:
			str = str + "5";
			break;
		case R.id.sixe_btn:
			str = str + "6";
			break;
		case R.id.seven_btn:
			str = str + "7";
			break;
		case R.id.eight_btn:
			str = str + "8";
			break;
		case R.id.nine_btn:
			str = str + "9";
			break;

		default:

			break;
		}

		show_tv.setText(str);

	}
	/**
	 * 数据格式转换
	 * @param str1
	 * @param str2
	 * @return Number
	 */

	private Number format(String str1, String str2) {

		int n1 = Integer.parseInt(str1);
		int n2 = Integer.parseInt(str2);
		Number number = new Number();
		number.n1 = n1;
		number.n2 = n2;
		return number;

	}

	/***
	 * 进行加法计算
	 * 
	 * @param str1
	 * @param str2
	 * @return String
	 */
	private String Add(String str1, String str2) {

		Number number = format(str1, str2);
		int result = number.n1 + number.n2;
		return String.valueOf(result);
	}

	/***
	 * 进行减法计算
	 * 
	 * @param str1
	 * @param str2
	 * @return String
	 */
	private String jian(String str1, String str2) {

		Number number = format(str1, str2);
		int result = number.n1 - number.n2;
		return String.valueOf(result);
	}

	/**
	 * 
	 * 进行除法计算
	 * 
	 * @param str1
	 * @param str2
	 * @return String
	 */
	private String chu(String str1, String str2) {

		Number number = format(str1, str2);
		int result = number.n1 / number.n2;
		return String.valueOf(result);
	}

	/**
	 * 
	 * 进行除法计算
	 * 
	 * @param str1
	 * @param str2
	 * @return String
	 */
	private String cheng(String str1, String str2) {

		Number number = format(str1, str2);
		int result = number.n1 * number.n2;
		return String.valueOf(result);
	}
}
