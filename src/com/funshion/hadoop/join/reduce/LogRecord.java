package com.funshion.hadoop.join.reduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class LogRecord implements WritableComparable{

	//1.make field contants all fields
	//2.impl write readFields
	//3.impl LogRecord(LogRecord reco)
	//4.impl toString();
	
	String username = "";
	String phoneNumber = "";
	String age = "";
	String location = "";
	int type=0;
	
	public LogRecord(){
		super();
	}
	
	public LogRecord( LogRecord reco){
		this.username = reco.username;
		this.phoneNumber = reco.phoneNumber;
		this.age = reco.age;
		this.location = reco.location;
		this.type = reco.type;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(username);
		out.writeUTF(phoneNumber);
		out.writeUTF(age);
		out.writeUTF(location);
		out.writeInt(type);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.username = in.readUTF();
		this.phoneNumber = in.readUTF();
		this.age = in.readUTF();
		this.location = in.readUTF();
		this.type = in.readInt();
	}

	@Override
	public int compareTo(Object o) {

		return 0;
	}
	
	@Override
	public String toString() {
		if(type == 1){
			return  username+","+ phoneNumber+","+age+","+location+","+type;
		}else {
			return  username+","+ phoneNumber+","+location;
		}
	}

}
