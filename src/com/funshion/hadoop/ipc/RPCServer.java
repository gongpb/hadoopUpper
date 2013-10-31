package com.funshion.hadoop.ipc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

public class RPCServer implements RPCPro{
	private Server server;
	
	public RPCServer(){
		try {
			server = RPC.getServer(this, "localhost",8888, new Configuration());
			server.start(); //监听
			server.join(); //等待
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getProtocolVersion(String protocol, long clientVersion)
			throws IOException {
		return 1;
	}

	@Override
	public Text test(Text t) {
		System.out.println("this is a server "+ t.toString());
		return t;
	}
	
	public static void main(String[] args) {
		new RPCServer();
	}
	
}
