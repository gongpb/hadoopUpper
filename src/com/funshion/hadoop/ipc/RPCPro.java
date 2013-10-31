package com.funshion.hadoop.ipc;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.VersionedProtocol;

public interface RPCPro extends VersionedProtocol{
	public Text test(Text t);
}
