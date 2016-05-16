package com.example.jong.mirrors;


import com.example.jong.mirrors.level.object.*;
import com.example.jong.mirrors.level.*;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;


public class MyView extends View {
	Level curLevel;	
	
	boolean finish = false;
	boolean onDrag = false;
	
	SegmentObject segment;

	
	public MyView(Context context) {
		super(context);

		curLevel = new Level();
	}
	
	
	
	public void setCurLevel(Level level) {
		curLevel = level;
	}

	
	
	public void reset() {
		if (curLevel != null) {
			
			curLevel.reset();
			
			curLevel.update();
	
			// ��ġ�� �����ϰ� �Ѵ�.
			finish = false;
			
			invalidate();
		}
	}

	
	protected void onDraw(Canvas canvas) {

		if(curLevel != null) {
			curLevel.onDraw(canvas);
		}
		
	}


	private void touch_start(int x, int y) {

		if (curLevel.touchCheck(x, y))
			curLevel.update();
	}

	
	private void touch_move(int x, int y) {

	}

	
	
	private void touch_up() {

		
	}
	
	
	

	public boolean onTouchEvent(MotionEvent event) {
		
		// Ray�� Goal�� ������ ���̻� ��ġ�� �ȵǰ� �Ѵ�.
		if(finish == true)			
			return false;
				
		if (curLevel == null)
			return true;
				
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		
		return true;
	}

}
