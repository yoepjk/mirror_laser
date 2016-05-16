package com.example.jong.mirrors.level.object;

import com.example.jong.mirrors.level.object.algorithm.intersection.SegmentIntersectionAlgorithm;
import android.R.bool;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class SegmentObject extends LevelObject {
	
	PointF start = new PointF();
	PointF end = new PointF();
	float length;
	float angle;
	
	SegmentObject(Paint paint) {
		super(paint);
		
		intersectionAlgorithm = new SegmentIntersectionAlgorithm();
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		if(controllable == true) {
				
			canvas.drawCircle(centerPoint.x, centerPoint.y, axisRadius, paint);
		}
		
		canvas.drawLine(start.x, start.y, end.x, end.y, paint);
	}
	
	@Override
	protected boolean touchCheck(int x, int y) {

		// �ſ��� ���� ������ �ʾ����� ����
		float distance = (centerPoint.x - x) * (centerPoint.x - x) + (centerPoint.y - y) * (centerPoint.y - y);
		if(distance > axisRadius * axisRadius)
			return false;
		
		return true;
	}
	
	@Override
	void onTouch(int x, int y) {
		
		adjustAngle();
	}
	
	
	
	
	public PointF getStart() {
		return start;
	}
	
	
	public PointF getEnd() {
		return end;
	}
	

	public PointF getCenterPoint() {
		return centerPoint;
	}
	

	public void set(PointF start, float degree, float length) {
		double angle = Math.toRadians(degree);
		
		end.x = (int)(start.x + length * Math.cos(angle));
		end.y = (int)(start.y + length * Math.sin(angle));
	}
	
	
	// Mirror ����
	public void setMirror(float angle, float length) {
		
		this.angle = angle;
		this.length = length;
		
		double degree = Math.toRadians(angle);
		
		start.x = (int)(centerPoint.x + (length / 2 * Math.cos(degree)));
		start.y = (int)(centerPoint.y + (length / 2 * Math.sin(degree)));
		
		end.x = (int)(centerPoint.x - (length / 2 * Math.cos(degree)));
		end.y = (int)(centerPoint.y - (length / 2 * Math.sin(degree)));		
	}
	
	
	
	// ���� ���� ������ ����
	public void adjustAngle(double unit) {
		
		// ������ unit �������� �ſ��� �׸��� �Ѵ�.
		angle += unit;
		
		double degree = Math.toRadians(angle);
		
		float dx = (length / 2 * (float)Math.cos(degree)); 
		float dy = (length / 2 * (float)Math.sin(degree));

		// ���� �ſ��� ��ǥ�� ����
		start.x = centerPoint.x - dx;
		start.y = centerPoint.y - dy;

		end.x = centerPoint.x + dx;
		end.y = centerPoint.y + dy;
	}
	
	public void adjustAngle() {
		// defult unit
		adjustAngle(-15.0);
	}
	
	
}

















