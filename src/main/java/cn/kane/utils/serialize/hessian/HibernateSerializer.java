package cn.kane.utils.serialize.hessian;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.AbstractSerializer;
import com.caucho.hessian.io.CollectionSerializer;
import com.caucho.hessian.io.MapSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentMap;


public class HibernateSerializer extends AbstractSerializer {

    CollectionSerializer collectionSeiralizer = new CollectionSerializer();
    
    MapSerializer mapSerializer = new MapSerializer();

    @Override
    public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {

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
