package com.example.jong.mirrors;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StageChoiceActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.choice);
				
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Button stage1 = (Button)findViewById(R.id.stage1);
		stage1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(StageChoiceActivity.this, MirrorActivity.class);		
				intent.putExtra("stage", 1);
				startActivity(intent);	
			}
		});	
		
		Button stage2 = (Button)findViewById(R.id.stage2);
		stage2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(StageChoiceActivity.this, MirrorActivity.class);		
				intent.putExtra("stage", 2);
				startActivity(intent);	
			}
		});	
		
		Button stage3 = (Button)findViewById(R.id.stage3);
		stage3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(StageChoiceActivity.this, MirrorActivity.class);		
				intent.putExtra("stage", 3);
				startActivity(intent);	
			}
		});	
		
		Button stage4 = (Button)findViewById(R.id.stage4);
		stage4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(StageChoiceActivity.this, MirrorActivity.class);		
				intent.putExtra("stage", 4);
				startActivity(intent);	
			}
		});	
		
		Button stage5 = (Button)findViewById(R.id.stage5);
		stage5.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(StageChoiceActivity.this, MirrorActivity.class);		
				intent.putExtra("stage", 5);
				startActivity(intent);	
			}
		});	
		

		
	}

}
