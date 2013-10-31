package com.funshion.hadoop.partitioner;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class ReducerPart extends MapReduceBase implements Reducer<Text, LongWritable, Text, LongWritable>{
	private LongWritable outputValue = new LongWritable();
	
	@Override
	public void reduce(Text key, Iterator<LongWritable> values,
			OutputCollector<Text, LongWritable> output, Reporter reporter)
			throws IOException {
		long sum = 1;
		while (values.hasNext()) {
			sum += values.next().get();
		}
		outputValue.set(sum);
		output.collect(key, outputValue);
	}

}
