package com.funshion.hadoop.sort.totalsort;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SortMapper extends MapReduceBase implements Mapper<Object, Text, Text, Text>{
   //<2
   //>2 <5
	@Override
	public void map(Object key, Text value, OutputCollector<Text, Text> output,
			Reporter reporter) throws IOException {
		StringTokenizer tokenizers = new StringTokenizer(value.toString());
		while (tokenizers.hasMoreTokens()) {
			Text out = new Text(tokenizers.nextToken());
			output.collect(out, out);
		}
	}
}
