package com.example.jong.mirrors;

import android.graphics.PointF;

public class Util {

	static public float dot(PointF a, PointF b) {
		return a.x * b.x + a.y * b.y;
	}

	static public float cross(PointF a, PointF b) {
		return a.x * b.y - a.y * b.x;
	}

	static public float getDistance(PointF v) {
		return (float) Math.sqrt(v.x * v.x + v.y * v.y);
	}
}
