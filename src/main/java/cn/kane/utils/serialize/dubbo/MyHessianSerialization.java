package cn.kane.utils.serialize.dubbo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

public class MyHessianSerialization implements Serialization {
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

	public ObjectOutput serialize(URL url, OutputStream out)
			throws IOException {
		return new MyHessian2ObjectOutput(out);
	}

	public ObjectInput deserialize(URL url, InputStream is)
			throws IOException {
		return new MyHessian2ObjectInput(is);
	}

}
