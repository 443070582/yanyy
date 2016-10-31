package com.test.myvisen;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class MyTabActivity extends TabActivity
{
	private RadioGroup main_radio;
	private RadioButton tab_home, tab_message, tab_add, tab_discover;
	private TabHost mHost;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		mHost = this.getTabHost();
		// 添加N个tab选项卡，定义他们的tab名，指示名，目标屏对应的类。
		mHost.addTab(mHost.newTabSpec("one").setIndicator("1")
				.setContent(new Intent(this, MainActivity.class)));
		mHost.addTab(mHost.newTabSpec("two").setIndicator("2")
				.setContent(new Intent(this, ThirdActivity.class)));
		mHost.addTab(mHost.newTabSpec("three").setIndicator("3")
				.setContent(new Intent(this, ZixunActivity.class)));
		mHost.addTab(mHost.newTabSpec("four").setIndicator("4")
				.setContent(new Intent(this, DisiActivity.class)));
		main_radio = (RadioGroup) findViewById(R.id.main_radio);
		tab_home = (RadioButton) findViewById(R.id.radio_button0);
		tab_message = (RadioButton) findViewById(R.id.radio_button1);
		tab_add = (RadioButton) findViewById(R.id.radio_button2);
		tab_discover = (RadioButton) findViewById(R.id.radio_button3);
		main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
					public void onCheckedChanged(RadioGroup group, int id)
					{
						switch (id)
						{
						
						case R.id.radio_button0://首页
							mHost.setCurrentTabByTag("one");
							break;
						case R.id.radio_button1://课程
							mHost.setCurrentTabByTag("two");
							break;
						case R.id.radio_button2://咨询
							mHost.setCurrentTabByTag("three");
							break;
						case R.id.radio_button3://我的
							mHost.setCurrentTabByTag("four");
							break;
						
						}
					}
				});
	}

}
