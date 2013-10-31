package com.funshion.hadoop.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
/**
 * 测试对象序列化
 * @author gongpb
 */
public class Person implements WritableComparable<Person>{
    Text name = new Text();
    Text sex = new Text();
    IntWritable age = new IntWritable();
    
    public Person(){
    	set("test", "man", 10);
    }
    
    public void set(String name, String sex, int age) {
    	this.name.set(name);
   		this.sex.set(sex);
   		this.age.set(age);
	}
    
   	public Person(String name, String sex, int age) {
   		super();
   		this.name.set(name);
   		this.sex.set(sex);
   		this.age.set(age);
   	}
   	
	public Person(Text name, Text sex, IntWritable age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		name.write(out);
		sex.write(out);
		age.write(out);
	}
    
	@Override
	public void readFields(DataInput in) throws IOException {
		name.readFields(in);
		sex.readFields(in);
		age.readFields(in);
	}

	@Override
	public int compareTo(Person o) {
		int result = 0;
		
		int comp1 = name.compareTo(o.name);
		if(comp1 !=0 ){
			return comp1;
		}
		
		int comp2 = sex.compareTo(o.sex);
		if(comp2 != 0){
			return comp2;
		}
		
		int comp3 = age.compareTo(o.age);
		if(comp3 != 0){
			return comp3;
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
}
