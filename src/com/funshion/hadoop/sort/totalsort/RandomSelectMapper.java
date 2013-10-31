package com.funshion.hadoop.sort.totalsort;

import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * 随机选择数据
 * @author gongpb
 */
public class RandomSelectMapper extends MapReduceBase implements Mapper<Object, Text, Text, Text>{
	
	private Random random = new Random();//选择数据
	private static int currentSize = 0; //控制选择多少数据
	
	@Override
	public void map(Object key, Text value, OutputCollector<Text, Text> output,
			Reporter reporter) throws IOException {
		//效率很低，不建议使用
		StringTokenizer tokenizers = new StringTokenizer(value.toString());
		
		while (tokenizers.hasMoreTokens()) {
			currentSize++ ;
			Random ran = new Random();
			if(random.nextInt(currentSize) == ran.nextInt(1)) {
				Text out = new Text(tokenizers.nextToken());
				output.collect(out, out);
			}
			else {
				tokenizers.nextToken();
			}
		}
		
	}

}
