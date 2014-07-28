/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kane.utils.serialize.dubbo;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentMap;

import com.alibaba.com.caucho.hessian.io.HessianProtocolException;
import com.alibaba.com.caucho.hessian.io.Serializer;
import com.alibaba.com.caucho.hessian.io.SerializerFactory;

public class MyHessian2SerializerFactory extends SerializerFactory {

	public static final SerializerFactory SERIALIZER_FACTORY = new MyHessian2SerializerFactory();
	
	private MyHibernateCollectionSerializer serializer = new MyHibernateCollectionSerializer() ; 

	private MyHessian2SerializerFactory() {
	}

	@Override
	public ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Serializer getSerializer(Class cl) throws HessianProtocolException{
		if (PersistentMap.class.isAssignableFrom(cl) || PersistentBag.class.isAssignableFrom(cl)) {
			return serializer ;
		}
		return super.getSerializer(cl);
	}
	
}
