package com.funshion.hadoop.seq;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;

public class SeqWrite {
	static Configuration conf = null;
	private static String uri = "hdfs://192.168.207.128:9000";
	static {
		conf = new Configuration();
	}
	
	private static String[] data = {"a","b","c","d","e","f","g","h","i","j","k","l,m,n","o,p,q,r,s,t"};
	
	public static void main(String[] args) {
		FileSystem fs = null;
		try {
			fs = FileSystem.get(URI.create(uri),conf);
			//new path
			Path path = new Path("/user/root/myseq.seq");
			//
			IntWritable key = new IntWritable();
			Text value = new Text();
//			Writer writer = SequenceFile.createWriter(fs, conf, path, key.getClass(), value.getClass());
			//压缩， 读的时候自动解压
			Writer writer = SequenceFile.createWriter(fs, conf, path, key.getClass(),
				value.getClass(), SequenceFile.CompressionType.RECORD, new BZip2Codec());

			//set value
			int length = data.length;
			for(int i=0; i<length; i++){
				key.set(i);
				value.set(data[i]);
				writer.append(key, value);
			}
			//close
			IOUtils.closeStream(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
