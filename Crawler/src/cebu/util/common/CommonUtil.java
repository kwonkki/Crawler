package cebu.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共函数
 * @author Administrator
 *
 */

public class CommonUtil {

	/**
	 * 记录日志
	 * @param clazz
	 * @param info
	 */
	public static final void log(Class<?> clazz, String info) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(info);
	}
	
	/**
	 * 判断字符串空指针或者空串
	 * @param str
	 * @return
	 */
	public static final boolean checkStrNullOrEmpty(String str) {
		return (str == null || str.equals("")) ? true : false;
	}
	
}
