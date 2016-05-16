package com.example.jong.mirrors.level.object.algorithm.intersection;

import android.graphics.PointF;
import com.example.jong.mirrors.Util;
import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.CircleObject;
import com.example.jong.mirrors.level.object.Goal;
import com.example.jong.mirrors.level.object.LevelObject;
import com.example.jong.mirrors.level.object.SegmentObject;

public class CircleIntersectionAlgorithm implements IIntersectionAlgorithm {

	public float intersectionTest(Ray ray, LevelObject object) {

		// �� ������Ʈ�� �ƴϸ� ����
		if (object instanceof CircleObject == false)
			return -1.0f;
				
		CircleObject circle = (CircleObject)object;
		PointF g = new PointF(circle.getCenterPoint().x, circle.getCenterPoint().y);
		PointF O = new PointF(g.x - ray.getStart().x, g.y - ray.getStart().y);
		PointF V = new PointF();
		V.set(ray.getDirection());

		float tValue;

		// �������� �������� Goal�� ������ �Ÿ� ���ϱ�
		if (Util.getDistance(O) < circle.getRadius()) {
			return -1.0f;
		}

		// �� ����(O, V)�� ���� ���⿡ �ִ��� Ȯ��(����)
		float dotOV = Util.dot(O, V);
		if (dotOV < 0) {
			return -1.0f;
		}

		// / Goal�� ������ ������ ���Ͱ��� �ִ� �Ÿ� ���ϱ�
		// �ڻ��� ���ϱ�
		float cosTheta = dotOV / (Util.getDistance(O) * Util.getDistance(V));

		// ���� ���ϱ�
		float sinTheta = (float) Math.sqrt(1 - cosTheta * cosTheta);

		// �ִܰŸ��� ��
		if (Util.getDistance(O) * sinTheta <= circle.getRadius()) {
			// ray�� t�� ���Ѵ�.
			PointF D = new PointF();
			D.set(ray.getDirection().x * ray.getDistance(), ray.getDirection().y * ray.getDistance());

			float a = D.x * D.x + D.y * D.y;
			float b = D.x * (ray.getStart().x - g.x) + D.y * (ray.getStart().y - g.y);
			float c = (ray.getStart().x - g.x) * (ray.getStart().x - g.x) + (ray.getStart().y - g.y)
					* (ray.getStart().y - g.y) - circle.getRadius() * circle.getRadius();

			float t1 = (float) (-b - Math.sqrt(b * b - a * c)) / a;
			float t2 = (float) (-b + Math.sqrt(b * b - a * c)) / a;

			if (t1 < t2) {
				tValue = t1;

			} else {
				tValue = t2;
			}

			return tValue;
		}
		
		return -1.0f;
	}

}



























