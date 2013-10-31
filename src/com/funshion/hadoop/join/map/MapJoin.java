package com.funshion.hadoop.join.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
/**
 * 案例分析：
1、输入两个文件，文件一内容如下：
（a）空格分隔：用户名 手机号 年龄
（b）内容样例：Tom 10010 20
2、文件二内容：
（a）空格分隔：手机号 城市
（b）内容样例：10010 山东
3、需要统计汇总信息为 用户名 手机号 年龄 城市
 * @author gongpb
 *
 */
public class MapJoin extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>{
	//1.overwrite config method to get cache file
	//2.read cache file and put all values map such as HashTable
	//3.in map method read val ues and join
	
	private Hashtable<String, String> joinData = new Hashtable<String, String>();
	
	private Text outputValue = new Text();
	private Text outputKey = new Text();
	
	@Override
	public void map(LongWritable inKey, Text inValue,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		String value = inValue.toString();
		String[] values = value.split("\\W+");
		String pos = joinData.get(values[1]);
		if(pos != null) {
			outputKey.set(values[0]);
			outputValue.set(values[0]+","+values[1]+","+values[2]+","+pos);
			output.collect(outputKey, outputValue);
		}
	}
	
	@Override
	public void configure(JobConf job) {
		
		try {
			Path[] cacheFiles = DistributedCache.getLocalCacheFiles(job);
			if(cacheFiles != null && cacheFiles.length > 0){
				String line = "";
				String[] values = null;
				BufferedReader reader = new BufferedReader(new FileReader(cacheFiles[0].toString()) );
				
				while ((line = reader.readLine()) != null){
					values = line.split("\\W+");
					joinData.put(values[0], values[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
