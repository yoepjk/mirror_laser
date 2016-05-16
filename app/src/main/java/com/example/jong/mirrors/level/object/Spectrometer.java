package com.example.jong.mirrors.level.object;

import com.example.jong.mirrors.level.object.algorithm.intersection.CircleIntersectionAlgorithm;
import com.example.jong.mirrors.level.object.algorithm.reflection.NoneReflectionAlgorithm;
import com.example.jong.mirrors.level.object.algorithm.reflection.SpectromReflectionAlgorithm;
import android.graphics.Canvas;
import android.graphics.Paint;


// �б���
public class Spectrometer extends CircleObject{
	
	public Spectrometer(Paint paint) {
		super(paint);
		
		intersectionAlgorithm = new CircleIntersectionAlgorithm();
		reflectionAlgorithm = new SpectromReflectionAlgorithm();
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(centerPoint.x, centerPoint.y, radius, paint);
		canvas.drawLine(centerPoint.x - (radius/2), centerPoint.y + (radius/4), centerPoint.x, centerPoint.y + (float)(radius*0.8), paint);
		canvas.drawLine(centerPoint.x + (radius/2), centerPoint.y + (radius/4), centerPoint.x, centerPoint.y + (float)(radius*0.8), paint);
	}
	
}
