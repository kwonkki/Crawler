package cebu.util.common;

import java.io.*;
import java.util.List;

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
	
	
	/**
	 * 检查文件是否为空或者是否存在
	 * @param file
	 * @return
	 */
	public static boolean check(File file) {
		return (file != null && file.exists()) ? true : false;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean checkListNullOrEmpty(List list) {
		return list == null ? true : list.isEmpty();
	}
	
	
	/**
	 * 从本地html文件中读取信息
	 * @param filePath	本地文件路径
	 * @return
	 */
	public static String readHtmlFromFile(File file) {
		if (!check(file))
			return null;

		StringBuffer sb = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n"); // 逐行读取，回车换行
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
