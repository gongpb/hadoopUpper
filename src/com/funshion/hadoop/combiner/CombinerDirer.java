package com.funshion.hadoop.combiner;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class CombinerDirer extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		JobConf jobConf = new JobConf(CombinerDirer.class);
		jobConf.setJobName("combiner without reduce");
		jobConf.setMapperClass(WordCountMapper.class);
		jobConf.setCombinerClass(MyCombiner.class);
		
		jobConf.setReducerClass(WordCountReduce.class);
//		jobConf.setNumReduceTasks(0); //不会起reduce程序
		
		jobConf.setMapOutputKeyClass(Text.class);
		jobConf.setMapOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(jobConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));
		
		JobClient.runJob(jobConf);
		return 0;
	}

	public static void main(String[] args){
		try {
			ToolRunner.run(new CombinerDirer(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
