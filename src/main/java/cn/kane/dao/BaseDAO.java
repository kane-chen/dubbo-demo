package cn.kane.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * 基础hibernate dao类
 * <p>
 *  
 */
/**
 * @author huicheng
 */
@SuppressWarnings({ "unchecked" })
public abstract class BaseDAO<T> {
	@Resource(name = "sessionFactory")
	@Autowired
	SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(BaseDAO.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/***
	 * Bean 类型
	 * 
	 * @return
	 */
	public Class<?> getPoClass() {
		Type type = getClass().getGenericSuperclass();
		Class<T> poClass = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) type;
			poClass = (Class<T>) pType.getActualTypeArguments()[0];
		}
		return poClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	public T getById(Serializable id) {
		Session session = getNewSession();
		try {
			T t = (T) session.get(this.getPoClass(), id);
			return t;
		} catch (Throwable e) {
			logger.error("ex", e);
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void createAll(List<?> polist) {
		Session session = getNewSession();
		try {
			for (int i = 0; i < polist.size(); i++) {
				Object po = polist.get(i);
				session.saveOrUpdate(po);
				if (i % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();
		} catch (Throwable e) {
			logger.error("ex", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateAll(List<?> polist) {
		Session session = getNewSession();
		try {
			for (int i = 0; i < polist.size(); i++) {
				Object po = polist.get(i);
				session.update(po);
				if (i % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void delete(T po) {
		Session session = this.getNewSession();
		try {
			session.delete(po);
		} catch (Throwable e) {
			logger.error("ex", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<?> findByJDBC(String hql, Object... values) {
		Session session = getNewSession();
		try {
			SQLQuery query = session.createSQLQuery(hql);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			List<?> list = query.list();
			return list;
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void deleteById(Serializable id) {
		Object o = getById(id);
		if (o != null) {
			Session session = getNewSession();
			try {
				session.delete(o);
			} catch (Throwable e) {
				logger.error("ex", e);
			} finally {
				if (session != null) {
					session.close();
				}
			}
		}
	}

	public List<T> findAll() {
		Session session = getNewSession();
		try {
			List<T> list = session.createQuery(getFromHql()).list();
			return list;
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void save(T po) {
		Session session = getNewSession();
		try {
			session.saveOrUpdate(po);
			session.flush();
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/**
	 * 每次保存或者更新都提交DB
	 * 
	 * @param po
	 */
	public void saveOrUpdate(T po) {
		Session session = getNewSession();
		try {
			session.saveOrUpdate(po);
			session.flush();
			session.clear();
		} catch (Throwable e) {
			logger.error("ex", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void create(T po) {
		Session session = getNewSession();
		try {
			session.save(po);
			session.flush();
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void merge(T po) {
		Session session = getNewSession();
		try {
			session.merge(po);
			session.flush();
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void update(T po) {
		Session session = getNewSession();
		try {
			session.merge(po);
			session.flush();
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 获得唯一查询结果
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public T doQueryUnique(final String hql, final Object... values) {

		Session session = getNewSession();
		try {
			Query query = session.createQuery(hql);
			query.setCacheable(false);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			T t = (T) query.uniqueResult();
			return t;
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/**
	 * 获得首个查询结果
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public T doQueryFirst(final String hql, final Object... values) {

		Session session = getNewSession();
		try {
			Query query = session.createQuery(hql);
			query.setCacheable(false);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			query.setFirstResult(0);
			query.setMaxResults(1);
			List<T> list = query.list();
			return list.isEmpty() ? null : list.get(0);
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	/**
	 * 执行sql插入更新或删除语句
	 * 
	 * @param hql
	 * @param values
	 */
	public void executeHsql(final String hql, final Object... values) {
		Session session = getNewSession();
		try {
			Query query = session.createQuery(hql);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			query.executeUpdate();
			session.flush();
			sessionFactory.getCache().evictEntityRegion(this.getPoClass());
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 执行sql插入更新或删除语句 针对只更新记录某些字段，在Hibernate缓存中不存在Bean的情况，不需要清除缓存中的对象
	 * 
	 * @param hql
	 * @param values
	 */
	public void executeHsqlWithoutEvict(final String hql, final Object... values) {
		Session session = getNewSession();
		try {
			Query query = session.createQuery(hql);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			query.executeUpdate();
			session.flush();
			session.clear();
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * 执行nameQuery
	 */
	public void executeNameQuery(final String hql, final Object... values) {
		Session session = getNewSession();
		try {
			SQLQuery query = session.createSQLQuery(hql);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			query.executeUpdate();
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<T> doQueryList(final String hql, final Object... values) {
		Session session = getNewSession();
		try {
			Query query = session.createQuery(hql);
			query.setCacheable(false);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			List<T> list = query.list();
			return list;
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public String getFromHql() {
		return "from " + this.getPoClass().getSimpleName();
	}

	public long doQueryCount(final String hql, final Object... values) {
		Session session = getNewSession();
		try {
			Query query = session.createQuery(hql);
			query.setCacheable(false);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			Long count = (Long) query.uniqueResult();
			return count != null ? count.longValue() : 0;
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public List<T> doQueryLimitListFromZero(final String hql, final int dataNum, final Object... values) {
		return doQueryLimitList(hql, 0, dataNum, values);
	}

	public List<T> doQueryLimitList(final String hql, final int firstResult, final int dataNum, final Object... values) {

		Session session = getNewSession();
		try {
			Query query = session.createQuery(hql);
			query.setCacheable(false);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			query.setFirstResult(firstResult);
			query.setMaxResults(dataNum);
			List<T> list = query.list();
			return list;
		} catch (Throwable e) {
			logger.error("ex", e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}