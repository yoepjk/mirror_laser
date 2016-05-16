package com.example.jong.mirrors.level.object;

import com.example.jong.mirrors.level.object.algorithm.reflection.SegmentReflectionAlgorithm;
import android.graphics.Paint;

public class Mirror extends SegmentObject {
	
	
	public Mirror(Paint paint) {
		super(paint);
		
		reflectionAlgorithm = new SegmentReflectionAlgorithm();
	}
	
	
}
