package com.example.jong.mirrors.level.object.algorithm.reflection;

import java.util.ArrayList;

import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.LevelObject;

public interface IReflectionAlgorithm {
	public ArrayList<Ray> reflect(Ray ray, LevelObject object, float tValue);
}
