package com.example.jong.mirrors.level;

import com.example.jong.mirrors.MirrorActivity;
import com.example.jong.mirrors.level.object.*;
import com.example.jong.mirrors.level.laser.*;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.PointF;

public class LevelImporter {
	
	final Context mContext;
	final MirrorActivity mirrorActivity;
	
	public LevelImporter(Context mContext, MirrorActivity mirrorActivity) {
				
		this.mContext = mContext;
		this.mirrorActivity = mirrorActivity;
	}
	
	public boolean loadTag_level(Element element, Level level){
		if (element.getNodeName().equals("level"))
		{
			NodeList childNodes = element.getChildNodes();
			for (int i=0; i<childNodes.getLength(); ++i) {
				Node child = childNodes.item(i);
				
				if (child.getNodeName().equals("startRay")) {
					if (loadTag_startRay((Element)child, level.getStartRay()) == false)
						return false;
					
				}else if (child.getNodeName().equals("goal")) {
					Goal goal = new Goal(level.greenPaint, level.yellowPaint, mirrorActivity);
					if (loadTag_goal((Element)child, goal) == false)
						return false;
					
					level.addObject(goal);
					
				}else if (child.getNodeName().equals("wall")) {
					Wall wall = new Wall(level.bluePaint);
					if (loadTag_wall((Element)child, wall) == false)
						return false;
					
					level.addObject(wall);
				
				}else if (child.getNodeName().equals("mirror")) {
					Mirror mirror = new Mirror(level.blackPaint);
					if(loadTag_mirror((Element)child, mirror) == false)
						return false;
										
					level.addObject(mirror);
				
				}else if (child.getNodeName().equals("spectrometer")) {
					Spectrometer spectrometer = new Spectrometer(level.magentaPaint);
					if (loadTag_spectrometer((Element)child, spectrometer) == false)
						return false;
					
					level.addObject(spectrometer);
				}	
			}
			
		}else
			return false;

		
		return true;
	}
	
	public boolean loadTag_startRay(Element element, Ray startRay) {
		// attribute �ε�
		String powerString = element.getAttribute("power");
		if (powerString != null) {
			startRay.setDistance(Float.parseFloat(powerString));
		}
		
		// �ڽ� �±� �ε�
		NodeList childNodes = element.getChildNodes();
		for (int i=0; i<childNodes.getLength(); ++i){
			Node child = childNodes.item(i);
			
			if (child.getNodeName().equals("position")) {
				if (loadTag_PointFType((Element)child, startRay.getStart()) == false)
					return false;
				
			}else if (child.getNodeName().equals("direction")) {
				if (loadTag_PointFType((Element)child, startRay.getDirection()) == false)
					return false;
			}
		}
		
		return true;
	}
	
	public boolean loadTag_PointFType(Element element, PointF point) {
		// attribute �ε�
		String xString = element.getAttribute("x");
		if (xString != null) {
			point.x = Float.parseFloat(xString);
		}

		String yString = element.getAttribute("y");
		if (yString != null) {
			point.y = Float.parseFloat(yString);
		}
		
		return true;
	}
	
	
	public boolean loadTag_goal(Element element, Goal goal) {
		// attribute �ε�
		String radiusString = element.getAttribute("radius");
		if (radiusString != null) {
			goal.setRadius(Float.parseFloat(radiusString));
		}
		
		// �ڽ� �±� �ε�
		NodeList childNodes = element.getChildNodes();
		for (int i=0; i<childNodes.getLength(); ++i){
			Node child = childNodes.item(i);
			
			if (child.getNodeName().equals("position")) {
				if (loadTag_PointFType((Element)child, goal.getCenterPoint()) == false)
					return false;
			}
		}
		
		return true;
	}
	
	public boolean loadTag_wall(Element element, Wall wall) {
		// attribute �ε�
		String angleString = element.getAttribute("angle");
		double angle = 0.0;
		if (angleString != null) {
			angle = Double.parseDouble(angleString);
		}
		
		String lengthString = element.getAttribute("length");
		double length = 80.0;
		if (lengthString != null) {
			length = Double.parseDouble(lengthString);
		}
		
		// �ڽ� �±� �ε�
		NodeList childNodes = element.getChildNodes();
		for (int i=0; i<childNodes.getLength(); ++i){
			Node child = childNodes.item(i);
			
			if (child.getNodeName().equals("position")) {
				if (loadTag_PointFType((Element)child, wall.getStart()) == false)
					return false;
			}
		}

		// ����
		wall.set(wall.getStart(), (float)angle, (float)length);
		
		return true;
	}
	
	public boolean loadTag_mirror(Element element, Mirror mirror) {
		// attribute �ε�
		String angleString = element.getAttribute("angle");
		double angle = 0.0;
		if (angleString != null) {
			angle = Double.parseDouble(angleString);
		}
		
		String lengthString = element.getAttribute("length");
		double length = 80.0;
		if (lengthString != null) {
			length = Double.parseDouble(lengthString);
		}
		
		// �ڽ� �±� �ε�
		NodeList childNodes = element.getChildNodes();
		for (int i=0; i<childNodes.getLength(); ++i){
			Node child = childNodes.item(i);
			
			if (child.getNodeName().equals("position")) {
				if (loadTag_PointFType((Element)child, mirror.getCenterPoint()) == false)
					return false;
			}
		}

		// ����
		mirror.setMirror((float)angle, (float)length);
		mirror.setControllable(true);
		
		return true;
	}

	
	// �б���
	public boolean loadTag_spectrometer(Element element, Spectrometer spectrometer) {
		// attribute �ε�
		String radiusString = element.getAttribute("radius");
		if (radiusString != null) {
			spectrometer.setRadius(Float.parseFloat(radiusString));
		}
		
		// �ڽ� �±� �ε�
		NodeList childNodes = element.getChildNodes();
		for (int i=0; i<childNodes.getLength(); ++i){
			Node child = childNodes.item(i);
			
			if (child.getNodeName().equals("position")) {
				if (loadTag_PointFType((Element)child, spectrometer.getCenterPoint()) == false)
					return false;
			}
		}
		
		return true;
	}
	
	
	
	
	public Level load(String path) {
		
		Level level = new Level();
		
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			AssetManager assetManager = mContext.getAssets();
			
			// �Ľ�
			InputStream inputStream = assetManager.open(path);
			Document doc = db.parse(inputStream);
			doc.getDocumentElement().normalize();
			
			// level �±� �ε�
			loadTag_level((Element)doc.getLastChild(), level);
			
		} catch (Exception e) {
			System.out.println("Pasing Excpetion = " + e);
			return null;
		}
		
		return level;
	}
		
	
	
}









