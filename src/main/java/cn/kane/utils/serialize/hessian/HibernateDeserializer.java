package cn.kane.utils.serialize.hessian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.collection.internal.PersistentBag;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractListDeserializer;
import com.caucho.hessian.io.IOExceptionWrapper;

public class HibernateDeserializer extends AbstractListDeserializer {

	private Class<?> _type;

	public HibernateDeserializer(Class<?> type) {
		_type = type;
	}

	public Class<?> getType() {
		return _type;
	}

	public Object readList(AbstractHessianInput in, int length)
			throws IOException {
		Collection<Object> list = this.createList();

		in.addRef(list);

		while (!in.isEnd())
			list.add(in.readObject());

		in.readEnd();

		return list;
	}

	public Collection<Object> readLengthList(AbstractHessianInput in, int length)
			throws IOException {
		Collection<Object> list = this.createList();

		in.addRef(list);

		for (; length > 0; length--)
			list.add(in.readObject());

		return list;
	}

	@SuppressWarnings("unchecked")
	private Collection<Object> createList() throws IOException {
		Collection<Object> list = null;

		if (_type == null)
			list = new ArrayList<Object>();
		else if (!_type.isInterface()) {
			try {
				if(_type == PersistentBag.class){
					list = new ArrayList<Object>() ;
				}else{
					list = (Collection<Object>) _type.newInstance();
				}
			} catch (Exception e) {
			}
		}

		if (list != null) {
		} else if (SortedSet.class.isAssignableFrom(_type))
			list = new TreeSet<Object>();
		else if (Set.class.isAssignableFrom(_type))
			list = new HashSet<Object>();
		else if (List.class.isAssignableFrom(_type))
			list = new ArrayList<Object>();
		else if (Collection.class.isAssignableFrom(_type))
			list = new ArrayList<Object>();
		else {
			try {
				list = (Collection<Object>) _type.newInstance();
			} catch (Exception e) {
				throw new IOExceptionWrapper(e);
			}
		}

		return list;
	}

}
