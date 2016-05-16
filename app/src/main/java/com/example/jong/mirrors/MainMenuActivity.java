package com.example.jong.mirrors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenuActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.mainmenu);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Button startButton = (Button)findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intent = new Intent(MainMenuActivity.this, StageChoiceActivity.class);						
				startActivity(intent);	
			}
		});

		Button optionButton = (Button)findViewById(R.id.optionButton);
		optionButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
			}
		}); 

		Button exitButton = (Button)findViewById(R.id.exitButton);
		exitButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				onBackPressed();
			}
		});
	}


	@Override
	public void onBackPressed() {
		
		String dialogMessage = "종료하시겠습니까?";
		String buttonYes = "확인";
		String buttonNo = "취소";
		
		new AlertDialog.Builder(MainMenuActivity.this)
		.setMessage(dialogMessage)
		.setPositiveButton( buttonYes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which)
			{
				moveTaskToBack(true);
				finish();
			}
		}).setNegativeButton(buttonNo, null).show();
	}


}












