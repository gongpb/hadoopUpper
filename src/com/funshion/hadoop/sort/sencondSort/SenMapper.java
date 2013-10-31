package com.funshion.hadoop.sort.sencondSort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SenMapper extends Mapper<LongWritable, Text, MyPair, IntWritable>{

	private MyPair pair = new MyPair();
	private IntWritable outputValue = new IntWritable();
	@Override
	protected void map(LongWritable inKey, Text inValue,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		String value = inValue.toString();
		String[] values = value.split("\\W+");
		
		pair.set(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
		outputValue.set(Integer.parseInt(values[1]));

		context.write(pair, outputValue);
	}
	
}
