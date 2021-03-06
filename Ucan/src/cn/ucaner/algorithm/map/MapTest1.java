package cn.ucaner.algorithm.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//id相同的map映射，最后一次map键值将覆盖前几次的键值
public class MapTest1 
{
	public static void main(String[] args)
	{
		Map m = new HashMap();   
		Person p1 = new Person();   
		Person p2 = new Person();   
		              
		p1.setId("1");   
		p1.setName("name1");   
		p2.setId("1");   
		p2.setName("name2");   
		                   
		m.put(p1, "person1");   
		m.put(p2, "person2");   
		    
		System.out.println("Map m's size :" + m.size());   
			          
		for(Object o :m.entrySet())
		{   
			Entry e = (Entry)o;   
			System.out.println("key:"+ e.getKey());   
			System.out.println("value:"+ e.getValue());   
		}   
	}
}
