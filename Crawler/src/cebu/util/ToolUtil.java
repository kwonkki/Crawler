package cebu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ToolUtil {
	/**
	 * 打印map容器信息
	 * @param map
	 */
	public static void printMap(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet())
			System.out.println(entry.getKey() + " : " + entry.getValue());

		int i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getKey() + ", ");
			if (++i == 10) {
				i = 0;
				System.out.println();
			}
		}

		System.out.println();
		i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getValue() + ", ");
			if (++i == 10) {
				i = 0;
				System.out.println();
			}
		}
		System.out.println();
	}
	
	
	/**
	 * 控制台打印文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public void printFile(String filePath) {
		FileReader fr = null;
		BufferedReader br = null;
		String s = null;
		try {
			fr = new FileReader(new File(filePath));
			br = new BufferedReader(fr);
			s = br.readLine();
			while (s != null) {
				System.out.println(s);
				s = br.readLine();
			}
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
	}
}
