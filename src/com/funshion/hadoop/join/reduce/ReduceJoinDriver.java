package com.funshion.hadoop.join.reduce;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/**
 * reduce 端的 join
 * @author gongpb
 */
public class ReduceJoinDriver extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		JobConf conf = new JobConf(ReduceJoinDriver.class);
		conf.setJobName("reduce join job");
		
		conf.setMapperClass(ReduceJoinMapper.class);
		conf.setReducerClass(ReduceJoinReducer.class);
		
		conf.setMapOutputKeyClass(LongWritable.class);
		conf.setMapOutputValueClass(LogRecord.class);
		
		conf.setOutputKeyClass(LongWritable.class);
		conf.setOutputValueClass(Text.class);
		
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		JobClient.runJob(conf);
		
		return 0;
	}

	public static void main(String[] args) {
		try {
			ToolRunner.run(new ReduceJoinDriver(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
