package com.funshion.hadoop.join.reduce;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class ReduceJoinReducer extends MapReduceBase implements Reducer<LongWritable, LogRecord, LongWritable, Text> {
	//1.cache the value  check the where from 
	@Override
	public void reduce(LongWritable key, Iterator<LogRecord> values,
			OutputCollector<LongWritable, Text> output, Reporter reporter)
			throws IOException {
		LogRecord thisLocation = null;
		List<LogRecord> users = new Vector<LogRecord>();
		//key <LogRecord><LogRecord>
		while (values.hasNext()){
			LogRecord reco = values.next();
			if (reco.type == 2) {
				thisLocation = new LogRecord(reco);
			} else {
				LogRecord logRecord = new LogRecord(reco);
				users.add(logRecord);
			}
		}
		
		for(LogRecord u : users) {
			if(thisLocation != null) {
				u.location = thisLocation.location;
				output.collect(new LongWritable(Long.parseLong(u.phoneNumber)), new Text(u.toString()));
			}
		}
		
	}
}
