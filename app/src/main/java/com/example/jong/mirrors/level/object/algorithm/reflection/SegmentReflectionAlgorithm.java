package com.example.jong.mirrors.level.object.algorithm.reflection;

import java.util.ArrayList;

import android.graphics.PointF;

import com.example.jong.mirrors.Util;
import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.LevelObject;
import com.example.jong.mirrors.level.object.Mirror;
import com.example.jong.mirrors.level.object.SegmentObject;

public class SegmentReflectionAlgorithm implements IReflectionAlgorithm {

	public ArrayList<Ray> reflect(Ray ray, LevelObject object, float tValue) {
		ArrayList<Ray> result = new ArrayList<Ray>();

		// �������� ��´�.
		PointF intersectionPoint = new PointF();
		intersectionPoint.x = ray.getStart().x + tValue * (ray.getEnd().x - ray.getStart().x);
		intersectionPoint.y = ray.getStart().y + tValue * (ray.getEnd().y - ray.getStart().y);
		
		// �ݻ�� ���͸� ���
		PointF reflectedVector = getReflectionVector(ray, (SegmentObject)object);
		
		// ���ο� ray�� ���� �߰�
		Ray reflectedRay = new Ray();
		reflectedRay.getStart().set(intersectionPoint);
		reflectedRay.getDirection().set(reflectedVector);
		reflectedRay.setDistance(1000.f);
		reflectedRay.setObjectForPass(object);
		result.add(reflectedRay);
		
		return result;
	}
	
	// �ݻ� ���͸� ��´�.. ������ �ſ�θ� ȣ���� ��.
	private PointF getReflectionVector(Ray ray, SegmentObject segment) {
		// / �Ի簢�� ��´�.
		float incidenceAngle;

		// ���� ���͸� ���Ѵ�.
		PointF normalVector = new PointF(
				-(segment.getEnd().y - segment.getStart().y), segment.getEnd().x
				- segment.getStart().x);
		if (Util.dot(ray.getDirection(), normalVector) < 0) {
			normalVector.x = -normalVector.x;
			normalVector.y = -normalVector.y;
		}

		// �Ի簢�� ����Ѵ�
		incidenceAngle = (float) Math.acos(Util.dot(ray.getDirection(), normalVector)
				/ Util.getDistance(normalVector));
		if (Util.cross(ray.getDirection(), normalVector) < 0)
			incidenceAngle = -incidenceAngle;

		// �ݻ�� ���͸� ��´�
		PointF tempVector = new PointF(-ray.getDirection().x, -ray.getDirection().y);
		PointF reflectionVector = new PointF();
		reflectionVector.x = (float) (tempVector.x * Math.cos(incidenceAngle * 2) -
				tempVector.y * Math.sin(incidenceAngle * 2));
		reflectionVector.y = (float) (tempVector.y * Math.cos(incidenceAngle * 2) + 
				tempVector.x * Math.sin(incidenceAngle * 2));

		return reflectionVector;
	}

}


















