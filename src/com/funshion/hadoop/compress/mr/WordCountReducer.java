package com.funshion.hadoop.compress.mr;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

//1.extends MapReduceBase and implements Reducer
//2.implement reduce method
//3.make sure that reduce input value and key's type must the same as Mapper output
//<hello,(1,1,1,1)>
public class WordCountReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable>{

	IntWritable value = new IntWritable();
	@Override
	public void reduce(Text arg0, Iterator<IntWritable> arg1,
			OutputCollector<Text, IntWritable> arg2, Reporter arg3)
			throws IOException {
		int sum = 0;
		while (arg1.hasNext()){
			sum += arg1.next().get();
		}		
		value.set(sum);
		arg2.collect(arg0, value);
	}
}