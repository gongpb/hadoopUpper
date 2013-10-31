package com.funshion.hadoop.ipc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {
	private RPCPro rpcPro;
	
	public RPCClient(){
		InetSocketAddress address = new InetSocketAddress("localhost",8888);
		try {
			rpcPro = (RPCPro) RPC.waitForProxy(RPCPro.class, 1, address, new Configuration());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void test(String s){
		System.out.println(rpcPro.test(new Text(s)));
	}
	
	public void close(){
		RPC.stopProxy(rpcPro);
	}
	
	public static void main(String[] args) {
		RPCClient client = new RPCClient();
		client.test("hello this is a client");
		client.close();
	}
	
}
