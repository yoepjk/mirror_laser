package com.example.jong.mirrors.level.object.algorithm.intersection;

import com.example.jong.mirrors.level.laser.Ray;
import com.example.jong.mirrors.level.object.LevelObject;

public interface IIntersectionAlgorithm {
	float intersectionTest(Ray ray, LevelObject object);
}
