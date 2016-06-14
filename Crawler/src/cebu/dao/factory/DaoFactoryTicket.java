package cebu.dao.factory;

import java.io.InputStream;
import java.util.Properties;

import cebu.dao.interfaces.IDaoTicket;

public class DaoFactoryTicket {
	private static IDaoTicket daoTicket = null;
	
	static {
		try {
			Properties prop = new Properties();
			InputStream is = DaoFactoryTicket.class.getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(is);
			String daoOrgBasinClass = prop.getProperty("DaoImplTicketClass");
			Class clazz = Class.forName(daoOrgBasinClass);
			daoTicket = (IDaoTicket) clazz.newInstance();
		} catch (Throwable  e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static final IDaoTicket getDao() {
		return daoTicket;
	}
}
