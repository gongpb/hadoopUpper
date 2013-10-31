package com.funshion.hadoop.recordReader;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.RecordReader;

public class MyRecordReader implements RecordReader<Text, Text>{
	
	private CompressionCodecFactory compressionCodes = null;
	private long start;
	private long pos;
	private long end;
	
	private String keyName = null; //output key
	private byte[] buffer = null; //文件内容
	FSDataInputStream in = null;
	//1.open split int MyRecordReader()
	//2.createKey createValue直接 new Text() 
	//3.next()
	//4.getPos() getProgress() close()
	
	public MyRecordReader(Configuration conf, FileSplit split) throws IOException{
		//1.get start pos
		//2.get end
		//3.get file path
		//4.create FileSystem instance
		//5.open file
		//6.find the start
		//7.create buffer
		//8.set pos
		start = split.getStart();
		end = split.getLength()+start;
		final Path filePath = split.getPath();
		keyName = filePath.toString();
		final FileSystem fs = FileSystem.get(conf);
		in = fs.open(filePath);
		in.seek(start);
		
		buffer = new byte[(int)(end-start)];
		this.pos = start;
		
	}
	//每行记录的索引
	@Override
	public boolean next(Text key, Text value) throws IOException {
		//1.locations <end
		//2.set key
		//3.clear value
		//4.read content
		//5.move pos
		//6.return true
		while (pos < end) {
			key.set(keyName);
			value.clear();
			in.readFully(pos, buffer);
			value.set(buffer);
			pos += buffer.length;
			return true;
		}
		return false;
	}
    
	@Override
	public Text createKey() {
		return new Text();
	}

	@Override
	public Text createValue() {
		return new Text();
	}
	//位置的游标
	@Override
	public long getPos() throws IOException {
		return pos;
	}

	@Override
	public void close() throws IOException {
		in.close();
	}
	//进程百分比
	@Override
	public float getProgress() throws IOException {
		if (start == end) {
		      return 0.0f;
		} else {
		      return Math.min(1.0f,(pos - start) / (float)(end - start));
		}
	}

}
