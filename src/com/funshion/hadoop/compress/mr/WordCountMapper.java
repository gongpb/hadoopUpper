package com.funshion.hadoop.compress.mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

//1.extends MapReduceBase and implements org.apache.hadoop.mapred.Mapper
//2.implement map method
public class WordCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{
	Text key = new Text();
	IntWritable outPut = new IntWritable();
	
	@Override
	public void map(LongWritable arg0, Text arg1,
			OutputCollector<Text, IntWritable> arg2, Reporter arg3)
			throws IOException {
		//1.get word in each line of file
		//2.output
		String line = arg1.toString();
		for(String value : line.split("\\W+")){
			key.set(value);
			outPut.set(1);
			arg2.collect(key, outPut);
		}
	}
	@Override
	public void configure(JobConf job) {
		super.configure(job);
	}
}
