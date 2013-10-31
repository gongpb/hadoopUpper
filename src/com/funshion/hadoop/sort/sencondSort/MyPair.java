package com.funshion.hadoop.sort.sencondSort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
/**
 * 二次排序
 * @author gongpb
 */
public class MyPair implements WritableComparable{

	private int first = 0;
	private int second = 0;
	
	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		return first*157+second;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MyPair){
			MyPair pari = (MyPair)obj;
			return pari.first == this.first && pari.second == this.second;
		} else {
			return false;
		}
	}

	public void set(int first, int second){
		this.first = first;
		this.second = second;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(first);
		out.writeInt(second);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.first = in.readInt();
		this.second = in.readInt();
	}

	@Override
	public int compareTo(Object o) {
		MyPair pari = (MyPair)o;
		if(this.first != pari.first) {
			return this.first < pari.first ? -1 : 1;
		} else if(this.second != pari.second){
			return this.second < pari.second ?-1 : 1;
		}
		return 0;
	}
}
