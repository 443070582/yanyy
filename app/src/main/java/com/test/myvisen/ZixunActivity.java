package com.test.myvisen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ZixunActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(com.test.myvisen.R.layout.activity_zixun);
	}


}
