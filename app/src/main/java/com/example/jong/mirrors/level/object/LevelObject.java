package com.example.jong.mirrors.level.object;

import java.util.ArrayList;

import com.example.jong.mirrors.level.laser.Laser;
import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.algorithm.intersection.IIntersectionAlgorithm;
import com.example.jong.mirrors.level.object.algorithm.reflection.IReflectionAlgorithm;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public abstract class LevelObject {
	
	Paint paint;
	IIntersectionAlgorithm intersectionAlgorithm;
	IReflectionAlgorithm reflectionAlgorithm;
	public PointF centerPoint = new PointF();
	boolean controllable = false;
	public static final int axisRadius = 20;
	private Link link;

	
	// ������
	public LevelObject(Paint paint) {
		this.paint = paint;
	}
	
	// �׸���
	public abstract void draw(Canvas canvas);
	
	// ray�� ���� ó��.. t�� ����
	public float intersectionTest(Ray curRay) {
		return intersectionAlgorithm.intersectionTest(curRay, this);
	}

	public ArrayList<Ray> reflect(Ray curRay, float tValue) {
		return reflectionAlgorithm.reflect(curRay, this, tValue);
	}
	
	public void reset() {
		
	}
	
	public boolean touch(int x, int y) {
		
		if (controllable == false || touchCheck(x, y) == false)
			return false;
		
		if(link != null)
			link.onTouch(x, y);
		else
			onTouch(x, y);
		
		return true;
	}
	
	void setLink(Link link) {
		this.link = link;
	}
	
	public void setControllable(boolean controllable) {
		
		this.controllable = controllable;		
	}
	
	
	public boolean getControllable() {
		return controllable;
	}
	
	abstract protected boolean touchCheck(int x, int y);
	
	// ��ġ �׽�Ʈ..
	abstract void onTouch(int x, int y);
}

















