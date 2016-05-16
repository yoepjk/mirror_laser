package com.example.jong.mirrors.level.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class CircleObject extends LevelObject {

	float radius;
	
	public CircleObject(Paint paint) {
		super(paint);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(centerPoint.x, centerPoint.y, radius, paint);
	}
	
	@Override
	protected boolean touchCheck(int x, int y) {
		return false;
	}
	
	@Override
	void onTouch(int x, int y) {
		
	}
	
	
	public float getRadius() {
		return radius;
	}
	
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	
	public PointF getCenterPoint() {
		return centerPoint;
	}
}
