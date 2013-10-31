package com.funshion.hadoop.seq;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

public class SeqReader {
	static Configuration conf = null;
	private static String uri = "hdfs://192.168.207.128:9000";
	static {
		conf = new Configuration();
	}
	
	public static void main(String[] args) {
		FileSystem fs = null;
		try {
			fs = FileSystem.get(URI.create(uri), conf);
			
			Path path = new Path("/user/root/myseq.seq");
			SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
			Writable keyClass = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), conf) ;
			Writable valueClass = (Writable) ReflectionUtils.newInstance(reader.getValueClass(),conf);
			while ( reader.next(keyClass,valueClass) ){
				System.out.println("key："+keyClass);
				System.out.println("value："+valueClass);
				System.out.println("postion："+reader.getPosition());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
