package com.sbgl.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

public class JsonUtilTest {
	public static void main(String[] args) {
		List<Person> l = new ArrayList<Person>();
		l.add(new Person("zhang", "24", "Beijing"));
		l.add(new Person("xiao", "24", "Beidfdjing"));
		l.add(new Person("long", "23", "Bedfijing"));
		l.add(new Person("haha", "22", "Bdng"));
		
		JSONArray jsArr = JSONArray.fromObject(l);
		
		System.out.println(jsArr.toString());
	}
}
