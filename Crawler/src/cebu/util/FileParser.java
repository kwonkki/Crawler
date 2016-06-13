package cebu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import cebu.model.Ticket;

/**
 * 利用jsoup，html解析工具类
 * 解析保存html的本地文件file
 * @author lihaijun
 *
 */

public class FileParser extends Parser{
	private static HtmlParser htmlParser;

	/** 单例模式 **/
	private FileParser() {
		htmlParser = HtmlParser.getInstance();
	}

	private static class ParseUtilInstanceHolder {
		private static FileParser Parse_Util = new FileParser();
	}

	public static FileParser getInstance() {
		return ParseUtilInstanceHolder.Parse_Util;
	}

	/**
	 * 检查文件是否为空或者是否存在
	 * @param file
	 * @return
	 */
	private boolean check(File file) {
		return (file != null && file.exists()) ? true : false;
	}
	
	
	/**
	 * 从本地html文件中读取信息
	 * @param filePath	本地文件路径
	 * @return
	 */
	private String readHtmlFromFile(File file) {
		if (!check(file))
			return "";

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
	
	/**
	 * 从本地文件解析站点信息
	 * @param file
	 * @return
	 */
	public Map<String, String> parseStation(File file) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (!check(file))
			return map;
		
		String html = this.readHtmlFromFile(file);
		return htmlParser.parseStation(html);
	}
	
	/**
	 * 从本地文件解析站点信息
	 * @param file
	 * @param baseUrl 本地文件来源的url
	 * @return
	 */
	public Map<String, String> parseStationWithUrl(File file, String baseUrl) { 
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (!check(file))
			return map;
		
		String html = this.readHtmlFromFile(file);
		return htmlParser.parseStationWithUrl(html, baseUrl);
	}
	
	/**
	 * 从本地文件解析下拉列表的时间信息
	 * @param file
	 * @return
	 */
	public Map<String, ArrayList<String>> parseTime(File file) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		if (!check(file))
			return map;
		
		String html = this.readHtmlFromFile(file);
		return htmlParser.parseTime(html);
	}
	
	/**
	 * 从本地文件解析下拉列表的时间信息
	 * @param file
	 * @param baseUrl 本地文件来源url
	 * @return
	 */
	public Map<String, ArrayList<String>> parseTimeWithUrl(File file, String baseUrl) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		if (!check(file))
			return map;
		
		String html = this.readHtmlFromFile(file);
		return htmlParser.parseTimeWithUrl(html, baseUrl);
	}
	
	
	/**
	 * 从本地文件解析航班信息
	 * @param file post提交表单后的html文件
	 * @param priceFiles 所有需要的radio value 产生的html文件，包含价格信息
	 * @return
	 */
	public ArrayList<Ticket> parseTicket(File file, ArrayList<File> priceFiles) {
		if (!check(file) || priceFiles == null || priceFiles.size() <= 0)
			return null;
		
		String html = this.readHtmlFromFile(file);
		ArrayList<String> radioValueGeneratedHtmls = new ArrayList<String>();
		for(File f : priceFiles)
			radioValueGeneratedHtmls.add(this.readHtmlFromFile(f));
		
		return htmlParser.parseTicket(html, radioValueGeneratedHtmls);
	}
	
	/**
	 * 从本地文件解析航班信息
	 * @param file post提交表单后的html文件
	 * @param priceFiles 所有需要的radio value 产生的html文件，包含价格信息
	 * @param baseUrl html文件来源的url
	 * @return
	 */
	public ArrayList<Ticket> parseTicketWithUrl(File file, ArrayList<File> priceFiles, String baseUrl) {
		if (!check(file) || priceFiles == null || priceFiles.size() <= 0)
			return null;
		
		String html = this.readHtmlFromFile(file);
		ArrayList<String> radioValueGeneratedHtmls = new ArrayList<String>();
		for(File f : priceFiles)
			radioValueGeneratedHtmls.add(this.readHtmlFromFile(f));
		
		return htmlParser.parseTicketWithUrl(html, radioValueGeneratedHtmls, baseUrl);
	}

}
