package com.example.jong.mirrors.level.object.algorithm.reflection;

import java.util.ArrayList;
import android.graphics.PointF;
import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.CircleObject;
import com.example.jong.mirrors.level.object.LevelObject;


public class SpectromReflectionAlgorithm implements IReflectionAlgorithm {

	public ArrayList<Ray> reflect(Ray ray, LevelObject object, float tValue) {
		
		ArrayList<Ray> result = new ArrayList<Ray>();
		
		// �������� ���Ѵ�.
//		PointF intersectionPoint = new PointF();
//		intersectionPoint.x = ray.getStart().x + tValue * (ray.getEnd().x - ray.getStart().x);
//		intersectionPoint.y = ray.getStart().y + tValue * (ray.getEnd().y - ray.getStart().y);
		
		// �ݻ�� ���� ���
		PointF tempVector = new PointF(0, 1);
		PointF reflectUpVector = new PointF();
		PointF reflectDownVector = new PointF();
		
		double radian = Math.toRadians(30);
		
		reflectUpVector.x = (float)(Math.cos(radian) * tempVector.x -
					Math.sin(radian) * tempVector.y);
		
		reflectUpVector.y = (float)(Math.cos(radian) * tempVector.y -
					Math.sin(radian) * tempVector.x);
		
		radian = Math.toRadians(-30);
		
		reflectDownVector.x = (float)(Math.cos(radian) * tempVector.x -
				Math.sin(radian) * tempVector.y);
	
		reflectDownVector.y = (float)(Math.cos(radian) * tempVector.y -
				Math.sin(radian) * tempVector.x);
	
		
		// �б��⿡�� �������� Ray�� �������� ���Ѵ�.
		PointF reflectStart = new PointF();		
		float radius = ((CircleObject)object).getRadius();
		reflectStart.x = object.centerPoint.x;
		reflectStart.y = object.centerPoint.y + radius;
		
		
		// ���ο� Ray �� ���� ���� �߰�
		Ray reflectUpRay = new Ray();
		Ray reflectDownRay = new Ray();
		
		reflectUpRay.getStart().set(reflectStart);
		reflectUpRay.getDirection().set(reflectUpVector);
		reflectUpRay.setDistance(1000.f);
		reflectUpRay.setObjectForPass(object);
		
		reflectDownRay.getStart().set(reflectStart);
		reflectDownRay.getDirection().set(reflectDownVector);
		reflectDownRay.setDistance(1000.f);
		reflectDownRay.setObjectForPass(object);
			
		result.add(reflectUpRay); 
		result.add(reflectDownRay);
		
		return result;
	}	
}




























