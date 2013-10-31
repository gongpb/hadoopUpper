package com.funshion.hadoop.compress;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
/**
 * 对流的压缩、解压缩demo
 * @author gongpb
 */
public class StremCompressor {
	private static Configuration conf = null;
	static {
		conf = new Configuration();
	}
	/**
	 *  测试方法，打jar包之后在linux中执行以下命令：
	 *  echo "text" | ./bin/hadoop jar compress.jar  
	 *  com.funshion.hadoop.compress.StremCompressor org.apache.hadoop.io.compress.GzipCodec | gunzip -c
	 * @param args
	 */
	public static void main(String[] args) {
		String codeClassName = args[0];
		Class<?> codeClass = null;
		
		try {
			codeClass = Class.forName(codeClassName);
			CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeClass, conf);
			
			CompressionOutputStream outputStream = codec.createOutputStream(System.out);
			
			IOUtils.copyBytes(System.in, outputStream, 4096,false);
			//echo "Text" | hadoop className org.Gzip LZO |gunzip
			outputStream.finish();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
