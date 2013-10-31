/**
 * 
 */
package com.funshion.hadoop.writable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * @author gongpb
 *
 */
public class HadoopSerializationUtil {
/**
 * 序列化
 * @param writable
 * @return
 * @throws IOException
 */
	public static byte[] serialize(Writable writable) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dataout = new DataOutputStream(out);
		//call write method
		writable.write(dataout);
		dataout.close();
		return out.toByteArray();
	}
	/**
	 * 反序列化
	 * @param writable
	 * @param bytes
	 * @throws IOException
	 */
	public static void deserialize(Writable writable, byte[] bytes) throws IOException{
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		DataInputStream datain = new DataInputStream(in);
		writable.readFields(datain);
		datain.close();
	}
	
}
