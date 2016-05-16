package com.example.jong.mirrors.level.laser;

import java.util.ArrayList;

import com.example.jong.mirrors.MirrorActivity;
import com.example.jong.mirrors.Util;
import com.example.jong.mirrors.level.object.*;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;


public class Ray {
	PointF start = new PointF();
	PointF end = new PointF();
	PointF direction = new PointF(); // ���� ����
	float distance;
	LevelObject objectForPass = null;

	public void onDraw(Canvas canvas, Paint paint) {

		canvas.drawLine(start.x, start.y, start.x + direction.x * distance,
				start.y + direction.y * distance, paint);
	}

	// ���� �˰���
	public IntersectionResult intersectionAlgorithmWithMirror(PointF end, Mirror mirror) {
		// �ſ�� �������� ���� �������� Ȯ��
		if ((mirror.getEnd().y - mirror.getStart().y) * (end.x - start.x)
				- (mirror.getEnd().x - mirror.getStart().x) * (end.y - start.y) != 0) {
			// ������ �ƴ� ���
			IntersectionResult result = new IntersectionResult();

			// ���� �˻�
			result.t = ((mirror.getEnd().x - mirror.getStart().x)
					* (start.y - mirror.getStart().y) - (mirror.getEnd().y - mirror
							.getStart().y) * (start.x - mirror.getStart().x))
							/ ((mirror.getEnd().y - mirror.getStart().y)
									* (end.x - start.x) - (mirror.getEnd().x - mirror
											.getStart().x) * (end.y - start.y));

			result.s = ((end.x - start.x) * (start.y - mirror.getStart().y) - (end.y - start.y)
					* (start.x - mirror.getStart().x))
					/ ((mirror.getEnd().y - mirror.getStart().y)
							* (end.x - start.x) - (mirror.getEnd().x - mirror
									.getStart().x) * (end.y - start.y));

			if (result.t < 0 || result.t > 1 || result.s < 0 || result.s > 1) {
				// �ſ�� �������� ���� ���
				return null;
			} else
				return result;
		}

		return null;
	}



	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public PointF getStart() {
		return start;
	}
	
	public PointF getEnd() {
		return end;
	}

	public PointF getDirection() {
		return direction;
	}
	
	public LevelObject getObjectForPass() {
		return objectForPass;
	}
	
	public void setObjectForPass(LevelObject object) {
		objectForPass = object;
	}

	// ���� �׽�Ʈ
	public ArrayList<Ray> intersectionProcess(ArrayList<LevelObject> objects) {
		
		ArrayList<Ray> result = new ArrayList<Ray>();

		// ray ���� ���͸� ����ȭ
		float d = Util.getDistance(direction);
		direction.x /= d;
		direction.y /= d;

		// ���� ���
		end.x = start.x + direction.x * distance;
		end.y = start.y + direction.y * distance;

		LevelObject nearestObject = null;
		float nearestT = 1000.f;
		
		// ��� ������Ʈ�� ���� �׽�Ʈ
		for (int i=0; i<objects.size(); ++i)
		{
			LevelObject obj = objects.get(i);
			obj.reset();
			
			// �������� ���� �ſ￡�� ������ �� ��, �� �ſ��� ���� �˰��򿡼� �����Ѵ�.
			if (objectForPass == obj)
				continue;
			
			float t = obj.intersectionTest(this);
			if (t >= 0.f) {
				// ���� ��
				
				if (t < nearestT) {
					// ���� ����� ��
					nearestObject = obj;
					nearestT = t;
				}
			}
				
		}

		// ���� �� ���� �ִ�
		if (nearestObject != null) {
			// �������� ��´�.
			PointF intersectionPoint = new PointF();
			intersectionPoint.x = start.x + nearestT * (end.x - start.x);
			intersectionPoint.y = start.y + nearestT * (end.y - start.y);

			// �ڽ��� distance�� ���δ�
			distance = Util.getDistance(new PointF(intersectionPoint.x - start.x,
											intersectionPoint.y - start.y));
			
			// ������Ʈ�� �ݻ�� ray�� ��´�
			ArrayList<Ray> reflectedRays = nearestObject.reflect(this, nearestT);
			result = reflectedRays;
				
			// Goal�� ����Ǹ� ����Ʈ ���� �ٲ۴�.
			if(nearestObject instanceof Goal) {
				((Goal)nearestObject).passGoal();
			}
			

		}

		return result;
	}

	
	 
}


















