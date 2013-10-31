package com.funshion.hadoop.combiner;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class WordCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{
	
	private Text key = new Text();
	private IntWritable value = new IntWritable();
	
	@Override
	public void map(LongWritable inkey, Text inValue,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		String line = inValue.toString();
		String[] values = line.split("\\W+");

		for(int i=0; i<values.length; i++){
			key.set(values[i]);
			value.set(1);
			output.collect(key, value);
		}
	}

}
