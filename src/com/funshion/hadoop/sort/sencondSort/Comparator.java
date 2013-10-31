package com.funshion.hadoop.sort.sencondSort;

import org.apache.hadoop.io.WritableComparator;
	
public class Comparator extends WritableComparator {
	
	static {
		WritableComparator.define(MyPair.class, new Comparator());
	}
	
	protected Comparator() {
		super(MyPair.class);
	}

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {

		return super.compareBytes(b1, s1, l1, b2, s2, l2);
	}
}
