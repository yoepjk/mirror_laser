package com.example.jong.mirrors.level.object;

import java.util.ArrayList;


public class Link {
	
	// ����� ������Ʈ arrayList
	ArrayList<LevelObject> objects = new ArrayList<LevelObject>();
	
	void onTouch(int x, int y) {

		// ����� ��� ������Ʈ�� ��ġ ����
		for(LevelObject obj : objects)
			obj.onTouch(x, y);
	}
	
	public void addObject(LevelObject obj) {
		
		objects.add(obj);
		obj.setLink(this);
	}
	
}
