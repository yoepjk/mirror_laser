package com.example.jong.mirrors.level.laser;

import java.util.ArrayList;

import com.example.jong.mirrors.level.object.*;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Laser {
	Paint paint;
	ArrayList<Ray> rays = new ArrayList<Ray>();
	Ray startRay = null;

	public Laser(Paint paint, Ray startRay) {
		this.paint = paint;
		this.startRay = startRay;
	}

	public void onDraw(Canvas canvas) {

		for (int i = 0; i < rays.size(); i++) {
			rays.get(i).onDraw(canvas, paint);
		}
	}

	public void init() {
		// ray�迭 �ʱ�ȭ
		rays = new ArrayList<Ray>();

		// �ʱ� ray�� �ִ´�
		startRay.distance = 1000.f;
		rays.add(startRay);
	}

	// ������ �ùķ��̼�
	public void simulate(ArrayList<LevelObject> objects) {

		// ray�� �ʱ�ȭ
		init();

		// ray�� ���� ������Ʈ�� ���� �׽�Ʈ
		for (int i = 0; i < rays.size(); i++) {
			ArrayList<Ray> reflectedRays = rays.get(i).intersectionProcess(objects);
			
			// �ݻ�� ray�� �߰�
			for (Ray r : reflectedRays)
				rays.add(r);
		}

	}

}

class IntersectionResult {
	float t = -1.f;
	float s = -1.f;
}
