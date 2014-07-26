package cn.kane.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

public class MySerializationBaseHessian implements Serialization {
	/**
	 * serialize-protocol id
	 */
	public static final byte ID = 10;
	
	private static final String CONTEXT_TYPE = "x-application/hessian2" ;
	


	public String getContentType() {
		return CONTEXT_TYPE;
	}

	public byte getContentTypeId() {
		return ID;
	}

	public ObjectOutput serialize(URL arg0, OutputStream arg1)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInput deserialize(URL arg0, InputStream arg1)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
