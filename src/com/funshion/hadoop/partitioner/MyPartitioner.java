package com.funshion.hadoop.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;
/**
 * <Text, LongWritable>输入是mapper的输出
 * @author gongpb
 */
public class MyPartitioner implements Partitioner<Text, LongWritable>{

	@Override
	public void configure(JobConf job) {
		
	}
	/**
	 * 返回int:分片的第几个
	 * numPartitions
	 */
	@Override
	public int getPartition(Text inKey, LongWritable inValue, int numPartitions) {
		int result = 0;
		String value =  inKey.toString();
		if("liaoning".equals(value)){
			result = 0 % numPartitions;
		} else if("sichuan".equals(value)) {
			result = 1 % numPartitions;
		} else if("guangdong".equals(value)){
			result = 2 % numPartitions;
		} else {
			result = 3 % numPartitions;
		}
		return result;
	}
}
