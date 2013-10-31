package com.funshion.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ChecksumFileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class TestCheckSumFileSystem {
	private static Configuration conf = null;
	static {
		conf = new Configuration();
	}
	
	public static void main(String[] args) throws IOException {
		LocalFileSystem fsFileSystem = ChecksumFileSystem.getLocal(conf);
		Path checkFileName = fsFileSystem.getChecksumFile(new Path("checksum/checksum.txt"));
		System.out.println("parent folder:"+checkFileName.getParent()+"  checksumFileName:"+checkFileName.getName());
	}
}
