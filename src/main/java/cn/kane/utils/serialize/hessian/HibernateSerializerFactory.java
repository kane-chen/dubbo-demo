package cn.kane.utils.serialize.hessian;
import org.hibernate.collection.spi.PersistentCollection;

import com.caucho.hessian.io.HessianProtocolException;
import com.caucho.hessian.io.Serializer;
import com.caucho.hessian.io.SerializerFactory;


public class HibernateSerializerFactory  extends SerializerFactory {

    @Override
    public Serializer getSerializer(Class cl) throws HessianProtocolException {

        //Hibernate的集合API使用为HibernateSerializer序列化
        if (PersistentCollection.class.isAssignableFrom(cl)) {
            return new HibernateSerializer();
        }
        return super.getSerializer(cl);
    }

}
