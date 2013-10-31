package com.funshion.hadoop.sort.sencondSort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class SenSortPartition extends Partitioner<MyPair, IntWritable>{

	@Override
	public int getPartition(MyPair key, IntWritable value, int numPartitions) {
		return Math.abs(key.getFirst() * 127 );
	}
	
	
}
