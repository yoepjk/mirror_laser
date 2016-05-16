package com.example.jong.mirrors.level.object;

import com.example.jong.mirrors.level.object.algorithm.reflection.NoneReflectionAlgorithm;
import android.graphics.Paint;

public class Wall extends SegmentObject {
	
	public Wall(Paint paint) {
		super(paint);
		
		reflectionAlgorithm = new NoneReflectionAlgorithm();
	}
	
}
