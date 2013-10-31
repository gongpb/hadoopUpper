package com.funshion.hadoop.partitioner;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MapperPart extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable>{

	private Text key = new Text();
	private LongWritable outputValue = new LongWritable();
	
	@Override
	public void map(LongWritable inKey, Text inValue,
			OutputCollector<Text, LongWritable> output, Reporter reporter)
			throws IOException {
		String line = inValue.toString();
		String[] values = line.split("\\W+");

		for(int i=0; i<values.length; i++){
			key.set(values[i]);
			outputValue.set(1);
			output.collect(key, outputValue);
		}
	}
}
