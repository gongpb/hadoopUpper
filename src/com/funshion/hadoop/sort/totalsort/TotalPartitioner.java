package com.funshion.hadoop.sort.totalsort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

public class TotalPartitioner implements Partitioner<Text, Text>{

	@Override
	public void configure(JobConf job) {
		
	}

	@Override
	public int getPartition(Text key, Text value, int numPartitions) {
		int result =0;
		if (Integer.parseInt(value.toString()) < 2){
			result = 0 % numPartitions;
		} else if (Integer.parseInt(value.toString()) >= 2 && Integer.parseInt(value.toString()) < 5) {
			result = 1 % numPartitions;
		} else {
			result = 2 % numPartitions;
		}
		return result;
	}
}
