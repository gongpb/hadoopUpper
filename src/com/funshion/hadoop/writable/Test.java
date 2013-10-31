package com.funshion.hadoop.writable;

import java.io.IOException;

import org.apache.hadoop.util.StringUtils;

public class Test {
	public static void main(String[] args) throws IOException {
		//1.test serialization
		Person person = new Person("tom", "man",20);
		byte[] values = HadoopSerializationUtil.serialize(person);
		
		System.out.println(StringUtils.byteToHexString(values));
		
		Person person1 = new Person();
		HadoopSerializationUtil.deserialize(person1, values);
		System.out.println(person1.toString());
		
	}
}
