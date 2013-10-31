package com.funshion.hadoop.join.reduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class ReduceJoinMapper extends MapReduceBase implements Mapper<LongWritable, Text, LongWritable, LogRecord>{

	//1.read files
	//2.check file by fields number
	
	private LongWritable key = new LongWritable();
	
	@Override
	public void map(LongWritable inKey, Text inValue, OutputCollector<LongWritable, LogRecord> output,
			Reporter reporter) throws IOException {
		String value = inValue.toString();
		String[] values = value.split("\\W+");
		if(values.length==2){
			//this is small table
			LogRecord logRecord = new LogRecord();
			logRecord.phoneNumber = values[0];
			logRecord.type = 2;
			logRecord.location = values[1];
			key.set(Long.parseLong(values[0]));
			output.collect(key, logRecord);
		} else {
			LogRecord logRecord = new LogRecord();
			logRecord.username = values[0];
			logRecord.phoneNumber = values[1];
			logRecord.age = values[2];
			logRecord.type = 1;
			key.set(Long.parseLong(values[1]));
			output.collect(key, logRecord);
		}
	}
}
