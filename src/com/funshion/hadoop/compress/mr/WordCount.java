package com.funshion.hadoop.compress.mr;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		
		if(args.length!=2){
			System.err.println("Usage: WordCount <input path> <output path>");
			System.exit(-1);
		}
		
		JobConf conf = new JobConf(getConf(), WordCount.class);
		conf.setJobName("WordCount");
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		// set map compress start
		conf.setBoolean("mapred.compress.map.output", true);
		conf.setMapOutputCompressorClass(GzipCodec.class);
		// set map compress end
		
		// set reducer compress start 
		//设置压缩
//		conf.setBoolean("mapred.output.compress", true);
//		conf.setClass("mapred.output.compression.codec", GzipCodec.class, CompressionCodec.class);
		// set reducer compress end 
		
		conf.setMapperClass(WordCountMapper.class);
		conf.setReducerClass(WordCountReducer.class);
		
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		JobClient.runJob(conf);
		
		return 0;
	}

	public static void main(String[] args) {
		try {
			int exitCode = ToolRunner.run(new WordCount(), args);
			System.exit(exitCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
