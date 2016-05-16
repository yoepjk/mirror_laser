package com.example.jong.mirrors.level.object.algorithm.intersection;

import android.graphics.PointF;
import com.example.jong.mirrors.Util;
import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.LevelObject;
import com.example.jong.mirrors.level.object.SegmentObject;

public class SegmentIntersectionAlgorithm implements IIntersectionAlgorithm {

	public float intersectionTest(Ray ray, LevelObject object) {

		// ���� ������Ʈ�� �ƴϸ� ����
		if (object instanceof SegmentObject == false)
			return -1.0f;

		SegmentObject segment = (SegmentObject)object;
		
		// ���� �������� ���� �������� Ȯ��
		if ((segment.getEnd().y - segment.getStart().y) * (ray.getEnd().x - ray.getStart().x)
				- (segment.getEnd().x - segment.getStart().x) * (ray.getEnd().y - ray.getStart().y) != 0) {
			// ������ �ƴ� ���

			float t, s;
			// ���� �˻�
			t = ((segment.getEnd().x - segment.getStart().x)
					* (ray.getStart().y - segment.getStart().y) - (segment.getEnd().y - segment
							.getStart().y) * (ray.getStart().x - segment.getStart().x))
							/ ((segment.getEnd().y - segment.getStart().y)
									* (ray.getEnd().x - ray.getStart().x) - (segment.getEnd().x - segment
											.getStart().x) * (ray.getEnd().y - ray.getStart().y));

			s = ((ray.getEnd().x - ray.getStart().x) * (ray.getStart().y - segment.getStart().y)
					- (ray.getEnd().y - ray.getStart().y) * (ray.getStart().x - segment.getStart().x))
					/ ((segment.getEnd().y - segment.getStart().y)
							* (ray.getEnd().x - ray.getStart().x) - (segment.getEnd().x - segment
									.getStart().x) * (ray.getEnd().y - ray.getStart().y));

			// ���� �������� ������ �� ���� ���
			if(t == 0 && s == 0) {
				// ������ ���Ϳ� segment ���͸� ����
				PointF W = new PointF(segment.getEnd().x - segment.getStart().x, segment.getEnd().y - segment.getStart().y);
				float dot = Util.dot(ray.getDirection(), W); 

				if(dot > 0 ) {
					t = (segment.getStart().x - ray.getStart().x) / (ray.getEnd().x - ray.getStart().x); 					

				}else if(dot < 0) {
					t = (segment.getEnd().x - ray.getStart().x) / (ray.getEnd().x - ray.getStart().x);
				}

			}


			if (t < 0 || t > 1 || s < 0 || s > 1) {
				// ���� �������� ���� ���
				return -1.0f;
			} else
				return t;
		}
		
		return -1.0f;
	}

}
