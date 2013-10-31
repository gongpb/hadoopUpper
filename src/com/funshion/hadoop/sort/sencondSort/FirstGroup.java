package com.funshion.hadoop.sort.sencondSort;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparator;

public class FirstGroup implements RawComparator<MyPair>{

	@Override
	public int compare(MyPair o1, MyPair o2) {
		int key = o1.getFirst();
		int key1 = o2.getFirst();
		return key == key1 ? 0 :(key < key1 ? -1 : 1);
	}
	
	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		
		return WritableComparator.compareBytes(b1, s1, Integer.SIZE/8, b2, s2, l2);
	}

}
