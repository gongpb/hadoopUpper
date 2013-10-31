package com.funshion.hadoop.recordReader;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class RecordReaderDriver extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		JobConf jobConf = new JobConf(RecordReaderDriver.class);
		jobConf.setJobName("recordReader");
		
		jobConf.setMapperClass(MapperRecordReader.class);
		jobConf.setMapOutputKeyClass(Text.class);
		jobConf.setMapOutputValueClass(Text.class);
		
		jobConf.setNumReduceTasks(0);
		
		jobConf.setInputFormat(MyInputFormat.class);
		
		MyInputFormat.setInputPaths(jobConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));
		
		JobClient.runJob(jobConf);
		return 0;
	}
	public static void main(String[] args) {
		try {
			ToolRunner.run(new RecordReaderDriver(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
