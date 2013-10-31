package com.funshion.hadoop.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

public class FileDecompressor {
	private static Configuration conf = null;
	static {
		conf = new Configuration();
	}
	/**
	 * 测试方法：
	 * 创建一个文件 test.txt 通过 gzip test.txt 压缩为文件test.txt.gz 
	 * 执行下面命令可以解压
	 * ./bin/hadoop jar compress.jar com.funshion.hadoop.compress.FileDecompressor factory/test.txt.gz 
	 * 解压文件放在hdfs目录factory/下
	 * @param args
	 */
	public static void main(String[] args) {
		String uri = args[0];
		Configuration conf = new Configuration();
		InputStream in = null;
		OutputStream outputStream = null;
		
		try {
			//获取fs 的instance
			FileSystem fs = FileSystem.get(URI.create(uri),conf);
			// create inputPath
			Path inPath = new Path(uri);
			//create  CompressionCodecFactory
			CompressionCodecFactory factory = new CompressionCodecFactory(conf);
			//创建压缩、解压缩 解码器
			CompressionCodec codec = factory.getCodec(inPath);
			//处理文件的前缀、后缀
			String outputUriString = CompressionCodecFactory.removeSuffix(uri, codec.getDefaultExtension());
			//创建一个输入流
			in = codec.createInputStream(fs.open(inPath));
			//创建一个输出流
			outputStream = fs.create(new Path(outputUriString));
			//输入流copy到输出流，并结束
			IOUtils.copyBytes(in, outputStream, conf);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(in);
			IOUtils.closeStream(outputStream);
		}
	}
}
