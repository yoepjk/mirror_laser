package com.example.jong.mirrors.level;

import java.util.ArrayList;

import com.example.jong.mirrors.level.object.*;
import com.example.jong.mirrors.level.laser.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Level {

	public Paint blackPaint;
	public Paint magentaPaint;
	public Paint redPaint;
	public Paint yellowPaint;
	public Paint bluePaint;
	public Paint greenPaint;

	Laser laser = null;
	ArrayList<LevelObject> objects = new ArrayList<LevelObject>();
	Ray startRay;

	public Level() {

		// �� �ʱ�ȭ
		blackPaint = initPaint(new Paint(), Color.BLACK, 4);
		magentaPaint = initPaint(new Paint(), Color.MAGENTA, 4);
		redPaint = initPaint(new Paint(), Color.RED, 4);
		greenPaint = initPaint(new Paint(), Color.GREEN, 4);
		yellowPaint = initPaint(new Paint(), Color.YELLOW, 4);
		bluePaint = initPaint(new Paint(), Color.BLUE, 4);

		// ���� ray
		startRay = new Ray();
		startRay.getStart().x = 50;
		startRay.getStart().y = 50;
		startRay.getDirection().x = 0;
		startRay.getDirection().y = 1;
		startRay.setDistance(1000.0f);

		// ������ ����
		laser = new Laser(redPaint, startRay);

		// ������ �ùķ��̼�
		laser.simulate(objects);
	}
	
	// ���� ���� ���
	public Ray getStartRay() {
		return startRay;
	}

	// ������Ʈ �߰�
	public LevelObject addObject(LevelObject object) {
		objects.add(object);
		return object;
	}
	
	
	// �� �ʱ�ȭ
	private Paint initPaint(Paint paint, int color, int width) {
		paint.setAntiAlias(true); // anti-aliasing ����(true�̸� �����ϰų� ���������� �ʴ� �⺻
		// ��)
		paint.setDither(true);
		paint.setColor(color); // �� ����
		paint.setStyle(Paint.Style.STROKE); // �� ��Ÿ��(���)
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(width); // �� ����

		return paint;
	}


	// �׸��� �Լ�
	public void onDraw(Canvas canvas) {

		canvas.drawColor(0xFFFFFFFF);

		// ������Ʈ�� �׸���.
		for (LevelObject o : objects)
			o.draw(canvas);

		// ������ �׸���.
		if (laser != null) {
			laser.onDraw(canvas);
		}

	}
	
	
	// ���� Ŭ���� ���� �ſ�(���� ������ ������)���� Ȯ��
	public boolean touchCheck(int x, int y) {
		
		boolean result = false;
		
		// ��� ������Ʈ�� ��ġ ȣ��
		for (LevelObject object : objects)
			if (object.touch(x, y))
				result = true;
		
		return result;
	}
	

	// ������Ʈ
	public void update(){
		laser.simulate(objects);
	}

	
	// ���� ����
	public void reset()
	{
		// ������Ʈ �迭 �ʱ�ȭ
		ArrayList<LevelObject> newObjects = new ArrayList<LevelObject>();
		for (LevelObject obj : objects) {
			obj.reset();
					
			
			// ���ο� �迭�� ����
			newObjects.add(obj);
		}
		objects = newObjects;
		
		// ������ �ʱ�ȭ
		laser.init();
	}

	
	public void test()
	{
		Link l = new Link();
		
		int count = 2;
		for (LevelObject obj : objects) {
			
			if (count <= 0)
				break;
			else if (count == 1)
				obj.setControllable(false);
			
			if (obj instanceof Mirror)
			{
				l.addObject(obj);
				--count;
			}
		}
		
	}
	
}




















