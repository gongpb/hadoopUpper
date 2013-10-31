package com.funshion.hadoop.join.map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class MapJoinDriver extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		DistributedCache.addCacheFile(new Path(args[1]).toUri(), conf);
		
		JobConf jobConf = new JobConf(conf, MapJoinDriver.class);
		jobConf.setJobName("map join");
		
		jobConf.setMapperClass(MapJoin.class);
		jobConf.setMapOutputKeyClass(Text.class);
		jobConf.setMapOutputValueClass(Text.class);
		
		jobConf.setNumReduceTasks(0);
		
		FileInputFormat.setInputPaths(jobConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobConf, new Path(args[2]));
		
		JobClient.runJob(jobConf);
		
		return 0;
	}
	
	public static void main(String[] args) {
		try {
			ToolRunner.run(new MapJoinDriver(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
