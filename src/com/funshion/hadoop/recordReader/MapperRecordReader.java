package com.funshion.hadoop.recordReader;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MapperRecordReader extends MapReduceBase implements Mapper<Text, Text, Text, Text>{
	
	private JobConf conf = null;
	
	@Override
	public void map(Text inKey, Text inValue, OutputCollector<Text, Text> output,
			Reporter reporter) throws IOException {
		String fileName = conf.get("map.input.file");
		output.collect(new Text(fileName), inValue);
	}
	
	@Override
	public void configure(JobConf job) {
		conf = job;
	}

}
