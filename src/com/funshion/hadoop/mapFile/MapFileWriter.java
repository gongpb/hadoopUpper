package com.funshion.hadoop.mapFile;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;

public class MapFileWriter {
	static Configuration conf = null;
	static {
		conf = new Configuration();
	}
	
	public static void main(String[] args) {
		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
			//URL
			String path = "/user/root/mymap.map";
			
			Text key = new Text();
			key.set("mymap");
			Text value = new Text();
			value.set("mysql");
			
			//MapFile 对象
			MapFile.Writer writer = new MapFile.Writer(conf, fs, path, key.getClass(), value.getClass());
			writer.append(key, value);
			//close
			IOUtils.closeStream(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
