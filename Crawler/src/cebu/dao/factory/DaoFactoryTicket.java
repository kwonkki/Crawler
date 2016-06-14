package cebu.dao.factory;

import java.io.InputStream;
import java.util.Properties;

import cebu.dao.interfaces.IDaoTicket;

/**
 * Ticket Dao Factory
 * 从propertities配置文件中获取dao的实现类
 * 如果以后更换其他实现方式如Spring、hibernate等，
 * 只需要将properties文件中对应实现类更改即可。
 * @author Administrator
 *
 */

public class DaoFactoryTicket {
	private static IDaoTicket daoTicket = null;
	
	/**
	 * 静态代码，读取配置文件，获取dao实现方式
	 */
	static {
		try {
			Properties prop = new Properties();
			// 读取properties配置文件
			InputStream is = DaoFactoryTicket.class.getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(is);
			// 获取配置文件中的到实现方式
			String daoOrgBasinClass = prop.getProperty("DaoImplTicketClass");
			// 利用反射，new一个dao实例
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(daoOrgBasinClass);
			daoTicket = (IDaoTicket) clazz.newInstance();
		} catch (Throwable  e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * 静态方法，获取dao实例
	 * @return
	 */
	public static final IDaoTicket getDao() {
		return daoTicket;
	}
}
