package com.example.jong.mirrors.level.object;

import com.example.jong.mirrors.MirrorActivity;
import com.example.jong.mirrors.level.laser.Laser;
import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.algorithm.intersection.CircleIntersectionAlgorithm;
import com.example.jong.mirrors.level.object.algorithm.reflection.NoneReflectionAlgorithm;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;


//Goal
public class Goal extends CircleObject {
	
	Paint normalPaint;
	Paint highlightedPaint;
	MirrorActivity mirrorActivity;

	public Goal(Paint normal, Paint highlighted, MirrorActivity mirrorActivity) {
		super(normal);
		
		this.mirrorActivity = mirrorActivity;
		
		this.normalPaint = normal;
		this.highlightedPaint = highlighted;
				
		intersectionAlgorithm = new CircleIntersectionAlgorithm();
		reflectionAlgorithm = new NoneReflectionAlgorithm();
	}
	
	public void passGoal() {
		
		this.paint = highlightedPaint;
		finishState();
	}
	
	@Override
	public void reset() {
		this.paint = normalPaint;
	}

	
	public void finishState() {
		mirrorActivity.finishState();
	}

}