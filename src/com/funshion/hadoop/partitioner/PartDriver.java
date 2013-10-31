package com.funshion.hadoop.partitioner;

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

public class PartDriver extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		JobConf jobConf = new JobConf(PartDriver.class);
		jobConf.setJobName("my partitioner");
		jobConf.setMapperClass(MapperPart.class);
		jobConf.setPartitionerClass(MyPartitioner.class);
		
		jobConf.setReducerClass(ReducerPart.class);
		//指定reduce Task的数量
		jobConf.setNumReduceTasks(4);

		jobConf.setOutputKeyClass(Text.class);
		jobConf.setOutputValueClass(LongWritable.class);
	    //配置输入、输出路径	
		FileInputFormat.setInputPaths(jobConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

		JobClient.runJob(jobConf);
		
		return 0;
	}

	public static void main(String[] args) {
		try {
			ToolRunner.run(new PartDriver(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
