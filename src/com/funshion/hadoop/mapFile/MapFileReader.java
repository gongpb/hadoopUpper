package com.funshion.hadoop.mapFile;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.util.ReflectionUtils;

public class MapFileReader {
	static Configuration conf = null;
	private static String uri = "hdfs://192.168.207.128:9000";
	static {
		conf = new Configuration();
	}
	
	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		String path = "/user/root/mymap.map";
		MapFile.Reader reader = new MapFile.Reader(fs, path, conf);
		
		Writable keyClass = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), conf) ;
		Writable valueClass = (Writable) ReflectionUtils.newInstance(reader.getValueClass(),conf);
	
		while (reader.next((WritableComparable) keyClass, valueClass)){
			System.out.println("key："+keyClass);
			System.out.println("value："+valueClass);
		}
	}
}
