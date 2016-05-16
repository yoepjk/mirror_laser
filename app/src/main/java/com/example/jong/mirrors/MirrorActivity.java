package com.example.jong.mirrors;


import com.example.jong.mirrors.level.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.jong.mirrors.level.LevelImporter;


public class MirrorActivity extends Activity {

	final Context mContext = this;
	MyView mView;
	int curStage;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mView = (MyView)new MyView(this);
		setContentView(mView);

//		LinearLayout root = new LinearLayout(this);
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//				ViewGroup.LayoutParams.FILL_PARENT,
//				ViewGroup.LayoutParams.FILL_PARENT, 
//				0.0F);
//
//		root.setLayoutParams(params);
//		root.setOrientation(LinearLayout.VERTICAL);

//		mView = (MyView)new MyView(this);
//		LinearLayout.LayoutParams mvParams = new LinearLayout.LayoutParams(
//				ViewGroup.LayoutParams.WRAP_CONTENT,
//				ViewGroup.LayoutParams.WRAP_CONTENT,
//				0.0F);
//		mView.setLayoutParams(mvParams);


//		View view = (View)View.inflate(this, R.layout.main, null);
//		mvParams = new LinearLayout.LayoutParams(
//				ViewGroup.LayoutParams.WRAP_CONTENT,
//				ViewGroup.LayoutParams.WRAP_CONTENT,
//				0.0F);
//		view.setLayoutParams(mvParams);
//
//		root.addView(view);
//		root.addView(mView);
//
//		setContentView(root);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		curStage = getIntent().getIntExtra("stage", 0);				
		reset();

//		Button resetButton = (Button)findViewById(R.id.resetButton);
//		resetButton.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				reset();
//			}		
//		});
//
	}

	
	public void reset() {
		
		reset(curStage);
	}
	
	public void reset(int stage) {
		
		curStage = stage;
				
		String xmlFile = "stage";
		xmlFile += curStage;
		xmlFile += ".xml";

		LevelImporter xmlLoad = new LevelImporter(mContext, MirrorActivity.this);
		mView.setCurLevel(xmlLoad.load(xmlFile));
		mView.reset();
	}

	@Override
	public void onBackPressed() {

		String dialogMessage = "나가시겠습니까?";
		String buttonRestart = "재시작";
		String buttonExit= "종료";

		new AlertDialog.Builder(MirrorActivity.this)
		.setMessage(dialogMessage)
		.setPositiveButton(buttonRestart, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				reset();
			}
		})
		.setNegativeButton( buttonExit, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		}).show();
	}



	public void finishState() {
		mView.finish = true;
		
		final String dialogMessage = "성공하셨습니다.";
		final String buttonRestart = "재시작";
		final String buttonExit= "종료";

		final boolean cancelable = false;
		
		new Handler().postDelayed(new Runnable()
		{
		    public void run()
		    {
		    	new AlertDialog.Builder(MirrorActivity.this)
				.setCancelable(cancelable)		
				.setMessage(dialogMessage)
//				.setPositiveButton(buttonNextRevel, new DialogInterface.OnClickListener() {
					
//					public void onClick(DialogInterface dialog, int which) {
//						
//					}
//				})
				.setNeutralButton(buttonRestart, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						reset();
					}
				})
				.setNegativeButton(buttonExit, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).show();		        
		    }
		}, 500);
		
		
		
		
		
	}

}




















