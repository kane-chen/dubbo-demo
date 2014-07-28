package cn.kane.utils.serialize.dubbo;
import com.alibaba.com.caucho.hessian.io.CollectionSerializer;
import com.alibaba.com.caucho.hessian.io.MapSerializer;
import com.alibaba.com.caucho.hessian.io.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentMap;


public class MyHibernateCollectionSerializer implements Serializer {

	CollectionSerializer collectionSeiralizer = new CollectionSerializer();
	MapSerializer mapSerializer = new MapSerializer();

	@Override
	public void writeObject(Object obj,
			com.alibaba.com.caucho.hessian.io.AbstractHessianOutput out)
			throws IOException {
		 boolean init = Hibernate.isInitialized(obj);
         if (init) {
             out.writeObject(obj);
             out.flush();
             return;
         }
         if (PersistentMap.class.isAssignableFrom(obj.getClass())) {
             //将没有初始化的Map序列化空的HashMap
             mapSerializer.writeObject(new HashMap<Object,Object>(), out);
         } else {
             //将没有初始化的List,Set等序列化为空的ArrayList
             collectionSeiralizer.writeObject(new ArrayList<Object>(), out);
         }
	}


}
