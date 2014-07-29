package cn.kane.utils.serialize.hessian;
import org.hibernate.collection.spi.PersistentCollection;

import com.caucho.hessian.io.Deserializer;
import com.caucho.hessian.io.HessianProtocolException;
import com.caucho.hessian.io.Serializer;
import com.caucho.hessian.io.SerializerFactory;


public class HibernateSerializerFactory  extends SerializerFactory {

    @SuppressWarnings("rawtypes")
	@Override
    public Serializer getSerializer(Class cl) throws HessianProtocolException {

        if (PersistentCollection.class.isAssignableFrom(cl)) {
            return new HibernateSerializer();
        }
        return super.getSerializer(cl);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Deserializer getDeserializer(Class cl)throws HessianProtocolException{
    	if(PersistentCollection.class.isAssignableFrom(cl)){
    		return new HibernateDeserializer(cl) ;
    	}
    	return super.getDeserializer(cl) ;
    }
}
