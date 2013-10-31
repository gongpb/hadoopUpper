package com.funshion.hadoop.sort.sencondSort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SenReducer extends Reducer<MyPair, IntWritable, Text, IntWritable> {

	private Text seq = new Text("<---------------->");
	private Text first = new Text();
	
	protected void reduce(MyPair arg0, Iterable<IntWritable> arg1,
			org.apache.hadoop.mapreduce.Reducer.Context arg2)
			throws IOException, InterruptedException {
		arg2.write(seq, null);
		first.set(arg0.getFirst()+"");
	    // 输出
		for(IntWritable value : arg1){
			arg2.write(first, value);
		}
	}
}
