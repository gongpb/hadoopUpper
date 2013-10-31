package com.funshion.hadoop.sort.totalsort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
/**
 * 两个Job
 * @author gongpb
 */
public class TotalSortDriver extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		
		runSelect( new Path(args[0]), new Path(args[1]));
		runSort( new Path(args[1]), new Path(args[2]));
		
		return 0;
	}
	
	public void runSelect( Path inputPath, Path outPath) throws IOException {
		
		JobConf jobConf = new JobConf(TotalSortDriver.class);
		
		jobConf.setMapperClass(RandomSelectMapper.class);
		jobConf.setReducerClass(RandomSelectReducer.class);
		
		jobConf.setOutputKeyClass(Text.class);
		jobConf.setOutputValueClass(Text.class);
		
		FileInputFormat.setInputPaths(jobConf, inputPath);
		FileOutputFormat.setOutputPath(jobConf, outPath);
		
		JobClient.runJob(jobConf);
	}
	
	public void runSort( Path inputPath, Path outPath) throws IOException {
		
		JobConf jobConf = new JobConf(TotalSortDriver.class);
		
		jobConf.setMapperClass(SortMapper.class);
		jobConf.setReducerClass(SortReducer.class);
		jobConf.setPartitionerClass(TotalPartitioner.class);

		jobConf.setNumReduceTasks(3);
		
		jobConf.setOutputKeyClass(Text.class);
		jobConf.setOutputValueClass(Text.class);
		
		FileInputFormat.setInputPaths(jobConf, inputPath);
		FileOutputFormat.setOutputPath(jobConf, outPath);
		
		JobClient.runJob(jobConf);
	}
	
	public static void main(String[] args) {
		try {
			ToolRunner.run(new TotalSortDriver(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
